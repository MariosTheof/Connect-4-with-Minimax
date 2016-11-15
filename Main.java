import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    int maxDepth = 4;
    int XColPos;
    Minimax ai = new Minimax(maxDepth, Board.X);
    Board connect4 = new Board();

    System.out.println("***********************");

    connect4.print();
    Scanner in = new Scanner(System.in);
    while(!connect4.isStalemate()) {
      System.out.println();


      if (connect4.getLastLetterPlayed() == Board.O) {
        System.out.println("Human moves.");
        System.out.println(" Choose column from 1-7 :");
        XColPos = in.nextInt();

        if(XColPos > 7 || XColPos < 1 ){
          System.err.println("Invalid Number.");
          break;
        }
        connect4.makeMove(XColPos - 1, Board.X);


      } else if (connect4.getLastLetterPlayed() == Board.X){
        System.out.println("\n Computer moves ");
        Move OMove = ai.MoveMiniMax(connect4);
        connect4.makeMove(OMove.getCol(), Board.O);
        
      }
      connect4.print();
    }

    if (connect4.getWinner() == Board.X) {
      System.out.println("Human player 'X' wins!");
    } else if (connect4.getWinner() == Board.O) {
      System.out.println("AI computer 'O' wins!");
    } else {
      System.out.println("It's a draw!");
    }
  }
}
