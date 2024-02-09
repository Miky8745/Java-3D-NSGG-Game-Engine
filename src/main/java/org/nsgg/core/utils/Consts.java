package org.nsgg.core.utils;

import org.joml.Vector3f;
import org.joml.Vector4f;

public class Consts {

    public static final String TITLE = "NSGG Game Engine";
    public static final float FOV = (float) Math.toRadians(90);
    public static final float Z_NEAR = 0.01f;
    public static final float Z_FAR = 1000f;
    public static final float MOUSE_SENSITIVITY = 0.2f;
    public static final float CAMERA_MOVE_SPEED = 0.1f;
    public static final long NANOSECOND = 1000000000L;
    public static final float FRAMERATE = 1000;
    public static final Vector4f DEFAULT_COLOR = new Vector4f(1.0f, 1.0f, 1.0f, 1.0f);
    public static final Vector3f AMBIENT_LIGHT = new Vector3f(0.1f, 0.1f, 0.1f);
    public static final float SPECULAR_POWER = 1.0f;
    public static final float TERRAIN_SIZE = 800;
    public static final int TERRAIN_VERTEX_COUNT = 128;
    public static final int MAX_SPOT_LIGHTS = 5;
    public static final int MAX_POINT_LIGHTS = 5;

}
