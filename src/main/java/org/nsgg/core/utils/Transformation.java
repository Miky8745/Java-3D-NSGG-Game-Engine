package org.nsgg.core.utils;

import org.nsgg.core.Camera;
import org.nsgg.core.entity.Entity;
import org.joml.Math;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.nsgg.core.entity.terrain.Terrain;

public class Transformation {

    public static Matrix4f createTransformationMatrix(Entity entity) {
        Matrix4f matrix = new Matrix4f();
        matrix.identity().translate(entity.getPos()).
                rotateX((float) Math.toRadians(entity.getRotation().x)).
                rotateY((float) Math.toRadians(entity.getRotation().y)).
                rotateZ((float) Math.toRadians(entity.getRotation().z)).
                scale(entity.getScale());
        return matrix;
    }

    public static Matrix4f createTransformationMatrix(Terrain terrain) {
        Matrix4f matrix = new Matrix4f();
        matrix.identity().translate(terrain.getPos()).scale(1);
        return matrix;
    }

    public static Matrix4f getViewMatrix(Camera camera) {
        Vector3f pos = camera.getPosition();
        Vector3f rotation = camera.getRotation();
        Matrix4f matrix = new Matrix4f();
        matrix.identity();
        matrix.rotate((float) Math.toRadians(rotation.x), new Vector3f(1,0,0)).
                rotate((float) Math.toRadians(rotation.y), new Vector3f(0,1,0)).
                rotate((float) Math.toRadians(rotation.z), new Vector3f(0,0,1));
        matrix.translate(-pos.x,-pos.y,-pos.z);
        return matrix;
    }
}
