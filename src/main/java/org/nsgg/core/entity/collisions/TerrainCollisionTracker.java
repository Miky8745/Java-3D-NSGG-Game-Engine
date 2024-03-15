package org.nsgg.core.entity.collisions;

import org.nsgg.core.entity.terrain.Terrain;
import org.nsgg.core.utils.Utils;
import org.nsgg.main.Launcher;

import java.util.List;

import static org.nsgg.core.utils.Consts.TERRAIN_SIZE;
public class TerrainCollisionTracker implements ICollisionTracker {

    private List<Terrain> terrains;
    private List<Collidable> collidables;

    public TerrainCollisionTracker(List<Terrain> terrains, List<Collidable> collidables) {
        this.terrains = terrains;
        this.collidables = collidables;
    }

    @Override
    public void update() {
        for (Terrain terrain : terrains) {
            for (Collidable artifact : collidables) {
                if (!((terrain.getPos().x - artifact.pos.x + (TERRAIN_SIZE / 2)> -(TERRAIN_SIZE / 2) && terrain.getPos().x - artifact.pos.x + (TERRAIN_SIZE / 2)< (TERRAIN_SIZE / 2)) &&
                        (terrain.getPos().z - artifact.pos.z + (TERRAIN_SIZE / 2)> -(TERRAIN_SIZE / 2) && terrain.getPos().z - artifact.pos.z + (TERRAIN_SIZE / 2)< (TERRAIN_SIZE / 2)))){
                    continue;
                }

                System.out.println(artifact.pos.x + "\n");
                float heightAtPoint = (float) Utils.getBlueAmountOnPixel((int) ((artifact.pos.x - terrain.getPos().x) / TERRAIN_SIZE), (int) ((artifact.pos.z - terrain.getPos().z) / TERRAIN_SIZE), Launcher.getGame().heightMap) / 2 + 2;
                if (artifact.pos.y < heightAtPoint) {
                    artifact.pos.y = heightAtPoint;
                    //System.out.println("Pod");
                } else if (artifact.pos.y == heightAtPoint && !artifact.onGround) {
                    artifact.onGround = true;
                } else if (artifact.onGround && artifact.pos.y != heightAtPoint) {
                    artifact.onGround = false;
                }
            }
        }
    }

    @Override
    public void add(Collidable collidable) {

    }

    @Override
    public void remove(Collidable collidable) {

    }
}