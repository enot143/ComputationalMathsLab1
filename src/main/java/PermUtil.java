import java.util.Arrays;

public class PermUtil {
    private double[][] arr;
    private int[] permSwappings;

    public PermUtil(double[][] arr) {
        this.arr = arr.clone();
        this.permSwappings = new int[arr.length];
        for (int i = 0; i < permSwappings.length; i++)
            permSwappings[i] = i;
    }

    public double[][] next() {
        if (arr != null) {
            double[][] res = Arrays.copyOf(arr, permSwappings.length);
            //Prepare next
            int i = permSwappings.length - 1;
            while (i >= 0 && permSwappings[i] == arr.length - 1) {
                swap(i, permSwappings[i]); //Undo the swap represented by permSwappings[i]
                permSwappings[i] = i;
                i--;
            }
            if (i < 0)
                arr = null;
            else {
                int prev = permSwappings[i];
                swap(i, prev);
                int next = prev + 1;
                permSwappings[i] = next;
                swap(i, next);
            }
            return res;
        }
        return null;
    }

    public int getFactorial(int f) {
        if (f <= 1) {
            return 1;
        } else {
            return f * getFactorial(f - 1);
        }
    }
    private void swap(int i, int j) {
        double[] tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
