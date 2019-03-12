package utils;

import processing.core.PVector;

public class PVector4D extends PVector {
    public float t;

    public PVector4D(float x, float y, float z, float t) {
        super(x, y, z);
        this.t = t;
    }
}
