package org.nsgg.core.entity;

public class Model {

    private final int id;
    private int vertexCount;
    private Material material;
    private final String name;

    public Model(int id, int vertexCount, String name) {
        this.id = id;
        this.vertexCount = vertexCount;
        this.name = name;
        this.material = new Material();
    }

    public Model(int id, int vertexCount, Texture texture, String name) {
        this.id = id;
        this.vertexCount = vertexCount;
        this.material = new Material(texture);
        this.name = name;
    }

    public Model(Texture texture, Model model) {
        this.id = model.getId();
        this.vertexCount = model.getVertexCount();
        this.name = model.getName();
        this.material = model.getMaterial();
        this.material.setTexture(texture);
    }

    public int getId() {
        return id;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public Texture getTexture() {
        return material.getTexture();
    }

    public void setTexture(Texture texture) {
        material.setTexture(texture);
    }

    public boolean hasTexture() {
        return material.hasTexture();
    }

    public String getName() {
        return name;
    }

    public void setTexture(Texture texture, float reflectance) {
        material.setTexture(texture);
        material.setReflectance(reflectance);
    }
}
