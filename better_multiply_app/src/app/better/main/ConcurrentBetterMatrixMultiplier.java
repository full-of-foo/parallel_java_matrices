package app.better.main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public final class ConcurrentBetterMatrixMultiplier {

	/**
	 * TODO
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

		int numTasks = Runtime.getRuntime().availableProcessors();
		final ExecutorService executor = Executors.newFixedThreadPool(numTasks);
	    for (int interval = numTasks, end = rowAmount1, size = (int) Math.ceil(rowAmount1 * 1.0 / numTasks); interval > 0; interval--, end -= size) {
	        final int to = end;
	        final int from = Math.max(0, end - size);
			int s = (int) Math.ceil(rowAmount1 * 1.0 / 4);
			int s2 = (int) Math.ceil(colAmount1 * 1.0 / 4); 
			int s3 = (int) Math.ceil(rowAmount2 * 1.0 / 4); 
	        final Runnable runnable = new Runnable() {

				@Override
				public void run() {
					for (int i = from; i < to / s; i+=s) {
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
				}
	        };
	        executor.execute(runnable);
	    }
	    shutDownThreadPool(executor);
	    
		return a3;
	}

	private static void shutDownThreadPool(ExecutorService executor) {
	    executor.shutdown();
		try {
			if (!executor.awaitTermination(60, TimeUnit.SECONDS)) { // Wait a while for existing tasks to terminate
				executor.shutdownNow(); // Cancel currently executing tasks
				if (!executor.awaitTermination(60, TimeUnit.SECONDS)) System.err.println("Pool did not terminate"); // Wait for tasks to respond
			}
		} catch (InterruptedException ie) { executor.shutdownNow(); }		
	}
}
