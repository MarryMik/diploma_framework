package org.example.xbidetection;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Report {
    private String name;
    private JSONObject report;
    private Integer number;
    private final String pathReport = "src/test/java/testdata/reports/report.json";
    private List<Incompatibility> incompatibilities;
    private List<List<Incompatibility>> incompatibilitiesByBrowser;
    private List <String> browsers;
    private List<List<Incompatibility>> icompatibilitiesByPages;
    private List <String> pages;

    private List<String>  getBrowsersNameList (){
               Set<String> uniqueBrowser = incompatibilities.stream()
                .map(xbi -> xbi.getBrowserName())
                .collect(Collectors.toSet());
        List<String> uniqueBrowserList = new ArrayList<>(uniqueBrowser);
        if(uniqueBrowserList.size() > 0){
            for(String browserName : uniqueBrowserList){
                Map<Boolean, List<Incompatibility>> partitioned = incompatibilities.stream()
                        .collect(Collectors.partitioningBy(xbi -> xbi.getBrowserName().equals(browserName)));
                incompatibilitiesByBrowser.add(partitioned.get(true));
            }
        }
        return uniqueBrowserList;
    }

    private List<String> getPagesNameList (){
        Set<String> uniquePages = incompatibilities.stream()
                .map(xbi -> xbi.getPageName())
                .collect(Collectors.toSet());
        List<String> uniquePageList = new ArrayList<>(uniquePages);
        if(uniquePageList.size() > 0){
            for(String pageName: uniquePageList){
                Map<Boolean, List<Incompatibility>> partitioned = incompatibilities.stream()
                        .collect(Collectors.partitioningBy(xbi -> xbi.getPageName().equals(pageName)));
                icompatibilitiesByPages.add(partitioned.get(true));
            }
        }
        return uniquePageList;
    }

    public Report(String name) throws JSONException, IOException {
        this.name = name;
        this.number = getNumberOfReport();
        incompatibilities = new ArrayList<>();
        report = new JSONObject();
        incompatibilitiesByBrowser = new ArrayList<>();
        icompatibilitiesByPages = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Integer getNumberOfReport() throws JSONException, IOException {
        String content = new String(Files.readAllBytes(Paths.get(pathReport)));
        JSONArray reportsArray = new JSONArray(content);
        return reportsArray.length();
    }

    public void saveReport() {
        if (report.length() > 0) {
            try {
                String content = new String(Files.readAllBytes(Paths.get(pathReport)));
                JSONArray jsonArray;
                if (content.isEmpty()) {
                    jsonArray = new JSONArray();
                } else {
                    jsonArray = new JSONArray(content);
                }
                jsonArray.put(report);
                try (FileWriter fileWriter = new FileWriter(pathReport)) {
                    fileWriter.write(jsonArray.toString(4));
                    System.out.println("Новий об'єкт успішно додано до JSON-файлу.");
                }
            } catch (IOException e) {
                System.out.println("Сталася помилка під час роботи з файлом: " + e.getMessage());
            } catch (org.json.JSONException e) {
                System.out.println("Сталася помилка з форматом JSON: " + e.getMessage());
            }
        }
    }

    public void showReport() throws JSONException {
        if (report.length() > 0) {
            System.out.println("\nЗвіт:");
            System.out.println(report.toString(4));
        }else{
            System.out.println("Звіт ще не створено.");
        }
    }

    private void putIncompatibilities(List <Incompatibility> incompatibilities, JSONObject pages) throws JSONException {
        JSONObject xbis = new JSONObject();
        for (Incompatibility i : incompatibilities) {
            JSONObject incoms = new JSONObject();
            incoms.put("Скріншот з відмітками", i.getFilePathScreenshot2());
            incoms.put("Тип", i.getIssueType());
            incoms.put("Деталі", i.getDetails());
            incoms.put("Веб елемент", i.getWebElementTagName());
            incoms.put("Візуальна різниця", i.getVisualDiffType());
            xbis.put( i+"" ,incoms);
        }
        pages.put("Несумісності", xbis);
    }

    private List<List<Incompatibility>> dividePagesXBIbyBrowsers(String browserName) {
        List<List<Incompatibility>> xbiByPageByBrowser = new ArrayList<>();
        for(List<Incompatibility> incByPage: icompatibilitiesByPages){
            Map<Boolean, List<Incompatibility>> partitioned = incByPage.stream()
                    .collect(Collectors.partitioningBy(xbi -> xbi.getBrowserName().equals(browserName)));
            xbiByPageByBrowser.add(partitioned.get(true));
        }
        return xbiByPageByBrowser;
    }

    public void createReport() throws JSONException, IOException {
        this.browsers = new ArrayList<>(getBrowsersNameList());
        this.pages = new ArrayList<>(getPagesNameList());
        report.put(number+"-Звіт", name);
        LocalDateTime currentDateTime = LocalDateTime.now();
        report.put("Дата і час", currentDateTime);
        // by browser
        for(List<Incompatibility> incBybrowser: incompatibilitiesByBrowser){
            JSONObject browsers = new JSONObject();
            browsers.put("Назва", incBybrowser.get(0).getBrowserName());
            browsers.put("Версія", incBybrowser.get(0).getBrowserVersion());

            JSONObject baselineBrowser = new JSONObject();
            baselineBrowser.put("Назва", incBybrowser.get(0).getBaselineBrowserName());
            baselineBrowser.put("Версія", incBybrowser.get(0).getBaseLineBrowserVersion() );
            browsers.put("Базовий браузер", baselineBrowser);
            //by page
            List<List<Incompatibility>> xbiByBrowserAndByPage = dividePagesXBIbyBrowsers(incBybrowser.get(0).getBrowserName());
            for(List<Incompatibility> incByPage: xbiByBrowserAndByPage){
                JSONObject pages = new JSONObject();
                JSONObject screensh = new JSONObject();
                screensh.put("Розмір", incByPage.get(0).getDetectedDifference().getVisDiff().getTestScreenshot().getSize());
                screensh.put("Скріншот сторінки", incByPage.get(0).getFilePath());
                pages.put("Скріншот", screensh);
                putIncompatibilities(incByPage, pages);

                System.out.println(incByPage.get(0).getPageName());

                browsers.put("Сторінка - "+incByPage.get(0).getPageName(), pages);
            }
            report.put("Браузер - "+incBybrowser.get(0).getBrowserName(), browsers);
        }

        //узагальнення
        JSONObject generalization = new JSONObject();
        generalization.put("Кількість тестованих браузерів",this.browsers.size());
        generalization.put("Кількіть тестованих сторінок", this.pages.size());
        generalization.put("Загальна кількість несумісностей",incompatibilities.size());
        for(List<Incompatibility> incBybrowser: incompatibilitiesByBrowser) {
            JSONObject browsers = new JSONObject();
            browsers.put("Розмір вікна", incBybrowser.get(0).getDetectedDifference().getVisDiff().getTestScreenshot().getSize());
            List<List<Incompatibility>> xbiByBrowserAndByPage = dividePagesXBIbyBrowsers(incBybrowser.get(0).getBrowserName());

            for(List<Incompatibility> incByPage: xbiByBrowserAndByPage) {
                JSONObject pages = new JSONObject();
                pages.put("Кількість несумісностей", incByPage.size());

                //type
                JSONObject typeXBI = new JSONObject();
                Set<String> uniqueIssueTypeXBIs = incByPage.stream()
                        .map(xbi -> xbi.getIssueType())
                        .collect(Collectors.toSet());
                for(String uniqueIssue : uniqueIssueTypeXBIs){
                    long count = incByPage.stream()
                            .filter( xbi -> xbi.getIssueType().equals(uniqueIssue))
                            .count();
                    typeXBI.put("Кількість ХВІ з '"+uniqueIssue+"'",count);
                }
                pages.put("Тип ХВІ",typeXBI);

                // web elements
                JSONObject webElementsXBI = new JSONObject();
                Set<String> uniqueElements = incByPage.stream()
                        .map(xbi -> xbi.getWebElementTagName())
                        .collect(Collectors.toSet());
                for(String uniqueElement : uniqueElements){
                    long count = incByPage.stream()
                       .filter( xbi -> xbi.getWebElementTagName().equals(uniqueElement))
                        .count();
                    webElementsXBI.put("Кількість ХВІ з елементом '"+uniqueElement+"'",count);
                }
                pages.put("Веб елементи", webElementsXBI);

                //details
//                JSONObject detailsXBI = new JSONObject();
//                Set<String> uniqueDetails = incByPage.stream()
//                        .map(xbi -> xbi.getDetails())
//                        .collect(Collectors.toSet());
//                for(String uniqueDetail : uniqueDetails){
//                    long count = incByPage.stream()
//                            .filter( xbi -> xbi.getDetails().equals(uniqueDetail))
//                            .count();
//                    detailsXBI.put("Кількість ХВІ з '"+uniqueDetail+"'",count);
//                }
//                pages.put("Деталі", detailsXBI);

                // visdiff type
                JSONObject visualDiffType = new JSONObject();
                Set<String> uniqueVissDiffTypes = incByPage.stream()
                        .map(xbi -> xbi.getVisualDiffType())
                        .collect(Collectors.toSet());
                for(String uniqueVissType : uniqueVissDiffTypes){
                    long count = incByPage.stream()
                            .filter( xbi -> xbi.getVisualDiffType().equals(uniqueVissType))
                            .count();
                    visualDiffType.put("Кількість елементів з'"+uniqueVissType+"'",count);
                }
                pages.put("Візуальна різниця", visualDiffType);
                browsers.put("Сторінка - "+incByPage.get(0).getPageName(), pages);
            }
            generalization.put("Браузер - "+incBybrowser.get(0).getBrowserName(), browsers);
        }

        report.put("Узагальнення", generalization);
    }

    public void addIncompatibility(Incompatibility i) {
        incompatibilities.add(i);
    }

    public void addIncompatibilities(List<Incompatibility> list){
        incompatibilities.addAll(list);
    }


    public List<Incompatibility> getIncompatibilities() {
        return incompatibilities;
    }

    public String getPathReport() {
        return pathReport;
    }

    public JSONObject getReport() {
        return report;
    }

    public Integer getNumber() {
        return number;
    }
}
