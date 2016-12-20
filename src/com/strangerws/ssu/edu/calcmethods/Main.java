package com.strangerws.ssu.edu.calcmethods;

import com.strangerws.ssu.edu.calcmethods.model.BoundaryValue;
import com.strangerws.ssu.edu.calcmethods.model.CauchyRow;
import com.strangerws.ssu.edu.calcmethods.model.Integral;
import com.strangerws.ssu.edu.calcmethods.view.impl.MethodView;

import java.util.Scanner;

/**
 * Created by DobryninAM on 25.10.2016.
 */
public class Main {
    public static int VARIANT = 12;
    public static int COUNT = 10;
    public static double ACCURACY = 0.000001;

    public static void main(String[] args) {
        MethodView view = new MethodView();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Краевая задача:");
        BoundaryValue value = new BoundaryValue();
        value.print();
        System.out.println("\n\n\n\n\nИнтегралы:");
        Integral integral = new Integral();
        integral.print();
    }
}