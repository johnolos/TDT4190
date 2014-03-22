package assignment4;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.Queue;

public class ProbeMessage extends Thread implements Serializable{
	
	private final ServerImpl server;
	
	private final Queue <Integer> waitingForResource = new LinkedList<Integer>();

	/*
	 * Whenever a process A is blocked for some resource, a probe message is
	 * sent to all processes A may depend on. The probe message contains the
	 * process id of A along with path that the message has followed through the
	 * distributed system. If a blocked process receives the probe, it will
	 * update the path information and forward the probe to all the process it
	 * may depend on. Non-blocked process may discard the probe.
	 * 
	 * If eventually the probe returns to process A, there is a circular waiting
	 * loop of blocked processes, and a deadlock is detected.
	 */

	public ProbeMessage(ServerImpl server) {
		this.server = server;
	}
	
	
	public void run() {
		try {
			server.receiveProbeMessage(waitingForResource);
		} catch (RemoteException e) {
			System.out.println(e.getStackTrace());
		}
	}



}
