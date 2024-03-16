package org.nsgg.core.physics;

import org.nsgg.core.entity.terrain.Terrain;
import org.nsgg.core.physics.collisions.Collidable;
import org.nsgg.core.physics.collisions.TerrainCollision;

import java.util.List;

public class PhysicsManager {

    private TerrainCollision terrainCollisionTracker;
    private Gravity gravity;
    private IPhysics physics;

    public PhysicsManager(List<Terrain> terrains, List<Collidable> terCollisions) {
        this.terrainCollisionTracker = new TerrainCollision(terrains, terCollisions);
        this.gravity = new Gravity(terCollisions);
    }

    public void update() {
        gravity.update();
        terrainCollisionTracker.update();
    }
}
