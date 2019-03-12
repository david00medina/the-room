package camera;

import processing.core.PApplet;
import processing.core.PVector;
import utils.PVector4D;

public class Camera {
    public final float CAMERAZ;

    private PApplet parent;
    private PVector pos;
    private PVector center;
    private PVector posRotation;
    private PVector cameraRotation;
    private PVector4D perspective;

    public Camera(PApplet parent, PVector pos, PVector center, PVector cameraRotation, PVector4D perspective) {
        this.parent = parent;
        this.pos = pos;
        this.center = center;
        this.cameraRotation = cameraRotation;
        this.perspective = perspective;

        CAMERAZ = (parent.height / 2.f) / PApplet.tan(PApplet.PI * 60.f / 360.f);
    }

    public Camera(PApplet parent, PVector pos, PVector center, PVector4D perspective) {
        this.parent = parent;
        this.pos = pos;
        this.center = center;
        this.cameraRotation = new PVector(0, 1, 0);
        this.perspective = perspective;

        CAMERAZ = (parent.height / 2.f) / PApplet.tan(PApplet.PI * 60.f / 360.f);
    }

    public Camera(PApplet parent) {
        this.parent = parent;
        CAMERAZ = (parent.height / 2.f) / PApplet.tan(PApplet.PI * 60.f / 360.f);
        reset();
    }

    public void refresh() {
        parent.camera(pos.x, pos.y, pos.z, center.x, center.y, center.z, cameraRotation.x, cameraRotation.y, cameraRotation.z);
        parent.perspective(perspective.x, perspective.y, perspective.z, perspective.t);
    }

    public void setPosition(float x, float y, float z) {
        pos = new PVector(x, y, z);
    }

    public void setCenter(float x, float y, float z) {
        center = new PVector(x, y, z);
    }

    public void setCameraRotation(float x, float y, float z) {
        cameraRotation = new PVector(x, y, z);
    }

    public void setPosRotation(float x, float y, float z) {
        posRotation = new PVector(x, y, z);
    }

    public void setPerspective(float fovy, int aspect, float zNear, float zFar) {
        perspective = new PVector4D(fovy, aspect, zNear, zFar);
    }

    public void reset() {
        this.pos = new PVector(parent.width / 2.f,
                parent.height / 2.f,
                (parent.height / 2.f) / PApplet.tan(PApplet.PI * 30.f / 180.f));

        this.center = new PVector(parent.width / 2.f,
                parent.height / 2.f,
                .0f);

        this.cameraRotation = new PVector(.0f, 1.f, .0f);

        this.perspective = new PVector4D(PApplet.PI / 3.f,
                (float) parent.width / parent.height,
                CAMERAZ / 10.f, CAMERAZ * 10.f);
    }
}