package run;

/**
 * 结构型模式
 * 适配器模式，其别名为包装器，既可以作为类结构型模式，也可以作为对象结构型模式。
 *
 * @author bianbian
 * @date 2019/1/25
 */
public class AdapterPattern {
    public static void main(String[] args) {
        test1();
        test2();
    }

    static void test1() {
        Adaptee adaptee = new Adaptee();
        Target target = new Adapter(adaptee);
        target.run();
    }

    static void test2() {
        Target target = new Adapter2();
        target.run();
    }
}

interface Target {
    void run();
}

/**
 * 对象适配器
 */
class Adapter implements Target {

    private Adaptee adaptee;

    @Override
    public void run() {
        this.adaptee.realRun();
    }

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }
}

class Adaptee {
    public void realRun() {
        System.out.println("Adaptee realRun");
    }

}

/**
 * 类适配器
 */
class Adapter2 extends Adaptee2 {

    @Override
    public void run() {
        this.realRun();
    }

}

class Adaptee2 implements Target {
    public void realRun() {
        System.out.println("Adaptee2 realRun");
    }

    @Override
    public void run() {
        System.out.println("Adaptee2 run");
    }
}