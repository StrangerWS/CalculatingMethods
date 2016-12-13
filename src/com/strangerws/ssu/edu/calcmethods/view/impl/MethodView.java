package com.strangerws.ssu.edu.calcmethods.view.impl;

import com.strangerws.ssu.edu.calcmethods.model.*;
import com.strangerws.ssu.edu.calcmethods.view.api.*;

import java.util.Scanner;

/**
 * Created by DobryninAM on 08.11.2016.
 */
public class MethodView implements RowView, LagrangeView, NewtonView, MatrixView, EulerView {

    @Override
    public void printMatrixLine(double[] line) {
        for (int i = 0; i < line.length; i++) {
            System.out.print(String.format("%(.4f\t", line[i]));
        }

        System.out.println();
    }

    @Override
    public void printMatrixWithVector(Matrix matrix) {
        for (int i = 0; i < matrix.getMatrix().length; i++) {
            for (int j = 0; j < matrix.getMatrix().length; j++) {
                if (matrix.getMatrix()[i][j] == -0) {
                    matrix.getMatrix()[i][j] = 0;
                }
                System.out.print(String.format("%(6.2f ", matrix.getMatrix()[i][j]));
            }
            System.out.println((String.format("\t\t\t%(.2f", matrix.getVector()[i])));
        }
        System.out.println();
    }

    public void printMatrixAnswer(Matrix matrix) {
        System.out.print("  Ответ: ");
        printMatrixLine(matrix.getAnswer());
    }

    @Override
    public void printArrayQandP(RunMatrix matrix) {
        System.out.print("\tМассив P: ");
        for (int i = 1; i < matrix.getArrayP().length; i++) {
            System.out.print(String.format("%.4f\t", matrix.getArrayP()[i]));
        }

        System.out.print("\n\tМассив Q: ");
        for (int i = 1; i < matrix.getArrayQ().length; i++) {
            System.out.print(String.format("%.4f\t", matrix.getArrayQ()[i]));
        }
        System.out.println();
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

    public void printCauchy(CauchyRow row, Scanner scanner) {
        row.print();
    }
}
