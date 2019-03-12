package scene;

import Player.Player;
import camera.Camera;
import light.Light;
import object.Material;
import object.SceneObject;
import object.Texture;
import processing.core.*;

import java.awt.*;

public class Scene extends PApplet {
    private SceneObject floor, star, candle, table, wall;
    private Player player;
    private Light light;
    private float starMove = .0f;
    private float dir = -1.f;

    public void settings() {
        size(1200, 1200, P3D);
    }

    public void setup() {
        light = new Light(this);
        Camera cam = new Camera(this);

        PVector pos = new PVector(width / 2.f, height / 2.f - 500.f, 1050.f);
        PVector center = new PVector(width / 2.f, height / 2.f - 400.f, -200.f);
        PVector rotation = new PVector(.0f, .0f, .0f);
        player = new Player(this, pos, center, rotation, cam, 5.5f,1.f, 2.5f,.5f);

        spawnObjects();
        placeMouseCenter();
        noCursor();
    }

    private void spawnObjects() {
        renderFloor();
        renderStar();
        renderCandle();
        renderTable();
        renderWall();
    }

    private void renderWall() {
        PShape wallShape = createShape(PConstants.BOX, 1200.f, 950.f, 75.f);
        PImage img = loadImage("res/models/wall/Textures/VC_Summer__2983.jpg");
        PVector ambient = new PVector(142.f, 43.f, 2.f);
        PVector emissive = new PVector(153.f, 87.f, 76.f);
        PVector specular = new PVector(235.f, 155.f, 130.f);
        float shininess = 50.f;
        Material mat = new Material(this, ambient, emissive, specular, shininess);
        wall = new SceneObject(this, new PVector(width / 2.f, height / 4.f - 150.f, -640.f), wallShape, new Texture(img, null), null, null);
    }

    private void renderTable() {
        PShape tableShape = loadShape("res/models/table/tbl022.obj");
        tableShape.rotateX(radians(90));
        tableShape.scale(.45f);
        table = new SceneObject(this, new PVector(width / 2.f - 200.f, height / 4.f - 20.f, -50.f), tableShape, null, null, null);
    }

    private void renderCandle() {
        PShape candleShape = loadShape("res/models/candle/candles_obj.obj");
        candleShape.rotateX(radians(180));
        candleShape.scale(.5f);
        PVector ambient = new PVector(0, 0, 0);
        PVector emissive = new PVector(10, 10, 10);
        PVector specular = new PVector(75, 75, 75);
        float shininess = 15.f;
        Material mat = new Material(this, ambient, emissive, specular, shininess);
        candle = new SceneObject(this, new PVector(width / 2.f - 200.f, height / 4.f - 200.f, -50.f), candleShape, null, null, null);
    }

    private void renderStar() {
        PShape starShape = createShape(PConstants.ELLIPSE, .0f, .0f, 500.f, 500.f);
        PImage img = loadImage("res/textures/2k_sun.jpg");
        PVector ambient = new PVector(246.f, 246.f, 70.f);
        PVector emissive = new PVector(255.f, 255.f, 212.f);
        PVector specular = new PVector(255.f, 255.f, 212.f);
        float shininess = 255.f;
        Material mat = new Material(this, ambient, emissive, specular, shininess);
        star = new SceneObject(this, new PVector(3 * width / 4.f, -height / 6.f, -1000.f), starShape, new Texture(img, null), mat, null);
    }

    private void renderFloor() {
        PShape floorShape = createShape(PConstants.BOX, 2400.f, 90, 2400.f);

        PImage img = loadImage("res/textures/floor.jpg");
        PVector ambient = new PVector(67.f, 59.f, 59.f);
        PVector emissive = new PVector(119.f, 112.f, 112.f);
        PVector specular = new PVector(174.f, 166.f, 166.f);
        float shininess = 128.f;
        Material mat = new Material(this, ambient, emissive, specular, shininess);
        floor = new SceneObject(this, new PVector(0, 0, 0), floorShape, new Texture(img, null), mat, null);
    }

    public void draw() {
        background(128);

        placeMouseCenter();

        strokeWeight(10);
        fill(255,0,0);
        player.refresh();
        point(player.getCenter().x, player.getCenter().y, player.getPos().z);

        lightSetting();

        pushMatrix();
        starMove = dir * 1;
        star.getPos().x = star.getPos().x + starMove;
        translate(star.getPos().x, star.getPos().y, star.getPos().z);
        if (star.getPos().x < -300) dir = -dir;
        else if (star.getPos().x > 1200) dir = -dir;
        star.refresh();
        popMatrix();

        pushMatrix();
        translate(candle.getPos().x, candle.getPos().y, candle.getPos().z);
        candle.refresh();
        popMatrix();

        pushMatrix();
        translate(table.getPos().x, table.getPos().y, table.getPos().z);
        table.refresh();
        popMatrix();

        pushMatrix();
        translate(wall.getPos().x, wall.getPos().y, wall.getPos().z);
        wall.refresh();
        popMatrix();

        pushMatrix();
        translate(width / 2.f, height / 2.f, .0f);
        floor.refresh();
        popMatrix();
    }

    private void lightSetting() {
        if (mousePressed) light.switchOn();
        else light.switchOff();
        if (!light.isOn()) background(70.f);

        PVector ambient = star.getMaterial().getAmbient();
        light.setAmbientLight(ambient.x, ambient.y, ambient.z, star.getPos().x, star.getPos().y, star.getPos().z);
        ambientLight(255,255,255, candle.getPos().x, candle.getPos().y, candle.getPos().z);
        directionalLight(255,255,255, -star.getPos().x, star.getPos().y, -star.getPos().z);
        light.setLightSpecular(255,255,255);
        pointLight(255,0,0,player.getPos().x, player.getPos().y, player.getPos().z);
        light.refresh();
    }

    private void placeMouseCenter() {
        try {
            Robot robot = new Robot();
            robot.mouseMove(displayWidth / 2, displayHeight / 2  + 59);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PApplet.main("scene.Scene");
    }
}
