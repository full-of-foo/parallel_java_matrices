package app.naive.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import matrix.utils.Matrix;

import org.junit.Test;

import app.naive.main.NaiveMatrixMultiplier;

public class NaiveMultiplyTest {
	
	@Test
	public void testMultiply() {
		Matrix m1 = new Matrix(2, 2, 3);
		Matrix m2 = new Matrix(2, 2, 3);
		double data[][] = NaiveMatrixMultiplier.multiply(m1.getArray(), m2.getArray());
		
		assertThat(data[0][0], is(9.0));
		assertThat(data[0][1], is(9.0));
		assertThat(data[1][0], is(9.0));
		assertThat(data[1][1], is(9.0));
	}
	
	@Test
	public void testMultiplyTime() {
		Matrix m1 = new Matrix(100000, 100, 1);
		Matrix m2 = new Matrix(100000, 100, 3);
		double data[][] = NaiveMatrixMultiplier.multiply(m1.getArray(), m2.getArray());
		
		assertThat(data[0][0], is(3.0));
		assertThat(data[0][1], is(3.0));
	}
}