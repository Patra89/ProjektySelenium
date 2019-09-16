# ProjektySelenium
Projekty zrobione w ramach nauki Selenium Webdriver
<br><br><br>
<b>PROJEKT ALLEGRO</b><br>
Testy Allegro wykonane przy użyciu Selenium Webdriver, TestNG, ExtentReports, Maven.<br>
Raporty będą przechowywane w Allegro/src/reports<br>
Screenshoty dla raportów będą przechowywane w Allegro/src/reports/screenshots<br>
Projekt zawiera sterowniki dla przeglądarek Chrome i Firefox(Allegro/src/drivers)
<br>
<br>
ProjektySelenium<br>
--<b>Allegro</b><br>
-----<b>src</b><br>
--------<b>drivers</b><br>
-----------chromedriver.exe<br>
-----------geckodriver.exe<br>
--------<b>main</b><br>
-----------<b>java</b><br>
--------------<b>data</b><br>
-----------------DataProviders.java<br>
--------------<b>listeners</b><br>
-----------------CustomListener.java<br>
--------------<b>pages</b><br>
-----------------MainPage.java<br>
-----------------PageBase.java<br>
-----------------ProductDetailsPage.java<br>
--------------<b>tests</b><br>
-----------------<b>mainPage</b><br>
--------------------LinksTest.java<br>
--------------------MojeAllegroTest.java<br>
--------------------ScrollTest.java<br>
--------------------SearchBarTest.java<br>
--------------------SideMenuTest.java<br>
-----------------<b>productPage</b><br>
--------------------BottomMenuTest.java<br>
--------------------CartTest.java<br>
--------------------LinksTest.java<br>
--------------------ProductAmountTest.java<br>
--------------------TestBase.java<br>
--------------<b>utils</b><br>
-----------------BrokenLinksFinder.java<br>
-----------------ExcelFileReader.java<br>
-----------------ExtentFactory.java<br>
-----------------ScreenshotUtil.java<br>
-----------------Wait.java<br>
-----------<b>resources</b><br>
--------------search_categories.xlsx<br>
--------------search_phrases.txt<br>
-----target<br>
-----test-output<br>
-----Allegro.iml<br>
-----pom.xml<br>
-----testng.xml<br>
