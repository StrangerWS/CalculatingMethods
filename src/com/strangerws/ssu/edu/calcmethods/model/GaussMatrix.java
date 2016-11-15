package com.strangerws.ssu.edu.calcmethods.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DobryninAM on 08.11.2016.
 */
public class GaussMatrix extends Matrix {

    public GaussMatrix(String matrixPath, String vectorPath){
        super(matrixPath, vectorPath);
        answer = new double[vector.length];
    }

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

    public void subtractLines(int firstLineNumber, int secondLineNumber, double substractElem) {
        for (int i = 0; i < matrix[firstLineNumber].length; i++) {
            matrix[secondLineNumber][i] -= matrix[firstLineNumber][i] * substractElem;
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

//        double[] tempLine = matrix[indexLine];
//        double tempVector = vector[indexLine];
//
//        matrix[indexLine] = matrix[indexToSwapLine];
//        vector[indexLine] = vector[indexToSwapLine];
//
//        matrix[indexToSwapLine] = tempLine;
//        vector[indexToSwapLine] = tempVector;
    }

    public void backwardStep() {
        for (int i = 0; i < matrix.length; i++) {
            int index = matrix.length - 1 - i;
            answer[index] = vector[index];

            for (int j = index + 1; j < matrix.length; j++) {
                answer[index] -= answer[j] * matrix[index][j];
            }
        }
    }
}
