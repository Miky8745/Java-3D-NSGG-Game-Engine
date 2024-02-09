#version 400 core

const int MAX_POINT_LIGHTS = 5;
const int MAX_SPOT_LIGHTS = 5;

in vec2 fragTextureCoord;
in vec3 fragNormal;
in vec3 fragPos;

out vec4 fragColor;

struct Material {
    vec4 ambient;
    vec4 diffuse;
    vec4 specular;
    int hasTexture;
    float reflectance;
    float metal;
};

struct DirectionalLight {
    vec3 color;
    vec3 direction;
    float intensity;
};

struct PointLight {
    vec3 color;
    vec3 position;
    float intensity;
    float constant;
    float linear;
    float exponent;
};

struct SpotLight {
    PointLight pl;
    vec3 coneDir;
    float cutoff;
};

uniform sampler2D textureSampler;
uniform vec3 ambientLight;
uniform Material material;
uniform float specularPower;
uniform DirectionalLight directionalLight;
uniform PointLight pointLights[MAX_POINT_LIGHTS];
uniform vec3 cameraPos;
uniform SpotLight spotLights[MAX_SPOT_LIGHTS];

vec4 ambientC;
vec4 specularC;
vec4 diffuseC;

void setupColors(Material material, vec2 textCoord) {
    if(material.hasTexture == 1) {
        ambientC = texture(textureSampler, textCoord);
        specularC = ambientC;
        diffuseC = ambientC;
    }
    else {
        ambientC = material.ambient;
        specularC = material.specular;
        diffuseC = material.diffuse;
    }
}

vec4 calcLightColor(vec3 lightColor, float intensity, vec3 pos, vec3 toLightDir, vec3 normal, vec3 specC) {
    vec4 diffuseColor = vec4(0,0,0,0);
    vec4 specularColor = vec4(0,0,0,0);

    float diffuseFactor = max(dot(normal, toLightDir), 0.0);
    diffuseColor = diffuseC * vec4(lightColor, 1.0) * intensity * diffuseFactor;

    vec3 cameraDirection = normalize(cameraPos - pos);
    vec3 fromLightDir = -toLightDir;
    vec3 reflectedLight = normalize(reflect(fromLightDir, normal));
    float specFactor = max(dot(cameraDirection, reflectedLight), 0.0);
    specFactor = pow(specFactor, specularPower);
    specularColor = mix(specularC, vec4(specC, 0), material.metal) * intensity * specFactor * material.reflectance * vec4(lightColor, 1.0);

    return (specularColor + diffuseColor);
}

vec4 calcDirectionalLight(DirectionalLight light, vec3 pos, vec3 normal) {
    return calcLightColor(light.color, light.intensity, pos, normalize(light.direction), normal, light.color);
}

vec4 calcPointLight(PointLight light, vec3 pos, vec3 normal) {
    vec3 lightDir = light.position - pos;
    vec3 toLightDir = normalize(lightDir);
    vec4 lightColor = calcLightColor(light.color, light.intensity, pos, toLightDir, normal, light.color);

    float dist = length(lightDir);
    float attenuationInv = light.constant + light.linear * dist + light.exponent * dist * dist;

    return lightColor/attenuationInv;
}

vec4 calcSpotLight(SpotLight light, vec3 pos, vec3 normal) {
    vec3 lightDir = light.pl.position - pos;
    vec3 toLightDir = normalize(lightDir);
    vec3 fromLightDir = -toLightDir;
    float spotAlpha = dot(fromLightDir, normalize(light.coneDir));

    vec4 color = vec4(0,0,0,0);

    if(spotAlpha > light.cutoff) {
        color = calcPointLight(light.pl, pos, normal);
        color *= (1.0 - (1.0 - spotAlpha) / (1.0 - light.cutoff));
    }

    return color;
}

void main() {
    setupColors(material, fragTextureCoord);

    vec4 diffuseSpecularComp = calcDirectionalLight(directionalLight, fragPos, fragNormal);
    for (int i = 0; i < MAX_POINT_LIGHTS; i++) {
        if(pointLights[i].intensity > 0) {
            diffuseSpecularComp += calcPointLight(pointLights[i], fragPos, fragNormal);
        }
    }
    for (int i = 0; i < MAX_SPOT_LIGHTS; i++) {
        if(spotLights[i].pl.intensity > 0) {
            diffuseSpecularComp += calcSpotLight(spotLights[i], fragPos, fragNormal);
        }
    }

    fragColor = ambientC * vec4(ambientLight, 1) + diffuseSpecularComp;
}