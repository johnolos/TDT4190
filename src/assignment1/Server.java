package assignment1;

import java.io.IOException;

public class Server {
	
	/**
	 * Class to run server version of the ConnectionImpl.
	 * @param args
	 */
	
	public static void main(String args[]) {
		try {
			ConnectionImpl connection = new ConnectionImpl(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
