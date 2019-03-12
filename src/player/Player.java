package player;

import camera.Camera;
import processing.core.PApplet;
import processing.core.PVector;

public class Player {
    private final float STEP;
    private final float SPEED;

    private PApplet parent;
    private PVector pos;
    private PVector center;
    private Camera cam;

    private float radius = .0f;
    private float zenith = .0f;
    private float azimuth = .0f;

    public Player(PApplet parent, PVector pos, PVector center, Camera cam, float step, float speed) {
        this.STEP = step;
        this.SPEED = speed;

        this.parent = parent;
        this.pos = pos;
        this.center = center;
        this.cam = cam;

        setInitialCenter();
    }

    private void setInitialCenter() {
        radius = PApplet.sqrt(center.x * center.x + center.y * center.y + center.z * center.z);
        zenith = PApplet.acos(center.z / radius);
        azimuth = PApplet.atan(center.y / center.x);
    }

    public PVector getPos() {
        return pos;
    }

    private void moveForward() {
        pos.z = pos.z - STEP * SPEED;
        center.z = center.z - (STEP * SPEED);
    }

    private void moveBackward() {
        pos.z = pos.z + STEP * SPEED;
        center.z = center.z + (STEP * SPEED);
    }

    private void moveRight() {
        pos.x = pos.x + STEP * SPEED;
        center.x += STEP * SPEED;
    }

    private void moveLeft() {
        pos.x = pos.x - STEP * SPEED;
        center.x -= STEP * SPEED;
    }

    private void moveTo() {
        if (parent.keyPressed) {
            if (parent.key == 'w') {
                moveForward();
            } else if (parent.key == 's') {
                moveBackward();
            } else if (parent.key == 'a') {
                moveLeft();
            } else if (parent.key == 'd') {
                moveRight();
            }
        }
    }

    public void refresh() {
        moveTo();

        cam.setCenter(center.x, center.y, center.z);
        cam.setPosition(pos.x, pos.y, pos.z);
        cam.refresh();
    }
}
