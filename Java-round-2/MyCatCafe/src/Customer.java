import java.time.LocalTime;

public class Customer {
    private final String name;
    private final int rua;
    LocalTime now = LocalTime.now();

    public Customer(String name, int rua) {
        this.name = name;
        this.rua = rua;
    }

    public String toString() {
        return "姓名:" + name + "\n" + "撸猫的次数:" + rua + "\n" + "到店时间:" + now;
    }
}
