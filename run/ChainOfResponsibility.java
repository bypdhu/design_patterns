package run;

/**
 * 行为型模式
 * 职责链模式
 *
 * @author bianbian
 * @date 2019/1/29
 */
public class ChainOfResponsibility {
    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        Approver wjzhang, gyang, jguo, meeting;
        wjzhang = new Director2("张无忌");
        gyang = new VicePresident("杨过");
        jguo = new President("郭靖");
        meeting = new Congress("董事会");

        //创建职责链
        wjzhang.setSuccessor(gyang);
        gyang.setSuccessor(jguo);
        jguo.setSuccessor(meeting);

        //创建采购单
        PurchaseRequest pr1 = new PurchaseRequest("11", 45000D, "购买倚天剑");
        wjzhang.handleRequest(pr1);

        PurchaseRequest pr2 = new PurchaseRequest("22", 60000D, "购买《葵花宝典》");
        wjzhang.handleRequest(pr2);

        PurchaseRequest pr3 = new PurchaseRequest("33", 160000D, "购买《金刚经》");
        wjzhang.handleRequest(pr3);

        PurchaseRequest pr4 = new PurchaseRequest("44", 800000D, "购买桃花岛");
        wjzhang.handleRequest(pr4);

    }
}

/**
 * 举例
 * Sunny软件公司承接了某企业SCM(Supply Chain Management，供应链管理)系统的开发任务，其中包含一个采购审批子系统。
 * 该企业的采购审批是分级进行的，即根据采购金额的不同由不同层次的主管人员来审批，
 * 主任可以审批5万元以下（不包括5万元）的采购单，
 * 副董事长可以审批5万元至10万元（不包括10万元）的采购单，
 * 董事长可以审批10万元至50万元（不包括50万元）的采购单，
 * 50万元及以上的采购单就需要开董事会讨论决定。
 */
class PurchaseRequest {

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getName() {
        return this.name;
    }

    public Double getAmount() {
        return this.amount;
    }

    public String getRemark() {
        return this.remark;
    }

    public PurchaseRequest(String name, Double amount, String remark) {
        this.name = name;
        this.amount = amount;
        this.remark = remark;
    }

    private String name;
    private Double amount;
    private String remark;

}

abstract class Approver {
    protected String name;
    protected Approver successor;

    public Approver(String name) {
        this.name = name;
    }

    public void setSuccessor(Approver successor) {
        this.successor = successor;
    }

    public abstract void handleRequest(PurchaseRequest request);
}

class Director2 extends Approver {

    public Director2(String name) {
        super(name);
    }

    @Override
    public void handleRequest(PurchaseRequest request) {
        if (request.getAmount() < 50000) {
            System.out.println("主任" + this.name + "审批采购单：" + request.getName() + "，金额：" + request.getAmount() + "元，采购目的：" + request.getRemark() + "。");
        } else {
            this.successor.handleRequest(request);
        }
    }
}

class VicePresident extends Approver {

    public VicePresident(String name) {
        super(name);
    }

    @Override
    public void handleRequest(PurchaseRequest request) {
        if (request.getAmount() < 100000) {
            System.out.println("副董事长" + this.name + "审批采购单：" + request.getName() + "，金额：" + request.getAmount() + "元，采购目的：" + request.getRemark() + "。");
        } else {
            this.successor.handleRequest(request);
        }
    }
}

class President extends Approver {

    public President(String name) {
        super(name);
    }

    @Override
    public void handleRequest(PurchaseRequest request) {
        if (request.getAmount() < 500000) {
            System.out.println("董事长" + this.name + "审批采购单：" + request.getName() + "，金额：" + request.getAmount() + "元，采购目的：" + request.getRemark() + "。");
        } else {
            this.successor.handleRequest(request);
        }
    }
}

class Congress extends Approver {

    public Congress(String name) {
        super(name);
    }

    @Override
    public void handleRequest(PurchaseRequest request) {
        System.out.println("董事会" + this.name + "审批采购单：" + request.getName() + "，金额：" + request.getAmount() + "元，采购目的：" + request.getRemark() + "。");

    }
}