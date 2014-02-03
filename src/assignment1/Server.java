package assignment1;

import java.io.IOException;

public class Server {
	
	public static void main(String args[]) {
		try {
			ConnectionImpl connection = new ConnectionImpl(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
