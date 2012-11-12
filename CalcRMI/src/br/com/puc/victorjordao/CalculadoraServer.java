package br.com.puc.victorjordao;


import java.rmi.*;
import java.rmi.server.*;

public class CalculadoraServer { 

	public CalculadoraServer() {
		try {
			System.setSecurityManager(new RMISecurityManager());
			Calculadora c = new CalculadoraImpl();
			Naming.rebind("rmi://127.0.0.1/ServicoCalculadora", c);
			System.out.println("Servico registrado com sucesso. Execute o cliente.");
		} catch (Exception e) {
			System.out.println("Problema: " + e);
		}
	}
	
	public static void main(String args[]) { 
		new CalculadoraServer();
	}
}