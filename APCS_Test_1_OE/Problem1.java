import java.util.*;

public class Problem1 { 

   public static void main(String[] args) { 
      String arg = args[0];
      new Problem1().start(arg);
   }

   public void start(String arg) {

      String[] input = arg.split(" ");

      switch (input[0]) {
         case "reverseArray":
            print1d(reverseArray(extract1d(input)));
            break;

         case "reverseAllRows":
            print2d(reverseAllRows(extract2d(input)));
            break;

         case "reverseMatrix":
            print2d(reverseMatrix(extract2d(input)));
            break;

         default:
            System.out.println("Incorrect argument");
            break;

      }

   }

   // SOLUTION BEGINS ---------------------------------------

   public int[] reverseArray(int[] arr) {
   	int[] changer = new int[arr.length];
   	for ( int i = arr.length - 1; i >= 0; i-- )
   		changer[arr.length - 1 - i] = arr[i];
   	return changer;
   }

   public int[][] reverseAllRows(int[][] mat) {
   	int[][] changer = new int[mat.length][mat[0].length];
   	for (int i = 0; i < mat.length; i++)
   		changer[i] = reverseArray(mat[i]);
   	return changer;
   }

   public int[][] reverseMatrix(int[][] mat) {
   	int[][] changer = new int[mat.length][mat[0].length];
   	for (int i = mat.length - 1; i >= 0; i--)
   		changer[mat.length - 1 - i] = mat[i];
   	return reverseAllRows(changer);
   }

   // SOLUTION ENDS ---------------------------------------

   public int[] extract1d(String[] input) {
      int[] arr = new int[Integer.parseInt(input[1])];
      for (int i = 0; i < arr.length; i++) {
         arr[i] = Integer.parseInt(input[i + 2]);
      }
      return arr;
   }

   public int[][] extract2d(String[] input) {
      int rows = Integer.parseInt(input[1]);
      int cols = Integer.parseInt(input[2]);
      int[][] matrix = new int[rows][cols];

      for (int i = 0; i < rows; i++) {
         for (int j = 0; j < cols; j++)
            matrix[i][j] = Integer.parseInt(input[i*cols + j + 3]);
      }

      return matrix;
   }

   public void print1d(int[] arr) {
   	for (int i = 0; i < arr.length; i++)
   		System.out.print(arr[i] + " ");
   	System.out.println("\n---------------------");
   }

   public void print2d(int[][] arr) {
   	for (int i = 0; i < arr.length; i++) {
   		for (int j = 0; j < arr[0].length; j++)
   			System.out.print(arr[i][j] + " ");
   		System.out.println();
   	}
   	System.out.println("---------------------");
   }

}