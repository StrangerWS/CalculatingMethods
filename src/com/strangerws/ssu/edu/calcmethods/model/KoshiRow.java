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
    }

    public KoshiRow(double a, double b) {
        rowKoshi = new ArrayList<>();
        this.a = a;
        this.b = b;


        makeRow();
    }

    @Override
    public void makeRow() {
        for (int i = (int) a; i < b; ++i) {
            rowKoshi.add(new KoshiRowElement(i,
                    getPreciseFunction(i),
                    getEulerFunction(i - 1, (double) getSum(i - 1).getKey(), 1),
                    getModifiedEulerFunction(i - 1, i, (double) getSum(i - 1).getKey(), 1),
                    getPredictorCorrector(i - 1, i, (double) getSum(i - 1).getKey(), 1)));
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
        for (KoshiRowElement elem : rowKoshi) {
            StringBuilder out = new StringBuilder();
            out.append("Элемент x: ");
            out.append(String.format("%(.2f", elem.getX()));
            out.append(" Элемент y: ");
            //Метод Эйлера
            //out.append(elem.getEulerFunction());
            //Модифицированный Метод Эйлера
            //out.append(elem.getModifiedEulerFunction());
            //Метод предиктора-корректора
            out.append(String.format("%(.2f", elem.getPredictorCorrector()));
            out.append(" Элемент y точн: ");
            out.append(String.format("%(.2f", elem.getFunction()));
            out.append(" Погрешность: ");
            //out.append(Math.abs(elem.getEulerFunction() - elem.getFunction()));
            //out.append(Math.abs(elem.getModifiedEulerFunction() - elem.getFunction()));
            out.append(String.format("%(.2f", Math.abs(elem.getPredictorCorrector() - elem.getFunction())));
            System.out.println(out);
        }
    }
}
