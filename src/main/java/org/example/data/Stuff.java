package org.example.data;

public class Stuff {
    private String id;
    private String name;
    private String surname;
    private String patronymic;
    private String job;
    private String login;
    private String password;
    private double salary;

    public Stuff(String surname, String name, String patronymic, String password, String jobTitle){
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.password = password;
        this.login = LoginGenerator.generatelogin(this);
        this.job = jobTitle;
        this.salary = SalaryWorkers.generateSalary(job);

    }

    public Stuff(String name, String surname, String patronymic, String job_title, double salary, String id) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.job = job_title;
        this.salary = salary;
        this.id = id;
    }

    public Stuff(String name, String surname, String patronymic) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }


    public String getJob_title() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

}
