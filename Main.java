import java.util.Scanner;
import java.util.Random;
import java.lang.System;

public class Main {
    public static void main(String[] args) {
        //column,row
        Scanner input = new Scanner(System.in);
        Random rand = new Random();
        String computerCharacter;
        boolean winner = false;
        int row = 0;
        int column = 0;
        int count = 0;
        int playOrder = 0;
        String character;
        int drawCheck = 0;

        String[][] board = new String[3][3];

        for (int i = 0; i < board.length; i++) {
            for (int k = 0; k < board.length; k++) {
                board[i][k] = "-";
            }
        }
        System.out.println(" NOUGHTS AND CROSSES ");
        System.out.println("---------------------");


        do {
            System.out.println("Enter either O, X, o or x to be your character: ");
            character = input.next();

        } while (!character.equals("O") && !character.equals("X") && !character.equals("o") && !character.equals("x"));

        if (character.equals("O") || character.equals("o")) {
            character = "O";
            computerCharacter = "X";
        } else {
            character = "X";
            computerCharacter = "O";
        }
        System.out.println("You are " + character + " and the computer is " + computerCharacter + ".");
        printBoard(board);

        do {
            playOrder = 0;
            if (count % 2 == 0) {
                //player go
                checkPlacementThenGo(board, character);
                printBoard(board);
                winner = checkForPlayerWinner(board, character, winner);
                count++;
                drawCheck++;
                drawChecker(drawCheck);
            } else if (count % 2 == 1) {
                //computer go
                if (playOrder == 0) {
                    playOrder = computerWinAttempt(board, character, computerCharacter, playOrder);
                }
                if (playOrder == 0) {
                    playOrder = compCounter(board, character, computerCharacter, playOrder);
                }
                if (playOrder == 0) {
                    playOrder = computerGo(board, computerCharacter, playOrder);
                }
                winner = checkForComputerWinner(board, computerCharacter, winner);
                printBoard(board);
                count++;
                drawCheck++;
                drawChecker(drawCheck);
            }


        } while (!winner);
    }
    public static void drawChecker(int drawCheck){
        if (drawCheck == 9) {
            System.out.println("Draw!");
            System.exit(0);
        }
    }
    public static void printBoard(String[][] board) {
        System.out.println();
        System.out.println("Row = side, Column = top");
        System.out.println("    1   2   3");
        System.out.println(" 1  " + board[0][0] + " | " + board[0][1] + " | " + board[0][2]);
        System.out.println("   -----------");
        System.out.println(" 2  " + board[1][0] + " | " + board[1][1] + " | " + board[1][2]);
        System.out.println("   -----------");
        System.out.println(" 3  " + board[2][0] + " | " + board[2][1] + " | " + board[2][2]);
        System.out.println();
    }

    public static boolean checkForPlayerWinner(String[][] board, String character, boolean winner) {
        for (int c = 0; c <= 2; c++) {
            if (board[0][c].equals(character) && board[1][c].equals(character) && board[2][c].equals(character)) {
                winner = true;
                System.out.println("Player win!");
                System.exit(0);
            } else if (board[c][0].equals(character) && board[c][1].equals(character) && board[c][2].equals(character)) {
                winner = true;
                System.out.println("Player win!");
                System.exit(0);
            } else if (board[0][0].equals(character) && board[1][1].equals(character) && board[2][2].equals(character)) {
                winner = true;
                System.out.println("Player win!");
                System.exit(0);
            } else if (board[2][0].equals(character) && board[1][1].equals(character) && board[0][2].equals(character)) {
                winner = true;
                System.out.println("Player win!");
                System.exit(0);
            }
        }
        return winner;

    }

    public static boolean checkForComputerWinner(String[][] board, String computerCharacter, boolean winner) {
        for (int c = 0; c <= 2; c++) {
            if (board[0][c].equals(computerCharacter) && board[1][c].equals(computerCharacter) && board[2][c].equals(computerCharacter)) {
                winner = true;
                System.out.println("Computer win!");
                printBoard(board);
                System.exit(0);
            } else if (board[c][0].equals(computerCharacter) && board[c][1].equals(computerCharacter) && board[c][2].equals(computerCharacter)) {
                winner = true;
                System.out.println("Computer win!");
                printBoard(board);
                System.exit(0);
            } else if (board[0][0].equals(computerCharacter) && board[1][1].equals(computerCharacter) && board[2][2].equals(computerCharacter)) {
                winner = true;
                System.out.println("Computer win!");
                printBoard(board);
                System.exit(0);
            } else if (board[2][0].equals(computerCharacter) && board[1][1].equals(computerCharacter) && board[0][2].equals(computerCharacter)) {
                winner = true;
                System.out.println("Computer win!");
                printBoard(board);
                System.exit(0);
            }
        }
        return winner;
    }

