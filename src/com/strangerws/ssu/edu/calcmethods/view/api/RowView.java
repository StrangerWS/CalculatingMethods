package com.strangerws.ssu.edu.calcmethods.view.api;

import com.strangerws.ssu.edu.calcmethods.model.Row;
import com.strangerws.ssu.edu.calcmethods.view.api.abstraction.View;

import java.util.Scanner;

/**
 * Created by DobryninAM on 08.11.2016.
 */
public interface RowView extends View {

    void printRow(Row row, Scanner scanner);
}
