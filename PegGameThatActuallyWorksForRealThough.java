import java.util.Scanner;

/**
 * Brian Waldron and Hunter Irving did this.
 * @author Brian Waldron
 * @author Hunter Irving
 * 
 * @date Please. I'm so lonely.
 */

public class PegGameThatActuallyWorksForRealThough extends AbstractGame<PegState>
{
	/**
	 * Double array of legal moves, in order of: From, Over, To.
	 */
	final private int[][] listOfMoves = 
		{
/* 0 */		{0, 1, 3}, {0, 2, 5},			
/* 2 */		{1, 3, 6}, {1, 4, 8},
/* 4 */		{2, 4, 7}, {2, 5, 9},
/* 6 */		{3, 6, 10}, {3, 1, 0}, {3, 4, 5}, {3, 7, 12},
/*10 */		{4, 7, 11}, {4, 8, 13},
/*12 */		{5, 8, 12}, {5, 4, 3}, {5, 2, 0}, {5, 9, 14},
/*16 */		{6, 3, 1}, {6, 7, 8},
/*18 */		{7, 4, 2}, {7, 8, 9},
/*20 */		{8, 7, 6}, {8, 4, 1},
/*22 */		{9, 8, 7}, {9, 5, 2},
/*24 */		{10, 6, 3}, {10, 11, 12},
/*26 */		{11, 7, 4}, {11, 12, 13},
/*28 */		{12, 11, 10}, {12, 7, 3}, {12, 8, 5}, {12, 13, 14},
/*32 */		{13, 12, 11}, {13, 8, 4},
/*34 */		{14, 13, 12}, {14, 9, 5}
		};
	
	private int err; //error message counter

	PegState scratchState;

	/**
	 * The constructor. No sh*t, Sherlock.
	 * 
	 * @param sType Necessary parameter for super constructor.
	 */
	public PegGameThatActuallyWorksForRealThough(int sType)
	{
		super(32768,sType);
		scratchState = new PegState();
		err = 0;
	}
	
	/**
	 * Sets up board, takes user input on start state.
	 */
	public void initGame()
	{
		PegState inputState = makeInitialState();
		
		Scanner kb = new Scanner(System.in);
		System.out.println("_____________________"   + "\n" +
		        "\\10  11  12  13  14 /"        + "\n" +
			" \\                 /"         + "\n" +
			"  \\ 6   7   8   9 /"	        + "\n" +
			"   \\             /"           + "\n" +
			"    \\ 3   4   5 /"		+ "\n" +
			"     \\         /"		+ "\n" +
			"      \\ 1   2 /"		+ "\n" +
			"       \\     /"		+ "\n" +
			"        \\ 0 /"		+  "\n" +
			"         \\ /"			+ "\n" +
			"	  v");
		System.out.println("Enter a starting position (0 - 14): ");
		int start = kb.nextInt();

		while (start < 0 || start > 14)
		{
			errorMessenger();
			System.out.println("Enter a starting position (0 - 14): ");
			start = kb.nextInt();
		}
		inputState.setBoard(start,  false);
		kb.close();
		
		startGame(inputState);
	}

	/**
	 * Adds children to hash table.
	 */
	public void addChildren()
	{
		for (int i = 0; i < listOfMoves.length; i++)
		{
			if (currentState.getBoard(listOfMoves[i][0]) != currentState.getBoard(listOfMoves[i][2]) 
					&& currentState.getBoard(listOfMoves[i][1]))
			{
				scratchState.copy(currentState);
				scratchState.setBoard(listOfMoves[i][0], !currentState.getBoard(listOfMoves[i][0]));
				scratchState.setBoard(listOfMoves[i][1], !currentState.getBoard(listOfMoves[i][1]));
				scratchState.setBoard(listOfMoves[i][2], !currentState.getBoard(listOfMoves[i][2]));
				if (addNewState(scratchState))
				{
					scratchState = new PegState();
				}
			}
		}

	}
	
	/**
	 * Makes clean board.
	 * 
	 * @return initial state
	 */
	public PegState makeInitialState() 
	{
		return new PegState();
	}
	
	/**
	 * Identifies completed board.
	 * 
	 * @return boolean of completion.
	 */	
	public boolean goalState()
	{
		return (currentState.getPegs() == 1);
	}

	/**
	 * Prints output of board.
	 * 
	 * @param st state to print
	 */	
	public void prettyPicture (PegState st)
	{
		PegState someState = st;
		someState.prettyPicture();
	}
	
	/**
	 * Main method.
	 * @param args Command Line prompt
	 */
	public static void main(String args[])
	{
		PegGameThatActuallyWorksForRealThough pegPuzzle;
		int searchMethod;
		
		/*if (args.length < 1)
		{
			System.out.println("Usage: executable inputfilename");
			System.exit(1);
		}*/
		
		if (args.length < 2)
		{
			searchMethod = 1;
		}
		else
		{
			searchMethod = new Integer(args[1]);
		}
		
		pegPuzzle = new PegGameThatActuallyWorksForRealThough(searchMethod);
		
		pegPuzzle.initGame();
		if (pegPuzzle.search())
		{
			System.out.println ("Solution Found");
			pegPuzzle.printSolution();
		}
		else
		{
			System.out.println ("No Solution Found");
		}
		pegPuzzle.printStats();
	}
	
	/**
	 * We got bored.
	 */
	public void errorMessenger()
	{
		err = err % 3;

		if(err == 0)
		{
			System.out.println("\nDoesn't this thing kinda look like a pepperoni pizza?\n"
					+ "...By the way, that's an invalid starting position");
		}
		else if(err == 1)
		{
			System.out.println("\nThat isn't a valid starting position, ignoramus.");
		}
		else if(err == 2)
		{
			System.out.println("\nInvalid starting position? This must be the work of... THE ILLUMINATI");
		}
		err++;
	}
}