    public static void checkPlacementThenGo(String[][] board, String character) {
        Scanner input = new Scanner(System.in);

        System.out.println("Player's move: ");
        System.out.println("Enter the row number you would like to play in: ");
        int row = input.nextInt();

        System.out.println("Enter the column number you would like to play in: ");
        int column = input.nextInt();

        System.out.println();
        while (row > 3 || row < 1) {
            if (row > 3) {
                System.out.println("Your row must be smaller than 4 (not inclusive): ");
                row = input.nextInt();
            } else if (row < 1) {
                System.out.println("Your row must be greater than 0 (not inclusive): ");
                row = input.nextInt();
            }
        }
        while (column > 3 || column < 1) {
            if (column > 3) {
                System.out.println("Your column must be smaller than 4 (not inclusive): ");
                column = input.nextInt();
            } else if (column < 1) {
                System.out.println("Your column must be greater than 0 (not inclusive): ");
                column = input.nextInt();
            }
        }
        while (!board[row - 1][column - 1].equals("-")) {
            System.out.println("This space has already been taken");
            System.out.println("Enter the row number you would like to play in: ");
            row = input.nextInt();

            System.out.println("Enter the column number you would like to play in: ");
            column = input.nextInt();
        }
        board[row - 1][column - 1] = character;


    }


    public static int compCounter(String[][] board, String character, String computerCharacter, int playOrder) {
        int onlyOneCounter = 0;
        // along a row check
        for (int z = 0; z <= 2; z++) {
            if (board[0][z].equals(character)) {
                if (board[1][z].equals(character)) {
                    if (onlyOneCounter == 0 && board[2][z].equals("-")) {
                        System.out.println("Player was close to winning... ");
                        board[2][z] = computerCharacter;
                        playOrder++;
                        onlyOneCounter++;
                    }
                } else {
                    if (board[2][z].equals(character)) {
                        if (onlyOneCounter == 0  && board[1][z].equals("-")) {
                            System.out.println("Player was close to winning... ");
                            board[1][z] = computerCharacter;
                            playOrder++;
                            onlyOneCounter++;
                        }
                    }
                }
            } else {
                if (board[1][z].equals(character)) {
                    if (board[2][z].equals(character)) {
                        if (onlyOneCounter == 0 && board[0][z].equals("-")) {
                            System.out.println("Player was close to winning... ");
                            board[0][z] = computerCharacter;
                            playOrder++;
                            onlyOneCounter++;
                        }
                    }
                }
            }
        }
        for (int g = 0; g <= 2; g++) {
            // along a column check
            if (board[g][0].equals(character)) {
                if (board[g][1].equals(character)) {
                    if (onlyOneCounter == 0 && board[g][2].equals("-")) {
                        System.out.println("Player was close to winning... ");
                        board[g][2] = computerCharacter;
                        playOrder++;
                        onlyOneCounter++;
                    }
                } else {
                    if (board[g][2].equals(character)) {
                        if (onlyOneCounter == 0 && board[g][1].equals("-")) {
                            System.out.println("Player was close to winning... ");
                            board[g][1] = computerCharacter;
                            playOrder++;
                            onlyOneCounter++;
                        }
                    }
                }
            } else {
                if (board[g][1].equals(character)) {
                    if (board[g][2].equals(character)) {
                        if (onlyOneCounter == 0 && board[g][0].equals("-")) {
                            System.out.println("Player was close to winning... ");
                            board[g][0] = computerCharacter;
                            playOrder++;
                            onlyOneCounter++;
                        }
                    }
                }
            }
        }
        //left to right diagonal
        if (board[0][0].equals(character)) {
            if (board[1][1].equals(character)) {
                if (onlyOneCounter == 0 && board[2][2].equals("-")) {
                    System.out.println("Player was close to winning... ");
                    board[2][2] = computerCharacter;
                    playOrder++;
                    onlyOneCounter++;
                }
            } else {
                if (board[2][2].equals(character)) {
                    if (onlyOneCounter == 0 && board[1][1].equals("-")) {
                        System.out.println("Player was close to winning... ");
                        board[1][1] = computerCharacter;
                        playOrder++;
                        onlyOneCounter++;
                    }
                }
            }
        } else if (board[1][1].equals(character)) {
            if (board[2][2].equals(character)) {
                if (onlyOneCounter == 0 && board[0][0].equals("-")) {
                    System.out.println("Player was close to winning... ");
                    board[0][0] = computerCharacter;
                    playOrder++;
                    onlyOneCounter++;
                }
            }
        }
        //right to left diagonal
        if (board[0][2].equals(character)) {
            if (board[1][1].equals(character)) {
                if (onlyOneCounter == 0 && board[2][0].equals("-")) {
                    System.out.println("Player was close to winning... ");
                    board[2][0] = computerCharacter;
                    playOrder++;
                    onlyOneCounter++;
                }
            } else {
                if (board[2][0].equals(character)) {
                    if (onlyOneCounter == 0 && board[1][1].equals("-")) {
                        System.out.println("Player was close to winning... ");
                        board[1][1] = computerCharacter;
                        playOrder++;
                        onlyOneCounter++;
                    }
                }
            }
        } else if (board[1][1].equals(character)) {
            if (board[2][0].equals(character)) {
                if (onlyOneCounter == 0 && board[0][2].equals("-")) {
                    System.out.println("Player was close to winning... ");
                    board[0][2] = computerCharacter;
                    playOrder++;
                    onlyOneCounter++;
                }
            }
        }
        return playOrder;
    }

