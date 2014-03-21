package assignment1;

import java.io.IOException;

public class Client {
	
	
	/**
	 * Class to run the Client version of ConnectionImpl.
	 * @param args
	 */
	public static void main(String args[]) {
		try {			
			ConnectionImpl connection = new ConnectionImpl(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}