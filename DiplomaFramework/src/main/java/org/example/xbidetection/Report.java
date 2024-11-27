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

public class Report {
    private String name;
    private JSONObject report;
    private final String pathReport = "src/test/java/testdata/reports/report.json";
    private List<Incompatibility> incompatibilities;
    private Integer number;

    public Report(String name) throws JSONException, IOException {
        this.name = name;
        this.number = getNumberOfReport();
        incompatibilities = new ArrayList<>();
        report = new JSONObject();
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

    public void createReport() throws JSONException, IOException {
        report.put(number+"-Звіт", name);
        LocalDateTime currentDateTime = LocalDateTime.now();
        report.put("Дата і час", currentDateTime);
        report.put("Несумісності", incompatibilities.size());
        for (Incompatibility i : incompatibilities) {
            JSONObject incoms = new JSONObject();
            JSONObject browser = new JSONObject();
            browser.put("назва", i.getBrowserName());
            browser.put("версія", i.getBrowserVersion());
            incoms.put("Браузер", browser);
            incoms.put("Сторінка", i.getPageName());
            JSONObject screensh = new JSONObject();
            screensh.put("скріншот сторінки", i.getFilePath());
            screensh.put("скріншот з вказівками", i.getFilePathScreenshot2());
            incoms.put("Скріншоти", screensh);
            incoms.put("Тип", i.getIssueType());
            if(!i.getIssueType().equals("Not existing element")){
                incoms.put("Деталі", i.getDetails());
                incoms.put("Веб елемент", i.getWebElementTagName());
            }else{
                incoms.put("Візуальна різниця", i.getVisualDiffType());
            }
            report.put("incomes", incoms);
        }
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
