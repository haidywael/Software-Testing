/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package matrixcal;

import java.util.Scanner;
/**
 *
 * @author waela
 */
public class MatrixCal {
    //static double [][]Inverse;
    static Scanner scan = new Scanner(System.in);
    
    public static void setting(char choice,Boolean t){
        double result[][];
        if(choice == '9'){
            System.exit(0);
        }
        else if (choice == '7'){
            System.out.println("Enter square size");
            int size = scan.nextInt();
            result = identity(size);
            display(result,result.length,result.length);
            return;
        }
        else{
            System.out.println("Enter no. of rows");
            int r1 = scan.nextInt();
            System.out.println("Enter no. of columns");
            int c1 = scan.nextInt();
            System.out.println("Enter First Matrix");
            double A[][] = new double[r1][c1];
            for(int i=0; i<r1; i++)
                for(int j=0; j<c1; j++)
                    A[i][j] = scan.nextDouble();
            //int r2 = r1, c2 = c1;
            if(choice == '1' || choice == '2' || choice == '3' ){
                int r2,c2;
                System.out.println("Enter no. of rows");
                r2 = scan.nextInt();
                System.out.println("Enter no. of columns");
                c2 = scan.nextInt();
                System.out.println("Enter Second Matrix");
                double B[][] = new double[r2][c2];
                for(int i=0; i<r2; i++)
                    for(int j=0; j<c2; j++)
                        B[i][j] = scan.nextDouble();

                if (choice == '1')
                    result = addMatrix(A,B,r1,c1,r2,c2);
                else if(choice == '2')
                    result = subtractMatrix(A,B,r1,c1,r2,c2);
                else 
                    result = multiplyMatrix(A,B,r1,c1,r2,c2);
                display(result,r1,c2);
            }
            else {
                // inverse
                if (choice == '8'){
                    boolean flag = false;
                    double [][] in = new double[r1][c1];
                    if (r1 != c1)
                        System.out.println("can't do the operation, Not square Matrix ");
                    else{
                        flag = inverse(A,in,r1);
                        display(in,r1,c1);
                    }
                }
                // scalar Multiplication
                else if (choice == '4'){
                    int x;
                    System.out.println("Enter scalar no.");
                    x = scan.nextInt();
                    result = scalarmultiplyMatrix(A,x,r1,c1);
                    display(result,r1,c1);
                }
                else if (choice == '5'){
                    result = transposeMatrix(A,r1,c1);
                    display(result,r1,c1);
                }
                else if(choice == '6'){
                    boolean flag = isSquare(r1,c1);
                    System.out.println(flag);
                }


            }
        }
    }
    
    public static double [][] addMatrixDriver(){
        double result[][];
        double A[][] = { {2, 1, 4},{0, 1, 1}};
        double B[][] = { {6, 3, -1},{1, 1, 0}};
        return result=addMatrix(A,B,2,3,2,3);
    }
    
    public static double[][] addMatrix(double[][] matrix1, double[][] matrix2, int rows1, int columns1, int rows2, int columns2){
        double sumMatrix[][] = new double[rows1][columns1];
        if(rows1 == rows2 && columns1 == columns2){
            for (int i=0; i<rows1; i++){
                for (int j=0; j<columns1; j++){
                    sumMatrix[i][j] = matrix1[i][j] + matrix2[i][j];
                }
            }
        } else System.out.println("Rows and columns are not equal so addition can not be performed");

        return sumMatrix;
    }
    
    public static double [][] subtractMatrixDriver(){
        double result[][];
        double A[][] = { {2, 1, 4},{0, 1, 1}};
        double B[][] = { {6, 3, -1},{1, 1, 0}};
        return result=subtractMatrix(A,B,2,3,2,3);
    }

    public static double[][] subtractMatrix(double[][] matrix1, double[][] matrix2, int rows1, int columns1, int rows2, int columns2){
        double subMatrix[][] = new double[rows1][columns1];
        if(rows1 == rows2 && columns1 == columns2){
            for (int i=0; i<rows1; i++){
                for (int j=0; j<columns1; j++){
                    subMatrix[i][j] = matrix1[i][j] - matrix2[i][j];
                }
            }
        } else System.out.println("Rows and columns are not equal so subtraction can not be performed");

        return subMatrix;
    }
    
    public static double[][] multiplyMatrixDriver()
    {
        double result[][];
        double A[][] = { {2, 1},{0, 1}};
        double B[][] = { {6, 3},{1, 1}};
        return result = multiplyMatrix(A,B,2,2,2,2);
    }

    public static double[][] multiplyMatrix(double[][] matrix1, double[][] matrix2, int rows1, int columns1, int rows2, int columns2){
        double mulMatrix[][] = new double[rows1][columns2];
        if(columns1 == rows2){
            for (int i=0; i<rows1; i++){
                for (int j=0; j<columns2; j++){
                    for (int k=0; k<rows2; k++)
                    mulMatrix[i][j] = mulMatrix[i][j] + matrix1[i][k] * matrix2[k][j];
                }
            }
        } else System.out.println("Multiplication can not be performed");

        return mulMatrix;
    }

    public static double[][] scalarmultiplyMatrix(double[][] matrix1, int number, int rows1, int columns1){
        double mulMatrix[][] = new double[rows1][columns1];
        for (int i=0; i<rows1; i++){
            for (int j=0; j<columns1; j++){
                mulMatrix[i][j] = number * matrix1[i][j];
            }
        }
        return mulMatrix;
    }
    
    public static double[][] transposeMatrixDriver(){
        double result [][];
        double A[][] = { {2, 1, 4},{0, 1, 1}};
        return result=transposeMatrix(A,2,3);        
    }

