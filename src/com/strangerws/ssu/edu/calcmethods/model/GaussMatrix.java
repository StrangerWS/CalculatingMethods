package com.strangerws.ssu.edu.calcmethods.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DobryninAM on 08.11.2016.
 */
public class GaussMatrix {

    private double[][] matrix;
    private double[] vector;


    public GaussMatrix() {
    }

    public GaussMatrix(double[][] matrix, double[] vector) {

        this.matrix = matrix;
        this.vector = vector;
    }

    public static void goForwardStroke(double[][] matrix, double[] vector) {
        int countZero = 0;
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            int count = 1;
            while (matrix[i][i] == 0) {
                if (count >= matrix.length) {
                    break;
                }
                countZero++;
                numbers.add(i);
                swapLines(matrix, vector, i, i + count++);
            }
            vector[i] /= matrix[i][i];

            if (matrix[i][i] != 1) {
                divideAtElement(matrix[i], matrix[i][i]);
            }

            for (int j = i + 1; j < matrix.length; j++) {
                double subtract = matrix[j][i] / matrix[i][i];
                if (matrix[i][j] - subtract != matrix[i][j]) {
                    subtractLines(matrix[i], matrix[j], subtract);
                    vector[j] -= vector[i];
                }
            }
        }
        System.out.println("  Количество нулей на главной диагонали - " + countZero + "\n  Нули встретились на ");
        for (int i = 0; i < numbers.size(); i++) {
            System.out.println("      номере диагонали [" + (numbers.get(i) + 1) + ", " + (numbers.get(i) + 1) + "]");
        }
        System.out.println();
    }

    public static void divideAtElement(double[] line, double value) {
        for (int i = 0; i < line.length; i++) {
            line[i] /= value;
        }
    }

    public static void subtractLines(double[] firstLine, double[] secondLine, double substractElem) {
        for (int i = 0; i < firstLine.length; i++) {
            secondLine[i] -= firstLine[i] * substractElem;
        }
    }

    public static void swapLines(double[][] matrix, double[] vector, int indexLine, int indexToSwapLine) {
        double[] tempLine = matrix[indexLine];
        double tempVector = vector[indexLine];

        double element = matrix[indexLine][indexLine];
        int swapIndex = indexToSwapLine;
        while (indexToSwapLine < vector.length) {
            if (element < Math.abs(matrix[indexLine][indexToSwapLine])) {
                element = Math.abs(matrix[indexLine][indexToSwapLine]);
                swapIndex = indexToSwapLine;
            }
            indexToSwapLine++;
        }

        matrix[indexLine] = matrix[swapIndex];
        vector[indexLine] = vector[swapIndex];

        matrix[swapIndex] = tempLine;
        vector[swapIndex] = tempVector;
    }

    public static void goReverseMotion(double[][] matrix, double[] vector, double[] answer) {
        for (int i = 0; i < matrix.length; i++) {
            int index = matrix.length - 1 - i;
            answer[index] = vector[index];

            for (int j = index + 1; j < matrix.length; j++) {
                answer[index] -= answer[j] * matrix[index][j];
            }
        }
    }
}
