package org.nsgg.core.entity;

import org.joml.Math;
import org.joml.Vector3f;
import org.nsgg.core.entity.collisions.Collidable;

public class Camera extends Collidable {

    public Camera() {
        super(new Vector3f(0,0,0),new Vector3f(0,0,0));
    }

    public Camera(Vector3f position, Vector3f rotation) {
        super(position,rotation);
    }

    public void movePosition(float x, float y, float z) {
        if(z != 0) {
            pos.x += (float) Math.sin(Math.toRadians(rotation.y)) * -1.0f * z;
            pos.z += (float) Math.cos(Math.toRadians(rotation.y)) * z;
        }

        if(x != 0) {
            pos.x += (float) Math.cos(Math.toRadians(rotation.y)) * -1.0f * x;
            pos.z += (float) Math.sin(Math.toRadians(rotation.y)) * -1.0f * x;
        }

        pos.y += y;
    }

    public void setPosition(float x, float y, float z) {
        this.pos.x = x;
        this.pos.y = y;
        this.pos.z = z;
    }

    public void setRotation(float x, float y, float z) {
        this.rotation.x = x;
        this.rotation.y = y;
        this.rotation.z = z;
    }

    public void moveRotation(float x, float y, float z) {
        float pitch = this.getRotation().x + x;

        if (pitch > 90) {
            this.rotation.x = 90;
        }else if (pitch < -90) {
            this.rotation.x = -90;
        } else {
            this.rotation.x += x;
        }

        this.rotation.y += y;
        this.rotation.z += z;
    }

    public Vector3f getPosition() {
        return pos;
    }

    public Vector3f getRotation() {
        return rotation;
    }
}
