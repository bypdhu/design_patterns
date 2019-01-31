package run;

import java.util.HashMap;
import java.util.Map;

/**
 * 创建型模式
 * 原型模式： 简单形式、登记形式
 *
 * @author bianbian
 * @date 2019/1/25
 */
public class PrototypePattern {
    public static void main(String[] args) {
        test1();
        test2();
    }

    static void test1() {
        Prototype p21 = new Prototype2();
        System.out.println(p21);
        Prototype p22 = (Prototype) p21.clone();
        System.out.println(p22);

        Prototype p11 = new Prototype1();
        System.out.println(p11);
        Prototype p12 = (Prototype) p11.clone();
        System.out.println(p12);
    }

    static void test2() {
        try {
            Prototype p11 = new Prototype1();
            PrototypeManager.setPrototype("p", p11);

            Prototype p13 = (Prototype) PrototypeManager.getPrototype("p").clone();
            p13.setName("张三");
            System.out.println("第一个实例: " + p13);

            Prototype p133 = (Prototype) PrototypeManager.getPrototype("p").clone();
            p13.setName("张三3");
            System.out.println("第一3个实例: " + p133);

            Prototype p21 = new Prototype2();
            PrototypeManager.setPrototype("p", p21);
            Prototype p24 = (Prototype) PrototypeManager.getPrototype("p").clone();
            p24.setName("李四");
            System.out.println("第二个实例： " + p24);

            PrototypeManager.removePrototype("p");
            Prototype p25 = (Prototype) PrototypeManager.getPrototype("p").clone();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

class PrototypeManager {
    private static Map<String, Prototype> map = new HashMap<>();

    public synchronized static void setPrototype(String id, Prototype prototype) {
        map.put(id, prototype);
    }

    public synchronized static void removePrototype(String id) {
        map.remove(id);
    }

    public synchronized static Prototype getPrototype(String id) throws Exception {
        Prototype prototype = map.get(id);
        if (prototype == null) {
            throw new Exception("您希望获取的原型还没有注册或已被销毁");
        }
        return prototype;
    }

    private PrototypeManager() {
    }
}

interface Prototype {
    public Object clone();

    void setName(String name);

    String getName();
}

class Prototype1 implements Prototype {

    private String name;

    @Override
    public Prototype clone() {
        Prototype prototype = new Prototype1();
        return prototype;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("Now in Prototype1(%s), name=%s", this.hashCode(), this.name);
    }
}


class Prototype2 implements Prototype {
    private String name;

    @Override
    public Prototype clone() {
        Prototype prototype = new Prototype2();
        return prototype;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("Now in Prototype2(%s), name=%s", this.hashCode(), this.name);
    }
}


