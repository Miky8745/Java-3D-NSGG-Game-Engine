package org.nsgg.core.utils.user;

import org.joml.Math;
import org.joml.Vector3f;
import org.joml.Vector4f;

public class Config {

    public String TITLE;// = "NSGG Game Engine";
    public float FOV;// = (float) Math.toRadians(50);
    public static final float Z_NEAR = 0.01f;
    public static final float Z_FAR = 1000f;
    public float MOUSE_SENSITIVITY;// = 0.2f;
    public float CAMERA_MOVE_SPEED;// = 0.2f;
    public static final long NANOSECOND = 1000000000L;
    public static final float FRAMERATE = 1000;
    public static final Vector4f DEFAULT_COLOR = new Vector4f(1.0f, 1.0f, 1.0f, 1.0f);
    public static final Vector3f AMBIENT_LIGHT = new Vector3f(0.1f, 0.1f, 0.1f);
    public static final float SPECULAR_POWER = 1.0f;
    public static final float TERRAIN_SIZE = 128;
    public static final int TERRAIN_VERTEX_COUNT = 256;
    public static final int MAX_SPOT_LIGHTS = 5;
    public static final int MAX_POINT_LIGHTS = 5;
    public static final Vector3f NIGHT_VISION_AMBIENT_LIGHT = new Vector3f(1,1,1);
    public float GRAVITY;// = 9.81f;
    public float JUMP_HEIGHT;// = 5;
    public String DEFAULT_GAMEMODE;

    public void init() {
        FOV = (float) Math.toRadians(FOV);
        GRAVITY = GRAVITY * -1;
        DEFAULT_GAMEMODE = DEFAULT_GAMEMODE.equals("Spectator") || DEFAULT_GAMEMODE.equals("Normal") ? DEFAULT_GAMEMODE : "Normal";
    }
}
