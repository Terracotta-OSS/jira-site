/*
 *	Scratchpad.
 *
 *	Copyright (c) EADS-DS 2011 All rights reserved
 *	This file and the information it contains are property of EADS-DS
 *	and confidential. They shall not be reproduced nor disclosed to
 *	any person except to those having a need to know them without
 *	prior written consent of EADS-DS.
 */

package tc;

import java.util.concurrent.TimeUnit;

import org.terracotta.api.TerracottaClient;


/**
 * TODO ClientShutdownTest.
 * 
 * @author Nicolas Estrada.
 */

public class ClientShutdownTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("Starting terracotta client...");

		long now = System.nanoTime();
		TerracottaClient client = new TerracottaClient("localhost:9510");
		client.getToolkit();

		long delta = System.nanoTime() - now;
		System.out.println("Startup took " + TimeUnit.NANOSECONDS.toMillis(delta)
				+ " milliseconds!");

		System.out.println("Shutting down terracotta client...");

		now = System.nanoTime();
		client.shutdown();
		delta = System.nanoTime() - now;

		System.out.println("Shutdown took " + TimeUnit.NANOSECONDS.toMillis(delta)
				+ " milliseconds.");

	}
}
