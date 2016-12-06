package com.strangerws.ssu.edu.calcmethods.model;

import com.strangerws.ssu.edu.calcmethods.Main;
import com.strangerws.ssu.edu.calcmethods.model.element.KoshiRowElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DobryninAM on 22.11.2016.
 */
public class KoshiRow extends Row {

    private List<KoshiRowElement> rowKoshi;


    public KoshiRow() {
        rowKoshi = new ArrayList<>();
        this.a = 0;
        this.b = 10;


        makeRow();
    }

    @Override
    public void makeRow() {
        KoshiRowElement element = new KoshiRowElement(1, Main.VARIANT + 2);
        element.setPreciseFunction(getPreciseFunction(1));
        element.setEulerFunction(Main.VARIANT + 2);
        element.setModifiedEulerFunction(Main.VARIANT + 2);
        element.setPredictorCorrector(Main.VARIANT + 2);
        rowKoshi.add(element);

        for (int i = 2; i <= b; i++) {
            rowKoshi.add(new KoshiRowElement(
                    i,
                    0,
                    getPreciseFunction(i),
                    getEulerFunction(i - 1, rowKoshi.get(i - 2).getEulerFunction(), 1),
                    getModifiedEulerFunction(i - 1, i, rowKoshi.get(i - 2).getModifiedEulerFunction(), 1),
                    getPredictorCorrector(i - 1, i, rowKoshi.get(i - 2).getPredictorCorrector(), 1)
            ));
        }
    }

    public double getPreciseFunction(double x) {
        return Main.VARIANT * Math.pow(x, 3.0) + Math.pow(x, 2.0) + x;
    }

    public double getEulerFunction(double x, double y, double h) {
        return y + h * getFunction(x, y);
    }

    public double getModifiedEulerFunction(double x1, double x2, double y, double h) {
        double x = (x1 + x2) / 2;
        double yNew = y + (h / 2) * getFunction(x1, y);
        return y + h * getFunction(x, yNew);
    }

    public double getPredictorCorrector(double x1, double x2, double y1, double h) {
        //double y2 = getEulerFunction(x1, y1, h);
        double y2 = getModifiedEulerFunction(x1, x2, y1, h);
        return y1 + h * ((getFunction(x1, y1) + getFunction(x2, y2)) / 2);
    }

    public double getFunction(double x, double y) {
        return Main.VARIANT * Math.pow(x, 3.0) + (3 * Main.VARIANT + 1) * Math.pow(x, 2.0) + 3 * x + 1 - y;
    }

    @Override
    public void print() {
        StringBuilder xRow = new StringBuilder();
        StringBuilder yRow = new StringBuilder();
        StringBuilder eulerRow = new StringBuilder();
        StringBuilder modifiedEulerRow = new StringBuilder();
        StringBuilder predictorCorrectorRow = new StringBuilder();
        StringBuilder accuracyEulerRow = new StringBuilder();
        StringBuilder accuracyModifiedEulerRow = new StringBuilder();
        StringBuilder accuracyPredictorCorrector = new StringBuilder();
        xRow.append("x: \t\t\t\t\t\t\t");
        yRow.append("y точн: \t\t\t\t\t");
        eulerRow.append("y - Эйлер: \t\t\t\t\t");
        modifiedEulerRow.append("y - мод Эйлер: \t\t\t\t");
        predictorCorrectorRow.append("y - предиктор-корректор: \t");
        accuracyEulerRow.append("Эйлер: \t\t\t\t\t\t");
        accuracyModifiedEulerRow.append("мод Эйлер: \t\t\t\t\t");
        accuracyPredictorCorrector.append("предиктор-корректор: \t\t");
        for (KoshiRowElement elem : rowKoshi) {
            xRow.append(String.format("%10d", (int) elem.getX()));
            eulerRow.append(String.format("%10.2f", elem.getEulerFunction()));
            modifiedEulerRow.append(String.format("%10.2f", elem.getModifiedEulerFunction()));
            predictorCorrectorRow.append(String.format("%10.2f", elem.getPredictorCorrector()));
            yRow.append(String.format("%10.2f", elem.getPreciseFunction()));
            accuracyEulerRow.append(String.format("%10.2f", Math.abs(elem.getEulerFunction() - elem.getPreciseFunction())));
            accuracyModifiedEulerRow.append(String.format("%10.2f", Math.abs(elem.getModifiedEulerFunction() - elem.getPreciseFunction())));
            accuracyPredictorCorrector.append(String.format("%10.2f", Math.abs(elem.getPredictorCorrector() - elem.getPreciseFunction())));
        }
        System.out.println(xRow);
        System.out.println(eulerRow);
        System.out.println(modifiedEulerRow);
        System.out.println(predictorCorrectorRow);
        System.out.println(yRow);
        System.out.println("Погрешность:");
        System.out.println(accuracyEulerRow);
        System.out.println(accuracyModifiedEulerRow);
        System.out.println(accuracyPredictorCorrector);
    }
}
