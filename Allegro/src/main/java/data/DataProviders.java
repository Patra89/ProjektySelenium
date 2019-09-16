package data;

import utils.ExcelFileReader;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DataProviders {

    @DataProvider(name = "searchData")
    public Object[] readDataFromFile(){

        List<Object> arr = new ArrayList<Object>();
        BufferedReader br = null;
        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader("src\\main\\resources\\search_phrases.txt"));
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


    @DataProvider(name = "getSideMenuItems")
    public Object[][] getSideMenuItems(){
        Object[][] objects = new Object[][]{
                {"Elektronika", "div._1h7wt:nth-child(1) > div:nth-child(2)"},
                {"Moda", "div._1h7wt:nth-child(2) > div:nth-child(2)"},
                {"Dom", "div._1h7wt:nth-child(3) > div:nth-child(2)"},
                {"Supermarket", "div._1h7wt:nth-child(4) > div:nth-child(2)"},
                {"Dziecko","div._1h7wt:nth-child(5) > div:nth-child(2)"},
                {"Uroda","div._1h7wt:nth-child(6) > div:nth-child(2)"},
                {"Zdrowie","div._1h7wt:nth-child(7) > div:nth-child(2)"},
                {"Kultura","div._1h7wt:nth-child(8) > div:nth-child(2)"},
                {"Sport","div._1h7wt:nth-child(9) > div:nth-child(2)"},
                {"Motoryzacja","div._1h7wt:nth-child(10) > div:nth-child(2)"},
                {"Ogłoszenia","div._1h7wt:nth-child(11) > div:nth-child(2)"},
                {"Kolekcje","div._1h7wt:nth-child(12) > div:nth-child(2)"},
                {"Firma","div._1h7wt:nth-child(13) > div:nth-child(2)"},
                {"eBilet.pl","div._1h7wt:nth-child(14) > div:nth-child(2)"}};
        return objects;
    }


    @DataProvider(name = "searchCategories")
    public Object[][] getSearchCategories(){
        Object[][] testData = ExcelFileReader.getTestData("src\\main\\resources\\search_categories.xlsx");
        return testData;
    }

}
