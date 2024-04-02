package org.nsgg.core.rendering;

import org.lwjgl.opengl.GL11;
import org.nsgg.core.ShaderManager;
import org.nsgg.core.WinManager;
import org.nsgg.core.entity.Camera;
import org.nsgg.core.entity.Entity;
import org.nsgg.core.entity.scenes.SceneManager;
import org.nsgg.core.entity.terrain.Terrain;
import org.nsgg.core.lighting.DirectionalLight;
import org.nsgg.core.lighting.PointLight;
import org.nsgg.core.lighting.SpotLight;
import org.nsgg.main.Launcher;

import java.util.ArrayList;
import java.util.List;

import static org.nsgg.core.utils.user.Config.*;

public class RenderingManager {

    private final WinManager window;
    private EntityRender entityRender;
    private TerrainRender terrainRender;
    private static boolean isCulling = false;

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
                             DirectionalLight directionalLight, boolean nightVision) {
        shader.setUniform("ambientLight", nightVision ? NIGHT_VISION_AMBIENT_LIGHT : AMBIENT_LIGHT);
        shader.setUniform("specularPower", SPECULAR_POWER);
        shader.setUniform("spotLights", spotLights);
        shader.setUniform("pointLights", pointLights);
        shader.setUniform("directionalLight", directionalLight);
    }

    public void render(Camera camera, SceneManager sceneManager) {
        render(camera, sceneManager.getDirectionalLight(), sceneManager.getPointLights(), sceneManager.getSpotLights(), sceneManager.isNightVision());
    }

    public void render(Camera camera, DirectionalLight directionalLight, PointLight[] pointLights, SpotLight[] spotLights, boolean nightVision) {
        clear();

        if (window.isResize()) {
            GL11.glViewport(0,0,window.getWidth(),window.getHeight());
            window.setResize(true);
        }

        entityRender.render(camera, pointLights, spotLights, directionalLight, nightVision);
        terrainRender.render(camera, pointLights, spotLights, directionalLight, nightVision);
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

    public static void enableCulling() {
        if(isCulling) {
            return;
        }
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glCullFace(GL11.GL_BACK);
        isCulling = true;
    }

    public static void disableCulling() {
        if(!isCulling) {
            return;
        }
        GL11.glDisable(GL11.GL_CULL_FACE);
        isCulling = false;
    }
}
