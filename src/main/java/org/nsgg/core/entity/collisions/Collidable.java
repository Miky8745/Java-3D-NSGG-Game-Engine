package org.nsgg.core.entity.collisions;

import org.joml.Vector3f;

public class Collidable {

    protected Vector3f pos, rotation;
    public boolean onGround;
    public float vy;

    public Collidable(Vector3f pos, Vector3f rotation) {
        this.pos = pos;
        this.rotation = rotation;
        this.onGround = true;
        this.vy = 0;
    }
}
