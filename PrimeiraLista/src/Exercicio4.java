import javax.swing.*; // Importa a classe de janelas JOptionPane

public class Exercicio4 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i;
		JOptionPane.showMessageDialog(null, "Quer saber se um número e par?");
		i = (int) Float.parseFloat(JOptionPane.showInputDialog(null,
				"Digite o número"));
		if (i % 2 == 0) {
			System.out.println(i + " " + "é um número par");
			JOptionPane.showMessageDialog(null, +i + " " + "é um número par");
		} else {
			System.out.println(i + " " + "e um número ímpar");
			JOptionPane.showMessageDialog(null, +i + " " + "é um número ímpar");
		}

	}

}
