package com.web.ashoktractors;

public class MonthlyPartSalesDTO {
    private String partName;
    private int totalSold;

    
    public MonthlyPartSalesDTO(String partName, int totalSold) {
        this.partName = partName;
        this.totalSold = totalSold;
    }

    // getters
    public String getPartName() {
        return partName;
    }

    public int getTotalSold() {
        return totalSold;
    }
}
