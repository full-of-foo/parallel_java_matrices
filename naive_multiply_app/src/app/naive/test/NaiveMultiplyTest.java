package app.naive.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import matrix.utils.Matrix;

import org.junit.Test;

import app.naive.main.NaiveMatrixMultiplier;

public class NaiveMultiplyTest {
	
	@Test
	public void testSimpleMultiply() {
		Matrix m1 = new Matrix(1, 1, 3);
		Matrix m2 = new Matrix(1, 1, 3);
		double data[][] = NaiveMatrixMultiplier.multiply(m1.getArray(), m2.getArray());
		assertThat(data[0][0], is(9.0));
	}

	@Test
	public void testBetterMultiply() {
		Matrix m1 = new Matrix(2, 3, 3);
		Matrix m2 = new Matrix(2, 3, 3);
		double data[][] = NaiveMatrixMultiplier.multiply(m1.getArray(), m2.getArray());
		
		assertThat(data[0][0], is(18.0));
		assertThat(data[0][1], is(18.0));
		assertThat(data[1][0], is(18.0));
		assertThat(data[1][1], is(18.0));
	}
	
	@Test
	public void testGoodDemensions() {
		Matrix m1 = new Matrix(4, 4, 2);
		Matrix m2 = new Matrix(4, 8, 3);
		assertNotNull(NaiveMatrixMultiplier.multiply(m1.getArray(), m2.getArray()));
		m1 = new Matrix(8, 4, 2);
		m2 = new Matrix(4, 4, 3);
		assertNotNull(NaiveMatrixMultiplier.multiply(m1.getArray(), m2.getArray()));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testBadDemensions1() {
		Matrix m1 = new Matrix(4, 4, 2);
		Matrix m2 = new Matrix(8, 4, 3); // bad row
		NaiveMatrixMultiplier.multiply(m1.getArray(), m2.getArray());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testBadDemensions2() {
		Matrix m1 = new Matrix(4, 8, 2); // bad col
		Matrix m2 = new Matrix(4, 4, 3);
		NaiveMatrixMultiplier.multiply(m1.getArray(), m2.getArray());
	}
}