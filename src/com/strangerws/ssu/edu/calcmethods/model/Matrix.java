package com.strangerws.ssu.edu.calcmethods.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by DobryninAM on 15.11.2016.
 */
public abstract class Matrix {

    protected double[][] matrix;
    protected double[] vector;
    double[] answer;

    public double[] getAnswer() {
        return answer;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public double[] getVector() {
        return vector;
    }

    public Matrix(String matrixPath, String vectorPath) {
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

    public abstract void forwardStep();
    public abstract void backwardStep();

}
