package org.nsgg.core.entity.scenes;

import org.joml.Vector3f;
import org.nsgg.core.entity.Entity;
import org.nsgg.core.entity.terrain.Terrain;
import org.nsgg.core.lighting.DirectionalLight;
import org.nsgg.core.lighting.PointLight;
import org.nsgg.core.lighting.SpotLight;
import org.nsgg.core.physics.collisions.Collidable;

import java.util.ArrayList;
import java.util.List;

import static org.nsgg.core.utils.Consts.AMBIENT_LIGHT;
import static org.nsgg.core.utils.Consts.NIGHT_VISION_AMBIENT_LIGHT;

public class SceneManager {

    private List<Entity> entities;
    private List<Terrain> terrains;
    private SpotLight[] spotLights;
    private PointLight[] pointLights;
    private DirectionalLight directionalLight;
    private float lightAngle;
    private float spotAngle = 0;
    private float spotInc = 1;
    private Vector3f ambientLight;
    private boolean nightVision;
    private List<Collidable> collidables;

    public SceneManager(float lightAngle, boolean nightVision) {
        entities = new ArrayList<>();
        terrains = new ArrayList<>();
        ambientLight = nightVision ? NIGHT_VISION_AMBIENT_LIGHT : AMBIENT_LIGHT;
        this.lightAngle = lightAngle;
        this.nightVision = nightVision;
        this.collidables = new ArrayList<>();
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    public List<Terrain> getTerrains() {
        return terrains;
    }

    public void setTerrains(List<Terrain> terrains) {
        this.terrains = terrains;
    }

    public SpotLight[] getSpotLights() {
        return spotLights;
    }

    public void setSpotLights(SpotLight[] spotLights) {
        this.spotLights = spotLights;
    }

    public PointLight[] getPointLights() {
        return pointLights;
    }

    public void setPointLights(PointLight[] pointLights) {
        this.pointLights = pointLights;
    }

    public DirectionalLight getDirectionalLight() {
        return directionalLight;
    }

    public void setDirectionalLight(DirectionalLight directionalLight) {
        this.directionalLight = directionalLight;
    }

    public float getLightAngle() {
        return lightAngle;
    }

    public void setLightAngle(float lightAngle) {
        this.lightAngle = lightAngle;
    }

    public float getSpotAngle() {
        return spotAngle;
    }

    public void setSpotAngle(float spotAngle) {
        this.spotAngle = spotAngle;
    }

    public float getSpotInc() {
        return spotInc;
    }

    public void setSpotInc(float spotInc) {
        this.spotInc = spotInc;
    }

    public Vector3f getAmbientLight() {
        return ambientLight;
    }

    public void setAmbientLight(Vector3f ambientLight) {
        this.ambientLight = ambientLight;
    }

    public void setAmbientLight(float x, float y, float z) {
        this.ambientLight = new Vector3f(x,y,z);
    }

    public void addTerrain(Terrain terrain){
        this.terrains.add(terrain);
    }

    public void addEntity(Entity entity) {
        this.entities.add(entity);
    }

    public void incLightAngle(float angle) {
        this.lightAngle += angle;
    }

    public boolean isNightVision() {
        return nightVision;
    }

    public void setNightVision(boolean nightVision) {
        this.nightVision = nightVision;
    }

    public List<Collidable> getCollidables() {
        return collidables;
    }

    public void setCollidables(List<Collidable> collidables) {
        this.collidables = collidables;
    }

    public void addCollidable(Collidable collidable) {
        if(!collidables.contains(collidable)) {
            collidables.add(collidable);
        }
    }

    public void removeCollidable(Collidable collidable) {
        collidables.remove(collidable);
    }
}
