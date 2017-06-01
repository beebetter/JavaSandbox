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

    public Knapsack() {
    }

    public class Item {
        Integer weight;
        Integer price;

        public Item(Integer weight, Integer price) {
            this.weight = weight;
            this.price = price;
        }
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public int getMaxWeight() {
        return maxWeight;
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
        List<Item> items = new ArrayList<Item>();
        for (int i = 1; i <= n; i++) {
            items.add(new Item(((i * 12 + 5) / 10) * 10, i * 10));
        }
        this.items = items;
    }

    public void printItems() {
        System.out.println("List of all items in knapsack");
        System.out.println("ціна вага");
        for (int i = 0; i < items.size(); i++) {
            System.out.println(items.get(i).price + " " + items.get(i).weight);
        }
    }

    public int calculateMaxWeight(int n) {
        return ((n * (n + 1) / 4)) * 10 + 5;
    }

    public void ini(int n) {
        setItems(n);
        int maxWeight = calculateMaxWeight(n);
        setMaxWeight(maxWeight);
    }


    public void printResult() {
        if (curBestItems != null && curBestItems.size() > 0) {
            System.out.println("Take:");
            for (int i = 0; i < curBestItems.size(); i++) {
                System.out.println(curBestItems.get(i).price + " " + curBestItems.get(i).weight );
            }
            System.out.println("Price: " + curMaxPrice + " Weight: " + curOptWeight);
        } else {
            System.out.println("There are no elements to take");
        }

    }

    public static void main(String[] args) {
        System.out.println("Write number of items (<=12) and number of tests");
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), numOfTests = in.nextInt();
        Knapsack ks = new Knapsack();
        ks.ini(n);
        ks.printItems();
        System.out.println("Максимальна_вага: " + ks.getMaxWeight());
        long allDuration = 0;
        int minTime = 999999999, maxTime = 0;
        System.out.println("-------------------------------------------");
        System.out.println("Номер_тесту Час");
        for (int i = 1; i <= numOfTests; i++) {
            Knapsack knapsack = new Knapsack();
            knapsack.ini(n);
            long startTime = System.nanoTime();
            knapsack.factoradicAlgo();
            long duration = (System.nanoTime() - startTime);
            allDuration +=duration;
            int timeProcessing = (int) (duration / 1000000);
            if (minTime > timeProcessing) {
                minTime = timeProcessing;
            }
            if (maxTime < timeProcessing) {
                maxTime = timeProcessing;
            }
            System.out.println(i + " " + timeProcessing);
            if (i == numOfTests) {
                System.out.println("-------------------------------------------");
                knapsack.printResult();
            }
        }
        int averageTimeProcessing = (int) (allDuration / 1000000 / numOfTests);
        System.out.println("-------------------------------------------");
        System.out.println("Кількість елементів | Кількість тестів | Середній час роботи (мс)");
        System.out.println(n + " " + numOfTests + " " + averageTimeProcessing);
    }

    public int getCurMaxPrice() {
        return this.curMaxPrice;
    }
}
