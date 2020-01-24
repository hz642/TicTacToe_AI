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
     */
    private String winner() {
        return null;
    }

    /**
     * Performs move in game
     */
    private void move() {
        return;
    }

    /**
     * Changes active player
     */
    private void changePlayer() {
        return;
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
        return;
    }
}