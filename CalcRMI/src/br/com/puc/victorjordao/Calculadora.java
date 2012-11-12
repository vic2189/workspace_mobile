package br.com.puc.victorjordao;



import java.rmi.*;

public interface Calculadora extends Remote {
	
	public long soma(long a, long b) throws RemoteException;
	public long subtrai(long a, long b) throws RemoteException;
	public long multiplica(long a, long b) throws RemoteException; 
	public long divide(long a, long b) throws RemoteException;
	
} 