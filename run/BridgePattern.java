package run;

/**
 * 结构型模式
 * 桥接模式，又称为柄体(Handle and Body)模式或接口(Interface)模式。
 *
 * @author bianbian
 * @date 2019/1/25
 */
public class BridgePattern {
    public static void main(String[] args) {
        Implementor implementor1 = new ConcreteImplementor1();
        Abstraction abstraction1 = new RefinedAbstraction(implementor1);
        abstraction1.operation();

        Abstraction abstraction2 = new RefinedAbstraction(new ConcreteImplementor2());
        abstraction2.operation();
    }
}

class RefinedAbstraction implements Abstraction {

    private Implementor implementor;

    public RefinedAbstraction(Implementor implementor) {
        this.implementor = implementor;
    }

    @Override
    public void operation() {
        implementor.operationImpl();
    }

}

interface Abstraction {
    void operation();
}

interface Implementor {
    void operationImpl();
}

class ConcreteImplementor1 implements Implementor {

    @Override
    public void operationImpl() {
        System.out.println("ConcreteImplementor1 operationImpl");
    }
}


class ConcreteImplementor2 implements Implementor {

    @Override
    public void operationImpl() {
        System.out.println("ConcreteImplementor2 operationImpl");
    }
}