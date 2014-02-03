package assignment1;

import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
	
	public static void main(String args[]) {
		// git add "navn p� endrede filer eller mapper"
		// git commit -m "Beskjed p� commit"
		// git push origin master https://github.com/johnolos/TDT4190.git
		try {
//			Registry registry = LocateRegistry.createRegistry(n);
//			//Runtime.getRuntime().exec("rmiregistry");
			
			ConnectionImpl connection = new ConnectionImpl(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
