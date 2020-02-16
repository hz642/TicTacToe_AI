import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author Sabeel Mansuri
 */
class TicTacToeGUI {
    static JFrame frame;
    static TicTacToe game;
    static JButton[] gameBoard;

    static Color X = new Color(253, 150, 134);
    static Color O = new Color(175, 211, 148);
    static Color BACKGROUND = new Color(64, 54, 53);
    static Color BORDER = new Color(183, 179, 170);

    static final ActionListener BUTTON_PRESSED = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            JButton clicked = (JButton) e.getSource();

            int clickedIndex = -1;
            for (int i = 0; i < gameBoard.length; i++) {
                if (clicked.equals(gameBoard[i])) {
                    clickedIndex = i;
                }
            }

            if (!game.moveGUI(clickedIndex)) {
                return;
            }

            clicked.setForeground(X);
            clicked.setText(game.player);
            game.changePlayer();
            game.winner();
            if (!game.winner.isBlank()) {
                endGame();
                return;
            }

            int aiMove = game.aiMoveGUI();
            game.moveGUI(aiMove);
            gameBoard[aiMove].setForeground(O);
            gameBoard[aiMove].setText(game.player);
            game.changePlayer();
            game.winner();
            if (!game.winner.isBlank()) {
                endGame();
                return;
            }
        }
    };

    TicTacToeGUI(TicTacToe game) {
        TicTacToeGUI.game = game;
        gameBoard = new JButton[9];
    }

    /**
     * Exposed method -- Runs game.
     */
    void runGame() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));
        panel.setBorder(BorderFactory.createLineBorder(BACKGROUND, 3));

        for (int i = 0; i < 9; i++) {
            gameBoard[i] = new JButton();
            gameBoard[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            gameBoard[i].setFocusPainted(false);
            gameBoard[i].setOpaque(true);
            gameBoard[i].setBorder(BorderFactory.createLineBorder(BORDER, 1));
            gameBoard[i].setBackground(BACKGROUND);
            gameBoard[i].setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 45));
            gameBoard[i].addActionListener(BUTTON_PRESSED);
            panel.add(gameBoard[i]);
        }

        frame.getContentPane().add(panel);
        frame.setSize(650, 650);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void endGame() {
        String endMessage = "";
        if (game.winner.equals("draw")) {
            endMessage = "The game has ended in a draw.";
        } else {
            endMessage = "The " + game.winner + " player has won!";
        }
        endMessage += "\nWould you like to play again?";
        int userSelection = JOptionPane.showConfirmDialog(null, endMessage, "Game Over", JOptionPane.YES_NO_OPTION);
        if (userSelection == JOptionPane.YES_OPTION) {
            for (JButton button : gameBoard) {
                button.setText("");
            }
            game.init();
        } else {
            System.exit(0);
        }
    }
}