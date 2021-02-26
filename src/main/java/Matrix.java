
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
        getMatrix();
    }

    private int n;          //размерность матрицы
    private int M = 1000;   //наибольшее число итераций
    private int k = 1;      //итерация
    private double[][] a;   //коэффициенты матрицы
    private double[] b;     //вектор свободных членов
    private double[] x;    //начальные приближения = ответ
    //TODO: заменить d на вектор
    private double[] d = new double[M];     //вектор погрешностей
    private double eps;     //заданная погрешность
    private double maxD = 0;//максимальная выч. погрешность
    private double D;       //вычисиляемая погрешность
    private double s;       //промежуточная переменная - сумма
    private double x_I;     //x_I_k

    /* Метод Гаусса-Зейделя */
    public void startSeidel() {
        if (isConverge(a)) {
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
        }else{
            System.out.println("Нельзя достичь диагонального преобладания.");
        }
    }

    /* Метод для проверки диагонального преобладания */
    //TODO: zamena - null ?
    public boolean isConverge(double[][] array) {
        boolean isConv = false;
        double[][] zamena = null;
        int i = 0;
        PermUtil permUtil = new PermUtil(array);
        while (!isConv && i < permUtil.getFactorial(array.length)) {
            i++;
            zamena = permUtil.next();
            isConv = checkConverge(zamena);
        }
        if (isConv && zamena != null){
            a = zamena.clone();
        }
        return isConv;
    }

    private boolean checkConverge(double[][] res) {
        double sum = 0;
        boolean isStrict = false;
        for (int i = 0; i < n; i++) {
            sum = 0 - abs(res[i][i]);
            for (int j = 0; j < n; j++) {
                sum = sum + abs(res[i][j]);
            }
            //System.out.println("сумма = " + sum + " ; модуль = " + abs(res[i][i]));
            if (abs(res[i][i]) < sum) {
                return false;
            }
            else{
                if (abs(res[i][i]) > sum)
                    isStrict = true;
            }
        }
        return isStrict;
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
        System.out.println("Вы ввели матрицу: ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%6.1f", a[i][j]);
            }
            System.out.print(" | ");
            System.out.printf("%6.1f", b[i]);
            System.out.println();
        }
    }
    //    private static void print(double[][] res) {
//        if (res != null) {
//            int n = 3;
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < n; j++) {
//                    System.out.print(res[i][j] + " ");
//                }
//                System.out.println();
//            }
//        }
//        System.out.println();
//    }
}