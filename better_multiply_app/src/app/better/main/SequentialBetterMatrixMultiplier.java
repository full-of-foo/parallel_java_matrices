package app.better.main;


public final class SequentialBetterMatrixMultiplier {

	/**
	 * Tiled matrix multiplication
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

		int s = (int) Math.ceil(rowAmount1 * 1.0 / 4);
		int s2 = (int) Math.ceil(colAmount1 * 1.0 / 4); 
		int s3 = (int) Math.ceil(rowAmount2 * 1.0 / 4); 
		for (int i = 0; i < rowAmount1 / s; i+=s) {
			for (int j = 0; j < colAmount1 / s2; j+=s2) {
				for (int k = 0; k < rowAmount2 /s3; k+=s3) {
					for (int i2 = i; i2 < (i+s) && i2 < rowAmount1; i2++) {
						for (int j2 = j; j2 < (j+s2) && j2 < colAmount1; j2++) {
							for (int k2 = k; k2 < (k+s3) && k2 < rowAmount2; k2++) {
								a3[i2][j2] += (a1[i2][k2] * a2[k2][j2]);
							}
						}
					}
				}
			}
		}		
		
		return a3;
	}

}
