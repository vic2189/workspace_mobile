package br.com.puc.victorjordao;

public interface Calculator extends java.rmi.Remote {
	public void add(int num1, int num2) throws java.rmi.RemoteException;

	public void mul(int a, int b) throws java.rmi.RemoteException;

	public void sub(int num1, int num2) throws java.rmi.RemoteException;
}
