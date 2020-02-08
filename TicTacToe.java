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
    int score;          // Holds score of the game, treating the "0" player as the maximizer 
    AI ai;              // Artificial intelligence bot

    static final int width = 3;
    static final String ANSI_RED = "\u001B[31m";
    static final String ANSI_BLUE = "\u001B[34m";
    static final String ANSI_RESET = "\u001B[0m";
    

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
                aiMove();
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
     * Determines if a win or draw has occured
     * 
     */
    private void winner() {
        int[] horizontalStarts = {0, 3, 6};
        int[] verticalStarts = {0, 1, 2};

        horizontalWinner(horizontalStarts);
        verticalWinner(verticalStarts);
        diagonalWinner();

        if (winner.isBlank() && emptySpaces == 0) {
            winner = "draw";
            run = false;
        };
    }

    /**
     * Checks if there is a vertical win.
     */
    private void verticalWinner(int[] starts) {
        for (int start : starts) {
            if (gameBoard[start].equals(gameBoard[start + 3]) && gameBoard[start].equals(gameBoard[start + 6])) {
                winner = gameBoard[start];
                run = false;
            }
        }
    }

    /**
     * Checks if there is a horizontal win.
     */
    private void horizontalWinner(int[] starts) {
        for (int start : starts) {
            if (gameBoard[start].equals(gameBoard[start + 1]) && gameBoard[start].equals(gameBoard[start + 2])) {
                winner = gameBoard[start];
                run = false;
            }
        }
    }

    /**
     * Checks if there is a diagonal win.
     */
    private void diagonalWinner() {
        if (gameBoard[0].equals(gameBoard[4]) && gameBoard[0].equals(gameBoard[8])) {
            winner = gameBoard[0];
            run = false;
        }

        if (gameBoard[2].equals(gameBoard[4]) && gameBoard[2].equals(gameBoard[6])) {
            winner = gameBoard[2];
            run = false;
        }
    }

    /**
     * Performs move in game
     */
    private void move() {
        int userSelection = 0;

        while (userSelection == 0) {
            try {
                System.out.print("Where do you want to place an " + player + "? ");
                userSelection = input.nextInt();
            } catch (Exception e) {
                System.out.println("This isn't a valid spot! Try again:");
                input.nextLine();
                userSelection = 0;
            }
            
            if (userSelection != 0) {
                if(isOutOfRange(userSelection)) {
                    System.out.println("You can only choose an empty position between 1 and 9! Try again:");
                    userSelection = 0;
                } else if (moveAlreadyMade(userSelection)) {
                    System.out.println("This spot has already been taken! Try again:");
                    userSelection = 0;
                }
            }
        }

        gameBoard[userSelection - 1] = player;
        emptySpaces--;
        computeScore();
        changePlayer();
    }

    /**
     * Determines if user selection is not in gameboard
     */
    private boolean isOutOfRange(int userSelection) {
        return (userSelection < 1 || userSelection > 9);
    }

    /**
     * Determines if user selection was previously played
     */
    private boolean moveAlreadyMade(int userSelection) {
        return gameBoard[userSelection - 1].matches("X|O");
    }

    private void aiMove() {
        ai = new AI(gameBoard, score, player, emptySpaces);
        int aiSelection = ai.computeBestMove();

        gameBoard[aiSelection] = player;
        emptySpaces--;
        computeScore();
        changePlayer();
    }

    /**
     * Computes score of game.
     * Points are positive for the "0" player, and negative for the "X" player.
     */
    private void computeScore() {
        int[] horizontalStarts = {0, 3, 6};
        int[] verticalStarts = {0, 1, 2};

        score = 0;
        horizontalScore(horizontalStarts);
        verticalScore(verticalStarts);
        diagonalScore();
    }

    /**
     * Updates score with vertical values.
     */
    private void verticalScore(int[] starts) {
        for (int start : starts) {
            int skew = 0;
            if (gameBoard[start].equals("X")) {
                skew -= 1;
            } else if (gameBoard[start].equals("0")) {
                skew += 1;
            } 
            
            if (gameBoard[start + 3].equals("X")) {
                skew -= 1;
            } else if (gameBoard[start + 3].equals("0")) {
                skew += 1;
            }

            if (gameBoard[start + 6].equals("X")) {
                skew -= 1;
            } else if (gameBoard[start + 6].equals("0")) {
                skew += 1;
            }
            int unsignedScore = (int) Math.pow(10, Math.abs(skew));
            int signedScore = skew > 0 ? unsignedScore : -unsignedScore;
            score += signedScore;
        }
    }

    /**
     * Updates score with horizontal values.
     */
    private void horizontalScore(int[] starts) {
        for (int start : starts) {
            int skew = 0;
            if (gameBoard[start].equals("X")) {
                skew -= 1;
            } else if (gameBoard[start].equals("0")) {
                skew += 1;
            } 
            
            if (gameBoard[start + 1].equals("X")) {
                skew -= 1;
            } else if (gameBoard[start + 1].equals("0")) {
                skew += 1;
            }

            if (gameBoard[start + 2].equals("X")) {
                skew -= 1;
            } else if (gameBoard[start + 2].equals("0")) {
                skew += 1;
            }
            int unsignedScore = (int) Math.pow(10, Math.abs(skew));
            int signedScore = skew > 0 ? unsignedScore : -unsignedScore;
            score += signedScore;
        }  
    }

    /**
     * Updates score with diagonal values.
     */
    private void diagonalScore() {
        int skew = 0;
        if (gameBoard[0].equals("X")) {
            skew -= 1;
        } else if (gameBoard[0].equals("0")) {
            skew += 1;
        } 
        
        if (gameBoard[4].equals("X")) {
            skew -= 1;
        } else if (gameBoard[4].equals("0")) {
            skew += 1;
        }

        if (gameBoard[8].equals("X")) {
            skew -= 1;
        } else if (gameBoard[8].equals("0")) {
            skew += 1;
        }

        int unsignedScore = (int) Math.pow(10, Math.abs(skew));
        int signedScore = skew > 0 ? unsignedScore : -unsignedScore;
        score += signedScore;

        skew = 0;
        if (gameBoard[2].equals("X")) {
            skew -= 1;
        } else if (gameBoard[2].equals("0")) {
            skew += 1;
        } 
        
        if (gameBoard[4].equals("X")) {
            skew -= 1;
        } else if (gameBoard[4].equals("0")) {
            skew += 1;
        }

        if (gameBoard[6].equals("X")) {
            skew -= 1;
        } else if (gameBoard[6].equals("0")) {
            skew += 1;
        }
        
        unsignedScore = (int) Math.pow(10, Math.abs(skew));
        signedScore = skew > 0 ? unsignedScore : -unsignedScore;
        score += signedScore;
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
        int index = 0;
        System.out.print("-------------" + "\n");
        for (int i = 0; i < width; i++) {
            System.out.print("|");
            for (int j = 0; j < width; j++) {
                System.out.print(" " + printAttribute(gameBoard[index]) + " |");
                index++;
            }
            System.out.println();
            System.out.print("-------------" + "\n");
        }
    }

    private String printAttribute(String attribute) {
        if (attribute.equals("X")) {
            return ANSI_RED + attribute + ANSI_RESET;
        }

        if (attribute.equals("O")) {
            return ANSI_BLUE + attribute + ANSI_RESET;
        }

        return attribute;
    }

    /**
     * Initializes game; equivalent to reset
     */
    private void init() {
        for (int i = 0; i < gameBoard.length; i++) {
            gameBoard[i] = Integer.toString(i+1);
        }
        player = "X";
        emptySpaces = 9;
        winner = "";
        score = 0;
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