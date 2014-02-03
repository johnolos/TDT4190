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
	private static Registry registry;
	private String url;
	private TicTacToe game;

	public ConnectionImpl(boolean server) throws RemoteException {
		System.setSecurityManager(new RMISecurityManager());
		this.url = "rmi://" + "localhost" + ":" + PORT + "/ConnectionImpl";
		
		if(server) {
			server();
		} else {
			client();
			otherPlayer.register(this);
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
	}

	@Override
	public void registerTurn(int x, int y) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nextPlayer() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hasWon() throws RemoteException {
		// TODO Auto-generated method stub
		
	}
}