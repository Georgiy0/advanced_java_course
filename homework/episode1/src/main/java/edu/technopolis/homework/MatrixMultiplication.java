package edu.technopolis.homework;
/**
 * Created by kubri on 10/20/2016.
 */
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MatrixMultiplication {
    public static void main(String... args) {
        StringBuilder builder = new StringBuilder(1000);
        for(int i=0; i<args.length; i++)
        {
            builder.append(" ");
            builder.append(args[i]);
        }

        String input = builder.toString();
        Scanner sc = new Scanner(input);
        int row;
        int col;
        try {
            row = sc.nextInt();
            col = sc.nextInt();
        }
        catch (NoSuchElementException e){
            System.out.println("Not enough arguments were provided for matrix sizes. Provide a correct input pleas.");
            return;
        }
        Matrix a = new Matrix(col, row);
        try {
            row = sc.nextInt();
            col = sc.nextInt();
        }
        catch (NoSuchElementException e){
            System.out.println("Not enough arguments were provided for matrix sizes. Provide a correct input pleas.");
            return;
        }
        Matrix b = new Matrix(col, row);
        try {
            a.init(sc);
            b.init(sc);
        }
        catch (NoSuchElementException e){
            System.out.println("Not enough arguments were provided for matrix elements. Provide a correct input pleas.");
            return;
        }
        Matrix c = Matrix.multiplication(a, b);
        if(c!=null){
            System.out.println("C = A*B =");
            c.print();
        }
        else
            System.out.println("Invalid sizes of matrix A and B");
    }
    public static class Matrix{
        private int matrix[][];
        private int col;
        private int row;

        public void setMatrix(int col, int row, int i) {
            this.matrix[col][row] = i;
        }

        public int getMatrix(int col, int row) {
            return this.matrix[col][row];
        }

        public int getCol() {
            return col;
        }

        public int getRow() {
            return row;
        }

        Matrix(int col, int row){
            this.col = col;
            this.row = row;
            matrix = new int[col][row];
        }

        public void init(Scanner sc){
            for(int i=0; i<row; i++){
                for(int j=0; j<col; j++)
                    matrix[j][i]=sc.nextInt();
            }
        }

        public void print(){
            for(int i=0; i<row; i++){
                for(int j=0; j<col; j++)
                    System.out.print(matrix[j][i]+"\t");
                System.out.println();
            }
        }

        public static Matrix multiplication(Matrix a, Matrix b){
            if(a.getCol() != b.getRow())
                return null;
            Matrix c = new Matrix(b.getCol(), a.getRow());
            for(int i=0; i<a.getRow(); i++)
                for(int j=0; j<b.getCol(); j++){
                    int n=0;
                    for(int k=0; k<a.getCol(); k++)
                        n+= a.getMatrix(k, i)*b.getMatrix(j, k);
                    c.setMatrix(j, i, n);
                }
            return c;
        }
    }
}