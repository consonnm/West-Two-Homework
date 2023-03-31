public abstract class Cat {
    protected String name;
    protected int age;
    protected double price;

    public Cat(String name, int age, double price) {
        this.name = name;
        this.age = age;
        this.price = price;
    }

    public abstract String toString();
}

class OrangeCat extends Cat {
    boolean isFat;

    public OrangeCat(String name, int age, boolean isFat) {
        super(name, age, 200);
        this.isFat = isFat;
    }

    public String toString() {
        return "姓名:" + name + "\n"
                + "年龄:" + age + "\n"
                + "真假:" + isFat + "\n"
                + "价格:" + price + "\n";
    }
}

class BlackCat extends Cat {
    public BlackCat(String name, int age) {
        super(name, age, 350);
    }

    public String toString() {
        return "姓名:" + name + "\n"
                + "年龄:" + age + "\n"
                + "价格:" + price + "\n";
    }
}

class WhiteCat extends Cat {
    public WhiteCat(String name, int age) {
        super(name, age, 400);
    }

    public String toString() {
        return "姓名:" + name + "\n"
                + "年龄:" + age + "\n"
                + "价格:" + price + "\n";
    }
}
