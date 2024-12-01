package ua.edu.ucu.apps.task2;

public class Main {
    public static void main(String[] args) {
        DB db = new DB();
        Авторизація авторизація = new Авторизація();
        if (авторизація.авторизуватися(db)) {
            ReportBuilder br = new ReportBuilder(db);
        }
    }
}
