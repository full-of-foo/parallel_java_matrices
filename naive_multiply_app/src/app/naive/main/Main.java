package app.naive.main;

import matrix.utils.Matrix;

public class Main {

	public static void main(String[] args) {
		// see http://stackoverflow.com/questions/11891969/visualvm-launcher-error
		while(true) {
			Matrix m1 = new Matrix(200, 200, 2);
			Matrix m2 = new Matrix(200, 200, 3);
			NaiveMatrixMultiplier.multiply(m1.getArray(), m2.getArray());
		} // hackidy-hack-hack (visualVM requires programs to live forever when profiling)
	}

}
