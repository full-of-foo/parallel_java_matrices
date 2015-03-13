package app.naive.main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class ConcurrentNaiveMatrixMultiplier {

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
	public static int[][] multiply(final int[][] a1, final int[][] a2) {
		final int rowAmount1 = a1.length;
		final int rowAmount2 = a2.length;
		final int colAmount1 = a1[0].length;
		final int colAmount2 = a2[0].length;
		final boolean isSquares = rowAmount1 == rowAmount2 && colAmount1 == colAmount2;
		final boolean isValidNonSquares = colAmount1 == rowAmount2;
		if (!isSquares && !isValidNonSquares) throw new IllegalArgumentException("Matrix dimensions must agree");
		final int a3[][] = new int[rowAmount1][colAmount1];

		int numTasks = 4;
		final ExecutorService executor = Executors.newFixedThreadPool(numTasks);
	    for (int interval = numTasks, end = rowAmount1, size = (int) Math.ceil(rowAmount1 * 1.0 / numTasks); interval > 0; interval--, end -= size) {
	        final int to = end;
	        final int from = Math.max(0, end - size);
	        final Runnable runnable = new Runnable() {

				@Override
				public void run() {
					for (int i = from; i < to; i++) {
						for (int j = 0; j < colAmount1; j++) {
							for (int k = 0; k < rowAmount2; k++) {
								a3[i][j] += (a1[i][k] * a2[k][j]);
							}
						}
					}
				}
	        };
	        executor.execute(runnable);
	    }
	    executor.shutdown();

		return a3;
	}
}
