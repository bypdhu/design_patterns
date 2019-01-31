package run;

import java.util.ArrayList;
import java.util.List;

/**
 * 行为型模式
 * 备忘录模式
 *
 * @author bianbian
 * @date 2019/1/30
 */
public class MementoPattern {

    private static int index = -1; //定义一个索引来记录当前状态所在位置
    private static MementoCaretaker mc = new MementoCaretaker();

    public static void main(String[] args) {
        testSaveOneState();

        System.out.println("");
        System.out.println("");
        System.out.println("");

        testSaveManyStates();
    }

    private static void testSaveManyStates() {
        Chessman chess = new Chessman("车",1,1);
        play(chess);
        chess.setY(4);
        play(chess);
        chess.setX(5);
        play(chess);
        undo(chess,index);
        undo(chess,index);
        redo(chess,index);
        redo(chess,index);


    }

    //下棋
    private static void play(Chessman chess) {
        mc.setMemento(chess.save()); //保存备忘录
        index++;
        System.out.println("棋子" + chess.getLabel() + "当前位置为：" + "第" + chess.getX() + "行" + "第" + chess.getY() + "列。");
    }

    //悔棋
    private static void undo(Chessman chess, int i) {
        System.out.println("******悔棋******");
        index--;
        chess.restore(mc.getMemento(i - 1)); //撤销到上一个备忘录
        System.out.println("棋子" + chess.getLabel() + "当前位置为：" + "第" + chess.getX() + "行" + "第" + chess.getY() + "列。");
    }

    //撤销悔棋
    private static void redo(Chessman chess, int i) {
        System.out.println("******撤销悔棋******");
        index++;
        chess.restore(mc.getMemento(i + 1)); //恢复到下一个备忘录
        System.out.println("棋子" + chess.getLabel() + "当前位置为：" + "第" + chess.getX() + "行" + "第" + chess.getY() + "列。");
    }

    private static void testSaveOneState() {
        MementoCaretakerOne mc2 = new MementoCaretakerOne();
        Chessman chess = new Chessman("车", 1, 1);
        display(chess);
        mc2.setMemento(chess.save()); //保存状态
        chess.setY(4);
        display(chess);
        mc2.setMemento(chess.save()); //保存状态
        display(chess);
        chess.setX(5);
        display(chess);
        System.out.println("******悔棋******");
        chess.restore(mc2.getMemento()); //恢复状态
        display(chess);

    }

    private static void display(Chessman chess) {
        System.out.println("棋子" + chess.getLabel() + "当前位置为：" + "第" + chess.getX() + "行" + "第" + chess.getY() + "列。");
    }

}

/**
 * 以象棋悔棋为例
 */
class Chessman {
    private String label;
    private int x;
    private int y;

    public Chessman(String label, int x, int y) {
        this.label = label;
        this.x = x;
        this.y = y;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getLabel() {
        return label;
    }

    public ChessmanMemento save() {
        return new ChessmanMemento(this);
    }

    public void restore(ChessmanMemento memento) {
        this.x = memento.getX();
        this.y = memento.getY();
        this.label = memento.getLabel();
    }
}

class ChessmanMemento {
    private String label;
    private int x;
    private int y;

    public ChessmanMemento(Chessman chessman) {
        label = chessman.getLabel();
        x = chessman.getX();
        y = chessman.getY();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getLabel() {
        return label;
    }
}

class MementoCaretakerOne {
    private ChessmanMemento memento;

    public ChessmanMemento getMemento() {
        return memento;
    }

    public void setMemento(ChessmanMemento memento) {
        this.memento = memento;
    }
}

class MementoCaretaker {
    private List<ChessmanMemento> mementos = new ArrayList<>();

    public ChessmanMemento getMemento(int i) {
        return mementos.get(i);
    }

    public void setMemento(ChessmanMemento memento) {
        mementos.add(memento);
    }
}