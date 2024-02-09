package org.nsgg.main;

import org.nsgg.core.EngineManager;
import org.nsgg.core.WinManager;

import static org.nsgg.core.utils.Consts.TITLE;

public class Launcher {
    private static WinManager window;
    private static GameLogic game;

    public static void main(String[] args) {
        window = new WinManager(TITLE, 1920, 1080,true);
        game = new GameLogic();
        EngineManager engine = new EngineManager();
        try {
            engine.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static WinManager getWindow() {
        return window;
    }

    public static GameLogic getGame() {
        return game;
    }
}