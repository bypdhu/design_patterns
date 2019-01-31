package run;

/**
 * 结构型模式
 * 装饰器模式
 *
 * @author bianbian
 * @date 2019/1/25
 */
public class DecoratorPattern {
    public static void main(String[] args) {
        test1();
        test2();

    }

    private static void test2() {
        Transform transform = new Car();
//        transform.move();

        Changer robot = new Robot(transform);
        robot.move();

        Changer airplane = new Airplane(transform);
        airplane.move();

    }

    private static void test1() {
        Component component = new ConcreteComponent();
        Decorator decorator = new ConcreteDecorator1(component);
        decorator.operation();

        Decorator decorator1 = new ConcreteDecorator1(component);
        decorator.operation();
    }
}

interface Component {
    void operation();
}

interface Decorator {
    void operation();
}

class ConcreteComponent implements Component {
    @Override
    public void operation() {
        System.out.println("ConcreteComponent operation");
    }
}

class ConcreteDecorator1 implements Decorator {

    @Override
    public void operation() {
        component.operation();
        System.out.println("ConcreteDecorator1 operation");
    }

    public ConcreteDecorator1(Component component) {
        this.component = component;
    }

    private Component component;
}

class ConcreteDecorator2 implements Decorator {

    @Override
    public void operation() {
        component.operation();
        System.out.println("ConcreteDecorator2 operation");
    }

    public ConcreteDecorator2(Component component) {
        this.component = component;
    }

    private Component component;
}


/**
 * 举例：变形金刚， 汽车、机器人、飞机
 */
interface Transform {
    void move();
}

class Car implements Transform {

    @Override
    public void move() {
        System.out.println("car move");
    }
}

interface Changer {
    void move();
}

class Robot implements Changer {
    @Override
    public void move() {
        transform.move();
        System.out.println("robot move");
        say();
    }

    private void say() {
        System.out.println("robot say");
    }

    public Robot(Transform transform) {
        this.transform = transform;
    }

    private Transform transform;
}

class Airplane implements Changer {

    @Override
    public void move() {
        transform.move();
        System.out.println("airplane move");
        fly();
    }

    private void fly() {
        System.out.println("airplane fly");
    }

    public Airplane(Transform transform) {
        this.transform = transform;
    }

    private Transform transform;
}