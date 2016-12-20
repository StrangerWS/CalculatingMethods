package com.strangerws.ssu.edu.calcmethods.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DobryninAM on 08.11.2016.
 */
public class GaussMatrix extends Matrix {

    public GaussMatrix(String matrixPath, String vectorPath) {
        super(matrixPath, vectorPath);
        answer = new double[vector.length];
    }

    public GaussMatrix(double[][] matrix, double[] vector) {
        super(matrix, vector);
        answer = new double[vector.length];
    }

    @Override
    public void forwardStep() {
        int zeroCounter = 0;
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            int count = 1;
            while (matrix[i][i] == 0) {
                if (count >= matrix.length) {
                    break;
                }
                zeroCounter++;
                numbers.add(i);
                swapLines(i, i + count++);
            }
            vector[i] /= matrix[i][i];

            if (matrix[i][i] != 1) {
                divideByElement(i, matrix[i][i]);
            }

            for (int j = i + 1; j < matrix.length; j++) {
                double subtract = matrix[j][i] / matrix[i][i];
                if (matrix[i][j] - subtract != matrix[i][j]) {
                    subtractLines(i, j, subtract);
                    vector[j] -= vector[i];
                }
            }
        }
        System.out.println("  На главной диагонали " + zeroCounter + " нулей.");
        for (int i = 0; i < numbers.size(); i++) {
            System.out.println("\t\tНоль на [" + (numbers.get(i) + 1) + ", " + (numbers.get(i) + 1) + "]");
        }
        System.out.println();
    }

    public void divideByElement(int lineNumber, double value) {
        for (int i = 0; i < matrix[lineNumber].length; i++) {
            matrix[lineNumber][i] /= value;
        }
    }

    public void subtractLines(int firstLineNumber, int secondLineNumber, double subtractElem) {
        for (int i = 0; i < matrix[firstLineNumber].length; i++) {
            matrix[secondLineNumber][i] -= matrix[firstLineNumber][i] * subtractElem;
        }
    }

    public void swapLines(int indexLine, int indexToSwapLine) {
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

    @Override
    public void backwardStep() {
        for (int i = 0; i < matrix.length; i++) {
            int index = matrix.length - 1 - i;
            answer[index] = vector[index];

            for (int j = index + 1; j < matrix.length; j++) {
                answer[index] -= answer[j] * matrix[index][j];
            }
        }
    }

    public boolean swap(int index) {

        for (int i = index; i < matrix.length; i++) {
            if (matrix[i][index] != 0) {
                swapRows(index);
                return true;
            }
        }

        for (int i = index; i < matrix.length; i++) {
            if (matrix[index][i] != 0) {
                swapColumns(index);
                return true;
            }
        }

        return false;
    }

    public void swapRows(int index) {
        int swapIndex = index;
        double element = matrix[index][index];

        int iterator = index;
        while (iterator < vector.length) {
            if (element < Math.abs(matrix[iterator][index])) {
                element = Math.abs(matrix[iterator][index]);
                swapIndex = iterator;
            }
            iterator++;
        }

        if (swapIndex == matrix.length) {
            return;
        }

        double[] tmpRow = matrix[index];
        matrix[index] = matrix[swapIndex];
        matrix[swapIndex] = tmpRow;

        double tmp = vector[index];
        vector[index] = vector[swapIndex];
        vector[swapIndex] = tmp;
    }

    public void swapColumns(int index) {
        int swapIndex = index;
        double element = matrix[index][index];

        int iterator = index;
        while (iterator < matrix.length) {
            if (element < Math.abs(matrix[index][iterator])) {
                element = Math.abs(matrix[index][iterator]);
                swapIndex = iterator;
            }
            iterator++;
        }

        if (swapIndex == matrix.length) {
            System.out.println(element);
            return;
        }

        for (int i = 0; i < matrix.length; i++) {
            double tmp = matrix[i][index];
            matrix[i][index] = matrix[i][swapIndex];
            matrix[i][swapIndex] = tmp;
        }
    }
}
