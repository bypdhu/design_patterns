package run;

import java.util.HashMap;
import java.util.Map;

/**
 * 结构型模式
 * 享元模式
 *
 * @author bianbian
 * @date 2019/1/28
 */
public class FlyweightPattern {
    public static void main(String[] args) {
        Flyweight one = FlyweightFactory.getFlyweight("one");
        one.operation();

        Flyweight two = FlyweightFactory.getFlyweight("two");
        two.operation();

        Flyweight three = FlyweightFactory.getFlyweight("one");
        three.operation();
    }
}

interface Flyweight {
    void operation();
}

class ConcreteFlyweight implements Flyweight {

    private String key;

    @Override
    public void operation() {
        System.out.println("ConcreteFlyweight operation, key: " + this.key);
    }

    public ConcreteFlyweight(String key) {
        this.key = key;
    }
}

class UnsharedConcreteFlyweight implements Flyweight {

    @Override
    public void operation() {
        System.out.println("UnsharedConcreteFlyweight operation");
    }
}

class FlyweightFactory {
    private static Map<String, Flyweight> map = new HashMap<>();

    public static Flyweight getFlyweight(String key) {
        if (key == null) {
            return null;
        }
        if (map.get(key) != null) {
            return map.get(key);
        } else {
            Flyweight fw = new ConcreteFlyweight(key);
            map.put(key, fw);
            return fw;
        }
    }
}


