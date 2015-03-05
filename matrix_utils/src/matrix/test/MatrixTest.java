package matrix.test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import matrix.utils.Matrix;

import org.junit.Test;

public class MatrixTest {

	@Test
	public void testEmptyConstructor() {
		Matrix m = new Matrix(2, 2);
		assertThat(m.get(0, 0), is(0.0));
		assertThat(m.get(0, 1), is(0.0));
		assertThat(m.get(1, 0), is(0.0));
		assertThat(m.get(1, 1), is(0.0));
	}

	@Test
	public void testScalarConstructor() {
		Matrix m = new Matrix(2, 2, 5);
		assertThat(m.get(0, 0), is(5.0));
		assertThat(m.get(0, 1), is(5.0));
		assertThat(m.get(1, 0), is(5.0));
		assertThat(m.get(1, 1), is(5.0));
	}

	@Test
	public void testDefaultConstructor() {
		double[][] avals = { { 1., 4., 7., 10. }, { 2., 5., 8., 11. },
				{ 3., 6., 9., 12. } };
		Matrix m = new Matrix(avals);
		assertNotNull(m);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIllegalDefaultConstructor() {
		double[][] rvals = { { 1., 4., 7. }, { 2., 5., 8., 11. },
				{ 3., 6., 9., 12. } };
		new Matrix(rvals);
	}

	@Test
	public void testDoubleIntConstructor() {
		double[] columnwise = { 1., 2., 3., 4., 5., 6., 7., 8., 9., 10., 11.,
				12. };
		int validld = 3;
		Matrix m = new Matrix(columnwise, validld);
		assertNotNull(m);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIllegalDoubleIntConstructor() {
		double[] columnwise = { 1., 2., 3., 4., 5., 6., 7., 8., 9., 10., 11.,
				12. };
		int invalidld = 5;
		new Matrix(columnwise, invalidld);
	}

	@Test
	public void testGetArray() {
		Matrix m = new Matrix(2, 2, 5);
		double matrixData[][] = m.getArray();
		assertThat(matrixData[0][0], is(5.0));
		assertThat(matrixData[0][1], is(5.0));
		assertThat(matrixData[1][0], is(5.0));
		assertThat(matrixData[1][1], is(5.0));
	}

	@Test
	public void testGetDemensions() {
		Matrix m = new Matrix(2, 2, 5);
		assertThat(m.getColumnDimension(), is(2));
		assertThat(m.getRowDimension(), is(2));
	}

	@Test
	public void testSetElemAndGetElem() {
		Matrix m = new Matrix(2, 2, 5);
		assertThat(m.get(0, 1), is(5.0));
		m.set(0, 1, 100.0);
		assertThat(m.get(0, 1), is(100.0));
	}

	@Test
	public void testGetSubMatrixWithStartAndEndIndices() {
		Matrix subMartix = new Matrix(10, 10, 5).getMatrix(0, 4, 0, 4);
		assertThat(subMartix.getColumnDimension(), is(5));
		assertThat(subMartix.getRowDimension(), is(5));
		assertThat(subMartix.get(0, 0), is(5.0));
		assertThat(subMartix.get(0, 1), is(5.0));
	}

	@Test
	public void testGetSubMatrixWithArraysOfIndices() {
		int rowIndices[] = { 0, 2, 4, 5, 9 };
		int colIndices[] = { 1, 2, 6, 7, 8 };
		Matrix subMartix = new Matrix(10, 10, 5).getMatrix(rowIndices,
				colIndices);

		assertThat(subMartix.getColumnDimension(), is(5));
		assertThat(subMartix.getRowDimension(), is(5));
		assertThat(subMartix.get(0, 0), is(5.0));
		assertThat(subMartix.get(0, 1), is(5.0));
	}

	@Test
	public void testGetSubMatrixWithRowIndices() {
		int rowIndices[] = { 0, 2, 4, 5, 9 };
		Matrix subMartix = new Matrix(10, 10, 5).getMatrix(rowIndices, 0, 4);

		assertThat(subMartix.getColumnDimension(), is(5));
		assertThat(subMartix.getRowDimension(), is(5));
		assertThat(subMartix.get(0, 0), is(5.0));
		assertThat(subMartix.get(0, 1), is(5.0));
	}

	@Test
	public void testGetSubMatrixWithColIndices() {
		int colIndices[] = { 1, 2, 6, 7, 8 };
		Matrix subMartix = new Matrix(10, 10, 5).getMatrix(colIndices, 0, 4);

		assertThat(subMartix.getColumnDimension(), is(5));
		assertThat(subMartix.getRowDimension(), is(5));
		assertThat(subMartix.get(0, 0), is(5.0));
		assertThat(subMartix.get(0, 1), is(5.0));
	}

	@Test
	public void testSetSubMatrixWithStartAndEndIndices() {
		Matrix m = new Matrix(3, 3, 6);
		Matrix subMartix = new Matrix(2, 2, 5);
		m.setMatrix(0, 1, 0, 1, subMartix);

		assertThat(m.get(0, 0), is(5.0));
		assertThat(m.get(0, 1), is(5.0));
		assertThat(m.get(1, 0), is(5.0));
		assertThat(m.get(1, 1), is(5.0));
		assertThat(m.get(2, 0), is(6.0));
	}

	@Test
	public void testSetSubMatrixWithArraysOfIndices() {
		Matrix m = new Matrix(3, 3, 6);
		int rowAndColIndices[] = { 0, 1 };
		Matrix subMartix = new Matrix(2, 2, 5);
		m.setMatrix(rowAndColIndices, rowAndColIndices, subMartix);

		assertThat(m.get(0, 0), is(5.0));
		assertThat(m.get(0, 1), is(5.0));
		assertThat(m.get(1, 0), is(5.0));
		assertThat(m.get(1, 1), is(5.0));
		assertThat(m.get(2, 0), is(6.0));
	}

	@Test
	public void testSetSubMatrixWithRowIndices() {
		Matrix m = new Matrix(3, 3, 6);
		int rowIndices[] = { 0, 1 };
		Matrix subMartix = new Matrix(2, 2, 5);
		m.setMatrix(rowIndices, 0, 1, subMartix);

		assertThat(m.get(0, 0), is(5.0));
		assertThat(m.get(0, 1), is(5.0));
		assertThat(m.get(1, 0), is(5.0));
		assertThat(m.get(1, 1), is(5.0));
		assertThat(m.get(2, 0), is(6.0));
	}

	@Test
	public void testSetSubMatrixWithColIndices() {
		Matrix m = new Matrix(3, 3, 6);
		int colIndices[] = { 0, 1 };
		Matrix subMartix = new Matrix(2, 2, 5);
		m.setMatrix(0, 1, colIndices, subMartix);

		assertThat(m.get(0, 0), is(5.0));
		assertThat(m.get(0, 1), is(5.0));
		assertThat(m.get(1, 0), is(5.0));
		assertThat(m.get(1, 1), is(5.0));
		assertThat(m.get(2, 0), is(6.0));
	}

	@Test
	public void testRandomMatrix() {
		Matrix m = Matrix.random(2, 2);

		assertThat(m.getColumnDimension(), is(2));
		assertThat(m.getRowDimension(), is(2));
		for (int r = 0; r < m.getRowDimension(); r++) {
			for (int c = 0; c < m.getArray()[r].length; c++) {
				assertThat(m.get(r, c), is(instanceOf(double.class)));
				assertThat(true, is(m.get(r, c) > 0.0));
				assertThat(true, is(m.get(r, c) < 1.0));
			}
		}
	}
}
