package br.com.puc.victorjordao;

public class Cal extends java.rmi.server.UnicastRemoteObject implements
		Calculator {
	public Cal() throws java.rmi.RemoteException {
		super();
	}

	public void add(int a, int b) throws java.rmi.RemoteException {
		int sum = a + b;
		System.out.println("Sum:" + sum);
	}

	public void mul(int a, int b) throws java.rmi.RemoteException {
		int mul = a * b;
		System.out.println("Mul:" + mul);
	}

	public void sub(int n, int m) throws java.rmi.RemoteException {
		int sub = n - m;
		System.out.println("Sub:" + sub);
	}
}