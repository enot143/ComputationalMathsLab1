import org.junit.Test;

public class Tests {
    double[][] a;
    double[] b;
    Matrix matrix;

    @Test
    public void testMatrixExample() {
        a = new double[][]{{10, 1, 1}, {2, 10, 1}, {2, 2, 10}};
        b = new double[]{14, 12, 13};
        matrix = new Matrix(3, a, b, 0.01);
        matrix.startMethod();
    }

    @Test
    public void testMatrix5_5() {
        a = new double[][]{{3, 2, 16, 1, 4}, {4, -1, 4, 45, -5}, {8, 6, 4, 7, -33}, {12, 88, -1, -7, 1}, {61, 1, 2, -2.5, 1.75}};
        b = new double[]{25, 3.98, 4, 98, 2.2};
        matrix = new Matrix(5, a, b, 0.01);
        matrix.startMethod();
    }

    @Test
    public void testMatrix6_6() {
        a = new double[][]{{35, 1, 2, 16, 1, 4}, {14, 90, -1, 4, 45, -5}, {8, 6, 79, 4, 7, -33}, {12, 88, -1, 145, -7, 1}, {61, 1, 2, -2.5, 175, 1.2}, {2, 2, 2, 2, 2, 30}};
        b = new double[]{25, 3.98, 4, 98, 2.2, 828};
        matrix = new Matrix(6, a, b, 0.001);
        matrix.startMethod();
    }

    @Test
    public void noDiagonal() {
        a = new double[][]{{10, 5, 5}, {5, 10, 5}, {5, 5, 10}};
        b = new double[]{14, 12, 13};
        matrix = new Matrix(3, a, b, 0.01);
        matrix.startMethod();
    }
    @Test
    public void ErrorDiagonal() {
        a = new double[][]{{10, 10, 0}, {2, 10, 1},  {2, 2, 10}} ;
        b = new double[]{14, 12, 13};
        matrix = new Matrix(3, a, b, 0.01);
        matrix.startMethod();
    }

}
