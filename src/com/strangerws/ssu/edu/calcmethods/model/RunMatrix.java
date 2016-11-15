package com.strangerws.ssu.edu.calcmethods.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by DobryninAM on 15.11.2016.
 */
public class RunMatrix extends Matrix {

    private double[] arrayP;
    private double[] arrayQ;

    public double[] getArrayP() {
        return arrayP;
    }

    public double[] getArrayQ() {
        return arrayQ;
    }

    public RunMatrix(String matrixPath, String vectorPath){
        super(matrixPath, vectorPath);
        arrayP = new double[matrix.length + 1];
        arrayQ = new double[matrix.length + 1];
        answer = new double[vector.length];
    }

    public void forwardStep() {
        arrayP[1] = matrix[0][1] / -matrix[0][0];
        arrayQ[1] = -vector[0] / -matrix[0][0];

        for (int i = 1; i < vector.length; i++) {
            if (i != vector.length - 1) {
                arrayP[i + 1] = matrix[i][i + 1] / (-matrix[i][i] - matrix[i][i - 1] * arrayP[i]);
            } else {
                arrayP[i + 1] = 0;
            }

            arrayQ[i + 1] = (matrix[i][i - 1] * arrayQ[i] - vector[i]) / (-matrix[i][i] - matrix[i][i - 1] * arrayP[i]);
        }
    }

    public void backwardStep() {
        answer[answer.length - 1] = arrayQ[answer.length];
        for (int i = answer.length - 2; i >= 0; i--) {
            answer[i] = arrayP[i + 1] * answer[i + 1] + arrayQ[i + 1];
        }
    }
}
