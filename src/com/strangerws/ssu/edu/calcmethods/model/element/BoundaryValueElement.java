package com.strangerws.ssu.edu.calcmethods.model.element;

/**
 * Created by DobryninAM on 13.12.2016.
 */
public class BoundaryValueElement {

    private double x;
    private double y;
    private double yPrecise;
    private double accuracy;

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public double getyPrecise() {
        return yPrecise;
    }

    public void setyPrecise(double yPrecise) {
        this.yPrecise = yPrecise;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public BoundaryValueElement(double x, double y, double yPrecise, double accuracy) {
        this.x = x;
        this.y = y;
        this.yPrecise = yPrecise;
        this.accuracy = accuracy;
    }
}
