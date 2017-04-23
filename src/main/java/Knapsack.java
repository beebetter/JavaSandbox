import java.util.ArrayList;
import java.util.List;

public class Knapsack {
    private int maxWeight;
    private int curMaxPrice;
    private int curOptWeight;
    private List <Item> items;
    private List <Item> curBestItems;

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
    public void check(List<Item> items) {
        int i = 0, curPrice = 0, curWeight = 0;
        for (; i < items.size() && curWeight + items.get(i).weight  <= maxWeight; i++) {
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
    public void setItems() {
        List<Item> items = new ArrayList<Item>();
        items.add(new Item(10, 10));
        items.add(new Item(20, 20));
        items.add(new Item(30, 40));
        items.add(new Item(40, 60));
        items.add(new Item(50, 70));
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
        Knapsack knapsack = new Knapsack(60);
        knapsack.setItems();
        knapsack.factoradicAlgo();
        knapsack.printResult();
    }
}
