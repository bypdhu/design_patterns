package run;

/**
 * 行为型模式
 * 策略模式
 *
 * @author bianbian
 * @date 2019/1/28
 */
public class StrategyPattern {
    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        StrategyContext context = new StrategyContext();

        Strategy strategy1 = new Strategy1();
        context.setStrategy(strategy1);
        context.algorithm();

        Strategy strategy2 = new Strategy2();
        context.setStrategy(strategy2);
        context.algorithm();
    }
}

class StrategyContext {
    void algorithm() {
        this.strategy.algorithm();
    }

    void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    private Strategy strategy;
}

interface Strategy {
    void algorithm();
}

class Strategy1 implements Strategy {

    @Override
    public void algorithm() {
        System.out.println("this is strategy1 algorithm.");
    }
}

class Strategy2 implements Strategy {

    @Override
    public void algorithm() {
        System.out.println("this is strategy2 algorithm.");
    }
}