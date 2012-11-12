package br.com.puc.victorjordao;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public class Client {
	public static void main(String[] args) throws NotBoundException,
			MalformedURLException {
		try {
			Calculator c = (Calculator) Naming.lookup("rmi://localhost/Call");
			c.add(23, 34);
			c.mul(2, 3);
			c.sub(23, 10);
			// Catch the exceptions that may occur - rubbish URL,
			Remote exception;
		} catch (RemoteException re) {
			System.out.println("RemoteException" + re);
		}
	}
}
