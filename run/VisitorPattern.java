package run;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 行为型模式
 * 访问者模式
 *
 * @author bianbian
 * @date 2019/1/31
 */
public class VisitorPattern {
    public static void main(String[] args) {
        test1();
        System.out.println("");
        test2();
    }

    private static void test2() {
        EmployeeList list = getEmployeeList();
        Department department = new HRDepartment();
        list.accept(department);

    }

    private static void test1() {
        EmployeeList list = getEmployeeList();

        Department department = new FADepartment();
        list.accept(department);
    }

    private static EmployeeList getEmployeeList() {
        EmployeeList list = new EmployeeList();
        Employee fte1, fte2, fte3, pte1, pte2, the1, the2;

        fte1 = new FulltimeEmployee("张无忌", 3200.00, 45);
        fte2 = new FulltimeEmployee("杨过", 2000.00, 40);
        fte3 = new FulltimeEmployee("段誉", 2400.00, 38);
        pte1 = new ParttimeEmployee("洪七公", 80.00, 20);
        pte2 = new ParttimeEmployee("郭靖", 60.00, 18);
        the1 = new ThirdEmployee("马云");
        the2 = new ThirdEmployee("马化腾");

        list.addEmployee(fte1);
        list.addEmployee(fte2);
        list.addEmployee(fte3);
        list.addEmployee(pte1);
        list.addEmployee(pte2);
        list.addEmployee(the1);
        list.addEmployee(the2);
        return list;
    }
}

/*
 * Sunny软件公司欲为某银行开发一套OA系统，在该OA系统中包含一个员工信息管理子系统，该银行员工包括正式员工和临时工，
 * 每周人力资源部和财务部等部门需要对员工数据进行汇总，汇总数据包括员工工作时间、员工工资等。该公司基本制度如下：
 * (1) 正式员工(Full time  Employee)每周工作时间为40小时，不同级别、不同部门的员工每周基本工资不同；如果超过40小时，
 * 超出部分按照100元/小时作为加班费；如果少于40小时，所缺时间按照请假处理，请假所扣工资以80元/小时计算，直到基本工资扣除到零为止。
 * 除了记录实际工作时间外，人力资源部需记录加班时长或请假时长，作为员工平时表现的一项依据。
 * (2) 临时工(Part time  Employee)每周工作时间不固定，基本工资按小时计算，不同岗位的临时工小时工资不同。人力资源部只需记录实际工作时间。
 * 人力资源部和财务部工作人员可以根据各自的需要对员工数据进行汇总处理，人力资源部负责汇总每周员工工作时间，而财务部负责计算每周员工工资。
 */

/**
 * 员工类：抽象元素类
 */
interface Employee {
    void accept(Department handler);
}

/**
 * 全职员工类：具体元素类
 */
@Data
class FulltimeEmployee implements Employee {

    private String name;
    private double weeklyWage;
    private int workTime;

    public FulltimeEmployee(String name, double weeklyWage, int workTime) {
        this.name = name;
        this.weeklyWage = weeklyWage;
        this.workTime = workTime;
    }

    @Override
    public void accept(Department handler) {
        handler.visit(this);
    }
}

/**
 * 兼职员工类：具体元素类
 */
@Data
class ParttimeEmployee implements Employee {

    private String name;
    private double hourWage;
    private int workTime;

    public ParttimeEmployee(String name, double hourWage, int workTime) {
        this.name = name;
        this.hourWage = hourWage;
        this.workTime = workTime;
    }

    @Override
    public void accept(Department handler) {
        handler.visit(this);
    }
}

@Data
class ThirdEmployee implements Employee {
    private String name;

    public ThirdEmployee(String name) {
        this.name = name;
    }

    @Override
    public void accept(Department handler) {
        handler.visit(this);
    }
}

/**
 * 部门类：抽象访问者类
 */
abstract class Department {
    public abstract void visit(FulltimeEmployee employee);

    public abstract void visit(ParttimeEmployee employee);

    public void visit(ThirdEmployee employee) {
        System.out.println("Department visit, name " + employee.getName());
    }
}

/**
 * 财务部类：具体访问者类
 */
class FADepartment extends Department {

    /**
     * 实现财务部对全职员工的访问
     *
     * @param employee
     */
    @Override
    public void visit(FulltimeEmployee employee) {
        int workTime = employee.getWorkTime();
        double weekWage = employee.getWeeklyWage();
        if (workTime > 40) {
            weekWage = weekWage + (workTime - 40) * 100;
        } else if (workTime < 40) {
            weekWage = weekWage - (40 - workTime) * 80;
            if (weekWage < 0) {
                weekWage = 0;
            }
        }
        System.out.println("正式员工" + employee.getName() + "实际工资为：" + weekWage + "元。");
    }

    /**
     * 实现财务部对兼职员工的访问
     *
     * @param employee
     */
    @Override
    public void visit(ParttimeEmployee employee) {
        int workTime = employee.getWorkTime();
        double hourWage = employee.getHourWage();
        System.out.println("临时工" + employee.getName() + "实际工资为：" + workTime * hourWage + "元。");

    }
}

/**
 * 人力资源部类：具体访问者类
 */
class HRDepartment extends Department {

    /**
     * 实现人力资源部对全职员工的访问
     *
     * @param employee
     */
    @Override
    public void visit(FulltimeEmployee employee) {

        int workTime = employee.getWorkTime();
        System.out.println("正式员工" + employee.getName() + "实际工作时间为：" + workTime + "小时。");
        if (workTime > 40) {
            System.out.println("正式员工" + employee.getName() + "加班时间为：" + (workTime - 40) + "小时。");
        } else if (workTime < 40) {
            System.out.println("正式员工" + employee.getName() + "请假时间为：" + (40 - workTime) + "小时。");
        }
    }

    /**
     * 实现人力资源部对兼职员工的访问
     *
     * @param employee
     */
    @Override
    public void visit(ParttimeEmployee employee) {
        int workTime = employee.getWorkTime();
        System.out.println("临时工" + employee.getName() + "实际工作时间为：" + workTime + "小时。");
    }
}

/**
 * 员工列表类：对象结构
 */
class EmployeeList {
    private List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void accept(Department handler) {
        for (Employee employee : employees) {
            employee.accept(handler);
        }
    }
}