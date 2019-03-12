package Player;

import camera.Camera;
import jogamp.opengl.util.pngj.chunks.PngChunkOFFS;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

public class Player {
    private final float STEP;
    private final float SPEED;
    private final float LOOK_STEP;
    private final float LOOK_SPEED;

    private PApplet parent;
    private PVector pos;
    private PVector center;
    private PVector rotation;
    private Camera cam;

    private float dx = .0f;
    private float dy = .0f;
    private float radius = .0f;
    private float zenith = .0f;
    private float azimuth = .0f;
    private boolean inialization = true;

    public Player(PApplet parent, PVector pos, PVector center, PVector rotation, Camera cam, float step, float speed, float lookStep, float lookSpeed) {
        this.STEP = step;
        this.SPEED = speed;
        this.LOOK_STEP = PApplet.radians(lookStep);
        this.LOOK_SPEED = PApplet.radians(lookSpeed);

        this.parent = parent;
        this.pos = pos;
        this.center = center;
        this.rotation = rotation;
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
        System.out.println("POS : " + pos + "CENTER : " + center);
    }

    private void moveLeft() {
        pos.x = pos.x - STEP * SPEED;
        center.x -= STEP * SPEED;
        System.out.println("POS : " + pos + ", CENTER : " + center);
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

    public PVector getCenter() {
        return center;
    }

    private void faceTo() {
        dx = parent.mouseX - parent.displayWidth / 2.f + 360;
        dy = parent.mouseY - parent.displayHeight / 2.f;

//        dy = parent.map(dy, );

//        System.out.println("DX : " + dx + ", DY: " + dy);
        if (!inialization) {
            azimuth += -dx * LOOK_STEP * LOOK_SPEED;
            zenith += dy * LOOK_STEP * LOOK_SPEED;

            center.x = radius * PApplet.sin(azimuth) * PApplet.cos(zenith);
            center.y = radius * PApplet.sin(azimuth) * PApplet.sin(zenith);
            center.z = radius * PApplet.cos(azimuth) + pos.z;
//            System.out.println("ZENITH : " + zenith + ", AZIMUTH : " + azimuth);
        }


        inialization = false;

        System.out.println("LOOKAT : " + center);
    }

    public void refresh() {
        //faceTo();
        moveTo();

        cam.setCenter(center.x, center.y, center.z);
        cam.setPosition(pos.x, pos.y, pos.z);
        cam.refresh();
    }
}
