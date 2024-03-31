package org.nsgg.main;

import com.google.gson.Gson;
import org.nsgg.core.EngineManager;
import org.nsgg.core.WinManager;
import org.nsgg.core.utils.Consts;

import java.io.FileNotFoundException;
import java.io.FileReader;


public class Launcher {
    private static WinManager window;
    private static GameLogic game;
    public static Consts consts;

    public static void main(String[] args) {
        Gson gson = new Gson();

        try(FileReader reader = new FileReader("src/main/resources/config.json")) {
            consts = gson.fromJson(reader, Consts.class);
            consts.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
        window = new WinManager(consts.TITLE, 1920, 1080,true);
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