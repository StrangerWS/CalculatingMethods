package com.strangerws.ssu.edu.calcmethods.model;

import com.strangerws.ssu.edu.calcmethods.Main;
import com.strangerws.ssu.edu.calcmethods.model.element.CauchyRowElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DobryninAM on 22.11.2016.
 */
public class CauchyRow extends Row {

    private List<CauchyRowElement> rowCauchy;


    public CauchyRow() {
        rowCauchy = new ArrayList<>();
        this.a = 0;
        this.b = 9;
        this.step = (b - a) / Main.COUNT;
        System.out.println(step);


        makeRow();
    }

    @Override
    public void makeRow() {
        CauchyRowElement element = new CauchyRowElement(a, Main.VARIANT + 2);
        element.setPreciseFunction(getPreciseFunction(1));
        element.setEulerFunction(Main.VARIANT + 2);
        element.setModifiedEulerFunction(Main.VARIANT + 2);
        element.setPredictorCorrector(Main.VARIANT + 2);
        rowCauchy.add(element);
        double elem = a + step;
        for (int i = 2; i <= 10; i++) {
            rowCauchy.add(new CauchyRowElement(
                    elem,
                    0,
                    getPreciseFunction(i),
                    getEulerFunction(i - 1, rowCauchy.get(i - 2).getEulerFunction(), step),
                    getModifiedEulerFunction(i - 1, i, rowCauchy.get(i - 2).getModifiedEulerFunction(), step),
                    getPredictorCorrector(i - 1, i, rowCauchy.get(i - 2).getPredictorCorrector(), step)
            ));
            elem += step;
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
        yRow.append("y - точное: \t\t\t\t");
        eulerRow.append("y - Эйлер: \t\t\t\t\t");
        modifiedEulerRow.append("y - мод Эйлер: \t\t\t\t");
        predictorCorrectorRow.append("y - предиктор-корректор: \t");
        accuracyEulerRow.append("Эйлер: \t\t\t\t\t\t");
        accuracyModifiedEulerRow.append("мод Эйлер: \t\t\t\t\t");
        accuracyPredictorCorrector.append("предиктор-корректор: \t\t");
        for (CauchyRowElement elem : rowCauchy) {
            xRow.append(String.format("%10.2f", elem.getX()));
            eulerRow.append(String.format("%10.2f", elem.getEulerFunction()));
            modifiedEulerRow.append(String.format("%10.2f", elem.getModifiedEulerFunction()));
            predictorCorrectorRow.append(String.format("%10.2f", elem.getPredictorCorrector()));
            yRow.append(String.format("%10.2f", elem.getPreciseFunction()));
            accuracyEulerRow.append(String.format("%10.2f", Math.abs(elem.getEulerFunction() - elem.getPreciseFunction())));
            accuracyModifiedEulerRow.append(String.format("%10.2f", Math.abs(elem.getModifiedEulerFunction() - elem.getPreciseFunction())));
            accuracyPredictorCorrector.append(String.format("%10.2f", Math.abs(elem.getPredictorCorrector() - elem.getPreciseFunction())));
        }
        System.out.println(xRow + "\n" + eulerRow + "\n" + modifiedEulerRow + "\n" + predictorCorrectorRow + "\n" + yRow + "\nПогрешность:" + "\n" + accuracyEulerRow + "\n" + accuracyModifiedEulerRow + "\n" + accuracyPredictorCorrector);
    }
}
