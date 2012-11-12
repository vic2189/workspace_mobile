package br.com.puc.victorjordao;

import java.rmi.Naming;

public class Call {
	public Call() {
		try {
			Cal c = new Cal();
			Naming.rebind("rmi://localhost/Call", c);
		} catch (Exception e) {
			System.out.println("Server Error: " + e);
		}
	}

	public static void main(String args[]) {// Create the new Calculator
											// servernew Call();}}
	}
}