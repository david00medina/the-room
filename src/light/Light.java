package light;

import processing.core.PApplet;
import processing.core.PVector;

public class Light {
    private PApplet parent;
    private PVector[] ambientLight;
    private PVector[] directionalLight;
    private PVector lightFalloff;
    private PVector lightSpecular;
    private boolean isOn;

    public Light(PApplet parent) {
        this.parent = parent;

        ambientLight = new PVector[]{
                new PVector(.0f, .0f, .0f),
                new PVector(.0f, .0f, .0f)
        };
        directionalLight = new PVector[]{
                new PVector(.0f, .0f, .0f),
                new PVector(.0f, .0f, 1.f)
        };
        lightFalloff = new PVector(1.f, .0f, .0f);
        lightSpecular = new PVector(255.f, 255.f, 255.f);
    }

    public void setAmbientLight(float r, float g, float b) {
        ambientLight[0].x = r;
        ambientLight[0].y = g;
        ambientLight[0].z = b;
    }

    public void setAmbientLight(float r, float g, float b, float x, float y, float z) {
        setAmbientLight(r, g, b);

        ambientLight[1].x = x;
        ambientLight[1].y = y;
        ambientLight[1].z = z;
    }

    public void setDirectionalLight(float r, float g, float b, float x, float y, float z) {
        directionalLight[0].x = r;
        directionalLight[0].y = g;
        directionalLight[0].z = b;

        directionalLight[1].x = x;
        directionalLight[1].y = y;
        directionalLight[1].z = z;
    }

    public void setLightFalloff(float constant, float linear, float quadratic) {
        lightFalloff.x = constant;
        lightFalloff.y = linear;
        lightFalloff.z = quadratic;
    }

    public void setLightSpecular(float r, float g, float b) {
        lightSpecular.x = r;
        lightSpecular.y = g;
        lightSpecular.z = b;
    }

    public boolean isOn() {
        return isOn;
    }

    public void switchOn() {
        parent.lights();
        isOn = true;
    }

    public void switchOff() {
        parent.noLights();
        isOn = false;
    }

    public void refresh() {
        parent.ambientLight(ambientLight[0].x, ambientLight[0].y, ambientLight[0].z,
                ambientLight[1].x, ambientLight[1].y, ambientLight[1].z);

        parent.directionalLight(directionalLight[0].x, directionalLight[0].y, directionalLight[0].z,
                directionalLight[1].x, directionalLight[1].y, directionalLight[1].z);

        parent.lightFalloff(lightFalloff.x, lightFalloff.y, lightFalloff.z);

        parent.lightSpecular(lightSpecular.x, lightSpecular.y, lightSpecular.z);
    }
}
