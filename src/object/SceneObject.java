package object;

import processing.core.*;
import processing.sound.SoundFile;

public class SceneObject {
    private PApplet parent;

    private PVector pos;
    private PShape model;
    private Texture texture;
    private Material material;
    private SoundFile sound;

    public SceneObject(PApplet parent, PVector pos, PShape model, Texture texture, Material material, SoundFile sound) {
        this.parent = parent;

        this.pos = pos;
        this.model = model;
        this.texture = texture;
        this.material = material;
        this.sound = sound;
    }

    public SceneObject(PApplet parent, PVector pos, PShape model, Material material) {
        this.parent = parent;
        this.pos = pos;
        this.model = model;
        this.material = material;
    }

    public PVector getPos() {
        return pos;
    }

    public Material getMaterial() {
        return material;
    }

    public void setPos(PVector pos) {
        this.pos = pos;
    }

    public void setModel(PShape model) {
        this.model = model;
    }

    public void setSound(SoundFile sound) {
        this.sound = sound;
    }

    public PShape getModel() {
        return model;
    }

    public void refresh() {
        //model.setTextureMode(PConstants.NORMAL);
        /*for (int i = 0; i < model.getVertexCount(); i++) {
            int j = i % 4;
            PVector v = model.getVertex(i);
            model.setTextureUV(i, texture.getUv()[j][0], texture.getUv()[j][1]);
        }*/
        if (material != null) material.refresh();
        if (texture != null) model.setTexture(texture.getTexture());
        refreshPosition();
    }

    private void refreshPosition() {
        parent.pushMatrix();
        parent.translate(pos.x, pos.y, pos.z);
        parent.shape(model);
        parent.popMatrix();
    }

    public void playSound() {
        sound.play();
    }

}
