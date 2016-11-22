package com.strangerws.ssu.edu.calcmethods.model;

import com.strangerws.ssu.edu.calcmethods.Main;

/**
 * Created by DobryninAM on 22.11.2016.
 */
public class KoshiTask {

    public static void doKoshi(){

        double leftPart = 1;
        double rightPart = 10;
        int countPartitions = 10;
        double one = rightPart - leftPart;
        one /= countPartitions - 1;
        double[] xArray = new double[countPartitions];
        xArray[0] = leftPart;

        for (int i = 1; i < countPartitions; i++) {
            xArray[i] = xArray[i - 1] + one;
        }

        System.out.println("Значения x:");
        printArray(xArray);
        System.out.println();

        double[] yPreciseArray = new double[xArray.length];

        for (int i = 0; i < yPreciseArray.length; i++) {
            yPreciseArray[i] = getPreciseFunc(xArray[i]);
        }

        double[] yArray = new double[xArray.length];
        yArray[0] = Main.VARIANT + 2;

        for (int i = 1; i < yArray.length; i++) {
            double h = xArray[i] - xArray[i - 1];
            yArray[i] = getEulerFunction(xArray[i - 1], yArray[i - 1], h);
            yArray[i] = getModifiedEulerFunction(xArray[i - 1], xArray[i], yArray[i - 1], h);
            yArray[i] = getPredictorCorrector(xArray[i - 1], xArray[i], yArray[i - 1], h);
        }

        System.out.println("Точные значения y:");
        printArray(yPreciseArray);
        System.out.println();

        System.out.println("Значения y по методу Эйлера:");
        printArray(yArray);
        System.out.println();

        System.out.println("Погрешность решения");
        for (int i = 0; i < yArray.length; i++) {
            System.out.print(String.format("%(.2f\t ", (Math.abs(yArray[i] - yPreciseArray[i]))));
        }
        System.out.println();
    }

    public static double getPreciseFunc(double x) {
        return Main.VARIANT * Math.pow(x, 3.0) + Math.pow(x, 2.0) + x;
    }

    public static double getEulerFunction(double x, double y, double h) {
        return y + h * getFunction(x, y);
    }

    public static double getModifiedEulerFunction(double x1, double x2, double y, double h) {
        double x = (x1 + x2) / 2;
        double yNew = y + (h / 2) * getFunction(x1, y);
        return y + h * getFunction(x, yNew);
    }

    public static double getPredictorCorrector(double x1, double x2, double y1, double h) {
        //double y2 = getEulerFunction(x1, y1, h);              //Точно
        double y2 = getModifiedEulerFunction(x1, x2, y1, h);    //Очень точно
        return y1 + h * ((getFunction(x1, y1) + getFunction(x2, y2)) / 2);
    }

    public static double getFunction(double x, double y) {
        return Main.VARIANT * Math.pow(x, 3.0) + (3 * Main.VARIANT + 1) * Math.pow(x, 2.0) + 3 * x + 1 - y;
    }

    public static void printArray(double[] array) {
        for (double value : array) {
            System.out.print(String.format("%(.2f\t ", value));
        }
        System.out.println();
    }


}
