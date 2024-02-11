package org.nsgg.main;

import org.joml.Math;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.glfw.GLFW;
import org.nsgg.core.*;
import org.nsgg.core.entity.Entity;
import org.nsgg.core.entity.Material;
import org.nsgg.core.entity.Model;
import org.nsgg.core.entity.Texture;
import org.nsgg.core.entity.scenes.SceneManager;
import org.nsgg.core.entity.terrain.BlendMapTerrain;
import org.nsgg.core.entity.terrain.Terrain;
import org.nsgg.core.entity.terrain.TerrainTexture;
import org.nsgg.core.lighting.DirectionalLight;
import org.nsgg.core.lighting.PointLight;
import org.nsgg.core.lighting.SpotLight;
import org.nsgg.core.rendering.RenderingManager;
import org.nsgg.core.utils.Utils;

import java.awt.image.BufferedImage;
import java.util.Random;

import static org.lwjgl.glfw.GLFW.*;
import static org.nsgg.core.utils.Consts.CAMERA_MOVE_SPEED;
import static org.nsgg.core.utils.Consts.MOUSE_SENSITIVITY;

public class GameLogic implements ILogic {

    private final RenderingManager renderer;
    private final WinManager window;
    private final ObjectLoader loader;
    private Camera camera;
    Vector3f cameraInc;
    private float pitch, yaw;
    Vector3f cameraRotInc;
    private boolean speed = false;
    private SceneManager sceneManager;
    public boolean nightVision = true;
    public boolean lock = false;
    private boolean escPressed = false;
    public BufferedImage heightMap;


    public GameLogic() {
        renderer = new RenderingManager();
        window = Launcher.getWindow();
        loader = new ObjectLoader();
        camera = new Camera();
        cameraInc = new Vector3f(0,0,0);
        cameraRotInc = new Vector3f(0,0,0);
        sceneManager = new SceneManager(-90, nightVision);
    }

