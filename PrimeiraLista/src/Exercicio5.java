public class Exercicio5 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i = 60;
		if (0 <= i & i<= 50) {
			System.out.println("reprovado");
		} else {
			if (51 <= i & i <= 75) {
				System.out.println("a recuperar");
			} else {
				if (76 <= i && i <= 90) {
					System.out.println("aprovado");
				} else {
					if (91 <= i && i <= 100) {
						System.out.println("aprovado com louvor");
					} else {
						if (101 <= i) {
							System.out.println("pontuação inválida");
						} else {
							if (i <= -1) {
								System.out.println("pontuação inválida");
							} else {
								System.out.println("não Sei");
							}
						}

					}
				}
			}
		}
	}
}
