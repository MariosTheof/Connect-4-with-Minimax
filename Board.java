import java.util.ArrayList;

public class Board
{
    //Variables for the Boards values
	public static final int X = 1;
	public static final int O = -1;
	public static final int EMPTY = 0;

    //Immediate move that lead to this board
    private Move lastMove;

    /* Variable containing who played last; whose turn resulted in this board
     * Even a new Board has lastLetterPlayed value; it denotes which player will play first
     */
	private int lastLetterPlayed;

	private int [][] gameBoard;
  private int winner;

	public Board()
	{
		lastMove = new Move();
		lastLetterPlayed = O;
		gameBoard = new int[6][7];
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 7; j++){
				gameBoard[i][j] = EMPTY;
			}
		}
	}

	public Board(Board board)
	{
		lastMove = board.lastMove;
		lastLetterPlayed = board.lastLetterPlayed;
		gameBoard = new int[6][7];
		for(int i = 0; i < 6; i++){
			for(int j = 0; j < 7; j++){
				gameBoard[i][j] = board.gameBoard[i][j];
			}
		}
	}

	public Move getLastMove()
	{
		return lastMove;
	}

	public int getLastLetterPlayed()
	{
		return lastLetterPlayed;
	}

	public int[][] getGameBoard()
	{
		return gameBoard;
	}

	public int getWinner() {
		return winner;
	}

	public void setWinner(int winner) {
		this.winner = winner;
	}

	public void setLastMove(Move lastMove)
	{
		this.lastMove.setRow(lastMove.getRow());
		this.lastMove.setCol(lastMove.getCol());
		this.lastMove.setValue(lastMove.getValue());
	}

	public void setLastLetterPlayed(int lastLetterPlayed)
	{
		this.lastLetterPlayed = lastLetterPlayed;
	}

	public void setGameBoard(int[][] gameBoard)
	{
		for(int i = 0; i < 6; i++)
		{
			for(int j = 0; j < 7; j++)
			{
				this.gameBoard[i][j] = gameBoard[i][j];
			}
		}
	}

    //Make a move; it places a letter in the board
	public void makeMove(int row, int col, int letter)
	{
    lastMove = new Move(row, col);
		gameBoard[row][col] = letter;
		lastLetterPlayed = letter;
	}

    //Checks whether a move is valid; whether a square is empty
	public boolean isValidMove(int row, int col)
	{
		if ((row == -1) || (col == -1) || (row > 5) || (col > 6))
		{
			return false;
		}
		if(gameBoard[row][col] != EMPTY)
		{
			return false;
		}
		return true;
	}

    //  Generates the children of the state
	public ArrayList<Board> getChildren(int letter)
	{
		ArrayList<Board> children = new ArrayList<Board>();
		for(int row = 0; row < 6; row++)
		{
			for(int col=0; col < 7; col++)
			{
				if(isValidMove(row, col))
				{
					Board child = new Board(this);
					child.makeMove(row, col, letter);
					children.add(child);
				}
			}
		}
		return children;
	}


	public int evaluate()
	{
	   int Xlines = 0;
     int Olines = 0;

     if (hasSomeoneWon()){
			 if (getWinner() == X){
				 Xlines = Xlines + 100;
			 } else {
				 Olines = Olines + 100;
			 }
		 }
		 return Xlines - Olines;  
	}



  public boolean hasSomeoneWon() {

		//Checks if win in a column
    for (int i = 5; i >= 3; i--) {
      for (int j = 0; j < 4; j++) {
        if (gameBoard[i][j] != EMPTY && gameBoard[i][j] == gameBoard[i][j+1] && gameBoard[i][j] == gameBoard[i][j+2] && gameBoard[i][j] == gameBoard[i][j+3]){
					setWinner(gameBoard[i][j]);
					return true;
				}
      }
    }

		//Checks if win in a row
		for (int i = 5; i >= 3; i--) {
			for (int j = 0; j < 7; j++) {
				if (gameBoard[i][j] != EMPTY && gameBoard[i][j] == gameBoard[i-1][j] && gameBoard[i][j] == gameBoard[i-2][j] && gameBoard[i][j] == gameBoard[i-3][j]){
					setWinner(gameBoard[i][j]);
					return true;
				}
			}
		}

		//Checks if win in diagonal
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				if (gameBoard[i][j] != EMPTY && gameBoard[i][j] == gameBoard[i+1][j+1] && gameBoard[i][j] == gameBoard[i+2][j+2] && gameBoard[i][j] == gameBoard[i+3][j+3]){
					setWinner(gameBoard[i][j]);
					return true;
				}
			}
		}
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				if (gameBoard[i][j] != EMPTY && gameBoard[i][j] == gameBoard[i-1][j+1] && gameBoard[i][j] == gameBoard[i-2][j+2] && gameBoard[i][j] == gameBoard[i-3][j+3]){
					setWinner(gameBoard[i][j]);
					return true;
				}
			}
		}

		setWinner(0);
		return false;
  }

	public boolean isStalemate(){
		if (hasSomeoneWon()) {
			return true;
		}

		for (int row = 0; row < 6; row++) {
			for (int col = 0; col < 7; col++) {
				if (gameBoard[row][col] == EMPTY) {return false;}
			}
		}
		return true;
	}


    //Prints the board
	public void print()
	{
		System.out.println("***************************\n");
		System.out.println("| 1 | 2 | 3 | 4 | 5 | 6 | 7 |");
		for(int row = 0; row < 6; row++) {
			for(int col = 0; col < 7; col++) {
				if (col != 6) {
					if (gameBoard[row][col] == 1) {
						System.out.print("| " + "X" + " ");
					} else if (gameBoard[row][col] == -1) {
						System.out.print("| " + "O" + " ");
					} else {
						System.out.print("| " + "-" + " ");
					}
				} else {
					if (gameBoard[row][col] == 1) {
						System.out.println("| " + "X" + " |");
					} else if (gameBoard[row][col] == -1) {
						System.out.println("| " + "O" + " |");
					} else {
						System.out.println("| " + "-" + " |");
					}
				}
			}
		}
	}
}
