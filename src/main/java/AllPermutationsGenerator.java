import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AllPermutationsGenerator {
    public <T> void heapsRecAlgo (int n, List<T> arr) {
        if (n == 1) {
            printAll(arr);
            return;
        }
        for (int i = 0; i < n - 1; i++) {
            heapsRecAlgo(n - 1, arr);
            T last = arr.get(n - 1);
            if (n % 2 == 0) {
                arr.set(n - 1, arr.get(i));
                arr.set(i, last);
            } else {
                arr.set(n - 1, arr.get(0));
                arr.set(0, last);
            }
        }
        heapsRecAlgo(n - 1, arr);
    }

    private <T> void printAll(List<T> arr) {
        if (arr == null || arr.size() == 0) {
            return;
        }
        System.out.print(arr.get(0));
        for (int i = 1; i < arr.size(); i++) {
            System.out.print(" "+ arr.get(i));
        }
        System.out.println();
    }
    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4));
        AllPermutationsGenerator test = new AllPermutationsGenerator();
        test.printAll(arr);
        test.heapsRecAlgo(arr.size(), arr);
    }
}
