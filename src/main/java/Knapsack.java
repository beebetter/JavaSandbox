import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Knapsack implements Serializable {
    private int maxWeight;
    private int curMaxPrice;
    private int curOptWeight;
    private List<Item> items;
    private List<Item> curBestItems;

    public Knapsack(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public class Item {
        Integer weight;
        Integer price;

        public Item(Integer weight, Integer price) {
            this.weight = weight;
            this.price = price;
        }
    }

    private List<Item> getPermutation(int n, int k, List<Item> items) {
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
        List<Item> permutation = new ArrayList<Item>(items);
        for (int i = 0; i < n; i++) {
            permutation.set(permuted[i], items.get(i));
        }
        return permutation;
    }

    public void factoradicAlgo() {
        int fact = 1;
        for (int i = 1; i <= items.size(); i++) {
            fact *= i;
        }
        for (int i = 0; i < fact; i++) {
            check(getPermutation(items.size(), i, items));
        }
    }

    public void factoradicAlgo(int begin, int end) {
        for (int i = begin; i < end; i++) {
            check(getPermutation(items.size(), i, items));
        }
    }

    public void check(List<Item> items) {
        int i = 0, curPrice = 0, curWeight = 0;
        for (; i < items.size() && curWeight + items.get(i).weight <= maxWeight; i++) {
            curWeight += items.get(i).weight;
            curPrice += items.get(i).price;
        }
        if (curPrice > curMaxPrice) {
            curBestItems = new ArrayList<Item>();
            for (int j = 0; j < i; j++) {
                curBestItems.add(items.get(j));
            }
            curMaxPrice = curPrice;
            this.curOptWeight = curWeight;
        }
    }

    public void setItems(int n) {
        List<Item> allPossibleItems = new ArrayList<Item>();
        allPossibleItems.add(new Item(10, 10));
        allPossibleItems.add(new Item(20, 20));
        allPossibleItems.add(new Item(30, 40));
        allPossibleItems.add(new Item(40, 60));
        allPossibleItems.add(new Item(50, 70));
        allPossibleItems.add(new Item(60, 90));
        allPossibleItems.add(new Item(70, 100));
        allPossibleItems.add(new Item(80, 110));
        allPossibleItems.add(new Item(90, 120));
        allPossibleItems.add(new Item(100, 125));
        allPossibleItems.add(new Item(110, 130));
        allPossibleItems.add(new Item(120, 135));
        List<Item> items = new ArrayList<Item>();
        for (int i = 0; i < n; i++) {
            items.add(allPossibleItems.get(i));
        }
        this.items = items;
    }

    public void printResult() {
        if (curBestItems != null && curBestItems.size() > 0) {
            System.out.println("Take:");
            for (int i = 0; i < curBestItems.size(); i++) {
                System.out.println(curBestItems.get(i).weight + " " + curBestItems.get(i).price);
            }
            System.out.println("Weight: " + curOptWeight + " Price: " + curMaxPrice);
        } else {
            System.out.println("There are no elements to take");
        }

    }

    public static void main(String[] args) {
        System.out.println("Write [number] of items (<=12) and [maxWeight]");
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), maxWeight = in.nextInt();
        Knapsack knapsack = new Knapsack(maxWeight);
        knapsack.setItems(n);
        long startTime = System.nanoTime();
        knapsack.factoradicAlgo();
        long duration = (System.nanoTime() - startTime);
        int timeProcessing = (int) (duration / 1000000);
        System.out.println("Factoradic algo processing time: " + timeProcessing);
        knapsack.printResult();
    }
}
