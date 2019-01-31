package run;

/**
 * 结构型模式
 * 代理模式
 *
 * @author bianbian
 * @date 2019/1/28
 */
public class ProxyPattern {
    public static void main(String[] args) {
        Proxy proxy = new RealProxy();
        proxy.request();
    }
}

interface Subject {
    void request();
}

class RealSubject implements Subject {

    @Override
    public void request() {
        System.out.println("RealSubject request");
    }
}

interface Proxy extends Subject {

}

class RealProxy implements Proxy {

    private RealSubject subject;

    public RealProxy() {
        this.subject = new RealSubject();
    }

    @Override
    public void request() {
        System.out.println("RealProxy request");
        this.preRequest();
        this.subject.request();
        this.postRequest();
    }

    private void preRequest() {
        System.out.println("RealProxy preRequest");
    }

    private void postRequest() {
        System.out.println("RealProxy postRequest");
    }
}

