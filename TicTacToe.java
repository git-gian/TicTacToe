import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>(0);
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>(0);

    static List topRow = Arrays.asList(1, 2, 3);
    static List midRow = Arrays.asList(4, 5, 6);
    static List botRow = Arrays.asList(7, 8, 9);

    static List leftCol = Arrays.asList(1, 4, 7);
    static List midCol = Arrays.asList(2, 5, 8);
    static List rightCol = Arrays.asList(3, 6, 9);

    static List diag1 = Arrays.asList(1, 5, 9);
    static List diag2 = Arrays.asList(3, 5, 7);

    static char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
                                {'-', '+', '-', '+', '-'},
                                {' ', '|', ' ', '|', ' '},
                                {'-', '+', '-', '+', '-'},
                                {' ', '|', ' ', '|', ' '}};

    static List<List> winConditions = new ArrayList<List>();

    static int winCount = 0;
    static int cpuWinCount = 0;
    static int tieCount = 0;
    public static void main(String[] args){

        winConditions.add(topRow);
        winConditions.add(midRow);
        winConditions.add(botRow);
        winConditions.add(leftCol);
        winConditions.add(midCol);
        winConditions.add(rightCol);
        winConditions.add(diag1);
        winConditions.add(diag2);

        printGameBoard(gameBoard);
        playGame(gameBoard);
        System.out.println();
    }

    /*
    * printGameBoard takes the 2D array 
    * created in the main method 
    * and prints it out to the console.
    */
    public static void printGameBoard(char[][] gameBoard){

        for(char[] row : gameBoard){ //for each ROW in gameboard 2D array
            for(char c : row){ //for each space in each ROW
                
                System.out.print(c);
            }
            System.out.println();
        }
    }

    /* This method is where the user inputs the position
        of where they want to place their piece.
        Handled by while loop and calling other
        helper methods to check validity of state of game.

    */
    public static void playGame(char[][] gameBoard){

        int playerPos;
        int cpuPos;
        Random rand = new Random();
        Scanner scan = new Scanner(System.in);
        String again = "";

        System.out.println("Enter a position on the board 1-9");


        while (!checkWinner()){

            playerPos = scan.nextInt();
            while (!checkPosition(playerPos)){
                System.out.println("Invalid position! Try another number \n");
                playerPos = scan.nextInt();
            }
            placePiece(playerPos, gameBoard, "player");
           
            if(playerPositions.size() + cpuPositions.size() < 9){
                cpuPos = rand.nextInt(9) + 1;
                while (!checkPosition(cpuPos)){

                    cpuPos = rand.nextInt(9) + 1;
                }

                placePiece(cpuPos, gameBoard, "cpu");
            }

            printGameBoard(gameBoard);
            System.out.println();
        }
        
        System.out.println("You've won " + winCount + " times, lost to the CPU " + cpuWinCount + " times, and tied " + tieCount + " times!");
        System.out.println("Play again? (Y/N)");
        again = scan.next();
        playAgain(again);
    }

    public static boolean checkPosition(int position){

        if ((playerPositions.contains(position) || cpuPositions.contains(position)) || ((position < 1) || (position > 9))){
            
            return false;
        }
        else{
            return true;
        }
    }

    /*
    * placePiece takes the position that the User inputs
    * and places it on the gameBoard as an X. If the CPU is
    * placing a piece, it will place an O in the assigned position
    */
    public static void placePiece(int position, char[][] gameBoard, String user){

        char piece = ' ';

        if (user.equals("player")){ // if the user placing is placing the piece, the piece is an X
            piece = 'X';
            playerPositions.add(position);
        }
        else if (user.equals("cpu")){ // if the CPU is pacing the piece, the piece is an O
            piece = 'O';
            cpuPositions.add(position);
        }

        switch(position){

            case 1:
            gameBoard[0][0] = piece;
                break;

            case 2:
            gameBoard[0][2] = piece;
                break;

            case 3:
            gameBoard[0][4] = piece;
                break;

            case 4:
            gameBoard[2][0] = piece;
                break;

            case 5:
            gameBoard[2][2] = piece;
                break;

            case 6:
            gameBoard[2][4] = piece;
                break;

            case 7:
            gameBoard[4][0] = piece;
                break;

            case 8:
            gameBoard[4][2] = piece;
                break;

            case 9:
            gameBoard[4][4] = piece;
                break;

            default:
                break;

        }
    }

    public static boolean checkWinner(){

        String winStatement;

        for(List l : winConditions){

            if (playerPositions.containsAll(l)){
                
                winStatement = "You win!";
                winCount++;
                System.out.println(winStatement);
                
                return true;
            }
            else if (cpuPositions.containsAll(l)){

                winStatement = "CPU wins!";
                cpuWinCount++;
                System.out.println(winStatement);

                return true;
            }
            else if (playerPositions.size() + cpuPositions.size() == 9 && !playerPositions.containsAll(l) && !cpuPositions.containsAll(l)){

                winStatement = "Tie game!";
                tieCount++;
                System.out.println(winStatement);

                return true;
            }
        }

        return false;
    }

    /* Resets the game board to empty spaces where pieces
        were played in the previous game.
        Also resets the array list for the player and cpu positions.
        Redirects back to playGame() method

        if option is No, do nothing (code exit?)
    */
    public static void playAgain(String a){

        if (a.equals("Y")){

            for (int i = 0; i < gameBoard.length; i++){
                for (int j = 0; j < gameBoard[i].length; j++){

                    if (gameBoard[i][j] == 'X' || gameBoard[i][j] == 'O'){
                        gameBoard[i][j] = ' ';
                    }
                }
            }

            playerPositions.clear();
            cpuPositions.clear();

            printGameBoard(gameBoard);
            playGame(gameBoard);
        }
        else if (a.equals("N")){
        }
    }
}