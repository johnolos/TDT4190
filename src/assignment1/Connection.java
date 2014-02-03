package assignment1;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Connection extends Remote {
		
	/*
	 * Function used to remotely send your object reference over to
	 * your opponent when game is initiated. For a game to be played,
	 * both must know the other.
	 * 
	 * Only called by the client.
	 */
	public void register(Connection player) throws RemoteException;
	
	
	/*
	 * Function used to register a player's turn.
	 */
	public boolean registerTurn(int x, int y, char playerMark) throws RemoteException;
	
	
	/*
	 * Function used to remotely tell the other player that it is the
	 * other player's turn.
	 * 
	 * Called by both client and server.
	 */
	public void nextPlayer() throws RemoteException;
	
	
	/*
	 * Function used to remotely indicate that a player has won.
	 * Both players should be asked to evaluate the current state
	 * of the game to evaluate who won.
	 * 
	 * Called by both client and server.
	 */
	public void hasWon(char playerMark) throws RemoteException;
	
	
}
