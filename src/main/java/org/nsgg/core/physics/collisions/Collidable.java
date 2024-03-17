package org.nsgg.core.physics.collisions;

import org.joml.Vector3f;

public class Collidable {

    public Vector3f pos, rotation;
    public boolean onGround, physics;
    public float vy;

    public Collidable(Vector3f pos, Vector3f rotation) {
        this.pos = pos;
        this.rotation = rotation;
        this.onGround = true;
        this.vy = 0;
        this.physics = true;
    }
}