    public static int computerWinAttempt(String[][] board, String character, String computerCharacter, int playOrder) {
        int onlyOneAttempt = 0;
        // along a column check
        for (int z = 0; z <= 2; z++) {
            if (board[0][z].equals(computerCharacter)) {
                if (board[1][z].equals(computerCharacter)) {
                    if (board[2][z].equals("-") && onlyOneAttempt == 0) {
                        System.out.println("Computer was close to winning... ");
                        board[2][z] = computerCharacter;
                        playOrder++;
                        onlyOneAttempt++;
                    }

                } else {
                    if (board[2][z].equals(computerCharacter)) {
                        if (board[1][z].equals("-")  && onlyOneAttempt == 0) {
                            System.out.println("Computer was close to winning... ");
                            board[1][z] = computerCharacter;
                            playOrder++;
                            onlyOneAttempt++;
                        }
                    }
                }
            } else {
                if (board[1][z].equals(computerCharacter)) {
                    if (board[2][z].equals(computerCharacter)) {
                        if (board[0][z].equals("-") && onlyOneAttempt == 0) {
                            System.out.println("Computer was close to winning... ");
                            board[0][z] = computerCharacter;
                            playOrder++;
                            onlyOneAttempt++;
                        }
                    }
                }
            }
        }
        for (int g = 0; g <= 2; g++) {
            // along a row check
            if (board[g][0].equals(computerCharacter)) {
                if (board[g][1].equals(computerCharacter)) {
                    if (board[g][2].equals("-") && onlyOneAttempt == 0) {
                        System.out.println("Computer was close to winning... ");
                        board[g][2] = computerCharacter;
                        playOrder++;
                        onlyOneAttempt++;
                    }
                } else {
                    if (board[g][2].equals(computerCharacter)) {
                        if (board[g][1].equals("-") && onlyOneAttempt == 0) {
                            System.out.println("Computer was close to winning... ");
                            board[g][1] = computerCharacter;
                            playOrder++;
                            onlyOneAttempt++;
                        }
                    }
                }
            } else {
                if (board[g][1].equals(computerCharacter)) {
                    if (board[g][2].equals(computerCharacter) && onlyOneAttempt == 0) {
                        if (board[g][0].equals("-")) {
                            System.out.println("Computer was close to winning... ");
                            board[g][0] = computerCharacter;
                            playOrder++;
                            onlyOneAttempt++;
                        }
                    }
                }
            }
            //left to right diagonal
            if (board[0][0].equals(computerCharacter)) {
                if (board[1][1].equals(computerCharacter) && onlyOneAttempt == 0) {
                    if (board[2][2].equals("-")) {
                        System.out.println("Computer was close to winning... ");
                        board[2][2] = computerCharacter;
                        playOrder++;
                        onlyOneAttempt++;
                    }
                } else {
                    if (board[2][2].equals(computerCharacter)) {
                        if (board[1][1].equals("-") && onlyOneAttempt == 0) {
                            System.out.println("Computer was close to winning... ");
                            board[1][1] = computerCharacter;
                            playOrder++;
                            onlyOneAttempt++;
                        }
                    }
                }
            } else if (board[1][1].equals(computerCharacter)) {
                if (board[2][2].equals(computerCharacter)) {
                    if (board[0][0].equals("-") && onlyOneAttempt == 0) {
                        System.out.println("Computer was close to winning... ");
                        board[0][0] = computerCharacter;
                        playOrder++;
                        onlyOneAttempt++;
                    }
                }
            }
            //right to left diagonal
            if (board[0][2].equals(character)) {
                if (board[1][1].equals(character)) {
                    if (board[2][0].equals("-") && onlyOneAttempt == 0) {
                        System.out.println("Computer was close to winning... ");
                        board[2][0] = computerCharacter;
                        playOrder++;
                        onlyOneAttempt++;
                    }

                } else {
                    if (board[2][0].equals(character)) {
                        if (board[1][1].equals("-") && onlyOneAttempt == 0) {
                            System.out.println("Computer was close to winning... ");
                            board[1][1] = computerCharacter;
                            playOrder++;
                            onlyOneAttempt++;
                        }
                    }
                }
            } else if (board[1][1].equals(character)) {
                if (board[2][0].equals(character)) {
                    if (board[0][2].equals("-") && onlyOneAttempt == 0) {
                        System.out.println("Computer was close to winning... ");
                        board[0][2] = computerCharacter;
                        playOrder++;
                        onlyOneAttempt++;
                    }
                }
            }
        }
        return playOrder;
    }

    public static int computerGo(String[][]board, String computerCharacter, int playOrder){
        System.out.println("Computer's move: ");
        Random rand = new Random();
        int compRow = rand.nextInt(2);
        int compColumn = rand.nextInt(2);
        while (!board[compRow][compColumn].equals("-")) {
            compRow = rand.nextInt(2);
            compColumn = rand.nextInt(2);
        }
        playOrder++;
        board[compRow][compColumn] = computerCharacter;
        System.out.println();
        return playOrder;
    }
}
