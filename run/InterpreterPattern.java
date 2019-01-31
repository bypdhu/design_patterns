package run;

import java.util.Stack;

/**
 * 行为型模式
 * 解释器模式
 *
 * @author bianbian
 * @date 2019/1/29
 */
public class InterpreterPattern {
    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        String instruction = "up move 5m and left run 10m and right move 23km";
        InstructionHandler handler = new InstructionHandler();
        handler.handle(instruction);
        System.out.println(handler.output());

    }
}

class InstructionHandler {
    private String instruction;
    private AbstractNode node;

    public void handle(String instruction) {
        AbstractNode left, right;
        AbstractNode direction, action, distance;
        Stack stack = new Stack();
        String[] words = instruction.split(" ");
        for (int i = 0; i < words.length; i++) {
            if ("and".equalsIgnoreCase(words[i])) {
                left = (AbstractNode) stack.pop();
                direction = new DirectionNode(words[++i]);
                action = new ActionNode(words[++i]);
                distance = new DistanceNode(words[++i]);

                right = new SentenceNode(direction, action, distance);
                stack.push(new AndNode(left, right));
            } else {
                direction = new DirectionNode(words[i]);
                action = new ActionNode(words[++i]);
                distance = new DistanceNode(words[++i]);

                stack.push(new SentenceNode(direction, action, distance));
            }
        }
        this.node = (AbstractNode) stack.pop();
    }

    public String output() {
        return this.node.interpret();
    }
}

abstract class AbstractNode {
    public abstract String interpret();
}

/**
 * And解释：非终结符表达式
 */
class AndNode extends AbstractNode {

    private AbstractNode left;
    private AbstractNode right;

    public AndNode(AbstractNode left, AbstractNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String interpret() {
        return this.left.interpret() + " 然后 " + this.right.interpret();
    }
}

/**
 * 简单句子解释：非终结符表达式
 */
class SentenceNode extends AbstractNode {

    private AbstractNode direction;
    private AbstractNode action;
    private AbstractNode distance;

    public SentenceNode(AbstractNode direction, AbstractNode action, AbstractNode distance) {
        this.direction = direction;
        this.action = action;
        this.distance = distance;
    }

    @Override
    public String interpret() {
        return direction.interpret() + action.interpret() + distance.interpret();
    }
}

/**
 * 方向解释：终结符表达式
 */
class DirectionNode extends AbstractNode {
    private String direction;

    public DirectionNode(String direction) {
        this.direction = direction;
    }

    @Override
    public String interpret() {
        switch (this.direction.toLowerCase()) {
            case "up":
                return "向上";
            case "down":
                return "向下";
            case "left":
                return "向左";
            case "right":
                return "向右";
            default:
                return "无效方向";
        }
    }
}

/**
 * 动作解释：终结符表达式
 */
class ActionNode extends AbstractNode {
    private String node;

    public ActionNode(String node) {
        this.node = node;
    }

    @Override
    public String interpret() {
        switch (this.node.toLowerCase()) {
            case "move":
                return "移动";
            case "run":
                return "快速移动";
            default:
                return "无效操作";
        }
    }
}

/**
 * 距离解释：终结符表达式
 */
class DistanceNode extends AbstractNode {

    private String distance;

    public DistanceNode(String distance) {
        this.distance = distance;
    }

    @Override
    public String interpret() {
        return this.distance;
    }
}