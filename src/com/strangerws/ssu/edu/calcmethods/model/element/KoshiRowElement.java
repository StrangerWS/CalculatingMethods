package com.strangerws.ssu.edu.calcmethods.model.element;

import javafx.util.Pair;

/**
 * Created by DobryninAM on 29.11.2016.
 */
public class KoshiRowElement extends RowElement {

    private double eulerFunction;
    private double modifiedEulerFunction;
    private double predictorCorrector;


    public double getEulerFunction() {
        return eulerFunction;
    }

    public double getModifiedEulerFunction() {
        return modifiedEulerFunction;
    }

    public double getPredictorCorrector() {
        return predictorCorrector;
    }

    public KoshiRowElement(Pair<Double, Integer> pair, double x) {
        super(pair, x);
    }

    public KoshiRowElement(double x, double function) {
        super(x, function);
    }

    public KoshiRowElement(double x, double function, double eulerFunction, double modifiedEulerFunction, double predictorCorrector) {
        super(x, function);
        this.eulerFunction = eulerFunction;
        this.modifiedEulerFunction = modifiedEulerFunction;
        this.predictorCorrector = predictorCorrector;
    }
}
