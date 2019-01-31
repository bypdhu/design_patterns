package run;

/**
 * 创建型模式
 * 单例模式
 *
 * @author bianbian
 * @date 2019/1/24
 */
public class Singleton {
    public static void main(String[] args) {
        Singleton1 singleton1 = Singleton1.getInstance();
        System.out.println(singleton1);
        Singleton1 singleton11 = Singleton1.getInstance();
        System.out.println(singleton11);

        assert singleton1 == singleton11;
    }
}

class Singleton1 {
    private static Singleton1 instance;

    private Singleton1() {
    }


    public static Singleton1 getInstance() {
        if (instance == null) {
            instance = new Singleton1();
        }
        return instance;
    }
}
