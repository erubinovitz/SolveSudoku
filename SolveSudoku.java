/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Evan
 */
import java.lang.Math;
import java.util.Scanner;
public class SolveSudoku {
    /*
   test
2 0 0  0 8 0  0 0 4 
0 7 6  0 0 0  0 0 0 
9 4 0  0 0 0  0 7 2 
    
0 0 0  7 0 0  2 0 0 
0 6 0  0 3 0  0 5 0 
0 9 0  5 0 0  0 3 0 
    
0 2 0  0 0 4  0 0 0  
0 0 0  0 0 0  1 0 6 
0 0 8  0 0 0  3 2 9 
    */
static int[][] board = new int[9][9];
static boolean printAllSolutions=false;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean testing = false;
        if (testing){
            board[0][0]=-5;
            board[0][1]=-5;
            board[0][2]=-2;
        }
        else{
            int a;
            for (int i=0; i<9; i++){
                for (int j=0; j<9; j++){
                    a=-1*sc.nextInt();
                    if ((a!=0)&&(!legalRow(i,Math.abs(a)-1)||!legalCol(j,Math.abs(a)-1)||!legalBox(((i/3)*3+j/3),Math.abs(a)-1))){
                        System.out.println("No solution. ");
                        System.exit(0);
                    }
                    else{
                    
                        board[i][j]=a;
                    }
                }
            }
        }
        System.out.println();
        backtrack(-1,0);
        System.out.println("No solution.");
    }
    public static boolean legalRow(int row, int item){
        item++;
        for (int i=0; i<9; i++){
            if (Math.abs(board[row][i])==item)return false;
          
        }
        return true;
    }
    public static boolean legalCol(int col, int item){
        item++;
        for (int i=0; i<9; i++){
            if (Math.abs(board[i][col])==item)return false;
        }
        return true;
    }
    public static boolean legalBox(int box, int item){
        item++;
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                if (Math.abs(board[i+(box/3)*3][j+3*(box%3)])==item)return false;
            }
        }
        return true;
    }
    
    
    
    
    public static boolean isASolution( int k){
        return k==9;
    }
    public static void processSolution(){
            for (int i=0; i<board.length; i++){
                for (int j=0; j<board[0].length; j++){
                    System.out.print(Math.abs(board[i][j])+" ");
                    if (j%3==2)
                        System.out.print(" ");
                }
                System.out.println();
                if (i%3==2&&i!=8)
                    System.out.println();
            }
            if (printAllSolutions)System.out.println();
    }
    public static void backtrack(int col, int row){
        int[][] candidates = new int[9][9];
        col++;           
        
         if (isASolution(col)){
            if (row==8&&col==9){
                processSolution();
                if (!printAllSolutions)
                    System.exit(0);
            }
            else {
               // processSolution();
                col=-1;
                row++;
                backtrack(col,row);
            }
            return;
        }
         if (board[row][col]<0){
            backtrack(col,row);
            return;
        }
        int nCandidates=0;
        nCandidates= constructCandidates(col,candidates,nCandidates,row); 
        
    
        for (int i=0; i<nCandidates; i++){
            board[row][col]=candidates[row][i];
           // System.out.println("board["+row+"]["+col+"]="+candidates[row][i]);
            backtrack(col,row);
            board[row][col]=0;
                
        }
       
    }
     public static void print(int[] arr){
        for (int i=0; i<arr.length; i++){
            System.out.print(arr[i]+" ");
        }
       System.out.println("\n");
    }
    public static int constructCandidates( int col, int[][] candidates, int nCandidates,int row){ // n = row we are constructing

        boolean inPerm[]= new boolean[9];


        for (int i=0; i<col; i++){
 
            if (board[row][i]!=0)
            inPerm[Math.abs(board[row][i])-1]=true; 
        }
        nCandidates=0;
        for (int i=0; i<9;i++){
          //  System.out.println("row is " + row +"  col is " + col + " i is " + i);
            if (!inPerm[i]&&legalRow(row,i)&&legalCol(col,i)&&legalBox(((row/3)*3+col/3),i)){
                candidates[row][nCandidates]=i+1;
                nCandidates++;
          
            }
           
        }

        return nCandidates;
    }
    

    
}