    public static double[][] transposeMatrix(double[][] matrix1, int rows1, int columns1){
        double transposeMatrix[][] = new double[rows1][columns1];
        for (int i=0; i<columns1; i++){
            for (int j=0; j<rows1; j++){
                transposeMatrix[i][j] = matrix1[j][i];
            }
        }
        return transposeMatrix;
    }
    
    public static boolean isSquare(int r, int c){
	if(r == c)
		return true;
	else 
		return false;
    }
    
    static double[][] identity(int squareSize){
	double idenMat[][] = new double[squareSize][squareSize];
	
	for(int i = 0; i < squareSize; i++){
		for(int j= 0; j< squareSize; j++){
			if(i == j)
                            idenMat[i][j] = 1; 
                        else
                            idenMat[i][j] = 0;
		}
	}
	return idenMat;
    }
    
    // Function to get cofactor of A[p][q] in temp[][]. n is current
    // dimension of A[][]
    static void getCofactor(double A[][], double temp[][], int p, int q, int n)
    {
        int i = 0, j = 0;

        // Looping for each element of the matrix
        for (int row = 0; row < n; row++)
        {
            for (int col = 0; col < n; col++)
            {
                // Copying into temporary matrix only those element
                // which are not in given row and column
                if (row != p && col != q)
                {
                    temp[i][j++] = A[row][col];

                    // Row is filled, so increase row index and
                    // reset col index
                    if (j == n - 1)
                    {
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }
    
    static double determinantStub(){

        double A[][] = {{2, 1},{0, 1}};
        double det = 2;
        return det;
    }
    /* Recursive function for finding determinant of matrix.
    n is current dimension of A[][]. */
    static double determinant(double A[][], int n, int squareSize)
    {
        int D = 0; // Initialize result

        // Base case : if matrix contains single element
        if (n == 1)
            return A[0][0];

        double [][]temp = new double[squareSize][squareSize]; // To store cofactors

        int sign = 1; // To store sign multiplier

        // Iterate for each element of first row
        for (int f = 0; f < n; f++)
        {
            // Getting Cofactor of A[0][f]
            getCofactor(A, temp, 0, f, n);
            D += sign * A[0][f] * determinant(temp, n - 1, squareSize);

            // terms are to be added with alternate sign
            sign = -sign;
        }

        return D;
    }
    
    static void adjointStub(double [][] adj){
        
        double A[][] = {{2, 1},{0, 1}};
        double result[][]={{1,-1},{0,2}};
        for (int i =0;i<2;i++){
           for (int j=0;j<2;j++){
               adj[i][j]=result[i][j];
           }
        }
    }
    // Function to get adjoint of A[N][N] in adj[N][N].
    static void adjoint(double A[][],double [][]adj, int squareSize)
    {
        if (squareSize == 1)
        {
            adj[0][0] = 1;
            return;
        }

        // temp is used to store cofactors of A[][]
        int sign = 1;
        double [][]temp = new double[squareSize][squareSize];

        for (int i = 0; i < squareSize; i++)
        {
            for (int j = 0; j < squareSize; j++)
            {
                // Get cofactor of A[i][j]
                getCofactor(A, temp, i, j, squareSize);

                // sign of adj[j][i] positive if sum of row
                // and column indexes is even.
                sign = ((i + j) % 2 == 0)? 1: -1;

                // Interchanging rows and columns to get the
                // transpose of the cofactor matrix
                adj[j][i] = (sign)*(determinant(temp, squareSize-1, squareSize));
            }
        }
    }
    
    // Function to calculate and store inverse, returns false if
    // matrix is singular
    static boolean inverse(double A[][], double[][] inverse, int squareSize)
    {
        // Find determinant of A[][]
        double det = determinant(A, squareSize, squareSize);
        if (det == 0)
        {
            System.out.print("Singular matrix, can't find its inverse");
            return false;
        }

        // Find adjoint
        double [][]adj = new double[squareSize][squareSize];
        adjoint(A, adj, squareSize);

        // Find Inverse using formula "inverse(A) = adj(A)/det(A)"
        for (int i = 0; i < squareSize; i++)
            for (int j = 0; j < squareSize; j++)
                inverse[i][j] = adj[i][j]/(double)det;

        return true;
    }
    
    static void display(double [][]A, int r, int c)
    {
        for (int i = 0; i < r; i++)
        {
            for (int j = 0; j < c; j++)
                System.out.print(A[i][j] + "\t");
            System.out.println("\n");
        }
        
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Enter the operation");
        Boolean t = new Boolean(true);
        while(t){
            System.out.println("\n -------------------------------- ");
            System.out.println("|1:              Addition               |\n --------------------------------------- \n|2:             Subtraction             |\n --------------------------------------- \n|3:            Multiplication           |\n --------------------------------------- \n|4:        Scalar Multiplication        |\n --------------------------------------- \n|5:              Transpose              |\n --------------------------------------- \n|6:            Square Or Not            |\n --------------------------------------- \n|7:          Make Identity Matrix       |\n --------------------------------------- \n|8:            Get The Inverse          |\n --------------------------------------- \n|9:                  Exit               |\n --------------------------------------- \n");

            System.out.println(" -------------------------------- \n\n");
            char c = MatrixCal.scan.next().charAt(0);
            setting(c,t);
        }
        //double A[][];
//        double c[][] = identity(3);
//        display(c,3,3);            
//            double A[][] = { { 1, 1, 1 },
//                      { 2, 2, 2 },
//                      { 3, 3, 3 },
//                      { 4, 4, 4 } };
//            double B[][] = { { 1, 1, 1, 1 },
//                      { 2, 2, 2, 2 },
//                      { 3, 3, 3, 3 } };
//
//            double C[][] = multiplyMatrix(A,B,4,3,3,4);
//            display(C,4,4);

            


    }
  
}
