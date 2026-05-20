package main.java.core;

import java.util.Scanner;

public class MatrixMultiplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of rows in matrix A: ");
        int rowsA = scanner.nextInt();
        System.out.print("Enter number of columns in matrix A: ");
        int colsA = scanner.nextInt();

        System.out.print("Enter number of rows in matrix B: ");
        int rowsB = scanner.nextInt();
        System.out.print("Enter number of columns in matrix B: ");
        int colsB = scanner.nextInt();

        if (colsA != rowsB) {
            System.out.println("Matrix multiplication not possible: columns in A must equal rows in B.");
            scanner.close();
            return;
        }

        int[][] A = new int[rowsA][colsA];
        int[][] B = new int[rowsB][colsB];

        System.out.println("Enter values for matrix A:");
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsA; j++) {
                System.out.printf("A[%d][%d]: ", i, j);
                A[i][j] = scanner.nextInt();
            }
        }

        System.out.println("Enter values for matrix B:");
        for (int i = 0; i < rowsB; i++) {
            for (int j = 0; j < colsB; j++) {
                System.out.printf("B[%d][%d]: ", i, j);
                B[i][j] = scanner.nextInt();
            }
        }

        int[][] result = new int[rowsA][colsB];
        coreLogic(A, B, rowsA, colsA, colsB, result);

        System.out.println("Result Matrix:");
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }

        scanner.close();
    }


    /**
     * Multiplies matrix A by matrix B and stores the result in the provided result matrix.
     *
     * @param A the left-hand matrix with dimensions rowsA x colsA
     * @param B the right-hand matrix with dimensions colsA x colsB
     * @param rowsA the number of rows in matrix A
     * @param colsA the number of columns in matrix A (and rows in matrix B)
     * @param colsB the number of columns in matrix B
     * @param result the output matrix with dimensions rowsA x colsB
     */
    private static void coreLogic(int[][] A, int[][] B, int rowsA, int colsA, int colsB, int[][] result) {
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }
    }
}