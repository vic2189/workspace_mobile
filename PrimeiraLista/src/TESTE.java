import javax.swing.JOptionPane;


public class TESTE {

	/**
	 * @param args
	 */
	private static String texto = "AINDA ME LEMBRO DE ALGO!QUE BOM";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		mostraJanela();
		escreveTexto();
	}
	public static void mostraJanela(){
		JOptionPane.showMessageDialog(null, texto);
	}
	
	public static void escreveTexto(){
		System.out.print(texto);
	}
}
