package run;

/**
 * 创建型模式
 * 工厂方法模式
 *
 * @author bianbian
 * @date 2019/1/24
 */
public class FactoryMethod {
    public static void main(String[] args) {
        IFactoryMethod fm1 = new FactoryMethod1();
        Product a = fm1.getProduct();
        a.use();

        IFactoryMethod fm2 = new FactoryMethod2();
        Product b = fm2.getProduct();
        b.use();
    }
}

interface IFactoryMethod {
    Product getProduct();
}

class FactoryMethod1 implements IFactoryMethod {

    @Override
    public Product getProduct() {
        return new Product1();
    }
}

class FactoryMethod2 implements IFactoryMethod {

    @Override
    public Product getProduct() {
        return new Product2();
    }
}