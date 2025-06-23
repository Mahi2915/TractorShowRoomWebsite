package com.web.ashoktractors;

public class MonthlyTractorDTO {

    private String modelName;
    private int totalSold;

    public MonthlyTractorDTO() {
    }

    public MonthlyTractorDTO(String modelName, int totalSold) {
        this.modelName = modelName;
        this.totalSold = totalSold;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public int getTotalSold() {
        return totalSold;
    }

    public void setTotalSold(int totalSold) {
        this.totalSold = totalSold;
    }
}

