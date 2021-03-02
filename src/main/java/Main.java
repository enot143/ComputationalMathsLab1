import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Input input = new Input();
        Matrix matrix = input.getMatrix();
        if (matrix != null){
            matrix.startMethod();
        }
    }
}
