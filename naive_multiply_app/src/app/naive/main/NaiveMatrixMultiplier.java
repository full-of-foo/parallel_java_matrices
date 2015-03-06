package app.naive.main;


public final class NaiveMatrixMultiplier {

	/**
	 * Naive element-by-element multiplication, a3 = a1*a2
	 * 
	 * @param a1
	 *            multiplying Matrix
	 * @param a2
	 *            multiplying Matrix
	 * @return a3
	 */
	public static double[][] multiply(double[][] a1, double[][] a2) {
		int rowAmount = a1.length;
		int colAmount = a1[0].length;
		double a3[][] = new double[rowAmount][colAmount];

		for (int i = 0; i < rowAmount; i++) {
			for (int j = 0; j < colAmount; j++) {
				a3[i][j] = a1[i][j] * a2[i][j];
			}
		}
		return a3;
	}

}
