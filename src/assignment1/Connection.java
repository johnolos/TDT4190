package assignment1;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Connection extends Remote {
	/**
	 * Interface of Connection. This interfaced is used to send
	 * over with Java RMI with reference to the class implementing
	 * this interface
	 */
	
	
	
	/**
	 * Function used to remotely send your object reference over to
	 * your opponent when game is initiated. For a game to be played,
	 * both must know the other.
	 * 
	 * Only called by the client.	 
	 * @param player
	 * @throws RemoteException
	 */
	public void register(Connection player) throws RemoteException;
	
	

	/**
	 * Function used to remotely register selected placement and which
	 * char it shall assign to that place.
	 * 
	 * Called by both client and server
	 * @param x
	 * @param y
	 * @param playerMark
	 * @return
	 * @throws RemoteException
	 */
	public boolean registerTurn(int x, int y, char playerMark) throws RemoteException;
	
	
	/**
	 * Function used to remotely tell the other player that it is the
	 * other player's turn.
	 * 
	 * Called by both client and server.
	 * @throws RemoteException
	 */
	public void nextPlayer() throws RemoteException;
	
	/**
	 * Function used to remotely indicate that a player has won.
	 * Both players should be asked to evaluate the current state
	 * of the game to evaluate who won.
	 * 
	 * Called by both client and server.
	 * @param playerMark Char
	 * @throws RemoteException
	 */
	public void hasWon(char playerMark) throws RemoteException;
	
	
}
