package object;

import processing.core.PImage;

public class Texture {
    private PImage texture;
    private int totalVertex;
    private float[][] uv;

    public Texture(PImage texture, float[][] uv) {
        this.texture = texture;
        this.uv = uv;
        if (uv != null) this.totalVertex = this.uv.length;
    }

    public float[][] getUv() {
        return uv;
    }

    public void setUv(float[][] uv) {
        this.totalVertex = uv.length;
        this.uv = uv;
    }

    public int getTotalVertex() {
        return totalVertex;
    }

    public void setTexture(PImage texture) {
        this.texture = texture;
    }

    public PImage getTexture() {
        return texture;
    }
}
