package com.strangerws.ssu.edu.calcmethods.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by DobryninAM on 08.11.2016.
 */
public class ReadController {

    public double[][] readMatrix(String path){
        double[][] matrix;
        try (BufferedReader fileStream = new BufferedReader(new FileReader(path))) {
            int dimension = Integer.parseInt(fileStream.readLine());
            matrix = new double[dimension][dimension];

            for (int i = 0; i < dimension; i++) {
                String[] numbers = fileStream.readLine().split(" ");
                for (int j = 0; j < dimension; j++) {
                    Double x = 5.5;
                    matrix[i][j] = Double.parseDouble(numbers[j]);
                }
            }
        } catch (IOException exception) {
            System.err.println("ОШИБКА: файл матрицы отсутствует или повреждён!");
            matrix = new double[0][0];
        }
        return matrix;
    }

    public static double[] readVector(String path) {
        double[] vector;

        try (BufferedReader fileStream = new BufferedReader(new FileReader(path))) {
            int dimension = Integer.parseInt(fileStream.readLine());
            vector = new double[dimension];
            String[] numbers = fileStream.readLine().split(" ");

            for (int i = 0; i < dimension; i++) {
                vector[i] = Double.parseDouble(numbers[i]);
            }

        } catch (IOException exception) {
            System.err.println("ОШИБКА: файл вектора отсутствует или повреждён!");
            vector = new double[0];
        }
        return vector;
    }
}
