package com.strangerws.ssu.edu.calcmethods.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DobryninAM on 08.11.2016.
 */
public class GaussMatrix {

    private double[][] matrix;
    private double[] vector;

    public double[][] getMatrix() {
        return matrix;
    }

    public double[] getVector() {
        return vector;
    }

    public GaussMatrix() {
    }

    public GaussMatrix(String matrixPath, String vectorPath) {
        try (BufferedReader fileStream = new BufferedReader(new FileReader(matrixPath))) {
            int dimension = Integer.parseInt(fileStream.readLine());
            matrix = new double[dimension][dimension];

            for (int i = 0; i < dimension; i++) {
                String[] numbers = fileStream.readLine().split(" ");
                for (int j = 0; j < dimension; j++) {
                    Double x = 5.5;
                    matrix[i][j] = Double.parseDouble(numbers[j]);
                }
            }
        } catch (IOException exception1) {
            System.err.println("ОШИБКА: файл матрицы отсутствует или повреждён!");
            matrix = new double[0][0];
        }

        try (BufferedReader fileStream = new BufferedReader(new FileReader(vectorPath))) {
            int dimension = Integer.parseInt(fileStream.readLine());
            vector = new double[dimension];
            String[] numbers = fileStream.readLine().split(" ");

            for (int i = 0; i < dimension; i++) {
                vector[i] = Double.parseDouble(numbers[i]);
            }

        } catch (IOException exception2) {
            System.err.println("ОШИБКА: файл вектора отсутствует или повреждён!");
            vector = new double[0];
        }
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
        System.out.println("  На главной диагонали " + zeroCounter + " нулей.\n\tНули на");
        for (int i = 0; i < numbers.size(); i++) {
            System.out.println("\t\tномере диагонали [" + (numbers.get(i) + 1) + ", " + (numbers.get(i) + 1) + "]");
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
    }

    public void backwardStep(double[] answer) {
        for (int i = 0; i < matrix.length; i++) {
            int index = matrix.length - 1 - i;
            answer[index] = vector[index];

            for (int j = index + 1; j < matrix.length; j++) {
                answer[index] -= answer[j] * matrix[index][j];
            }
        }
    }
}
