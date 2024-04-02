package org.nsgg.main;

import org.nsgg.core.EngineManager;
import org.nsgg.core.WinManager;
import org.nsgg.core.utils.user.Config;
import org.nsgg.core.utils.Utils;

import java.io.FileNotFoundException;


public class Launcher {
    private static WinManager window;
    private static GameLogic game;
    public static Config config;

    public static void main(String[] args) throws Exception {
        initEngine();
        EngineManager engine = new EngineManager();
        try {
            engine.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void initEngine() throws Exception {
        config = Utils.loadConfig();
        if (config == null) {throw new FileNotFoundException("Config does not exist or could not be found or accessed");}
        window = new WinManager(config.TITLE, 1920, 1080,true);
        game = new GameLogic();
    }

    public static WinManager getWindow() {
        return window;
    }

    public static GameLogic getGame() {
        return game;
    }
}