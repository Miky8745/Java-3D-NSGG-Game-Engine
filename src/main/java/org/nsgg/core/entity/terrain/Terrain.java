package org.nsgg.core.entity.terrain;

import org.joml.Random;
import org.joml.Vector3f;
import org.nsgg.core.ObjectLoader;
import org.nsgg.core.entity.Material;
import org.nsgg.core.entity.Model;
import org.nsgg.core.entity.Texture;
import org.nsgg.core.utils.Utils;
import org.nsgg.main.Launcher;

import java.awt.image.BufferedImage;

import static org.nsgg.core.utils.Config.TERRAIN_SIZE;
import static org.nsgg.core.utils.Config.TERRAIN_VERTEX_COUNT;

public class Terrain {

    private Vector3f pos;
    private Model model;
    private TerrainTexture blendMap;
    private BlendMapTerrain blendMapTerrain;
    private Random rnd = new Random();

    public Terrain(Vector3f pos, ObjectLoader loader, Material material, BlendMapTerrain blendMapTerrain, TerrainTexture blendMap) {
        this.pos = pos;
        this.model = generateTerrain(loader);
        this.model.setMaterial(material);
        this.blendMap = blendMap;
        this.blendMapTerrain = blendMapTerrain;
    }

    private Model generateTerrain(ObjectLoader loader) {
        int count = TERRAIN_VERTEX_COUNT * TERRAIN_VERTEX_COUNT;
        BufferedImage heightMap = Launcher.getGame().heightMap;
        float[] vertices = new float[count * 3];
        float[] normals = new float[count * 3];
        float[] textureCoords = new float[count * 2];
        int[] indices = new int[6 * (TERRAIN_VERTEX_COUNT - 1) * (TERRAIN_VERTEX_COUNT - 1)];
        int vertexPointer = 0;
        for (int i = 0; i < TERRAIN_VERTEX_COUNT; i++) {
            for (int j = 0; j < TERRAIN_VERTEX_COUNT; j++) {
                vertices[vertexPointer * 3] = j / (TERRAIN_VERTEX_COUNT - 1.0f) * TERRAIN_SIZE;
                vertices[vertexPointer * 3 + 1] = (float) Utils.getBlueAmountOnPixel(i, j, heightMap) / 2;
                vertices[vertexPointer * 3 + 2] = i / (TERRAIN_VERTEX_COUNT - 1.0f) * TERRAIN_SIZE;
                normals[vertexPointer * 3] = 0;
                normals[vertexPointer * 3 + 1] = 1;
                normals[vertexPointer * 3 + 2] = 0;
                textureCoords[vertexPointer * 2] = j/(TERRAIN_VERTEX_COUNT - 1.0f);
                textureCoords[vertexPointer * 2 + 1] = i/(TERRAIN_VERTEX_COUNT - 1.0f);
                vertexPointer++;
            }
        }

        int pointer = 0;
        for(int z = 0; z < TERRAIN_VERTEX_COUNT - 1.0f; z++) {
            for (int x = 0; x < TERRAIN_VERTEX_COUNT - 1.0f; x++) {
                int topLeft = (z*TERRAIN_VERTEX_COUNT) + x;
                int topRight = topLeft + 1;
                int bottomLeft = ((z+1) * TERRAIN_VERTEX_COUNT) + x;
                int bottomRight = bottomLeft + 1;
                indices[pointer++] = topLeft;
                indices[pointer++] = bottomLeft;
                indices[pointer++] = topRight;
                indices[pointer++] = topRight;
                indices[pointer++] = bottomLeft;
                indices[pointer++] = bottomRight;
            }
        }

        return loader.loadModel(vertices, textureCoords, normals, indices, "Terrain");
    }

    public Vector3f getPos() {
        return pos;
    }

    public Model getModel() {
        return model;
    }

    public Material getMaterial() {
        return model.getMaterial();
    }

    public Texture getTexture() {
        return model.getTexture();
    }

    public TerrainTexture getBlendMap() {
        return blendMap;
    }

    public BlendMapTerrain getBlendMapTerrain() {
        return blendMapTerrain;
    }
}
