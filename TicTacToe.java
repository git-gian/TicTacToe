import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();
    public static void main(String[] args){

        char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
                                {'-', '+', '-', '+', '-'},
                                {' ', '|', ' ', '|', ' '},
                                {'-', '+', '-', '+', '-'},
                                {' ', '|', ' ', '|', ' '}};

        printGameBoard(gameBoard);
        playGame(gameBoard);

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

    public static void playGame(char[][] gameBoard){

        int playerPos;
        int cpuPos;
        Random rand = new Random();

        System.out.println("Enter a position on the board 1-9");


        while (checkWinner().equals("")){

            Scanner scan = new Scanner(System.in);
            playerPos = scan.nextInt();
            while (!checkPosition(playerPos)){
                System.out.println("Position occupied! Try another number");
                playerPos = scan.nextInt();
            }
            placePiece(playerPos, gameBoard, "player");

            if (!checkWinner().equals("You win!")){

                cpuPos = rand.nextInt(9) + 1;

                while (!checkPosition(cpuPos)){
                    cpuPos = rand.nextInt(9) + 1;
                }
            
            placePiece(cpuPos, gameBoard, "cpu");
            }

            printGameBoard(gameBoard);
            System.out.println();
        }
    }

    public static boolean checkPosition(int position){

        if (playerPositions.contains(position) || cpuPositions.contains(position)){
            
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

    public static String checkWinner(){

        String winStatement = "";
        
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);

        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);

        List diag1 = Arrays.asList(1, 5, 9);
        List diag2 = Arrays.asList(3, 5, 7);
        
        List<List> winConditions = new ArrayList<List>();

        winConditions.add(topRow);
        winConditions.add(midRow);
        winConditions.add(botRow);
        winConditions.add(leftCol);
        winConditions.add(midCol);
        winConditions.add(rightCol);
        winConditions.add(diag1);
        winConditions.add(diag2);

        for(List l : winConditions){

            if (playerPositions.containsAll(l)){
                
                winStatement = "You win!";
                System.out.println(winStatement);
            }
            else if (cpuPositions.containsAll(l)){

                winStatement = "CPU wins!";
                System.out.println(winStatement);
            }
            else if (playerPositions.size() + cpuPositions.size() == 9){

                winStatement = "Tie game!";
                System.out.println(winStatement);
            }
        }
        
        return winStatement;
    }
}