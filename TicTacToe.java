import java.util.Scanner;

/**
 * @author Sabeel Mansuri
 * @author Hannah Zhou
 * @author Hemanth Battu
 */
class TicTacToe {
    Scanner input;      // IO
    String winner;      // Winner of game OR ""
    String player;      // Active player; initialized to X
    String[] gameBoard; // Representation of Tic-Tac-Toe board
    int emptySpaces;    // Count of spaces left to be played; initialized to 9
    boolean run;        // Used to enable replay ability

    /**
     * Constructor
     */
    public TicTacToe() {
        gameBoard = new String[9];
        input = new Scanner(System.in);
        run = true;
    }

    /**
     * Game logic
     */
    void runGame() {
        while(run) {
            init();
            printGame();
            while(true) {
                move();
                printGame();
                winner();
                if (!winner.isBlank()) {
                    break;
                }
            }
            endGame();
        }
    }

    /**
     * Returns winner or draw
     * 
     * TODO - This function should determine if one of the players
     * has three X's or O's in a row, or if the game has ended in a draw.
     * 
     * If the X player has won, set winner = "X" and set run = false.
     * If the O player has won, set winner = "O" and set run = false.
     * If the game has ended in a draw, set winner = "draw" and set run = false.
     * Otherwise, do not change winner or run.
     */
    private String winner() {
        return null;
    }

    /**
     * Performs move in game
     * 
     * TODO - This function should prompt the user to make a move. It will
     * confirm that both the input and the requested move is valid. If either
     * is untrue, it will repeatedly re-prompt the user until both conditions
     * are met. After that, it will update gameBoard and emptySpaces. Finally,
     * it will call changePlayer().
     */
    private void move() {
        return;
    }

    /**
     * Changes active player
     */
    private void changePlayer() {
        if (player.equals("X")) {
            player = "O";
        } else {
            player = "X";
        }
    }

    /**
     * Prints game to stdout
     */
    private void printGame() {
        return;
    }

    /**
     * Initializes game; equivalent to reset
     */
    private void init() {
        for (int i = 0; i < gameBoard.length; i++) {
            gameBoard[i] = " ";
        }
        player = "X";
        emptySpaces = 9;
        winner = "";
    }

    /**
     * Ends game; option for reset
     */
    private void endGame() {
        String endMessage = "";
        if (winner.equals("draw")) {
            endMessage = "The game has ended in a draw.";
        } else {
            endMessage = "The " + winner + " player has won!";
        }
        System.out.println(endMessage);

        run = false;
        System.out.print("Want to play another game [y/n]? ");
        String userSelection = input.next();
        if(userSelection.equals("y")) {
            run = true;
        }  
    }
}