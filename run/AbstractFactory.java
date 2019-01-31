package run;

/**
 * 创建型模式
 * 抽象工厂模式
 *
 * @author bianbian
 * @date 2019/1/24
 */
public class AbstractFactory {
    public static void main(String[] args) {
        IAbstractFactory af1 = new AbstractFactory1();
        AbstractProductA a1 = af1.getProductA();
        a1.use();
        AbstractProductB b1 = af1.getProductB();
        b1.handle();

        IAbstractFactory af2 = new AbstractFactory2();
        AbstractProductA a2 = af2.getProductA();
        a2.use();
        AbstractProductB b2 = af2.getProductB();
        b2.handle();

    }
}


interface IAbstractFactory {
    AbstractProductA getProductA();

    AbstractProductB getProductB();
}

class AbstractFactory1 implements IAbstractFactory {

    @Override
    public AbstractProductA getProductA() {
        System.out.println("AbstractFactory1 getProductA");
        return new ProductA();
    }

    @Override
    public AbstractProductB getProductB() {
        System.out.println("AbstractFactory1 getProductB");
        return new ProductB();
    }
}

class AbstractFactory2 implements IAbstractFactory {


    @Override
    public AbstractProductA getProductA() {
        System.out.println("AbstractFactory2 getProductA");
        return new ProductA();
    }

    @Override
    public AbstractProductB getProductB() {
        System.out.println("AbstractFactory2 getProductB");
        return new ProductB();
    }
}

abstract class AbstractProductA {
    abstract void use();
}

interface AbstractProductB {
    void handle();
}

class ProductA extends AbstractProductA {
    @Override
    void use() {
        System.out.println("ProductA use");
    }
}

class ProductB implements AbstractProductB {

    @Override
    public void handle() {
        System.out.println("ProductB handle");
    }
}