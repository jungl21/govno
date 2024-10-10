package org.example.data;

import java.util.Scanner;

public class Stuff {
    private String id;
    private String name;
    private String surname;
    private String patronymic;
    private String job_title;
    private String login;
    private String password;
    private double salary;

    public Stuff(String surname, String name, String patroymic, String password){
        //this.id =
        this.name = name;
        this.surname = surname;
        this.patronymic = patroymic;
        this.job_title = checkJob();
        this.password = password;
        this.login = LoginGenerator.generatelogin(this);
        this.salary = 0; //TODO() - генерация зарплаты
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

    public void setPatroymic(String patroymic) {
        this.patronymic = patroymic;
    }

    public static String checkJob() {
        Scanner sc = new Scanner(System.in);
        var job_title = sc.nextLine();

        switch (job_title.toUpperCase()) {
            case "ADMINISTRATOR", "MANAGER", "ENGINEER" -> {
                return job_title;
            }
            default -> {
                System.out.println("Incorrect job. Please, try again.");
                checkJob();
            }
        }
        return job_title;

    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob(String job) {
        this.job_title = job;
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
