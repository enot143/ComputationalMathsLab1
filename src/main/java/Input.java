import java.io.*;

public class Input {
    public Input() throws IOException {
        validate("Ввод с клавиатуры(1) или из файла(2)?");
        if (type.equals("1")){
          getFromKeyboard();
        }
        else if (type.equals("2")){
            getFromFile();
        }
    }

    private int dimension;   //размерность матрицы
    private double[][] a;    //матрица A
    private double[] b;      //вектор-столбец свободных членов
    private double eps;     //погрешность
    private Matrix matrix;
    private BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));
    private String type;    //тип ввода - файл/клавиатура


    public void getFromKeyboard()  {
        validate("Введите размерность матрицы: ");
        a = new double[dimension][dimension];
        b = new double[dimension];
        validate("Введите коэффициенты матрицы: ");
        validate("Введите погрешность: ");
        matrix = new Matrix(dimension, a, b, eps);
    }

    public void getFromFile() throws IOException {
        boolean correctMatrix = true;
        System.out.println("Введите имя файла");
        String fileName = keyboardReader.readLine();
        File file = new File(fileName);
        if (file.canRead() && file.exists()) {
            try {
                InputStream ips = new FileInputStream(fileName);
                InputStreamReader ipsr = new InputStreamReader(ips);
                BufferedReader fileReader = new BufferedReader(ipsr);
                inputDimension(fileReader);
                a = new double[dimension][dimension];
                b = new double[dimension];
                inputCoefficient(fileReader);
                inputEps(fileReader);
                fileReader.close();
            } catch (Exception e) {
                correctMatrix = false;
                System.out.println("Формат файла некорректен.");
            }
        } else {
            correctMatrix = false;
            System.out.println("Невозможно прочитать файл.");
        }
        if (correctMatrix){
            matrix = new Matrix(dimension, a, b, eps);
        }
    }

    public void validate(String s) {
        boolean correctInput = false;
        while (!correctInput) {
            try {
                correctInput = true;
                System.out.println(s);
                switch (s) {
                    case ("Введите размерность матрицы: "):
                        inputDimension(keyboardReader);
                        break;
                    case ("Введите коэффициенты матрицы: "):
                        inputCoefficient(keyboardReader);
                        break;
                    case ("Введите погрешность: "):
                        inputEps(keyboardReader);
                        break;
                    case("Ввод с клавиатуры(1) или из файла(2)?"):
                        inputType(keyboardReader);
                }
            } catch (Exception e) {
                correctInput = false;
                System.out.println("Ввод некорректен. Попробуйте еще раз.");
            }
        }
    }
    private void inputType(BufferedReader br) throws Exception {
        type = br.readLine();
        if (!(type.equals("1") || type.equals("2"))){
            throw new Exception();
        }
    }
    private void inputEps(BufferedReader br) throws IOException {
        eps = Double.parseDouble(br.readLine());
    }

//    private void inputB(BufferedReader br) throws IOException {
//        String[] matrixB = br.readLine().split(" ");
//        for (int i = 0; i < dimension; i++) {
//            b[i] = Double.parseDouble(matrixB[i]);
//        }
//    }

    private void inputCoefficient(BufferedReader br) throws IOException {
        for (int i = 0; i < dimension; i++) {
            String[] matrixA = br.readLine().split(" ");
            for (int j = 0; j < dimension; j++) {
                a[i][j] = Double.parseDouble(matrixA[j]);
            }
            b[i] = Double.parseDouble(matrixA[dimension]);
        }
    }

    private void inputDimension(BufferedReader br) throws IOException {
        dimension = Integer.parseInt(br.readLine());
    }

    public Matrix getMatrix(){
        return matrix;
    }
}