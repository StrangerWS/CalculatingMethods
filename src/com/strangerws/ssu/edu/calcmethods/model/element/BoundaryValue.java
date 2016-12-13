package com.strangerws.ssu.edu.calcmethods.model.element;

import com.strangerws.ssu.edu.calcmethods.Main;

/**
 * Created by DobryninAM on 13.12.2016.
 */
public class BoundaryValue {

    private static final double START = 0;
    private static final double END = Main.VARIANT;

    public double getPFunction(double x) {
        return x * x;
    }

    public double getQFunction(double x) {
        return x * x * x;
    }

    public double getYSharpSharpFunction(double x) {
        return 6 * END;
    }

    public double getFunction(double x) {
        return getYSharpSharpFunction(x) + getPFunction(x) * getYSharpFunction(x) + getYFunction(x);
    }
}
