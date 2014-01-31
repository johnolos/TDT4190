package assignment1;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Connection extends Remote {
	
	// Functions to be available for remote access should be written here.
	// They shall also be casting RemoteException
	
	/*
	 * Function used to remotely send your object reference over to
	 * your opponent when game is initiated. For a game to be played,
	 * both must know the other.
	 */
	public void register(Connection player) throws RemoteException;
	

}
