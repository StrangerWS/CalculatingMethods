package com.strangerws.ssu.edu.calcmethods;

import com.strangerws.ssu.edu.calcmethods.model.Row;
import com.strangerws.ssu.edu.calcmethods.model.Table;

import java.util.Scanner;

/**
 * Created by DobryninAM on 25.10.2016.
 */
public class Main {
    public static int VARIANT = 12;
    public static int COUNT = 10;
    public static double ACCURACY = 0.000001;

    public static void main(String[] args) {
        Row row = new Row(-0.05, 0.05);
        Table table = new Table();
        Table table1 = new Table();
        System.out.println("Создание ряда");
        row.makeRow();
        row.print();
        System.out.println("\nРасширение ряда");
        row.extendRow();
        row.print();
        System.out.println("\nМетод Лагранжа");
        System.out.println("Введите количество элементов: ");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        table.fill(n);
        table1.fillLagrange(table);
        table.print();
        System.out.println();
        table1.print();
    }
}