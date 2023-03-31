import java.time.LocalTime;
import java.util.Scanner;

public class MyCafeText {
    public static void main(String[] args) {
        LocalTime end = LocalTime.of(10, 30);
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你猫咖的启动资金:");
        double balance = sc.nextDouble();
        MyCatCafe Ufo = new MyCatCafe(balance);
        Ufo.setBalance(balance);
        OrangeCat Jason = new OrangeCat("庄庄", 2, true);
        BlackCat Conson = new BlackCat("泽泽", 1);
        WhiteCat Eason = new WhiteCat("呱呱", 3);
        try {
            Ufo.buyCat(Jason);
            System.out.println("购买成功!" + "\n" + Jason);
            Ufo.buyCat(Conson);
            System.out.println("购买成功:" + "\n" + Conson);
            Ufo.buyCat(Eason);
            System.out.println("购买成功:" + "\n" + Eason);
        } catch (InsufficientBalanceException e1) {
            System.out.println(e1.getMessage());
        }
        System.out.println("今日猫咖开始招待客人了");
        try {
            while (true) {
                System.out.println("请输入您的姓名和想撸猫的次数:");
                String name = sc.next();
                int rua = sc.nextInt();
                Customer Michael = new Customer(name, rua);
                Ufo.serveCustomers(Michael, rua);
                LocalTime now = LocalTime.now();
                if (now.isAfter(end)) {
                    Ufo.shutdown();
                    break;
                }
            }
        } catch (CatNotFoundException e2) {
            System.out.println(e2.getMessage());
        }
    }
}
