package run;

import java.util.ArrayList;
import java.util.List;

/**
 * 行为型模式
 * 迭代器模式
 *
 * @author bianbian
 * @date 2019/1/29
 */
public class IteratorPattern {
    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        List products = new ArrayList();
        products.add("倚天剑");
        products.add("屠龙刀");
        products.add("断肠草");
        products.add("葵花宝典");
        products.add("四十二章经");

        AbstractObjectList list;
        AbstractIterator iterator;

        list = new ProductObjectList(products); //创建聚合对象
        iterator = list.createIterator();    //创建迭代器对象

        System.out.println("正向遍历：");
        while (!iterator.isLast()) {
            System.out.print(iterator.getNextItem() + "，");
            iterator.next();
        }
        System.out.println();
        System.out.println("-----------------------------");
        System.out.println("逆向遍历：");
        while (!iterator.isFirst()) {
            System.out.print(iterator.getPreviousItem() + "，");
            iterator.previous();
        }
    }
}

/**
 * 抽象迭代器
 */
interface AbstractIterator {
    void next();

    Boolean isLast();

    Object getNextItem();

    void previous();

    Boolean isFirst();

    Object getPreviousItem();
}

/**
 * 具体迭代器：商品迭代器
 */
class ProductIterator implements AbstractIterator {
    private ProductObjectList productObjectList;
    private List products;
    private int cursor1;
    private int cursor2;

    public ProductIterator(ProductObjectList productObjectList) {
        this.productObjectList = productObjectList;
        this.products = productObjectList.getObjects();
        cursor1 = 0;
        cursor2 = this.products.size() - 1;
    }

    @Override
    public void next() {
        if (cursor1 < products.size()) {
            cursor1++;
        }
    }

    @Override
    public Boolean isLast() {
        return (cursor1 == products.size());
    }

    @Override
    public Object getNextItem() {
        return products.get(cursor1);
    }

    @Override
    public void previous() {
        if (cursor2 > -1) {
            cursor2--;
        }
    }

    @Override
    public Boolean isFirst() {
        return (cursor2 == -1);
    }

    @Override
    public Object getPreviousItem() {
        return products.get(cursor2);
    }
}

/**
 * 抽象聚合类
 */
abstract class AbstractObjectList {
    protected List objects = new ArrayList<>();

    public AbstractObjectList(List objects) {
        this.objects = objects;
    }

    public void addObject(Object object) {
        this.objects.add(object);
    }

    public void removeObject(Object object) {
        this.objects.remove(object);
    }

    public List getObjects() {
        return this.objects;
    }

    public abstract AbstractIterator createIterator();
}

/**
 * 具体聚合类：商品聚合类
 */
class ProductObjectList extends AbstractObjectList {

    public ProductObjectList(List objects) {
        super(objects);
    }

    @Override
    public AbstractIterator createIterator() {
        return new ProductIterator(this);
    }
}