package run;

/**
 * 行为型模式
 * 状态模式，其别名为状态对象(Objects for States)，状态模式是一种对象行为型模式。
 *
 * @author bianbian
 * @date 2019/1/28
 */
public class StatePattern {
    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        HaveDinner haveDinner = new HaveDinner();
        haveDinner.changeState(new ChooseDinner());

        haveDinner.next();
        haveDinner.next();
        haveDinner.next();
        try {
            haveDinner.next();
        } catch (Exception e) {
            System.out.println("!!! No more state");
        }
    }
}

/**
 * 如吃晚饭流程，可以分为取餐、吃饭、收拾三个部分。
 */
class HaveDinner {
    void changeState(DinnerState state) {
        this.state = state;
    }

    void next() {
        this.state.handle(this);
    }

    private DinnerState state;
}

interface DinnerState {
    void handle(HaveDinner dinner);
}

class ChooseDinner implements DinnerState {

    @Override
    public void handle(HaveDinner dinner) {
        System.out.println("now choose dinner");
        dinner.changeState(new EatDinner());
    }
}

class EatDinner implements DinnerState {

    @Override
    public void handle(HaveDinner dinner) {
        System.out.println("now eat dinner");
        dinner.changeState(new FinishDinner());
    }
}

class FinishDinner implements DinnerState {

    @Override
    public void handle(HaveDinner dinner) {
        System.out.println("now finish dinner.");
        dinner.changeState(null);
    }
}