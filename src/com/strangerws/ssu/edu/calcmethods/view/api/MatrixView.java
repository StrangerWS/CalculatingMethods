package com.strangerws.ssu.edu.calcmethods.view.api;

import com.strangerws.ssu.edu.calcmethods.model.Matrix;
import com.strangerws.ssu.edu.calcmethods.model.RunMatrix;

/**
 * Created by DobryninAM on 08.11.2016.
 */
public interface MatrixView {

    void printMatrixLine(double[] line);

    void printMatrixWithVector(Matrix matrix);

    void printMatrixAnswer(Matrix matrix);

    void printArrayQandP(RunMatrix matrix);
}
