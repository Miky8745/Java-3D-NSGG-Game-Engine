package org.nsgg.core.utils;


import com.google.gson.Gson;
import org.lwjgl.system.MemoryUtil;
import org.nsgg.core.physics.IPhysics;
import org.nsgg.core.physics.collisions.Collidable;
import org.nsgg.core.utils.user.Config;
import org.nsgg.main.Launcher;

import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utils {
    public static float deltaTime = 0;

    public static FloatBuffer storeDataInFloatBuffer(float[] data) {
        FloatBuffer buffer = MemoryUtil.memAllocFloat(data.length);
        buffer.put(data).flip();
        return buffer;
    }

    public static IntBuffer storeDataInIntBuffer(int[] data) {
        IntBuffer buffer = MemoryUtil.memAllocInt(data.length);
        buffer.put(data).flip();
        return buffer;
    }

    public static String loadResource(String filename) throws Exception {
        String result;
        try (
            InputStream in = Utils.class.getResourceAsStream(filename);
            Scanner scanner = new Scanner(in, StandardCharsets.UTF_8);
        ) {
            result = scanner.useDelimiter("\\A").next();
        }
        return result;
    }

    public static List<String> readAllLines(String fileName) {
        List<String> list = new ArrayList<>();
        System.out.println();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName))))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static int getBlueAmountOnPixel(int x, int y, BufferedImage image) {
        return image.getRGB(x,y) & 0xFF;
    }

    public static void removeAllPhysicsFromCollidableObject(Collidable collidable) {
        List<IPhysics> units = Launcher.getGame().getPhysicsManager().getPhysicsUnits();
        for (IPhysics unit: units) {
            unit.remove(collidable);
        }
    }

    public static void addAllPhysicsUnitsToCollidableObject(Collidable collidable) {
        List<IPhysics> units = Launcher.getGame().getPhysicsManager().getPhysicsUnits();
        for (IPhysics unit: units) {
            unit.add(collidable);
        }
    }

    public static Config loadConfig() {
        Config config;
        Gson gson = new Gson();

        try(FileReader reader = new FileReader("src/main/resources/config.json")) {
            config = gson.fromJson(reader, Config.class);
            config.init();
            return config;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
