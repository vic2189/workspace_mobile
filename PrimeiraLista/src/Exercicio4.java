import javax.swing.*; // Importa a classe de janelas JOptionPane

public class Exercicio4 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i;
		JOptionPane.showMessageDialog(null, "Quer saber se um n�mero e par?");
		i = (int) Float.parseFloat(JOptionPane.showInputDialog(null,
				"Digite o n�mero"));
		if (i % 2 == 0) {
			System.out.println(i + " " + "� um n�mero par");
			JOptionPane.showMessageDialog(null, +i + " " + "� um n�mero par");
		} else {
			System.out.println(i + " " + "e um n�mero �mpar");
			JOptionPane.showMessageDialog(null, +i + " " + "� um n�mero �mpar");
		}

	}

}
