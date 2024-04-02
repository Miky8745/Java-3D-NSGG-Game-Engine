package org.nsgg.core;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.nsgg.core.utils.Utils;
import org.nsgg.main.Launcher;

import static org.nsgg.core.utils.user.Config.FRAMERATE;
import static org.nsgg.core.utils.user.Config.NANOSECOND;

public class EngineManager {

    private static int fps;
    private static float frametime = 1.0f / FRAMERATE;
    private boolean isRunning;
    private WinManager window;
    private GLFWErrorCallback errorCallback;
    private ILogic gameLogic;
    private MouseInput mouseInput;
    private long lastFrame;
    private long thisFrame;

    private void init() throws Exception {
        GLFW.glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));
        window = Launcher.getWindow();
        gameLogic = Launcher.getGame();
        window.init();
        mouseInput = new MouseInput();
        gameLogic.init();
        mouseInput.init();
        this.lastFrame = System.nanoTime();
        this.thisFrame = System.nanoTime();
    }

    public void start() throws Exception {
        init();
        if(isRunning) {
            return;
        }
        run();
    }

    public void run() {
        this.isRunning = true;
        int frames = 0;
        long frameCounter = 0;
        long lastTime = System.nanoTime();
        double unprocessedTime = 0;

        while (isRunning) {
            boolean render = false;
            long startTime = System.nanoTime();
            long passedTime = startTime - lastTime;
            lastTime = startTime;

            unprocessedTime += passedTime / (double) NANOSECOND;
            frameCounter += passedTime;

            input();

            while (unprocessedTime > frametime) {
                render = true;
                unprocessedTime -= frametime;

                if(window.windowShouldClose()) {
                    stop();
                }

                if (frameCounter >= NANOSECOND) {
                    setFps(frames);
                    frames = 0;
                    frameCounter = 0;
                }
            }

            if (render) {
                update(frametime);
                render();
                frames++;
                thisFrame = System.nanoTime();
                Utils.deltaTime = ((thisFrame - lastFrame) / 1000000000f);
                lastFrame = thisFrame;
            }
        }
        cleanup();
    }

    private void stop() {
        if(!isRunning) {
            return;
        }
        isRunning = false;
    }

    private void input() {
        gameLogic.input();
        mouseInput.input();
    }

    private void render() {
        gameLogic.render();
        window.update();
    }

    private void update(float interval) {
        gameLogic.update(interval, mouseInput);
    }

    private void cleanup() {
        gameLogic.cleanup();
        window.cleanup();
        errorCallback.free();
        GLFW.glfwTerminate();
    }

    public static int getFps() {
        return fps;
    }

    public static void setFps(int fps) {
        EngineManager.fps = fps;
    }
}
