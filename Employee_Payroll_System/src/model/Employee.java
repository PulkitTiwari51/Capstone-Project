package payroll.model;

public class Employee {
    private int id;
    private String name;
    private String position;
    private String joinDate;
    private double basicSalary;

    // Constructors
    public Employee() {
    }

    public Employee(String name, String position, String joinDate, double basicSalary) {
        this.name = name;
        this.position = position;
        this.joinDate = joinDate;
        this.basicSalary = basicSalary;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }
}