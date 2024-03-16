package org.nsgg.core.physics.collisions;

import org.nsgg.core.entity.scenes.SceneManager;
import org.nsgg.core.entity.terrain.Terrain;
import org.nsgg.core.physics.IPhysics;
import org.nsgg.core.utils.Utils;
import org.nsgg.main.Launcher;

import java.util.List;

import static org.nsgg.core.utils.Consts.TERRAIN_SIZE;
public class TerrainCollision implements IPhysics {

    private List<Terrain> terrains;
    private List<Collidable> collidables;

    public TerrainCollision(List<Terrain> terrains, List<Collidable> collidables) {
        this.terrains = terrains;
        this.collidables = collidables;
    }

    @Override
    public void update() {
        float halfTerrainSize = TERRAIN_SIZE/2;
        for (Terrain terrain : terrains) {
            for (Collidable artifact : collidables) {
                if (!((terrain.getPos().x - artifact.pos.x + halfTerrainSize > -halfTerrainSize && terrain.getPos().x - artifact.pos.x + halfTerrainSize < halfTerrainSize) &&
                        (terrain.getPos().z - artifact.pos.z + halfTerrainSize > -halfTerrainSize && terrain.getPos().z - artifact.pos.z + halfTerrainSize < halfTerrainSize))){
                    artifact.onGround = false;
                    continue;
                }

                float heightAtPoint = (float) Utils.getBlueAmountOnPixel((int) ((artifact.pos.x - terrain.getPos().x) / TERRAIN_SIZE), (int) ((artifact.pos.z - terrain.getPos().z) / TERRAIN_SIZE), Launcher.getGame().heightMap) / 2 + 2;
                if (artifact.pos.y < heightAtPoint) {
                    artifact.pos.y = heightAtPoint;
                }

                if (artifact.pos.y == heightAtPoint && !artifact.onGround) {
                    artifact.onGround = true;
                } else if (artifact.onGround && artifact.pos.y != heightAtPoint) {
                    artifact.onGround = false;
                }
            }
        }
    }

    @Override
    public void add(Collidable collidable) {
        if(!collidables.contains(collidable)) {
            collidables.add(collidable);
        }
    }

    @Override
    public void remove(Collidable collidable) {
        collidables.remove(collidable);
    }

    public void add(Terrain terrain) {
        if(!terrains.contains(terrain)) {
            terrains.add(terrain);
        }
    }

    public void remove(Terrain terrain) {
        terrains.remove(terrain);
    }

    public void alignCollidablesWithSceneManager(SceneManager sceneManagerInstance) {
        collidables = sceneManagerInstance.getCollidables();
    }

    public void alignTerrainsWithSceneManager(SceneManager sceneManagerInstance) {
        terrains = sceneManagerInstance.getTerrains();
    }

    public void syncWithSceneManager(SceneManager sceneManagerInstance) {
        alignCollidablesWithSceneManager(sceneManagerInstance);
        alignTerrainsWithSceneManager(sceneManagerInstance);
    }
}