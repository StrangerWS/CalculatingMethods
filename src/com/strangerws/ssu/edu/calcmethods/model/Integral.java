package com.strangerws.ssu.edu.calcmethods.model;

import com.strangerws.ssu.edu.calcmethods.Main;

/**
 * Created by DobryninAM on 13.12.2016.
 */
public class Integral {

    private static final double START = 0;
    private static final double END = Main.VARIANT;
    private static final double STEP = (END - START) / (Main.COUNT - 1);

    private double[] xRow;
    private double[] yPreciseRow;
    private double[] yRow;

    public Integral() {
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

        double h = Math.abs(xRow[0] - xRow[1]);

        for (int i = 0; i < xRow.length; i++) {
            for (int j = 0; j < xRow.length; j++) {
                matrix[i][j] = h * getCore(xRow[i], xRow[j]);

                if (j == 0 || j == xRow.length - 1) {
                    matrix[i][j] /= 2;
                }

                if (i == j) {
                    matrix[i][j]++;
                }
            }
        }

        for (int i = 0; i < xRow.length; i++) {
            vector[i] = getFunction(xRow[i]);
        }

        gaussMethodSolution(new GaussMatrix(matrix, vector));
    }

    private double getYPreciseFunction(double x) {
        return x * x + END;
    }

    private double getFunction(double x) {
        return Math.pow(x, 2) + END + x * (Math.pow(END, 4) / 4 + Math.pow(END, 3) / 2 + x * Math.pow(END, 5) / 5 + x * Math.pow(END, 4) / 3 + Math.pow(x, 2) * Math.pow(END, 6) / 6 + Math.pow(x, 2) * Math.pow(END, 5) / 4);
    }

    private double getCore(double x, double t) {
        return x * t + Math.pow(x, 2) * Math.pow(t, 2) + Math.pow(x, 3) * Math.pow(t, 3);
    }

    private void gaussMethodSolution(GaussMatrix matrix) {
        for (int i = 0; i < matrix.getMatrix().length; i++) {
            if (matrix.getMatrix()[i][i] == 0) {
                if (!matrix.swap(i)) {
                    System.out.println("ОШИБКА: Матрица не может быть обработана!");
                    yRow = matrix.getAnswer();
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

        yRow = matrix.getAnswer();
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
            xRow.append(String.format("%12.7f\t", this.xRow[i]));
            yRow.append(String.format("%12.7f\t", this.yRow[i]));
            yPreciseRow.append(String.format("%12.7f\t", this.yPreciseRow[i]));
            accuracyRow.append(String.format("%12.7f\t", (Math.abs(this.yRow[i] - this.yPreciseRow[i]))));
        }

        System.out.println(xRow + "\n" + yRow + "\n" + yPreciseRow + "\n" + accuracyRow);
    }
}
