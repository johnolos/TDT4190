package assignment1;

import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class InitiateGame {
	
	public static void main(String args[]) {
		int n = 3005 + 31*10;
		try {
//			Registry registry = LocateRegistry.createRegistry(n);
//			//Runtime.getRuntime().exec("rmiregistry");
			ConnectionImpl connection = new ConnectionImpl(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
