package assignment4;

import java.io.Serializable;
import java.util.ArrayList;

public class ProbeMessage implements Serializable{

	int resourceId;
	ArrayList<Integer> path;

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

	
	// id-id-id-id
	
	ProbeMessage(int transactionId, int resourceId) {
		this.path = new ArrayList<Integer>();
		this.path.add(transactionId);
		this.resourceId = resourceId;
//		this.path.add(otherProcessId);
	}
	
	public void addPath(int transactionId) {
		this.path.add(transactionId);
	}
	
	public boolean evaluateDeadLock() {
		if(path.size() < 2) {
			return false;
		}
		for(int i = 0; i <= path.size() - 2; i++) {
			if(path.get(i).compareTo( path.get(path.size() - 1)) == 0) {
				return true;
			}
		}
		return false;
	}
	
	public int getDeadLockID() {
		return path.get(path.size() - 1);
	}
	
//	
//	public static void main(String args[]) {
//		ProbeMessage test1 = new ProbeMessage(2001, 1002);
//		test1.addPath(2002);
//		test1.addPath(2001);
//		
//		ProbeMessage test2 = new ProbeMessage(2002, 2003);
//		test2.addPath(2003);
//		
//		
//		ProbeMessage test4 = new ProbeMessage(2002, 2003);
//		test4.addPath(1001);
//		test4.addPath(2002);
//		test4.addPath(2003);
//		
//		
//		
//		
//		
//		System.out.println(test1.evaluateDeadLock());
//		System.out.println(test2.evaluateDeadLock());
//		System.out.println(test4.evaluateDeadLock());
//		
//	}

}
