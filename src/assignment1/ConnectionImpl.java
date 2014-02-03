package assignment1;

import java.io.IOException;
import java.net.MalformedURLException;
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
	private Registry registry;
	private String url;

	public ConnectionImpl(boolean server) throws RemoteException {
		System.setSecurityManager(new RMISecurityManager());
		this.url = "rmi://" + "localhost" + ":" + PORT + "/ConnectionImpl";
		
		if(server) {
			server();
		} else {
			client();
		}
		
//		try {
//			System.out.println("Looking up someone!");
//			otherPlayer = (Connection) Naming.lookup(url);
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (NotBoundException e) {
//			e.printStackTrace();
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		} finally {
//			if (otherPlayer == null) {
//				System.out
//						.println("No players online! Please wait for someone to connect.");
//				Connection inter = this;
//				try {
//					Naming.bind("url", inter);
//				} catch (MalformedURLException | AlreadyBoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				// ConnectionImpl stub =
//				// (ConnectionImpl)UnicastRemoteObject.exportObject(this,3005 +
//				// 31*10);
//				// this.registry.rebind("ConnectionImpl", stub);
//				catch (RemoteException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			} else {
//			}
//		}
	}
	
	/**
	 * Server specific code to be run
	 */
	private void server() {
		try {
			this.registry = LocateRegistry.createRegistry(PORT);
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
			this.registry = LocateRegistry.getRegistry("localhost",PORT);
			this.otherPlayer = (Connection)this.registry.lookup("ConnectionImpl");
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
	}
}