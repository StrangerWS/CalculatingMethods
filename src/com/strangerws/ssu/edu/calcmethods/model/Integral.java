package com.strangerws.ssu.edu.calcmethods.model;

import com.strangerws.ssu.edu.calcmethods.Main;

import java.util.List;

/**
 * Created by DobryninAM on 13.12.2016.
 */
public class Integral {

    private static final double A = 0;
    private static final double B = Main.VARIANT;
    private static final int LAMBDA = 1;

    private List<Double> xRow;
    private List<Double> yRow;
    private List<Double> yPreciseRow;
    private List<Double> accuracyRow;

    public void fillRows() {
        for (double i = A; i < B; i++) {
            xRow.add(i);
            yPreciseRow.add(getPreciseRow(i));
            yRow.add(getIntegralRow(i));
        }
    }

    public double getPreciseRow(double x) {
        return x * x + 2;
    }
    public double getIntegralRow(double x){
        return 0;
    }
}
