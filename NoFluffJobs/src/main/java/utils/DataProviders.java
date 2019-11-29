package utils;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataProviders {

    @DataProvider(name = "searchData")
    public Object[] getSearchData(){
        return readDataFromFile("search_phrases.txt");
    }

    @DataProvider(name = "invalidEmails")
    public Object[] getInvalidEmails(){
        return readDataFromFile("invalidEmailAddresses.txt");
    }

    @DataProvider(name = "validEmails")
    public Object[] getValidEmails(){
        return readDataFromFile("validEmailAddresses.txt");
    }

    private Object[] readDataFromFile(String fileName){

        List<Object> arr = new ArrayList<Object>();
        BufferedReader br = null;
        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader("src\\main\\resources\\"+fileName));
            while ((sCurrentLine = br.readLine()) != null) {
                arr.add(sCurrentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null){
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return arr.toArray();
    }

    @DataProvider(name = "shareButtons")
    public Object[][] getShareButtons(){

        // Object[][]buttons contains:
        //button path, should new window be opened?, domain name or new WebElement name

        Object[][]buttons = new Object[][]{
                {By.xpath("//button[contains(text(),\"Facebook\")]"), true,"facebook.com"},
                {By.xpath("//button[contains(text(),\"Twitter\")]"), true, "twitter.com"},
                {By.xpath("//button[contains(text(),\"Linkedin\")]"), true, "linkedin.com"},
                {By.xpath("//button[contains(text(),\"E-mail\")]"), false, By.xpath("//nfj-posting-share-email-modal")}};
        return buttons;
    }
}
