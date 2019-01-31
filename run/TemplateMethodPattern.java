package run;

/**
 * 行为型模式
 * 模板方法模式
 * 模板方法模式是一种基于继承的代码复用技术，它是一种类行为型模式。
 *
 * @author bianbian
 * @date 2019/1/30
 */
public class TemplateMethodPattern {
    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        BankAccount account = new CurrentAccount();
        account.handle("张无忌", "12345");

        BankAccount account1 = new SavingAccount();
        account1.handle("叶孤城", "09876");
    }
}

abstract class BankAccount {
    public Boolean validate(String account, String password) {
        System.out.println(account + " " + password);
        return true;
    }

    public abstract void calculateInterest();

    public void display() {
        System.out.println("显示利息！");
    }

    public void handle(String account, String password) {
        if (!validate(account, password)) {
            System.out.println("账号或者密码错误！");
            return;
        } else {
            calculateInterest();
            display();
        }
    }
}

class CurrentAccount extends BankAccount {

    @Override
    public void calculateInterest() {
        System.out.println("按照活期利率计算利息！");
    }
}

class SavingAccount extends BankAccount {

    @Override
    public void calculateInterest() {
        System.out.println("按照定期利率计算利息！");
    }

    @Override
    public Boolean validate(String account, String password) {
        System.out.println(account + " " + password);
        return false;
    }
}