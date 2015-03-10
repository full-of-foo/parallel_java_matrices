package app.naive.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import matrix.utils.Matrix;

import org.junit.Test;

import app.naive.main.ConcurrentNaiveMatrixMultiplier;
import app.naive.main.SequentialNaiveMatrixMultiplier;

public class SequentialNaiveMatrixMultiplyTest {
	
	@Test
	public void testSimpleMultiply() {
		Matrix m1 = new Matrix(1, 1, 3);
		Matrix m2 = new Matrix(1, 1, 3);
		int data[][] = SequentialNaiveMatrixMultiplier.multiply(m1.getArray(), m2.getArray());
		assertThat(data[0][0], is(9));
	}

	@Test
	public void testBetterMultiply() {
		Matrix m1 = new Matrix(2, 3, 3);
		Matrix m2 = new Matrix(2, 3, 3);
		int data[][] = SequentialNaiveMatrixMultiplier.multiply(m1.getArray(), m2.getArray());
		
		assertThat(data[0][0], is(18));
		assertThat(data[0][1], is(18));
		assertThat(data[1][0], is(18));
		assertThat(data[1][1], is(18));
	}
	
	@Test
	public void testGoodDemensions() {
		Matrix m1 = new Matrix(4, 4, 2);
		Matrix m2 = new Matrix(4, 8, 3);
		assertNotNull(SequentialNaiveMatrixMultiplier.multiply(m1.getArray(), m2.getArray()));
		m1 = new Matrix(8, 4, 2);
		m2 = new Matrix(4, 4, 3);
		assertNotNull(SequentialNaiveMatrixMultiplier.multiply(m1.getArray(), m2.getArray()));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testBadDemensions1() {
		Matrix m1 = new Matrix(4, 4, 2);
		Matrix m2 = new Matrix(8, 4, 3); // bad row
		SequentialNaiveMatrixMultiplier.multiply(m1.getArray(), m2.getArray());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testBadDemensions2() {
		Matrix m1 = new Matrix(4, 8, 2); // bad col
		Matrix m2 = new Matrix(4, 4, 3);
		SequentialNaiveMatrixMultiplier.multiply(m1.getArray(), m2.getArray());
	}

	
// ~1.32 seconds
//	@Test
//	public void testTimes() {
//		Matrix m1 = Matrix.random(550, 550);
//		Matrix m2 = Matrix.random(550, 550);
//		assertNotNull(SequentialNaiveMatrixMultiplier.multiply(m1.getArray(), m2.getArray()));
//	}

// ~ 0.6 seconds
//		@Test
//		public void testTimes() {
//			Matrix m1 = Matrix.random(550, 550);
//			Matrix m2 = Matrix.random(550, 550);
//			assertNotNull(ConcurrentNaiveMatrixMultiplier.multiply(m1.getArray(), m2.getArray()));
//		}
	
	
	@Test
	public void testTimes() {
		Matrix m1 = Matrix.random(4, 4);
		Matrix m2 = Matrix.random(4, 4);
		
		int[][] a = ConcurrentNaiveMatrixMultiplier.multiply(m1.getArray(), m2.getArray());
		Matrix m3 = new Matrix(a);
		m3.print(1,1);
		a = SequentialNaiveMatrixMultiplier.multiply(m1.getArray(), m2.getArray());
		m3 = new Matrix(a);
		m3.print(1,1);
		
		assertNotNull(ConcurrentNaiveMatrixMultiplier.multiply(m1.getArray(), m2.getArray()));
	}
	
	
	
	
}