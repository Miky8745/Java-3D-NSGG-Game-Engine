package org.nsgg.core.entity;

import org.joml.Vector4f;

import static org.nsgg.core.utils.Config.DEFAULT_COLOR;

public class Material {
    private Vector4f ambientColor, diffuseColor, specularColor;
    private float reflectance, metal;
    private Texture texture;
    private boolean disableCulling;

    public Material() {
        this.ambientColor = DEFAULT_COLOR;
        this.diffuseColor = DEFAULT_COLOR;
        this.specularColor = DEFAULT_COLOR;
        this.texture = null;
        this.reflectance = 0;
        this.metal = 0.5f;
        this.disableCulling = false;
    }

    public Material(Vector4f color, float reflectance, float metal) {
        this(color,color,color,reflectance, null, metal);
    }

    public Material(Vector4f color, float reflectance, Texture texture, float metal) {
        this(color,color,color,reflectance, texture, metal);
    }

    public Material(Texture texture) {
        this(DEFAULT_COLOR, DEFAULT_COLOR, DEFAULT_COLOR, 0, texture, 0.5f);
    }

    public Material(Texture texture, float reflectance, float metal) {
        this(DEFAULT_COLOR, DEFAULT_COLOR, DEFAULT_COLOR, reflectance, texture, metal);
    }

    public Material(Vector4f color, float reflectance, Texture texture, float metal, boolean disableCulling) {
        this(color,color,color,reflectance, texture, metal, disableCulling);
    }

    public Material(Texture texture, boolean disableCulling) {
        this(DEFAULT_COLOR, DEFAULT_COLOR, DEFAULT_COLOR, 0, texture, 0.5f, disableCulling);
    }

    public Material(Texture texture, float reflectance, float metal, boolean disableCulling) {
        this(DEFAULT_COLOR, DEFAULT_COLOR, DEFAULT_COLOR, reflectance, texture, metal, disableCulling);
    }

    public Material(Vector4f ambientColor, Vector4f diffuseColor, Vector4f specularColor, float reflectance, Texture texture, float metal) {
        this.ambientColor = ambientColor;
        this.diffuseColor = diffuseColor;
        this.specularColor = specularColor;
        this.reflectance = reflectance;
        this.texture = texture;
        this.metal = metal;
        this.disableCulling = false;
    }

    public Material(Vector4f ambientColor, Vector4f diffuseColor, Vector4f specularColor, float reflectance, Texture texture, float metal, boolean disableCulling) {
        this.ambientColor = ambientColor;
        this.diffuseColor = diffuseColor;
        this.specularColor = specularColor;
        this.reflectance = reflectance;
        this.texture = texture;
        this.metal = metal;
        this.disableCulling = disableCulling;
    }

    public Vector4f getAmbientColor() {
        return ambientColor;
    }

    public void setAmbientColor(Vector4f ambientColor) {
        this.ambientColor = ambientColor;
    }

    public Vector4f getDiffuseColor() {
        return diffuseColor;
    }

    public void setDiffuseColor(Vector4f diffuseColor) {
        this.diffuseColor = diffuseColor;
    }

    public Vector4f getSpecularColor() {
        return specularColor;
    }

    public void setSpecularColor(Vector4f specularColor) {
        this.specularColor = specularColor;
    }

    public float getReflectance() {
        return reflectance;
    }

    public void setReflectance(float reflectance) {
        this.reflectance = reflectance;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public boolean hasTexture() {
        return texture != null;
    }

    public float getMetal() {
        return metal;
    }

    public void setMetal(float metal) {
        this.metal = metal;
    }

    public boolean isDisableCulling() {
        return disableCulling;
    }

    public void setDisableCulling(boolean disableCulling) {
        this.disableCulling = disableCulling;
    }
}
