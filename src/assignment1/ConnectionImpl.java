package assignment1;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.*;

public class ConnectionImpl extends UnicastRemoteObject implements Connection {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7639896393776979985L;
	public static final int PORT = 3005 + 31*10;
	private Connection otherPlayer;
	private boolean server;
	private static Registry registry;
	private String url;
	private TicTacToe game;

	public ConnectionImpl(boolean server) throws RemoteException {
		this.server = server;
		System.setSecurityManager(new RMISecurityManager());
		this.url = "rmi://" + "localhost" + ":" + PORT + "/ConnectionImpl";
		
		if(server) {
			server();
		} else {
			client();
			otherPlayer.register(this);
			// Initiate game-window
			System.out.println("Starting game...");
			this.game = new TicTacToe(this,this.otherPlayer, this.server, 'O');
		}
	}
	
	/**
	 * Server specific code to be run
	 */
	private void server() {
		try {
			ConnectionImpl.registry = LocateRegistry.createRegistry(PORT);
			registry.rebind("ConnectionImpl", this);
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
			ConnectionImpl.registry = LocateRegistry.getRegistry("localhost",PORT);
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
		this.game = new TicTacToe(this, this.otherPlayer, this.server, 'X');
	}

	@Override
	public boolean registerTurn(int x, int y, char playerMark) throws RemoteException {
		return this.game.getBoardModel().setCell(x, y, playerMark);
		
	}

	@Override
	public void nextPlayer() throws RemoteException {
		this.game.changePlayer();
	}

	@Override
	public void hasWon(char playerMark) throws RemoteException {
		this.game.gameFinished();
		this.game.setStatusMessage("Player " + playerMark + " won!");
	}
}