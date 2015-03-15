package app.benchmarking.main;
import java.util.concurrent.Callable;

import app.better.main.ConcurrentBetterMatrixMultiplier;
import app.naive.main.ConcurrentNaiveMatrixMultiplier;
import bb.util.Benchmark;
import matrix.utils.Matrix;

public final class Benchmark200By300RectMatrices {
	public final static Matrix m1 = new Matrix(200, 300, 2);
	public final static Matrix m2 = new Matrix(200, 300, 3);

	public static void main(String[] args) {
		final int[][] a1 = m1.getArray();
		final int[][] a2 = m2.getArray();
		
		final Callable<int[][]> callableNaiveMultiply = new Callable<int[][]>() {
			public int[][] call() throws Exception {
				return ConcurrentNaiveMatrixMultiplier.multiply(a1, a2);
			}
		};
		final Callable<int[][]> callableBetterMultiply = new Callable<int[][]>() {
			public int[][] call() throws Exception {
				return ConcurrentBetterMatrixMultiplier.multiply(a1, a2);
			}
		};
		try {
			System.out.println("Naive Result: " + new Benchmark(callableNaiveMultiply).toStringFull());
			System.out.println("Better Result: " + new Benchmark(callableBetterMultiply).toStringFull());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}


