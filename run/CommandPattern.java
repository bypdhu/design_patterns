package run;

/**
 * 行为型模式
 * 命令模式，其别名为动作(Action)模式或事务(Transaction)模式
 *
 * @author bianbian
 * @date 2019/1/28
 */
public class CommandPattern {
    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        Television television = new Television();

        TVCommand tvCommand1 = new OnCommand(television);
        TVController tvController1 = new TVController(tvCommand1);
        tvController1.call();

        TVCommand tvCommand = new OffCommand(television);
        TVController tvController = new TVController(tvCommand);
        tvController.call();

    }
}

/**
 * 以电视机遥控器为例。
 * 遥控器为调用者Invoker，电视机为接受者Receiver
 */
class Television {
    void off() {
        System.out.println("Television off");
    }

    void on() {
        System.out.println("Television on");
    }
}

/**
 * 遥控器的一个按钮
 */
class TVController {
    private TVCommand tvCommand;

    public TVController(TVCommand tvCommand) {
        this.tvCommand = tvCommand;
    }

    void call() {
        tvCommand.execute();
    }
}


interface TVCommand {
    void execute();
}

class OffCommand implements TVCommand {

    private Television television;

    public OffCommand(Television television) {
        this.television = television;
    }

    @Override
    public void execute() {
        television.off();
    }
}

class OnCommand implements TVCommand {
    private Television television;

    public OnCommand(Television television) {
        this.television = television;
    }

    @Override
    public void execute() {
        television.on();
    }
}