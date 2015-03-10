package app.naive.main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public final class ConcurrentNaiveMatrixMultiplier {

	private static class NaiveMultiplierTask extends RecursiveAction {
		private static final long serialVersionUID = 1L;
		private int[][] a1;
		private int[][] a2;
		private int[][] a3;
		private int rowAmount1;
		private int rowAmount2;
		private int colAmount1;
		private int colAmount2;
		private int forRow;

		public NaiveMultiplierTask(int[][] a1, int[][] a2) {
			this.a1 = a1;
			this.a2 = a2;
			this.rowAmount1 = a1.length;
			this.rowAmount2 = a2.length;
			this.colAmount1 = a1[0].length;
			this.colAmount2 = a2[0].length;
			this.a3 = new int[rowAmount1][colAmount1];
			
			boolean isSquares = rowAmount1 == rowAmount2 && colAmount1 == colAmount2;
			boolean isValidNonSquares = colAmount1 == rowAmount2;
			if (!isSquares && !isValidNonSquares) throw new IllegalArgumentException("Matrix dimensions must agree");
		}
		
		public NaiveMultiplierTask(int[][] a1, int[][] a2, int forRow) {
			this(a1, a2);
			this.forRow = forRow;
		}
		
	    private void multiplyRowByColumn(int forRow) {
	        for (int j = 0; j < colAmount1; j++) {
	            for (int k = 0; k < rowAmount2; k++) {
	            	a3[this.forRow][j] += (a1[this.forRow][k] * a2[k][j]);
	            }
	        }
	    }

		@Override
		protected void compute() {
			if((Integer) forRow != null) {
				multiplyRowByColumn(this.forRow);
			} else {
				List<NaiveMultiplierTask> tasks = new ArrayList<>();
	    		for (int i = 0; i < rowAmount1; i++) {
	                tasks.add(new NaiveMultiplierTask(a1, a2, this.forRow));
	            }
	            invokeAll(tasks);	
			}
		}
	}

	/**
	 * Current naive multiplication, a3 = a1*a2
	 * 
	 * @param a1
	 *            multiplying Matrix
	 * @param a2
	 *            multiplying Matrix
	 * @throws IllegalArgumentException
	 * @return a3
	 */
	public static int[][] multiply(int[][] a1, int[][] a2) {
		final ForkJoinPool pool = new ForkJoinPool(4);
		final NaiveMultiplierTask multiplierTask = new NaiveMultiplierTask(a1, a2);
		pool.invoke(multiplierTask);
		return multiplierTask.a3;
	}
}
