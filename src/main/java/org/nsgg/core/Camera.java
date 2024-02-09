package org.nsgg.core;

import org.joml.Math;
import org.joml.Vector3f;

public class Camera {

    private Vector3f position, rotation;

    public Camera() {
        position = new Vector3f(0,0,0);
        rotation = new Vector3f(0,0,0);
    }

    public Camera(Vector3f position, Vector3f rotation) {
        this.rotation = rotation;
        this.position = position;
    }

    public void movePosition(float x, float y, float z) {
        if(z != 0) {
            position.x += (float) Math.sin(Math.toRadians(rotation.y)) * -1.0f * z;
            position.z += (float) Math.cos(Math.toRadians(rotation.y)) * z;
        }

        if(x != 0) {
            position.x += (float) Math.cos(Math.toRadians(rotation.y)) * -1.0f * x;
            position.z += (float) Math.sin(Math.toRadians(rotation.y)) * -1.0f * x;
        }

        position.y += y;
    }

    public void setPosition(float x, float y, float z) {
        this.position.x = x;
        this.position.y = y;
        this.position.z = z;
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
        return position;
    }

    public Vector3f getRotation() {
        return rotation;
    }
}
