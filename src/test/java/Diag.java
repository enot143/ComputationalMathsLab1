import org.junit.Test;

import java.util.Arrays;

public class Diag {
    double[][] a;
    double[] b;
    Matrix matrix;

    @Test
    public void testMatrixExample() {
//        a = new double[][]{{10, 1, 1}, {2, 10, 1}, {2, 2, 10}};
        a = new double[][]{{2, 2, 10}, {2, 10, 1},{10, 10, 0}} ;
//        a = new double[][]{{10, 5, 5}, {5, 10, 5}, {5, 5, 10}};
//        a = new double[][]{{35, 1, 2, 16, 1, 4}, {14, 90, -1, 4, 45, -5}, {8, 6, 79, 4, 7, -33}, {12, 88, -1, 145, -7, 1}, {61, 1, 2, -2.5, 175, 1.2}, {2, 2, 2, 2, 2, 30}};

        Diagonal diagonal = new Diagonal(a);
        if (diagonal.findNewMatrix()) {
            System.out.println(Arrays.deepToString(diagonal.getNewMatrix()));
        }
        else{
            System.out.println("Ошибка");
        }
    }
}
