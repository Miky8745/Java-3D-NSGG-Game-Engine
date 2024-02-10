package org.nsgg.core;

import org.joml.Vector2d;
import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;
import org.nsgg.main.Launcher;

public class MouseInput {

    private final Vector2d previousPos, currentPos;
    private final Vector2f displayVec;
    private boolean inWindow = false, leftButtonPress = false, rightButtonPress = false;

    public MouseInput() {
        previousPos = new Vector2d(-1,-1);
        currentPos = new Vector2d(-1,-1);
        displayVec = new Vector2f();
    }

    public void init() {
        GLFW.glfwSetCursorPosCallback(Launcher.getWindow().getWindow(), (window, xpos, ypos) -> {
            currentPos.x = xpos;
            currentPos.y = ypos;
        });

        GLFW.glfwSetCursorEnterCallback(Launcher.getWindow().getWindow(), (window, entered) -> {
            inWindow = entered;
        });

        GLFW.glfwSetMouseButtonCallback(Launcher.getWindow().getWindow(), (window, button, action, mods) -> {
            leftButtonPress = button == GLFW.GLFW_MOUSE_BUTTON_1 && action == GLFW.GLFW_PRESS;
            rightButtonPress = button == GLFW.GLFW_MOUSE_BUTTON_2 && action == GLFW.GLFW_PRESS;
        });
    }

    public void input() {
        displayVec.y = 0;
        displayVec.x = 0;
        System.out.println(previousPos.x);
        if(previousPos.x > 0 && previousPos.y > 0 && inWindow) {
            double x = currentPos.x - previousPos.x;
            double y = currentPos.y - previousPos.y;
            boolean rotateX = x != 0;
            boolean rotateY = x != 0;
            if (rotateX) {
                displayVec.y = (float) x;
            }
            if (rotateX) {
                displayVec.x = (float) y;
            }
        }
            //previousPos.x = currentPos.x;
            //previousPos.y = currentPos.y;

            previousPos.x = (double) Launcher.getWindow().getWidth() /2;
            previousPos.y = (double) Launcher.getWindow().getHeight() /2;
    }

    public Vector2f getDisplayVec() {
        return displayVec;
    }

    public boolean isLeftButtonPress() {
        return leftButtonPress;
    }

    public boolean isRightButtonPress() {
        return rightButtonPress;
    }
}
