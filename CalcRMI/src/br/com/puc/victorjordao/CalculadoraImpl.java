package br.com.puc.victorjordao;


import java.rmi.*;
import java.rmi.server.*;

public class CalculadoraImpl extends UnicastRemoteObject implements Calculadora { 

	public CalculadoraImpl() throws RemoteException {
		super(); 
	}
	
	public long soma(long a, long b) throws RemoteException { 
		return a + b; 
	}
	
	public long subtrai(long a, long b) throws RemoteException { 
		return a - b; 
	}
	
	public long multiplica(long a, long b) throws RemoteException { 
		return a * b; 
	}
	
	public long divide(long a, long b) throws RemoteException { 
		return a / b; 
	}

}