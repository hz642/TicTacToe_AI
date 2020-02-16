/**
 * @author Sabeel Mansuri
 */
public class TicTacToeDriver { 
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.init();
        TicTacToeGUI gui = new TicTacToeGUI(game);
        gui.runGame();
    }
}