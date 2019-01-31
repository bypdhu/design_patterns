package run;

/**
 * 创建型模式
 * 简单工厂模式
 *
 * @author Administrator
 */
public class SimpleFactory {

    public static Product getProduct(String type) {
        if ("A".equals(type)) {
            return new Product1();
        } else if ("B".equals(type)) {
            return new Product2();
        }
        return null;
    }

    public static void main(String[] args) {
        Product a = SimpleFactory.getProduct("A");
        assert a != null;
        a.use();

        Product b = SimpleFactory.getProduct("B");
        assert b != null;
        b.use();

    }


}

interface Product {
    void use();
}

class Product1 implements Product {

    @Override
    public void use() {
        System.out.println("Product1");
    }
}

class Product2 implements Product {

    @Override
    public void use() {
        System.out.println("Product2");
    }
}