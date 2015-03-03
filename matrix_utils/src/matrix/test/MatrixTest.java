package matrix.test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import matrix.utils.Matrix;

public class MatrixTest {
    Matrix A,B,C,Z,O,I,R,S,X,SUB,M,T,SQ,DEF,SOL;
    int errorCount=0;
    int warningCount=0;
    double tmp, s;
    double[] columnwise = {1.,2.,3.,4.,5.,6.,7.,8.,9.,10.,11.,12.};
    double[] rowwise = {1.,4.,7.,10.,2.,5.,8.,11.,3.,6.,9.,12.};
    double[][] avals = {{1.,4.,7.,10.},{2.,5.,8.,11.},{3.,6.,9.,12.}};
    double[][] rankdef = avals;
    double[][] tvals =  {{1.,2.,3.},{4.,5.,6.},{7.,8.,9.},{10.,11.,12.}};
    double[][] subavals = {{5.,8.,11.},{6.,9.,12.}};
    double[][] rvals = {{1.,4.,7.},{2.,5.,8.,11.},{3.,6.,9.,12.}};
    double[][] pvals = {{4.,1.,1.},{1.,2.,3.},{1.,3.,6.}};
    double[][] ivals = {{1.,0.,0.,0.},{0.,1.,0.,0.},{0.,0.,1.,0.}};
    double[][] evals = 
       {{0.,1.,0.,0.},{1.,0.,2.e-7,0.},{0.,-2.e-7,0.,1.},{0.,0.,1.,0.}};
    double[][] square = {{166.,188.,210.},{188.,214.,240.},{210.,240.,270.}};
    double[][] sqSolution = {{13.},{15.}};
    double[][] condmat = {{1.,3.},{7.,9.}};
    double[][] badeigs = {{0,0,0,0,0}, {0,0,0,0,1},{0,0,0,1,0},
			    {1,1,0,0,1},{1,0,1,0,1}};
    int rows=3,cols=4;
    int invalidld=5;/* should trigger bad shape for construction with val */
    int raggedr=0; /* (raggedr,raggedc) should be out of bounds in ragged array */
    int raggedc=4; 
    int validld=3; /* leading dimension of intended test Matrices */
    int nonconformld=4; /* leading dimension which is valid, but nonconforming */
    int ib=1,ie=2,jb=1,je=3; /* index ranges for sub Matrix */
    int[] rowindexset = {1,2}; 
    int[] badrowindexset = {1,3}; 
    int[] columnindexset = {1,2,3};
    int[] badcolumnindexset = {1,2,4};
    double columnsummax = 33.;
    double rowsummax = 30.;
    double sumofdiagonals = 15;
    double sumofsquares = 650;
    
    @Test
    public void testEmptyConstructor(){
    	Matrix m = new Matrix(2, 2);
    	assertThat(m.get(0,0), is(0.0));
    	assertThat(m.get(0,1), is(0.0));
    	assertThat(m.get(1,0), is(0.0));
    	assertThat(m.get(1,1), is(0.0));
    }

    @Test
    public void testScalarConstructor(){
    	Matrix m = new Matrix(2, 2, 5);
    	assertThat(m.get(0,0), is(5.0));
    	assertThat(m.get(0,1), is(5.0));
    	assertThat(m.get(1,0), is(5.0));
    	assertThat(m.get(1,1), is(5.0));
    }
    
    @Test
    public void testDefaultConstructor() {
        Matrix m = new Matrix(avals);
        assertNotNull(m);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testIllegalDefaultConstructor() {
    	new Matrix(rvals);
    }
    
    @Test
    public void testDoubleIntConstructor() {
         Matrix m = new Matrix(columnwise, validld);
         assertNotNull(m);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testIllegalDoubleIntConstructor() {
         new Matrix(columnwise, invalidld);
    }
    
}
