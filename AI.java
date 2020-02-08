import java.util.Arrays;
import java.util.ArrayList;

class AI {
    Simulator root;

    AI(String[] currentGameBoard, int currentScore, String currentPlayer, int currentEmptySpaces) {
        this.root = new Simulator(Arrays.copyOf(currentGameBoard, currentGameBoard.length), currentScore, currentPlayer, currentEmptySpaces, "Max", -1);
    }

    /**
     * TODO - Exposed method; builds tree, runs minimax, and returns optimal move.
     * 
     * This method should call buildTree on the root node, then run minimax on the root.
     * Then, it should find which child node resulted in the minimax optimal value, and return
     * the move that child represents.
     */
    int computeBestMove() {
        System.out.println("Nice move, now it's my turn!");
        return -1;
    }

    /**
     * TODO - Runs minimax algorithm on root node, returns best position.
     * 
     * This method should be identical to the one in Stepik, except that we're
     * using an ArrayList of children. We should update Stepik to match this design.
     */
    private int minimax(Simulator node) {
        return -1;
    }

    /**
     * TODO - Builds the game tree from the given node.
     * 
     * This is a recursive method with the following structure:
     * Base case: If the node is terminal, return
     * Recursive: 
     *  Get all possible moves for this node
     *  For every possible move
     *      Create a Simulator child node that is a copy of the current state
     *      Perform the move on the child
     *      Add child to node's children ArrayList
     *      buildTree(child)
     */
    private void buildTree(Simulator node) {
        return;
    }

    /**
     * TODO - Changes type from Max player to Min player or vice versa.
     */
    private String changeType(String type) {
        return "";
    }

    /**
     * Returns value (score).
     */
    private int payoff(Simulator n) {
        return n.value;
    }

    /**
     * Returns true if node is terminal.
     */
    private boolean isTerminal(Simulator n) {
        return n.type.equals("Terminal") || !n.winner.isBlank();
    }

    /**
     * Returns true if node is a Max player.
     */
    private boolean isMaxPlayer(Simulator n) {
        return n.type.equals("Max");
    }

    /**
     * Returns true if node is a Min player.
     */
    private boolean isMinPlayer(Simulator n) {
        return n.type.equals("Min");
    }
}

class Simulator {
    /* Game variables */
    String winner;      // Winner of game OR ""
    String player;      // Active player; initialized to X
    String[] gameBoard; // Representation of Tic-Tac-Toe board
    int emptySpaces;    // Count of spaces left to be played; initialized to 9

    /* AI variables */
    String type;                    // One of: "Max", "Min", "Terminal"
    int value;                      // Holds score of game (value of node)
    ArrayList<Simulator> children;  // Holds children of current node
    int position;                   // Holds position to simulate


    Simulator(String[] gameBoard, int score, String player, int emptySpaces, String type, int position) {
        this.gameBoard = gameBoard;
        this.value = score;
        this.player = player;
        this.emptySpaces = emptySpaces;
        this.type = type;
        this.position = position;
        this.children = new ArrayList<>();
        this.winner = "";
    }

    /**
     * TODO - Game logic.
     * 
     * Perform a move at the given position, update emptySpaces. 
     * Finish by changing player, computing score, and checking winner.
     */
    void makeMove(int position) {
        return;
    }

    /**
     * Computes score of game.
     * Points are positive for the "0" player, and negative for the "X" player.
     */
    private void computeScore() {
        int[] horizontalStarts = {0, 3, 6};
        int[] verticalStarts = {0, 1, 2};

        value = 0;
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
            value += signedScore;
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
            value += signedScore;
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
        value += signedScore;

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
        value += signedScore;
    }


    /**
     * Returns all positions where a player has not yet made a move.
     */
    ArrayList<Integer> getPossibleMoves() {
        ArrayList<Integer> moves = new ArrayList<>();
        for(int pos = 0; pos < gameBoard.length; pos++) {
            if (!moveAlreadyMade(pos)) {
                moves.add(pos);
            }
        }
        return moves;
    }

    /**
     * Determines if user selection was previously played
     */
    private boolean moveAlreadyMade(int userSelection) {
        return gameBoard[userSelection].matches("X|O");
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
        }
    }

    /**
     * Checks if there is a vertical win.
     */
    private void verticalWinner(int[] starts) {
        for (int start : starts) {
            if (gameBoard[start].equals(gameBoard[start + 3]) && gameBoard[start].equals(gameBoard[start + 6])) {
                winner = gameBoard[start];
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
            }
        }
    }

    /**
     * Checks if there is a diagonal win.
     */
    private void diagonalWinner() {
        if (gameBoard[0].equals(gameBoard[4]) && gameBoard[0].equals(gameBoard[8])) {
            winner = gameBoard[0];
        }

        if (gameBoard[2].equals(gameBoard[4]) && gameBoard[2].equals(gameBoard[6])) {
            winner = gameBoard[2];
        }
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
}