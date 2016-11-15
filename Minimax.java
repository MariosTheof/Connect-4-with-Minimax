import java.util.ArrayList;
import java.util.Random;

public class Minimax {

  private int maxDepth;
  private int playerLetter;

  public Minimax() {
    maxDepth = 4;
    playerLetter = Board.X;
  }

  public Minimax(int maxDepth, int playerLetter) {
    this.maxDepth = maxDepth;
    this.playerLetter = playerLetter;
  }

  public Move MoveMiniMax(Board board) {
    if (playerLetter == Board.X) {
      return maximum(new Board(board), 0);
    } else {
      return minimum(new Board(board), 0);
    }
  }

  public int getMaxDepth() {
    return maxDepth;
  }

  public void setMaxDepth(int maxDepth) {
    this.maxDepth = maxDepth;
  }

  public int getPlayerLetter(){
    return playerLetter;
  }

  public void setPlayerLetter(int playerLetter){
    this.playerLetter = playerLetter;
  }



  // The maximum and minimum functions are called interchangingly, one after another until a max depth is reached

  //Min works similarly to max
public Move minimum(Board board, int depth) {
      Random r = new Random();

  if((board.isStalemate()) || (depth == maxDepth)) {
    Move lastMove = new Move(board.getLastMove().getRow(), board.getLastMove().getCol(), board.evaluate());
    return lastMove;
  }
  ArrayList<Board> children = new ArrayList<Board>(board.getChildren(Board.O));
  Move minMove = new Move(Integer.MAX_VALUE);
  for (Board child : children) {
    Move move = maximum(child, depth + 1);
    if(move.getValue() <= minMove.getValue()) {
              if ((move.getValue() == minMove.getValue())) {
                  if (r.nextInt(2) == 0) {
                      minMove.setRow(child.getLastMove().getRow());
                      minMove.setCol(child.getLastMove().getCol());
                      minMove.setValue(move.getValue());
                  }
              }
              else {
                      minMove.setRow(child.getLastMove().getRow());
                      minMove.setCol(child.getLastMove().getCol());
                      minMove.setValue(move.getValue());
              }
          }
      }
      return minMove;
}


public Move maximum(Board board, int depth) {
      Random r = new Random();

      /* If MAX is called on a state that is terminal or after a maximum depth is reached,
       * then a heuristic is calculated on the state and the move returned.
       */
  if((board.isStalemate()) || (depth == maxDepth))
  {
    Move lastMove = new Move(board.getLastMove().getRow(), board.getLastMove().getCol(), board.evaluate());
    return lastMove;
  }
      //The children-moves of the state are calculated
  ArrayList<Board> children = new ArrayList<Board>(board.getChildren(Board.X));
  Move maxMove = new Move(Integer.MIN_VALUE);
  for (Board child : children) {
          //And for each child min is called, on a lower depth
    Move move = minimum(child, depth + 1);
          //The child-move with the greatest value is selected and returned by max
    if(move.getValue() >= maxMove.getValue()) {
              if ((move.getValue() == maxMove.getValue())) {
                  //If the heuristic has the same value then we randomly choose one of the two moves
                  if (r.nextInt(2) == 0) {
                      maxMove.setRow(child.getLastMove().getRow());
                      maxMove.setCol(child.getLastMove().getCol());
                      maxMove.setValue(move.getValue());
                  }
              }
              else {
                  maxMove.setRow(child.getLastMove().getRow());
                  maxMove.setCol(child.getLastMove().getCol());
                  maxMove.setValue(move.getValue());
              }
    }
  }
  return maxMove;
}

}
