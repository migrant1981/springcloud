package com.example.startsstock.entity;

public class ImportSummary {

    private String companyName;
    private String companyCode;
    private String stock_exchange;
    private long importCount;
    private String fromDate;
    private String toDate;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getStock_exchange() {
        return stock_exchange;
    }

    public void setStock_exchange(String stock_exchange) {
        this.stock_exchange = stock_exchange;
    }

    public long getImportCount() {
        return importCount;
    }

    public void setImportCount(long importCount) {
        this.importCount = importCount;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
}
