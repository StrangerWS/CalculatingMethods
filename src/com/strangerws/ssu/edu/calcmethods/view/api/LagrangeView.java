package com.strangerws.ssu.edu.calcmethods.view.api;

import com.strangerws.ssu.edu.calcmethods.model.Table;
import com.strangerws.ssu.edu.calcmethods.view.api.abstraction.View;

import java.util.Scanner;

/**
 * Created by DobryninAM on 08.11.2016.
 */
public interface LagrangeView extends View {

    void printLagrange(Table table, Table table1, Scanner scanner);
}
