package gr.aueb.cf.projects;

import java.util.Scanner;

/**
 * A program to demonstrate Tic Tac Toe game.
 */

public class TicTacToeApp {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to 3x3 Tic Tac Toe!");
        char[][] board = {{'1', '2', '3'},
                          {'4', '5', '6'},
                          {'7', '8', '9'}};
        printBoard(board);
        while (true) {
            player1Turn(board);
            if (gameOver(board)){
                break;
            }
            printBoard(board);
            player2Turn(board);
            if (gameOver(board)){
                break;
            }
            printBoard(board);
        }
    }
    /**
     * Print a 3×3 board.
     * @param board    the game board.
     */
    public static void printBoard(char[][] board) {
        System.out.print((board[0][0]));
        System.out.print("|");
        System.out.print((board[0][1]));
        System.out.print("|");
        System.out.println((board[0][2]));
        System.out.println("-----");
        System.out.print((board[1][0]));
        System.out.print("|");
        System.out.print((board[1][1]));
        System.out.print("|");
        System.out.println((board[1][2]));
        System.out.println("-----");
        System.out.print((board[2][0]));
        System.out.print("|");
        System.out.print((board[2][1]));
        System.out.print("|");
        System.out.println((board[2][2]));
    }

    /**
     * Method to place The Player’s moves.
     * @param board   the game board.
     * @param position   the position in the board.
     * @param symbol     the symbol 'X' or 'O' that each player has.
     */
    public static void move(char[][] board, String position, char symbol) {
        switch (position) {
            case "1":
                board[0][0] = symbol;
                break;
            case "2":
                board[0][1] = symbol;
                break;
            case "3":
                board[0][2] = symbol;
                break;
            case "4":
                board[1][0] = symbol;
                break;
            case "5":
                board[1][1] = symbol;
                break;
            case "6":
                board[1][2] = symbol;
                break;
            case "7":
                board[2][0] = symbol;
                break;
            case "8":
                board[2][1] = symbol;
                break;
            case "9":
                board[2][2] = symbol;
                break;
            default:
                System.out.println("Invalid Input");
        }
    }

    /**
     * Check the validation of movements of players.
     * @param board     the game board.
     * @param position  the position in the board.
     * @return           true if the position of the board after the movement of players is valid,
     *                   otherwise false.
     */
    public static boolean isValidMove(char[][] board, String position) {
        switch (position) {
            case "1":
                return (board[0][0] == '1');
            case "2":
                return (board[0][1] == '2');
            case "3":
                return (board[0][2] == '3');
            case "4":
                return (board[1][0] == '4');
            case "5":
                return (board[1][1] == '5');
            case "6":
                return (board[1][2] == '6');
            case "7":
                return (board[2][0] == '7');
            case "8":
                return (board[2][1] == '8');
            case "9":
                return (board[2][2] == '9');
            default:
                return false;
        }
    }
    /**
     * A method that asks and places the first player's move.
     * @param board   the game board.
     */
    public static void player1Turn(char[][] board) {
        Scanner in = new Scanner(System.in);
        String input;
        while(true) {
            System.out.println("Player 1's turn, enter a slot number to place X in:");
            input = in.nextLine();
            if (isValidMove(board, input)) {
                break;
            } else {
                System.out.println("Invalid input, re-enter a slot number:");
            }
        }
        move(board, input, 'X');
    }

    /**
     * A Method that asks and places the second player's move.
     * @param board  the game board.
     */
    public static void player2Turn(char[][] board) {
        Scanner in = new Scanner(System.in);
        String input;
        while(true) {
            System.out.println("Player 2's turn, enter a slot number to place X in:");
            input = in.nextLine();
            if (isValidMove(board, input)) {
                break;
            } else {
                System.out.println("Invalid input, re-enter a slot number:");
            }
        }
        move(board, input, 'O');
    }

    /**
     * A method that checks the winning result of players.
     * @param board   the game board.
     * @param symbol   the symbol 'X' or 'O' that each player has.
     * @return         true if the winning result is valid, otherwise false.
     */
    public static boolean winner(char[][] board, char symbol) {
        if ((board[0][0] == symbol && board[0][1] == symbol && board[0][2] == symbol) ||
                (board[1][0] == symbol && board[1][1] == symbol && board[1][2] == symbol) ||
                (board[2][0] == symbol && board[2][1] == symbol && board[2][2] == symbol) ||
                (board[0][0] == symbol && board[1][0] == symbol && board[2][0] == symbol) ||
                (board[0][1] == symbol && board[1][1] == symbol && board[2][1] == symbol) ||
                (board[0][2] == symbol && board[1][2] == symbol && board[2][2] == symbol) ||
                (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) ||
                (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol)) {
            return true;
        }
        return false;
    }

    /**
     * Α method that checks for a tie.
     * @param board   the game board.
     * @return        true if no one win or lose from both player x and O, otherwise false.
     */
    public static boolean isDraw(char[][] board) {
        if(board[0][0] != '1' && board[0][1] != '2' && board[0][2] != '3' && board[1][0] !='4' &&
                board[1][1] != '5' && board[1][2] != '6' && board[2][0] != '7' && board[2][1] != '8' && board[2][2] != '9') {
            printBoard(board);
            System.out.println("Its a tie!! Thanks for using the App!!");
            return true;
        }
        return false;
    }
    /**
     * A method that will display the winner contestant or the case of a tie.
     * @param board  the game board.
     * @return       true, if player 1 with symbol 'X' wins or if player 2 with symbol 'O' wins
     *               or in case of a tie, otherwise false.
     */
    public static boolean gameOver(char [][] board) {
        if (winner(board, 'X')) {
            printBoard(board);
            System.out.println("Player 1 wins! Congratulations!");
            System.out.println("Thanks for using the App!!");
            return true;
        } else if (winner(board, 'O')) {
            printBoard(board);
            System.out.println("Player 2 wins! Congratulations!");
            System.out.println("Thanks for using the App!!");
            return true;
        } else if (isDraw(board)) {
            return true;
        }
        return false;
    }
}
