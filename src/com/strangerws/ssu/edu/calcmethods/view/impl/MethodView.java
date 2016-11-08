package com.strangerws.ssu.edu.calcmethods.view.impl;

import com.strangerws.ssu.edu.calcmethods.model.GaussMatrix;
import com.strangerws.ssu.edu.calcmethods.model.Row;
import com.strangerws.ssu.edu.calcmethods.model.Table;
import com.strangerws.ssu.edu.calcmethods.view.api.*;

import java.util.Scanner;

/**
 * Created by DobryninAM on 08.11.2016.
 */
public class MethodView implements RowView, LagrangeView, NewtonView, GaussView, EulerView {

    @Override
    public void printGaussLine(GaussMatrix matrix, int matrixLineNumber) {
        for (int i = 0; i < matrix.getMatrix()[matrixLineNumber].length; i++) {
            System.out.print(String.format("%(.4f\t", matrix.getMatrix()[matrixLineNumber][i]));
        }

        System.out.println();
    }

    @Override
    public void printGaussWithVector(GaussMatrix matrix) {
        for (int i = 0; i < matrix.getMatrix().length; i++) {
            for (int j = 0; j < matrix.getMatrix().length; j++) {
                if (matrix.getMatrix()[i][j] == -0) {
                    matrix.getMatrix()[i][j] = 0;
                }
                System.out.print(String.format("%(6.2f ", matrix.getMatrix()[i][j]));
            }
            System.out.println(String.format(String.format("\t\t\t%(.2f", matrix.getVector()[i])));
        }
        System.out.println();
    }


    @Override
    public void printLagrange(Table table, Table table1, Scanner scanner) {
        System.out.println("Метод Лагранжа");
        System.out.println("Введите количество элементов: ");
        int n = Integer.parseInt(scanner.nextLine());
        table.fill(n);
        table1.fillLagrange(table);
        table.print();
        System.out.println();
        table1.print();
        System.out.println();
    }

    @Override
    public void printRow(Row row, Scanner scanner) {
        System.out.println("Вычисление ряда");
        System.out.println("Введите начало отрезка:");
        double a = Double.parseDouble(scanner.nextLine());
        System.out.println("Введите конец отрезка:");
        double b = Double.parseDouble(scanner.nextLine());
        row = new Row(a, b);
        System.out.println("\nСгенерированный ряд:");
        row.print();
        System.out.println("\nРасширенный ряд:");
        row.extendRow();
        row.print();
        System.out.println();
    }

    @Override
    public void print() {

    }
}
