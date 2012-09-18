public class Exercicio3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int r = 0;
		int i = 0;
		int a = 9;
		int g = 0;
		while (g < 10) {
			r = i + 5 * a - a;
			System.out.println(r);
			g++;
			a++;
			i = a / 3;

		}
	}

}
