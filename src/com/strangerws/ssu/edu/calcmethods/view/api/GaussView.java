package com.strangerws.ssu.edu.calcmethods.view.api;

import com.strangerws.ssu.edu.calcmethods.model.GaussMatrix;

/**
 * Created by DobryninAM on 08.11.2016.
 */
public interface GaussView {

    void printGaussLine(GaussMatrix matrix, int matrixLineNumber);

    void printGaussWithVector(GaussMatrix matrix);

}
