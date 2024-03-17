package org.nsgg.core.physics;

import org.nsgg.core.entity.terrain.Terrain;
import org.nsgg.core.physics.collisions.Collidable;
import org.nsgg.core.physics.collisions.TerrainCollision;

import java.util.ArrayList;
import java.util.List;

public class PhysicsManager {

    private TerrainCollision terrainCollisionTracker;
    private Gravity gravity;
    private List<IPhysics> physicsUnits;

    public PhysicsManager(List<Terrain> terrains, List<Collidable> terCollisions) {
        this.terrainCollisionTracker = new TerrainCollision(terrains, terCollisions);
        this.gravity = new Gravity(terCollisions);
        this.physicsUnits = new ArrayList<>();
        physicsUnits.add(terrainCollisionTracker);
        physicsUnits.add(gravity);
    }

    public void update() {
        gravity.update();
        terrainCollisionTracker.update();
    }

    public TerrainCollision getTerrainCollisionTracker() {
        return terrainCollisionTracker;
    }

    public void setTerrainCollisionTracker(TerrainCollision terrainCollisionTracker) {
        this.terrainCollisionTracker = terrainCollisionTracker;
    }

    public Gravity getGravity() {
        return gravity;
    }

    public void setGravity(Gravity gravity) {
        this.gravity = gravity;
    }

    public List<IPhysics> getPhysicsUnits() {
        return physicsUnits;
    }

    public void setPhysicsUnits(List<IPhysics> physicsUnits) {
        this.physicsUnits = physicsUnits;
    }
}
