public class Exercico2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int f = 1;
		while (f <= 10) {
			System.out.println("\n" + "Tabuada do"+" " + f);
			int g = 1;
			while (g <= 10) {
				int h = f * g;
				System.out.println(h);
				g++;
			}
			f++;			
		}

		int a = 0;
		for (int b = 1; b <= 10; b++) {
			System.out.println("\n" + "Tabuada do" + " " + b);
			a++;
			for (int c = 1; c <= 10; c++) {
				int e = a * c;
				System.out.println(e);
			}
		}
	}
}
