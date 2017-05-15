import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AllPermutationsGenerator {
    private <T> void generate(int n, List<T> arr) {
        if (n == 1) {
            //printAll(arr);
            return;
        }
        for (int i = 0; i < n - 1; i++) {
            generate(n - 1, arr);
            T last = arr.get(n - 1);
            if (n % 2 == 0) {
                arr.set(n - 1, arr.get(i));
                arr.set(i, last);
            } else {
                arr.set(n - 1, arr.get(0));
                arr.set(0, last);
            }
        }
        generate(n - 1, arr);
    }

    public <T> void heapsRecAlgo(List<T> items) {
        generate(items.size(), items);
    }

    private <T> List<T> getPermutation(int n, int k, List<T> items) {
        int ind, m = k;
        int[] permuted = new int[n];
        int[] elems = new int[n];
        for (int i = 0; i < n; i++) {
            elems[i] = i;
        }
        for (int i = 0; i < n; i++) {
            ind = m % (n - i);
            m = m / (n - i);
            permuted[i] = elems[ind];
            elems[ind] = elems[n - i - 1];
        }
        List<T> permutation = new ArrayList<T>(items);
        for (int i = 0; i < n; i++) {
            permutation.set(permuted[i], items.get(i));
        }
        return permutation;
    }

    public <T> void factoradicAlgo(List<T> items) {
        int fact = 1;
        for (int i = 1; i <= items.size(); i++) {
            fact *= i;
        }
        for (int i = 0; i < fact; i++) {
            getPermutation(items.size(), i, items);
        }
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
        System.out.println("Write number of items");
        //Scanner in = new Scanner();
        //int n = in.nextInt();
        List<Integer> arr = new ArrayList<Integer>();
        for (int i = 1; i <= 11; i++) {
            arr.add(i);
        }
        AllPermutationsGenerator test = new AllPermutationsGenerator();

        long startTime = System.nanoTime();
        test.heapsRecAlgo(arr);
        long duration = (System.nanoTime() - startTime);
        int timeProcessing = (int) (duration / 1000000);
        System.out.println("Heap's algo: " + timeProcessing);

        startTime = System.nanoTime();
        test.factoradicAlgo(arr);
        duration = (System.nanoTime() - startTime);
        timeProcessing = (int) (duration / 1000000);
        System.out.println("Factoradic algo: " + timeProcessing);

    }
}
