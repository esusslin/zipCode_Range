package com.company;


// Zip Code Range Class
// an instance constitutes a zipCode Range
// which cannot be used for shipping

// Central unit of input and output of zipCodeRangeCondenser

public class Range {

    private String begZipCode;
    private String endZipCode;

    public Range(String begZipCode, String endZipCode) {
        // single instance is created with beg and end of range
        this.begZipCode = begZipCode;
        this.endZipCode = endZipCode;
    }

    public String getBegZipCode() {
        return begZipCode;
    }

    public String getEndZipCode() {
        return endZipCode;
    }

    public void setBegZipCode(String begZipCode) {
        this.begZipCode =  begZipCode;
    }

    public void setEndZipCode(String bendZipCode) {
        this.endZipCode =  endZipCode;
    }
}
