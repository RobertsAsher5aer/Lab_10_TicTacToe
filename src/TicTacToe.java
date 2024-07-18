import java.util.Scanner;

public class TicTacToe {
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String[][] board = new String[ROW][COL];
    private static String currentPlayer = "X"; // X always starts

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            clearBoard();
            display();

            while (true) {
                int row, col;

                row = getRangedInt(scanner, "Player " + currentPlayer + ", enter your move row", 1, 3) - 1;
                col = getRangedInt(scanner, "Player " + currentPlayer + ", enter your move column", 1, 3) - 1;

                if (isValidMove(row, col)) {
                    board[row][col] = currentPlayer;
                    display();

                    if (isWin(currentPlayer)) {
                        System.out.println("Player " + currentPlayer + " wins!");
                        break;
                    }

                    if (isTie()) {
                        System.out.println("The game is a tie!");
                        break;
                    }

                    switchPlayer();
                } else {
                    System.out.println("This move is not valid. The cell is already occupied.");
                }
            }

            System.out.println("Do you want to play again? (yes or no)");
            String response = scanner.nextLine();
            if (!response.equalsIgnoreCase("yes")) {
                break;
            }
        }
    }

    // Method to clear the board
    private static void clearBoard() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                board[i][j] = " ";
            }
        }
        currentPlayer = "X"; // Reset to X
    }

    // Method to display the board
    private static void display() {
        System.out.println("-------------");
        for (int i = 0; i < ROW; i++) {
            System.out.print("| ");
            for (int j = 0; j < COL; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    // Method to check if a move is valid
    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < ROW && col >= 0 && col < COL && board[row][col].equals(" ");
    }

    // Method to check if the current player has won
    private static boolean isWin(String player) {
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
    }

    // Method to check if there is a row win
    private static boolean isRowWin(String player) {
        for (int i = 0; i < ROW; i++) {
            if (board[i][0].equals(player) && board[i][1].equals(player) && board[i][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    // Method to check if there is a column win
    private static boolean isColWin(String player) {
        for (int i = 0; i < COL; i++) {
            if (board[0][i].equals(player) && board[1][i].equals(player) && board[2][i].equals(player)) {
                return true;
            }
        }
        return false;
    }

    // Method to check if there is a diagonal win
    private static boolean isDiagonalWin(String player) {
        if (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) {
            return true;
        }
        if (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player)) {
            return true;
        }
        return false;
    }

    // Method to check if the game is a tie
    private static boolean isTie() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (board[i][j].equals(" ")) {
                    return false;
                }
            }
        }
        return !isWin("X") && !isWin("O");
    }

    // Method to switch the current player
    private static void switchPlayer() {
        currentPlayer = currentPlayer.equals("X") ? "O" : "X";
    }

    // Method to get a ranged integer input
    public static int getRangedInt(Scanner pipe, String prompt, int low, int high) {
        int value = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print(prompt + " [" + low + " - " + high + "]: ");
            if (pipe.hasNextInt()) {
                value = pipe.nextInt();
                if (value >= low && value <= high) {
                    validInput = true;
                } else {
                    System.out.println("Invalid input: " + value + ". Please enter a value between " + low + " and " + high + ".");
                }
            } else {
                String trash = pipe.next();
                System.out.println("Invalid input: " + trash + ". Please enter a valid integer.");
            }
            pipe.nextLine();
        }

        return value;
    }
}
