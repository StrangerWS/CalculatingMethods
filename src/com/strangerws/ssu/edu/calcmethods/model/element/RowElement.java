package com.strangerws.ssu.edu.calcmethods.model.element;

import javafx.util.Pair;

/**
 * Created by DobryninAM on 25.10.2016.
 */
public class RowElement implements Comparable<RowElement>{
    private int count;
    private double x;
    private double function;

    public RowElement(double x, double function) {
        this.x = x;
        this.function = function;
        this.count = 0;
    }

    public RowElement(Pair<Double, Integer> pair, double x){

        this.function = pair.getKey();
        this.count = pair.getValue();
        this.x = x;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setFunction(double function) {
        this.function = function;
    }

    public double getFunction() {
        return function;
    }

    public double getX() {
        return x;
    }

    public int getCount() {
        return count;
    }


    @Override
    public int compareTo(RowElement o) {
        if (this.x < o.x) return -1;
        else if (this.x > o.x) return 1;
        else return 0;
    }
}
