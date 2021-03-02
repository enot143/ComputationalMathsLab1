
import java.util.Arrays;

import static java.lang.Math.abs;

public class Matrix {

    public Matrix(int n, double[][] a, double[] b, double eps) {
        this.n = n;
        this.a = a.clone();
        this.b = b.clone();
        this.eps = eps;
        this.x = new double[n];
        for (int i = 0; i < n; i++) {
            x[i] = 0;
        }
        System.out.println("Вы ввели матрицу: ");
        getMatrix();
    }

    private int n;          //размерность матрицы
    private int M = 1000;   //наибольшее число итераций
    private int k = 1;      //итерация
    private double[][] a;   //коэффициенты матрицы
    private double[] b;     //вектор свободных членов
    private double[] x;    //начальные приближения = ответ
    private double[] d = new double[M];     //вектор погрешностей
    private double eps;     //заданная погрешность
    private double maxD = 0;//максимальная выч. погрешность
    private double D;       //вычисиляемая погрешность
    private double s;       //промежуточная переменная - сумма
    private double x_I;     //x_I_k

    public void startMethod() {
        if (isConverge(a)) {
            startSeidel();
        } else {
            System.out.println("Нельзя достичь диагонального преобладания.");
        }
    }

    /* Метод Гаусса-Зейделя */
    private void startSeidel() {
        maxD = 0;
        for (int i = 0; i < n; i++) {
            s = 0;
            for (int j = 0; j < i - 1; j++) {
                s += a[i][j] * x[j];
            }
            for (int j = i + 1; j < n; j++) {
                s += a[i][j] * x[j];
            }
            x_I = (b[i] - s) / a[i][i];
            D = abs(x_I - x[i]);
            if (D > maxD) maxD = D;
            x[i] = x_I;
        }
        d[k - 1] = maxD;
        if (maxD < eps) {
            getVectorX(x);
            getIteration(k);
            getError(d);
        } else if (k < M) {
            k++;
            startSeidel();
        } else {
            System.out.println("Итерации расходятся.");
        }
    }

    /* Метод для проверки диагонального преобладания */
    public boolean isConverge(double[][] array) {
        Diagonal diagonal = new Diagonal(array);
        boolean f = diagonal.findNewMatrix();
        if (f) {
            a = diagonal.getNewMatrix().clone();
            System.out.println("Матрица с диагональным преобладанием:");
            getMatrix();
        }
        return f;
    }

    /* Вывод вектора неизвестных x-ов */
    private void getVectorX(double[] x) {
        System.out.println("Вектор неизвестных X: ");
        for (int i = 0; i < n; i++) {
            System.out.printf("%10.4f", x[i]);
            System.out.println();
        }
    }

    /* Вывод количества итераций */
    private void getIteration(int k) {
        System.out.println("Количество итераций = " + k);
    }

    /*Вывод вектора погрешностей */
    private void getError(double[] d) {
        System.out.println("Вектор погрешностей : ");
        for (int i = 0; i < n; i++) {
            System.out.printf("%10.4f", d[i]);
            System.out.println();
        }
    }

    /* Вывод введенной матрицы */
    private void getMatrix() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%6.1f", a[i][j]);
            }
            System.out.print(" | ");
            System.out.printf("%6.1f", b[i]);
            System.out.println();
        }
    }
}