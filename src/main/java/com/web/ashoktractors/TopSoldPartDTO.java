package com.web.ashoktractors;

public class TopSoldPartDTO {
    private int partId;
    private String partName;
    private int totalQuantitySold;

    // Constructors
    public TopSoldPartDTO() {}

    public TopSoldPartDTO(int partId, String partName, int totalQuantitySold) {
        this.partId = partId;
        this.partName = partName;
        this.totalQuantitySold = totalQuantitySold;
    }

    // Getters and Setters
    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public int getTotalQuantitySold() {
        return totalQuantitySold;
    }

    public void setTotalQuantitySold(int totalQuantitySold) {
        this.totalQuantitySold = totalQuantitySold;
    }
}

