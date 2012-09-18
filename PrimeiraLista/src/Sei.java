
import javax.swing.JOptionPane;
public class Sei {
    public static void main (String [] args) {
        
        
        int numero =  Integer.parseInt(JOptionPane.showInputDialog("Digite o numero que será fatorado número"));    
        int fatorial = 1;
        for (int i = 1; i <= numero ; i++ ){
         fatorial *= i;
        }
        JOptionPane.showMessageDialog(null,"O valor do numero Fatorial de "+numero+" tem o valor = "+fatorial);
        System.out.println(fatorial);
    }
}
