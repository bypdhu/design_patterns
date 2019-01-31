package run;

/**
 * 结构型模式
 * 外观模式，又称为门面模式
 *
 * @author bianbian
 * @date 2019/1/25
 */
public class FacadePattern {
    public static void main(String[] args) {
        Facade facade = new FacadeImpl();
        facade.wrapOperation();
    }
}

interface Facade {
    void wrapOperation();
}

/**
 * 新增SystemD时，新建一个具体实现FacadeImpl2
 */
class FacadeImpl implements Facade {

    @Override
    public void wrapOperation() {
        systemA.operation();
        systemB.operation();
        systemC.operation();
    }

    public FacadeImpl() {
        systemA = new SystemA();
        systemB = new SystemB();
        systemC = new SystemC();
    }

    private SystemA systemA;
    private SystemB systemB;
    private SystemC systemC;
}

class SystemA {
    public void operation() {
        System.out.println("SystemA operation");
    }
}

class SystemB {
    public void operation() {
        System.out.println("SystemB operation");
    }
}

class SystemC {
    public void operation() {
        System.out.println("SystemC operation");
    }
}