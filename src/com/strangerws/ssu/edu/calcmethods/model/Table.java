package com.strangerws.ssu.edu.calcmethods.model;

import com.strangerws.ssu.edu.calcmethods.model.element.RowElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DobryninAM on 01.11.2016.
 */
public class Table {
    private List<RowElement> table = new ArrayList<>();

    public void fill(int n){
        for (int i = 1; i <= n; i++){
            table.add(new RowElement((double)i, getFunction(i, n)));

        }
    }

    private double getFunction(double x, int n) {
        double function = 1.0;
        for (int i = 0; i < n - 1; i++){
            function *= x;
        }
        return function;
    }

    public void fillLagrange(Table originTable) {
        int length = originTable.table.size() * 2 - 1;
        int last = originTable.table.size() - 1;

        for (int i = 1; i < length; i += 2){
            int first = i / 2;
            int second = (i + 1) / 2;
            table.add(new RowElement(originTable.table.get(first).getX(), originTable.table.get(first).getFunction()));

            double x = (originTable.table.get(first).getX() + originTable.table.get(second).getX()) / 2;
            double function = originTable.getLagrange(x, length / 2);

            table.add(new RowElement(x, function));
        }
        table.add(new RowElement(originTable.table.get(last).getX(), originTable.table.get(last).getFunction()));
    }

    private double getLagrange(double x, int n){
        double result = 0;

        for (int i = 0; i <= n; i++) {
            double resultStep = 1;

            for (int j = 0; j <= n; j++) {
                if (i != j) {
                    double first = x - table.get(j).getX();
                    double second = table.get(i).getX() - table.get(j).getX();
                    double elem = first / second;
                    resultStep *= elem;
                }
            }
            result += resultStep * table.get(i).getFunction();
        }
        return result;
    }

    public void print(){
        for (RowElement elem : table)
        System.out.println(String.format("Функция от %s равна %s", elem.getX(), elem.getFunction()));
    }
}