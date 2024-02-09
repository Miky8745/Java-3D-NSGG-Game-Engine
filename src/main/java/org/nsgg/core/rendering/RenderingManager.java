package org.nsgg.core.rendering;

import org.lwjgl.opengl.GL11;
import org.nsgg.core.Camera;
import org.nsgg.core.ShaderManager;
import org.nsgg.core.WinManager;
import org.nsgg.core.entity.Entity;
import org.nsgg.core.entity.scenes.SceneManager;
import org.nsgg.core.entity.terrain.Terrain;
import org.nsgg.core.lighting.DirectionalLight;
import org.nsgg.core.lighting.PointLight;
import org.nsgg.core.lighting.SpotLight;
import org.nsgg.main.Launcher;

import java.util.ArrayList;
import java.util.List;

import static org.nsgg.core.utils.Consts.AMBIENT_LIGHT;
import static org.nsgg.core.utils.Consts.SPECULAR_POWER;

public class RenderingManager {

    private final WinManager window;
    private EntityRender entityRender;
    private TerrainRender terrainRender;

    public RenderingManager() {
        window = Launcher.getWindow();
    }

    public void init() throws Exception {
        entityRender = new EntityRender();
        terrainRender = new TerrainRender();

        entityRender.init();
        terrainRender.init();
    }

    public static void renderLights(ShaderManager shader, PointLight[] pointLights, SpotLight[] spotLights,
                             DirectionalLight directionalLight) {
        shader.setUniform("ambientLight", AMBIENT_LIGHT);
        shader.setUniform("specularPower", SPECULAR_POWER);
        shader.setUniform("spotLights", spotLights);
        shader.setUniform("pointLights", pointLights);
        shader.setUniform("directionalLight", directionalLight);
    }

    public void render(Camera camera, SceneManager sceneManager) {
        render(camera, sceneManager.getDirectionalLight(), sceneManager.getPointLights(), sceneManager.getSpotLights());
    }

    public void render(Camera camera, DirectionalLight directionalLight, PointLight[] pointLights, SpotLight[] spotLights) {
        clear();

        if (window.isResize()) {
            GL11.glViewport(0,0,window.getWidth(),window.getHeight());
            window.setResize(true);
        }

        entityRender.render(camera, pointLights, spotLights, directionalLight);
        terrainRender.render(camera, pointLights, spotLights, directionalLight);
    }

    public void processEntities(Entity entity) {

        List<Entity> entityList = entityRender.getEntities().get(entity.getModel());
        if(entityList != null) {
            entityList.add(entity);
        } else {
            List<Entity> newEntityList = new ArrayList<>();
            newEntityList.add(entity);
            entityRender.getEntities().put(entity.getModel(), newEntityList);
        }
    }

    public void processTerrain(Terrain terrain) {
        terrainRender.getTerrain().add(terrain);
    }

    public void clear() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    }

    public void cleanup() {
        entityRender.cleanup();
        terrainRender.cleanup();
    }
}
