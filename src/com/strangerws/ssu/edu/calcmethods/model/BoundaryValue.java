package com.strangerws.ssu.edu.calcmethods.model;

import com.strangerws.ssu.edu.calcmethods.Main;

/**
 * Created by DobryninAM on 13.12.2016.
 */
public class BoundaryValue {

    private static final double START = 0;
    private static final double END = Main.VARIANT;
    private static final double STEP = (END - START) / (Main.COUNT - 1);

    private double[] xRow;
    private double[] yPreciseRow;
    private double[] yRow;
    private double[] gaussMethodRow;

    public BoundaryValue() {
        xRow = new double[Main.COUNT];
        yPreciseRow = new double[Main.COUNT];
        double[][] matrix = new double[Main.COUNT][Main.COUNT];
        double[] vector = new double[Main.COUNT];

        xRow[0] = START;
        for (int i = 1; i < Main.COUNT; i++) {
            xRow[i] = xRow[i - 1] + STEP;
        }

        for (int i = 0; i < Main.COUNT; i++) {
            yPreciseRow[i] = getYPreciseFunction(xRow[i]);
        }

        for (int j = 0; j < matrix.length; j++) {
            double x = xRow[j];

            for (int k = 0; k < matrix.length; k++) {
                matrix[j][k] = getYSharpSharpFunction(x, k) + getYSharpFunction(x, k) * getPFunction(x) + getYFunction(x, k) * getQFunction(x);
            }
        }

        for (int i = 0; i < vector.length; i++) {
            vector[i] = getFunction(xRow[i]);
        }

        gaussMethod(new GaussMatrix(matrix, vector));
        generateYRow();
    }

    private double getYPreciseFunction(double x) {
        return x * x * x - END * x * x;
    }

    private void generateYRow() {
        yRow = new double[Main.COUNT];

        for (int i = 1; i < yRow.length - 1; i++) {
            for (int k = 0; k < yRow.length; k++) {
                yRow[i] += gaussMethodRow[k] * getYFunction(xRow[i], k);
            }
        }
    }

    private double getPFunction(double x) {
        return x * x;
    }

    private double getQFunction(double x) {
        return x * x * x;
    }

    private double getYSharpSharpFunction(double x, int k) {
        return (k + 2) * (k + 1) * Math.pow(x, k) * (x - END) + (k + 2) * Math.pow(x, k + 1) + (k + 2) * Math.pow(x, k + 1);
    }

    private double getYSharpFunction(double x, int k) {
        return (k + 2) * Math.pow(x, k + 1) * (x - END) + Math.pow(x, k + 2);
    }

    private double getYFunction(double x, int k) {
        return Math.pow(x, k + 2) * (x - END);
    }

    private double getFunction(double x) {
        return Math.pow(x, 6) - END * Math.pow(x, 5) + 3 * Math.pow(x, 4) - 2 * END * Math.pow(x, 3) + 6 * x - 2 * END;
    }

    private void gaussMethod(GaussMatrix matrix) {
        for (int i = 0; i < matrix.getMatrix().length; i++) {
            if (matrix.getMatrix()[i][i] == 0) {
                if (!matrix.swap(i)) {
                    System.out.println("ОШИБКА: Матрица не может быть обработана!");
                    gaussMethodRow = matrix.getAnswer();
                }
            }

            matrix.getVector()[i] /= matrix.getMatrix()[i][i];

            if (matrix.getMatrix()[i][i] != 1) {
                matrix.divideByElement(i, matrix.getMatrix()[i][i]);
            }

            for (int j = i + 1; j < matrix.getMatrix().length; j++) {
                double subtract = matrix.getMatrix()[j][i] / matrix.getMatrix()[i][i];
                if (matrix.getMatrix()[i][j] - subtract != matrix.getMatrix()[i][j]) {
                    matrix.subtractLines(i, j, subtract);
                    matrix.vector[j] -= matrix.vector[i] * subtract;
                }
            }
        }

        for (int i = 0; i < matrix.getMatrix().length; i++) {
            int index = matrix.getMatrix().length - 1 - i;
            matrix.answer[index] = matrix.vector[index];

            for (int j = index + 1; j < matrix.getMatrix().length; j++) {
                matrix.answer[index] -= matrix.answer[j] * matrix.getMatrix()[index][j];
            }
        }

        gaussMethodRow = matrix.getAnswer();
    }

    public void print() {
        StringBuilder xRow = new StringBuilder();
        StringBuilder yRow = new StringBuilder();
        StringBuilder yPreciseRow = new StringBuilder();
        StringBuilder accuracyRow = new StringBuilder();
        xRow.append("x: \t\t\t\t");
        yRow.append("y: \t\t\t\t");
        yPreciseRow.append("y - точное: \t");
        accuracyRow.append("Погрешность: \t");
        for (int i = 0; i < Main.COUNT; i++) {
            xRow.append(String.format("%22.15f\t", this.xRow[i]));
            yRow.append(String.format("%22.15f\t", this.yRow[i]));
            yPreciseRow.append(String.format("%22.15f\t", this.yPreciseRow[i]));
            accuracyRow.append(String.format("%22.15f\t", (Math.abs(this.yRow[i] - this.yPreciseRow[i]))));
        }

        System.out.println(xRow + "\n" + yRow + "\n" + yPreciseRow + "\n" + accuracyRow);
    }
}

