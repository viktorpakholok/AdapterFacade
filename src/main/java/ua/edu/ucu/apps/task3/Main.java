package ua.edu.ucu.apps.task3;

public class Main {
    public static void main(String[] args) {
        
        CompanyInfoExtraction companyInfoExtraction = 
        new CompanyInfoExtraction();

        Company res = companyInfoExtraction.extractInfo("https://ucu.edu.ua/");
        System.out.println(res);
    }
}
