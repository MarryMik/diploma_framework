package org.example.xbidetection;

import java.util.List;

public class Report {
    private String data;
    private final String pathReport ="";
    private List<Incompatibility> incompatibilities;

    public Report() {}
    public void saveReport(){}

    public void showReport(){}

    private void createReport(){}

    public void addIncompatibility(Incompatibility i){
        incompatibilities.add(i);
    }

    public String getData(){
        return data;
    }

    public List<Incompatibility> getIncompatibilities(){
        return incompatibilities;
    }

    public String getPathReport(){
        return pathReport;
    }
}
