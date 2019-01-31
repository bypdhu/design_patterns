package run;

/**
 * 创建型模式
 * 建造者模式
 *
 * @author bianbian
 * @date 2019/1/24
 */
public class BuildPattern {
    public static void main(String[] args) {
        IBuilder builder = new Builder();
        IDirector director = new Director();
        director.setBuilder(builder);
        Product a = director.construct("A");
        a.use();

        Product b = director.construct("B");
        b.use();
    }
}

interface IBuilder {
    void buildA();

    void buildB();

    Product getResult(String type);
}

class Builder implements IBuilder {

    @Override
    public void buildA() {
        System.out.println("Builder buildA");
    }

    @Override
    public void buildB() {
        System.out.println("Builder buildB");
    }

    @Override
    public Product getResult(String type) {
        if ("A".equals(type)) {
            return new Product1();
        } else {
            return new Product2();
        }
    }
}

interface IDirector {
    void setBuilder(IBuilder builder);

    Product construct(String type);
}

class Director implements IDirector {

    private IBuilder builder;

    @Override
    public void setBuilder(IBuilder builder) {
        this.builder = builder;
    }

    @Override
    public Product construct(String type) {
        builder.buildA();
        builder.buildB();
        return builder.getResult(type);
    }
}
