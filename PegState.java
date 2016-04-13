/**
 * Supplementary method to PegGameThatActuallyWorksForRealThough
 * 
 * @author Brian Waldron
 * @author Hunter Irving
 *
 * @date Seriously. Please. I'm tired of crying myself to sleep at night.
 */

public class PegState implements State
{
	public boolean[] board; //The peg board.
	
	/**
	 * Constructor. Duh.
	 */
	public PegState()
	{
		board = new boolean[15];
		
		for (int i = 0; i < board.length; i++)
		{
			board[i] = true;
		}
	}
	
	/**
	 * Hashes the board based on a binary conversion.
	 * Also, mmm. String cheese.
	 * 
	 * @return hash code
	 */
	public int hashCode()
	{
		String cheese = "";
		
		for (int i = 0; i < board.length; i++)
		{
			if (board[i])
			{
				cheese += "1";
			}
			else
			{
				cheese += "0";
			}
		}
		
		int hash = Integer.parseInt(cheese,2);
		
		return hash;	
	}
	
	/**
	 * Returns value of board at index x.
	 * 
	 * @param x index
	 * @return value of board at x
	 */
	public boolean getBoard(int x)
	{
		return board[x];
	}
	
	/**
	 * Returns number of pegs on the current board.
	 * 
	 * @return number of pegs
	 */
	public int getPegs()
	{
		int count = 0;
		
		for (int i = 0; i < board.length; i++)
		{
			if (board[i])
			{
				count++;
			}
		}
		
		return count;
	}
	
	/**
	 * Sets value of board at index x to value val.
	 * 
	 * @param x index
	 * @param val value to set
	 */
	public void setBoard(int x, boolean val)
	{
		board[x] = val;
	}
	
	/**
	 * Creates a clone of the current board.
	 * 
	 * @return cloned board
	 */
	public PegState clone()
	{
		PegState newState = new PegState();
		newState.copy(this);
		return newState;
	}
	
	/**
	 * Copies the board. Because reasons.
	 */
	public void copy(State otherPeg)
	{
		PegState comp = (PegState) otherPeg;
		for (int i = 0; i < board.length; i++)
		{
			board[i] = comp.board[i];
		}
	}
	
	/**
	 * Compares two boards.
	 * 
	 * @return boolean of comparison
	 */
	@SuppressWarnings("unchecked")
	public boolean equals(Object otherPeg)
	{
		if (otherPeg instanceof PegState)
		{
			PegState comp = (PegState) otherPeg;
			for (int i = 0; i < board.length; i++)
			{
				if (board[i] != comp.board[i])
				{
					return false;
				}
			}
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * Paints a pretty picture.
	 */
	public void prettyPicture()
	{
		System.out.println(
			    "\n_____________________\n" +
			    "\\ "+pegCheck(board, 10)+"   "+pegCheck(board, 11)+"   "+pegCheck(board, 12)+"   "+pegCheck(board, 13)+"   "+pegCheck(board, 14)+" /\n" +
				" \\                 /\n" +
				"  \\ "+pegCheck(board, 6)+"   "+pegCheck(board, 7)+"   "+pegCheck(board, 8)+"   "+pegCheck(board, 9)+" /\n"	+
				"   \\             /\n"  	+
				"    \\ "+pegCheck(board, 3)+"   "+pegCheck(board, 4)+"   "+pegCheck(board, 5)+" /\n"		+
				"     \\         /\n"		+
				"      \\ "+pegCheck(board, 1)+"   "+pegCheck(board, 2)+" /\n"		+
				"       \\     /\n"		    +
				"        \\ "+pegCheck(board, 0)+" /\n"			+
				"         \\ /\n"			+
				"	  V\n");
	}
	
	/**
	 * Helper method for pretty picture.
	 * @param board the board
	 * @param i the index
	 * @return appropriately spaced digit(s)
	 */
	public String pegCheck(boolean[] board, int i)
	{
		String returnMe;
		if(board[i] == false)
		{
			returnMe = ".";
		}
		else
		{
			returnMe = "O";
		}
		return returnMe;
	}
}
