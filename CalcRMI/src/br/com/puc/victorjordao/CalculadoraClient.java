package br.com.puc.victorjordao;

import java.rmi.*;
import java.net.*;

public class CalculadoraClient { 

	public static void main(String args[]) { 
		try {
			Calculadora c = (Calculadora)Naming.lookup("rmi://localhost/ServicoCalculadora");
			
			System.out.println("4 - 3 = " + c.subtrai(4, 3));
			System.out.println("4 + 5 = " + c.soma(4, 5));
			System.out.println("3 * 6 = " + c.multiplica(3, 6));
			System.out.println("9 / 3 = " + c.divide(9, 3));
			
		} catch (Exception e) {
			 System.out.println(e);
		}
	}

}