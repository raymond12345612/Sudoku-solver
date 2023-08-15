public class SudokuSolver {
  // let the length array is 9
  private static final int GRID_SIZE = 9;
  
  public static void main(String[] args) {
    // put the number 1-9; number 0 is blank
    int[][] board = {
        {0, 0, 1, 8, 0, 0, 4, 0, 0},
        {4, 6, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 5, 0, 0, 0, 1, 6, 0},
        {0, 0, 0, 0, 3, 0, 0, 8, 7},
        {0, 0, 0, 0, 0, 0, 9, 0, 0},
        {3, 0, 0, 9, 1, 0, 0, 5, 0},
        {0, 7, 0, 2, 9, 0, 0, 0, 5},
        {0, 0, 3, 0, 0, 0, 0, 0, 0},
        {6, 0, 0, 0, 4, 0, 0, 0, 0} 
      };

    // print the original sudoku
    printBoard(board);
    // check the solution
    if (solveBoard(board)) {
      System.out.println("Solved successfully!");
    }
    else {
      System.out.println("Unsolvable board :(");
    }
    // print out the final answer
    printBoard(board);
    
  }
  
  // For the print board with | , - , and number
  private static void printBoard(int[][] board) {
    for (int row = 0; row < GRID_SIZE; row++) {
      if (row % 3 == 0 && row != 0) {
        System.out.println("-----------");
      }
      for (int column = 0; column < GRID_SIZE; column++) {
        if (column % 3 == 0 && column != 0) {
          System.out.print("|");
        }
        System.out.print(board[row][column]);
      }
      System.out.println();
    }
  }

// check for the number in the row
  private static boolean isNumberInRow(int[][] board, int number, int row) {
    for (int i = 0; i < GRID_SIZE; i++) {
      if (board[row][i] == number) {
        return true;
      }
    }
    return false;
  }
  // check for the number in the column
  private static boolean isNumberInColumn(int[][] board, int number, int column) {
    for (int i = 0; i < GRID_SIZE; i++) {
      if (board[i][column] == number) {
        return true;
      }
    }
    return false;
  }
  // check for the number in the 3X3 box
  private static boolean isNumberInBox(int[][] board, int number, int row, int column) {
    int localBoxRow = row - row % 3;
    int localBoxColumn = column - column % 3;
    
    for (int i = localBoxRow; i < localBoxRow + 3; i++) {
      for (int j = localBoxColumn; j < localBoxColumn + 3; j++) {
        if (board[i][j] == number) {
          return true;
        }
      }
    }
    return false;
  }
  // check for the placement is it valid
  private static boolean isValidPlacement(int[][] board, int number, int row, int column) {
    return !isNumberInRow(board, number, row) &&
        !isNumberInColumn(board, number, column) &&
        !isNumberInBox(board, number, row, column);
  }
  // check the solve board 
  private static boolean solveBoard(int[][] board) {
    for (int row = 0; row < GRID_SIZE; row++) {
      for (int column = 0; column < GRID_SIZE; column++) {
        if (board[row][column] == 0) {
          for (int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++) {
            if (isValidPlacement(board, numberToTry, row, column)) {
              board[row][column] = numberToTry;
              
              if (solveBoard(board)) {
                return true;
              }
              else {
                board[row][column] = 0;
              }
            }
          }
          return false;
        }
      }
    }
    return true;
  }
  
  
  
}
