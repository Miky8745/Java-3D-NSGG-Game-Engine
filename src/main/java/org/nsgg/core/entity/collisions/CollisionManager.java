package org.nsgg.core.entity.collisions;

import org.nsgg.core.entity.terrain.Terrain;

import java.util.List;

public class CollisionManager {

    private TerrainCollisionTracker terrainCollisionTracker;

    public CollisionManager(List<Terrain> terrains, List<Collidable> terCollisions) {
        this.terrainCollisionTracker = new TerrainCollisionTracker(terrains, terCollisions);
    }

    public void update() {
        terrainCollisionTracker.update();
    }
}
