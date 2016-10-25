package com.strangerws.ssu.edu.calcmethods.model;

import com.strangerws.ssu.edu.calcmethods.Main;
import com.strangerws.ssu.edu.calcmethods.model.element.RowElement;
import javafx.util.Pair;

import java.util.TreeSet;

/**
 * Created by DobryninAM on 25.10.2016.
 */
public class Row {
    private TreeSet<RowElement> row;
    private double step;

    private double a;
    private double b;

    public Row(double a, double b) {
        row = new TreeSet<>();
        this.a = a;
        this.b = b;

        step = Math.abs(b - a)/ Main.COUNT;

        makeRow();
    }

    public void makeRow() {
        for (double i = a; i < b; i += step){
            row.add(new RowElement(getSum(i), i));
        }
    }

    public Pair getSum(double x){
        double function = 1;
        double functionForCount = 1;
        int count = 0;

        while (Math.abs(functionForCount) >= Main.ACCURACY)
        {
            functionForCount *= Math.pow(-1, count) * Math.pow(Main.VARIANT * x, 2) / (2 * count * 2 * count - 1);
            function += functionForCount;
            count++;
        }
        return new Pair<>(function, count);
    }

    public void print(){
        for (RowElement elem : row){
            System.out.println(String.format
                    ("Для элемента %1$f сумма ряда равна %2$f и достигает своего максимума на элементе %3$d",
                    elem.getX(), elem.getFunction(), elem.getCount()));
        }
    }

    public void extendRow(){
        double subX;
        double subFunction;

        for (double i = a; i < b; i += step){
            subX = (2 * i + step) / 2;
            subFunction = ((double)getSum(i).getKey() + (double)getSum(i + step).getKey())/ 2;
            row.add(new RowElement(new Pair<Double, Integer>(subFunction, 0), subX));
        }
    }

    public void interpolateLagrange(){

    }
}