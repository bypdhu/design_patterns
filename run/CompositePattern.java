package run;

import java.util.ArrayList;
import java.util.List;

/**
 * 结构性模式
 * 组合模式，包括安全模式和透明模式
 * <p>
 * 安全模式和透明模式的区别
 * 1. 安全模式在抽象组件中只定义一些默认的行为或属性，它是把树枝节点和树叶节点彻底分开；透明模式是把用来组合使用的方法放到抽象类中，
 * 不管叶子对象还是树枝对象都有相同的结构，通过判断确认是叶子节点还是树枝节点，如果处理不当，这个会在运行期出现问题，不是很建议的方式。
 * 2. 安全模式与依赖倒置原则冲突；透明模式的好处就是它基本遵循了依赖倒转原则，方便系统进行扩展。
 * 3. 安全模式在遍历树形结构的的时候需要进行强制类型转换；在透明模式下，遍历整个树形结构是比较容易的，不用进行强制类型转换。
 *
 * @author bianbian
 * @date 2019/1/28
 */
public class CompositePattern {
    public static void main(String[] args) {
        testSecurity();

        testTransparent();
    }

    static void testSecurity() {
        Composite root = new Composite("根节点");

        Composite branch1 = new Composite("第一个分支");
        Composite branch2 = new Composite("第二个分支");
        Leaf leaf1 = new Leaf("第一个树叶");
        Leaf leaf2 = new Leaf("第二个树叶");
        Leaf leaf3 = new Leaf("第三个树叶");

        root.add(branch1);
        branch1.add(leaf3);
        branch1.add(branch2);
        branch1.add(leaf1);
        branch2.add(leaf2);
        root.display(null);

    }

    static void testTransparent() {
        Composite2 root = new Composite2("根节点");

        Composite2 branch1 = new Composite2("第一个分支");
        Composite2 branch2 = new Composite2("第二个分支");
        Leaf2 leaf1 = new Leaf2("第一个树叶");
        Leaf2 leaf2 = new Leaf2("第二个树叶");
        Leaf2 leaf3 = new Leaf2("第三个树叶");

        root.add(branch1);
        branch1.add(leaf3);
        branch1.add(branch2);
        branch1.add(leaf1);
        branch2.add(leaf2);
        root.display(null);

    }


}

/**
 * 安全模式
 */
interface ComponentSec {
    String getName();
}

class Composite implements ComponentSec {

    private String name;
    private List<ComponentSec> components = new ArrayList<>();

    public void add(ComponentSec component) {
        this.components.add(component);
    }

    public void remove(ComponentSec component) {
        this.components.remove(component);
    }

    public List<ComponentSec> list() {
        return this.components;
    }

    public void display(String prefix) {
        if (prefix == null) {
            prefix = "";
            System.out.println(this.getName() + "/");
        } else {
            System.out.println(prefix + "- " + this.getName() + "/");
        }

        prefix += "  ";
        for (ComponentSec component : this.list()) {
            if (component instanceof Leaf) {
                System.out.println(prefix + "- " + component.getName());
            } else {
                ((Composite) component).display(prefix);
            }
        }
    }


    @Override
    public String getName() {
        return this.name;
    }

    public Composite(String name) {
        this.name = name;
    }
}

class Leaf implements ComponentSec {

    private String name;

    @Override
    public String getName() {
        return this.name;
    }

    public Leaf(String name) {
        this.name = name;
    }
}


/**
 * 透明模式
 */
interface ComponentTran {
    String getName();

    void add(ComponentTran ct);

    void remove(ComponentTran ct);

    List<ComponentTran> list();
}

class Composite2 implements ComponentTran {
    private String name;
    private List<ComponentTran> components = new ArrayList<>();

    public Composite2(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void add(ComponentTran ct) {
        this.components.add(ct);
    }

    @Override
    public void remove(ComponentTran ct) {
        this.components.remove(ct);
    }

    @Override
    public List<ComponentTran> list() {
        return this.components;
    }

    public void display(String prefix) {
        if (prefix == null) {
            prefix = "";
            System.out.println(this.getName() + "/");
        } else {
            System.out.println(prefix + "- " + this.getName() + "/");
        }

        prefix += "  ";
        for (ComponentTran component : this.list()) {
            if (component instanceof Leaf2) {
                System.out.println(prefix + "- " + component.getName());
            } else {
                ((Composite2) component).display(prefix);
            }
        }
    }
}

class Leaf2 implements ComponentTran {

    private String name;

    public Leaf2(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    @Deprecated
    public void add(ComponentTran ct) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public void remove(ComponentTran ct) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public List<ComponentTran> list() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
}
