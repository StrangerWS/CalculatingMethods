package com.strangerws.ssu.edu.calcmethods.model;

import com.strangerws.ssu.edu.calcmethods.Main;
import com.strangerws.ssu.edu.calcmethods.model.element.BoundaryValueElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DobryninAM on 13.12.2016.
 */
public class BoundaryValue {

    private static final double START = 0;
    private static final double END = Main.VARIANT;
    private static final double STEP = (END - START) / Main.COUNT;

    private List<BoundaryValueElement> row = new ArrayList<>();


    public double getPFunction(double x) {
        return x * x;
    }

    public double getQFunction(double x) {
        return x * x * x;
    }

    public double getYSharpSharpFunction(double x) {
        return 6 * x - 2 * END;
    }

    public double getYSharpFunction(double x) {
        return 3 * x * x - 2 * END * x;
    }

    private double getYFunction(double x) {
        return x * x * x - END * x * x;
    }

    public double getFunction(double x) {
        return getYSharpSharpFunction(x) + getPFunction(x) * getYSharpFunction(x) + getQFunction(x) * getYFunction(x);
    }

    public void fillRows() {
        for (double i = START; i < END; i += STEP) {
            row.add(new BoundaryValueElement(i, getFunction(i), getYFunction(i), Math.abs(getFunction(i) - getYFunction(i))));
        }
    }

    public void print() {
        StringBuilder xRow = new StringBuilder();
        StringBuilder yRow = new StringBuilder();
        StringBuilder yPreciseRow = new StringBuilder();
        StringBuilder accuracyRow = new StringBuilder();
        xRow.append("x: \t\t\t\t");
        yRow.append("y: \t\t\t\t");
        yPreciseRow.append("y - точное: \t");
        accuracyRow.append("Погрешность: \t");
        for (BoundaryValueElement elem : row) {
            xRow.append(String.format("%14.2f", elem.getX()));
            yRow.append(String.format("%14.2f", elem.getY()));
            yPreciseRow.append(String.format("%14.2f", elem.getyPrecise()));
            accuracyRow.append(String.format("%14.2f", elem.getAccuracy()));
        }
        System.out.println(xRow + "\n" + yRow + "\n" + yPreciseRow + "\n" + accuracyRow);
    }
}

