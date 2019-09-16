package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentFactory{

    private static ExtentReports report;
    private static ExtentHtmlReporter htmlReporter;

    public static void startReport(String name){

        htmlReporter = new ExtentHtmlReporter("src\\reports\\"+name+".html");
        report = new ExtentReports();
        report.attachReporter(htmlReporter);
        report.setSystemInfo("Platform", "Windows 10");

        htmlReporter.config().setDocumentTitle("test report");
        htmlReporter.config().setReportName(name);
        htmlReporter.config().setTheme(Theme.STANDARD);
    }

    public static void finishReport() {
        report.flush();
    }

    public static synchronized ExtentTest startTest(String testName) {
        return report.createTest(testName);
    }

}