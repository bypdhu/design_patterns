package run;

import java.util.ArrayList;
import java.util.List;

/**
 * 行为型模式
 * 观察者模式，又叫做发布-订阅模式、模型-视图模式、源-监听器模式或从属者模式。
 *
 * @author bianbian
 * @date 2019/1/28
 */
public class ObserverPattern {
    public static void main(String[] args) {
        test1();
    }

    static void test1() {
        ISubject subject = new SubjectImpl("主题1");
        Observer observer = new ObserverImpl("观察者1");
        Observer observer1 = new ObserverImpl("观察者2");

        subject.attach(observer);
        subject.attach(observer1);

        subject.setStatus("123");
        subject.notice();
        System.out.println("-------------");

        subject.detach(observer);
        subject.setStatus("231313131");
        subject.notice();

    }
}

interface ISubject {
    void attach(Observer observer);

    void detach(Observer observer);

    void notice();

    String getStatus();

    void setStatus(String status);

    String getName();
}

class SubjectImpl implements ISubject {

    @Override
    public void attach(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notice() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String getStatus() {
        return this.status;
    }

    @Override
    public String getName() {
        return name;
    }

    private String name;
    private String status;
    private List<Observer> observers = new ArrayList<>();

    public SubjectImpl(String name) {
        this.name = name;
    }
}

interface Observer {
    void update(ISubject subject);

}

class ObserverImpl implements Observer {

    @Override
    public void update(ISubject subject) {
        String status = subject.getStatus();
        System.out.println("ObserverImpl " + name + " : " + "[update] status " + status + " from " + subject.getName());

    }

    private String name;

    public ObserverImpl(String name) {
        this.name = name;
    }
}