    @Override
    public void init() throws Exception {
        renderer.init();

        Model model = loader.loadOBJModel("src/main/resources/models/cube.obj");
        model.setTexture(new Texture(loader.loadTexture("src/main/resources/textures/lucky_block.png")), 1.0f);
        model.getMaterial().setMetal(0);
        model.getMaterial().setDisableCulling(true);

        TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("src/main/resources/textures/grass.png"));
        TerrainTexture redTexture = new TerrainTexture(loader.loadTexture("src/main/resources/textures/flowers.png"));
        TerrainTexture greenTexture = new TerrainTexture(loader.loadTexture("src/main/resources/textures/dirt.png"));
        TerrainTexture blueTexture = new TerrainTexture(loader.loadTexture("src/main/resources/textures/stone.png"));
        TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("src/main/resources/textures/emptyBlendMap.png"));
        heightMap = loader.loadHeightMap("src/main/resources/textures/heightMap.png");

        BlendMapTerrain blendMapTerrain = new BlendMapTerrain(backgroundTexture,redTexture,greenTexture,blueTexture);

        Terrain terrain = new Terrain(new Vector3f(0,-1,-400), loader, new Material(new Vector4f(0.0f,0.0f,0.0f,0.0f), 0.1f, 0.5f),blendMapTerrain, blendMap);
        //Terrain terrain1 = new Terrain(new Vector3f(-800,-1,-800), loader, new Material(new Vector4f(0.0f,0.0f,0.0f,0.0f), 0.1f, 0.5f),blendMapTerrain, blendMap);
        sceneManager.addTerrain(terrain);
        //sceneManager.addTerrain(terrain1);

        Random random = new Random();
        for(int i = 0; i < 200; i++) {
            float x = random.nextFloat() * 100 - 50;
            float y = random.nextFloat() * 100 - 50;
            float z = random.nextFloat() * -300;
            sceneManager.addEntity(new Entity(model, new Vector3f(x,y,z),
                    new Vector3f(random.nextFloat() * 180, random.nextFloat() * 180, 0),1));
        }
        sceneManager.addEntity(new Entity(model, new Vector3f(0,0,-7), new Vector3f(0,0,0), 1));

        //point
        PointLight pointLight0 = new PointLight(
                new Vector3f(1f,0f,0f),
                new Vector3f(-0.5f, -0.5f, -4.8f),
                0f
        );

        PointLight pointLight1 = new PointLight(
                new Vector3f(0f,1f,0f),
                new Vector3f(0.5f, 0.5f, -4.8f),
                0f
        );


        //spot
        SpotLight spotLight0 = new SpotLight(
                new PointLight(
                        new Vector3f(1f,0f,0f),
                        new Vector3f(-0.5f, -0.5f, -4.8f),
                        1f
                ),
                new Vector3f(0f,0,-1f),
                Math.cos(Math.toRadians(60f))
        );

        SpotLight spotLight1 = new SpotLight(
                new PointLight(
                        new Vector3f(0f,1f,0f),
                        new Vector3f(0.5f, 0.5f, -4.8f),
                        1f
                ),
                new Vector3f(0f,0,-1f),
                Math.cos(Math.toRadians(60f))
        );

        //direct
        sceneManager.setLightAngle(90);
        sceneManager.setDirectionalLight(new DirectionalLight(
                new Vector3f(0,0,0),
                new Vector3f(0,0,0),
                0
        ));

        SpotLight[] spotLights = new SpotLight[]{spotLight0,spotLight1};
        PointLight[] pointLights = new PointLight[]{pointLight0, pointLight1};

        sceneManager.setPointLights(pointLights);
        sceneManager.setSpotLights(spotLights);
    }

    @Override
    public void input() {
        cameraInc.set(0,0,0);
        cameraRotInc.set(0,0,0);
        glfwSetInputMode(window.getWindow(), GLFW_CURSOR, GLFW_CURSOR_HIDDEN);
        glfwSetCursorPos(window.getWindow(), (double) window.getWidth() /2, (double) window.getHeight() /2);
        speed = false;
        if(window.isKeyPressed(GLFW.GLFW_KEY_W)) {
            cameraInc.z = -1;
        }
        if(window.isKeyPressed(GLFW.GLFW_KEY_S)) {
            cameraInc.z = 1;
        }

        if(window.isKeyPressed(GLFW.GLFW_KEY_A)) {
            cameraInc.x = 1;
        }
        if(window.isKeyPressed(GLFW.GLFW_KEY_D)) {
            cameraInc.x = -1;
        }

        if(window.isKeyPressed(GLFW.GLFW_KEY_SPACE)) {
            cameraInc.y = 1;
        }
        if(window.isKeyPressed(GLFW.GLFW_KEY_LEFT_SHIFT)) {
            cameraInc.y = -1;
        }

        if(window.isKeyPressed(GLFW.GLFW_KEY_LEFT_CONTROL)) {
            speed = true;
        }

        if(window.isKeyPressed(GLFW.GLFW_KEY_V)) {
            nightVision = true;
        }
        if(window.isKeyPressed(GLFW.GLFW_KEY_B)) {
            nightVision = false;
        }

        if(window.isKeyPressed(GLFW_KEY_ESCAPE) && !escPressed){
            escPressed = true;
            lock = !lock;
        } else if(escPressed && !window.isKeyPressed(GLFW_KEY_ESCAPE)){
            escPressed = false;
        }
    }

    @Override
    public void update(float interval, MouseInput input) {
        int speedMultiplier = speed ? 50 : 1;
        camera.movePosition(cameraInc.x * CAMERA_MOVE_SPEED * speedMultiplier, cameraInc.y * CAMERA_MOVE_SPEED * speedMultiplier, cameraInc.z * CAMERA_MOVE_SPEED * speedMultiplier);

        if(!lock) {
            Vector2f rotVec = input.getDisplayVec();
            camera.moveRotation(rotVec.x * MOUSE_SENSITIVITY, rotVec.y * MOUSE_SENSITIVITY, 0);
        }
        pitch = camera.getRotation().x;
        yaw = camera.getRotation().y;

        sceneManager.setNightVision(nightVision);

        for (Entity entity : sceneManager.getEntities()) {
            renderer.processEntities(entity);
        }

        for (Terrain terrain : sceneManager.getTerrains()) {
            renderer.processTerrain(terrain);
        }
    }

    @Override
    public void render() {
        renderer.render(camera, sceneManager);
    }

    @Override
    public void cleanup() {
        renderer.cleanup();
        loader.cleanup();
    }

    public float getPitch() {
        return pitch;
    }
    public float getYaw() {
        return yaw;
    }
}

