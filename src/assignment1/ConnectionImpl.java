package assignment1;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.*;

public class ConnectionImpl extends UnicastRemoteObject implements Connection {

	/**
	 * Working implementation of Connection interface.
	 */
	private static final long serialVersionUID = 7639896393776979985L;
	public static final int PORT = 3005 + 31*10; 	/** Connection port **/
	private Connection otherPlayer; 				/** Reference to other player **/
	private boolean server; 						/** Save behavior as boolean value **/
	private static Registry registry; 				/** Registry reference **/
	private TicTacToe game; 						/** Game reference **/

	
	/**
	 * Constructor for implementation of Connection interface
	 * 
	 * @param server True if it shall behave like a server
	 * @throws RemoteException
	 */
	public ConnectionImpl(boolean server) throws RemoteException {
		this.server = server;
		System.setSecurityManager(new RMISecurityManager());
		if(server) {
			server();
		} else {
			client();
			otherPlayer.register((Connection)this);
			// Initiate game-window
			System.out.println("Starting game...");
			this.game = new TicTacToe(this.otherPlayer, this.server, 'O');
		}
	}
	
	/**
	 * Server specific code to be run
	 */
	private void server() {
		try {
			// Register a new registry
			ConnectionImpl.registry = LocateRegistry.createRegistry(PORT);
			// Bind interface with reference to this class to this registry
			registry.rebind("ConnectionImpl", (Connection)this);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		System.out.println("System is ready");
	}
	
	
	
	/**
	 * Client specific code to be run
	 */
	private void client() {
		try {
			// Try to get registry if it is available
			ConnectionImpl.registry = LocateRegistry.getRegistry("localhost",PORT);
			// Try to get other player from that registry
			this.otherPlayer = (Connection)ConnectionImpl.registry.lookup("ConnectionImpl");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		System.out.println("Connection has been made");
	}

	@Override
	public void register(Connection player) {
		this.otherPlayer = player;
		System.out.println("Player connected.");
		System.out.println("Starting game...");
		// Create a game instance of TicTacToe
		this.game = new TicTacToe(this.otherPlayer, this.server, 'X');
	}

	@Override
	public boolean registerTurn(int x, int y, char playerMark) throws RemoteException {
		// Returns true if the game locally have detected a winner
		return this.game.getBoardModel().setCell(x, y, playerMark);
		
	}

	@Override
	public void nextPlayer() throws RemoteException {
		// Change locally player
		this.game.changePlayer();
	}

	@Override
	public void hasWon(char playerMark) throws RemoteException {
		// Runs local code in game to indicate that the game is finished
		this.game.gameFinished();
		// Change the status message to indicate that the game is finished
		this.game.setStatusMessage("Player " + playerMark + " won!");
	}
}