package app.naive.main;


public final class SequentialNaiveMatrixMultiplier {

	/**
	 * Naive element-by-element multiplication, a3 = a1*a2
	 * 
	 * @param a1
	 *            multiplying Matrix
	 * @param a2
	 *            multiplying Matrix
	 * @throws IllegalArgumentException
	 * @return a3
	 */
	public static int[][] multiply(int[][] a1, int[][] a2) {
		int rowAmount1 = a1.length;
		int rowAmount2 = a2.length;
		int colAmount1 = a1[0].length;
		int colAmount2 = a2[0].length;
		boolean isSquares = rowAmount1 == rowAmount2 && colAmount1 == colAmount2;
		boolean isValidNonSquares = colAmount1 == rowAmount2;
		if (!isSquares && !isValidNonSquares) throw new IllegalArgumentException("Matrix dimensions must agree");

		int a3[][] = new int[rowAmount1][colAmount1];
		for (int i = 0; i < rowAmount1; i++) {
			for (int j = 0; j < colAmount1; j++) {
				for (int k = 0; k < rowAmount2; k++) {
					a3[i][j] += (a1[i][k] * a2[k][j]);
				}
			}
		}
		return a3;
	}

}
