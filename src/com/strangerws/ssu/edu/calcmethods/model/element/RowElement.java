package com.strangerws.ssu.edu.calcmethods.model.element;

import javafx.util.Pair;

/**
 * Created by DobryninAM on 25.10.2016.
 */
public class RowElement implements Comparable<RowElement>{
    private int count;
    private double x;
    private double function;

    public RowElement(Pair<Double, Integer> pair, double x){
        this.function = pair.getKey();
        this.count = pair.getValue();
        this.x = x;
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
