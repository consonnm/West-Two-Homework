import java.util.ArrayList;
import java.util.Random;

interface CatCafe {
    void buyCat(Cat a);

    void serveCustomers(Customer b, int frequency);

    void shutdown();
}

public class MyCatCafe implements CatCafe {
    private double balance;
    private int number = 0;
    private int catnumber = 0;
    ArrayList<Cat> cats = new ArrayList<>();
    ArrayList<Customer> customers = new ArrayList<>();

    public MyCatCafe(double balance) {
        this.balance = balance;
        System.out.println("今日猫咖开业啦！");
    }

    public void buyCat(Cat a) throws InsufficientBalanceException {
        cats.add(a);
        if (balance < a.price)
            throw new InsufficientBalanceException("店铺余额不足啦！无法购买新的猫猫！");
        else {
            balance -= a.price;
            catnumber++;
        }

    }

    public void serveCustomers(Customer b, int frequency) throws ClassCastException {
        customers.add(b);
        Random r = new Random();
        int i;
        for (i = 0; i < frequency; i++) {
            int sequence = r.nextInt(cats.size());
            if (catnumber < 0)
                throw new CatNotFoundException("猫咖里的猫猫都被占用啦!");
            else {
                balance += 15;
                number++;
                catnumber--;
                System.out.println("你rua了猫猫" + "\n" + cats.get(sequence) + "花费了15元" + "\n");
            }
        }
    }

    public void shutdown() {
        System.out.println("今天的营业结束啦！");
        System.out.println("今天的顾客有:");
        for (Customer e : customers) {
            System.out.println(e);
        }
        System.out.println("今天的营收为:" + number * 15 + "!");
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}