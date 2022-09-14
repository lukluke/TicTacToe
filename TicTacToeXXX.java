/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author luke
 */
public class TicTacToeXXX implements TicTacToe {
    static String [][] matrix;
    static int numPlayer, userstep, user = 0;
    static String status = "";
    static Player player = Player.X;
    boolean error = false;
    
      /**
     * This method is used to initialize the size of the grid. When it is called, 
the grid 
     * is re-initialized and all marks currently on the grid are erased. 
     * 
     * @param size                          the number of spaces in a row (e.g. 3 
means 3x3)
     * @throws IllegalArgumentException     if the input size is less than 3
     */
    
    @Override
    public void init(int size) throws IllegalArgumentException { 
      if (size <= 2)
         throw new IllegalArgumentException();

       matrix = new String[size][size];
       
        int count = 1; 
        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
              matrix[i][j] = Integer.toString(count);
              count ++;
            }
        }
    }
    
      /**
     * This method returns true if the game can continue. It returns false if a 
winner has
     * been determined or all grid positions have been marked.
     */

    @Override
    public boolean hasNext() {
        if (!horizontalBingo()) {
            status = "win";
            return false;
        } else if (!straightBingo()) {
            status = "win";
            return false;
        } else if (!slash()) {
            status = "win";
            return false;
        } else if (!backslash()) {
            status = "win";
            return false;
        } else if (!isFull()) {
            return false;
        }
        return true;
   }
    
    
    private boolean horizontalBingo() {
        int lineX = 0;
        int lineO = 0;
        for(int i=0; i< matrix.length; i++) {
            for(int j=0; j<matrix.length; j++) { 
                 if (matrix[i][j] == Player.X.toString()) {
                     lineX ++;
                 }
                 if (matrix[i][j] == Player.O.toString()) {
                     lineO ++;
                 }
            }
            if (lineX == matrix.length || lineO == matrix.length) {
               return false;
            }
            lineX = 0;
            lineO = 0;
         }
         return true;
    }
    
    private boolean backslash() {   
        int lineX = 0;
        int lineO = 0;
        int count = 0;
        for(int i= (matrix.length - 1); i >= 0; i--) {
             if (matrix[count][i] == Player.X.toString()) {
                 lineX ++;
             }
             if (matrix[count][i] == Player.O.toString()) {
                 lineO ++;
             }
             count ++;
                
            if (lineX == matrix.length || lineO == matrix.length) {
               return false;
            }
        }
        return true;
    }
     

    private boolean slash() {
        int lineX = 0;
        int lineO = 0;
        for(int i=0; i< matrix.length; i++) {
             if (matrix[i][i] == Player.X.toString()) {
                 lineX ++;
             }
             
             if (matrix[i][i] == Player.O.toString()) {
                 lineO ++;
             }
                
            if (lineX == matrix.length || lineO == matrix.length) {
               return false;
            }
        }
         return true;
    }
    
    private boolean straightBingo() {
        int lineX = 0;
        int lineO = 0;
        for(int i=0; i< matrix.length; i++) {
            for (int j=0; j<matrix.length; j++) { 
                if (matrix[j][i] == Player.X.toString()) {
                    lineX ++;
                }

                 if (matrix[j][i] == Player.O.toString()) {
                     lineO ++;
                 }
            }
            if (lineX == matrix.length || lineO == matrix.length) {
               return false;
            }
            lineX = 0;
            lineO = 0;
         }
         return true;
    }
    
    /**
     * This method returns true when martrix is full. It returns false when the
martrix is full
     * is still in progress or there is no winner.
     */

    
    private boolean isFull() {
        if (userstep ==  (matrix.length * matrix.length)) {
             return false;
        }
        
        return true;
    }
    
     /**
     * This method returns the player of the current turn. 
     */

    @Override
    public Player getTurn() {
        if(!error) {
            user = numPlayer%2+1;
            numPlayer ++;
            if (user  == 1) {
                player = Player.X;
                return Player.X;
            } else {
                 player = Player.O;
                return Player.O;
            }
            
        } 
        return player;

    }
    
    /**
     * This method puts the current player mark in the specified position.
     * 
     * @param pos                           a number indicating a particular grid 
position
     * @throws IllegalArgumentException     if the input position cannot be marked
     */

    @Override
    public void mark(int pos) throws IllegalArgumentException {
        error = false;
        boolean passed = false;

         for(int i=0; i< matrix.length; i++) {
            for(int j=0; j<matrix.length; j++) { 
                 if (matrix[i][j] != Player.X.toString() && matrix[i][j] != Player.O.toString()) {
                     if (Integer.parseInt(matrix[i][j]) == pos) {
                       matrix[i][j]= player.toString();
                       passed = true;
                       userstep ++;
                     }
                 }
            }
         }
        
         if (!passed)
             error = true;
         
         if (error)
              throw new IllegalArgumentException();

    }

    @Override
    public void print() { 
        for(int i=0; i< matrix.length; i++) {
          // inner loop for column
          for(int j=0; j<matrix[0].length; j++) {     
              if (String.valueOf(matrix[i][j]).length() == 1) {
                  System.out.printf("%s%2s",  matrix[i][j], "|");  
              } else {
                   System.out.print(matrix[i][j] + "|");
              }
          }
          System.out.println(); 
        }
    }
    
     /**
     * This method returns true if the game has a winner. It returns false when the
game
     * is still in progress or there is no winner.
     */

    @Override
    public boolean hasWinner() {
        if (status == "win") {
            return true;
        }
        return false;
    }
    
     /**
     * This method returns the winner of the game.
     * 
     * @return                              the winner of the game
     * @throws IllegalStateException        if the game is in progress or has no 
winner
     */

    @Override
    public Player getWinner() throws IllegalStateException {
       if (status == "win") {
           return player;
       }
       throw new IllegalStateException();
    }
    
}
