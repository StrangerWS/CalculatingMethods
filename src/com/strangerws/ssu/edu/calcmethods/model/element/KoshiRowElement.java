package com.strangerws.ssu.edu.calcmethods.model.element;

import javafx.util.Pair;

/**
 * Created by DobryninAM on 29.11.2016.
 */
public class KoshiRowElement extends RowElement {

    private double preciseFunction;
    private double eulerFunction;
    private double modifiedEulerFunction;
    private double predictorCorrector;

    public double getPreciseFunction() {
        return preciseFunction;
    }

    public double getEulerFunction() {
        return eulerFunction;
    }

    public double getModifiedEulerFunction() {
        return modifiedEulerFunction;
    }

    public double getPredictorCorrector() {
        return predictorCorrector;
    }

    public void setPreciseFunction(double preciseFunction) {
        this.preciseFunction = preciseFunction;
    }

    public void setEulerFunction(double eulerFunction) {
        this.eulerFunction = eulerFunction;
    }

    public void setModifiedEulerFunction(double modifiedEulerFunction) {
        this.modifiedEulerFunction = modifiedEulerFunction;
    }

    public void setPredictorCorrector(double predictorCorrector) {
        this.predictorCorrector = predictorCorrector;
    }

    public KoshiRowElement(Pair<Double, Integer> pair, double x) {
        super(pair, x);
    }

    public KoshiRowElement(double x, double function) {
        super(x, function);
    }

    public KoshiRowElement(double x, double function, double preciseFunction, double eulerFunction, double modifiedEulerFunction, double predictorCorrector) {
        super(x, function);
        this.preciseFunction = preciseFunction;
        this.eulerFunction = eulerFunction;
        this.modifiedEulerFunction = modifiedEulerFunction;
        this.predictorCorrector = predictorCorrector;
    }
}
