package assignment1;

import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
	
	public static void main(String args[]) {
		try {			
			ConnectionImpl connection = new ConnectionImpl(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
