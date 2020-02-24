package com.company;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class RangeToolTest {

    private RangeTool rangeTool1;
    private List<Range> ranges1;
    private Range zipRange1;
    private Range zipRange2;
    private Range zipRange3;
    private Range zipRange4;
    private Range zipRange5;

    private RangeTool rangeTool2;
    private List<Range> ranges2;
    private Range zipRange6;
    private Range zipRange7;
    private Range zipRange8;
    private Range zipRange9;
    private Range zipRange10;
    private Range zipRange11;

    @BeforeEach
    void setUp() {
        zipRange1 = new Range("00733", "00900");
        zipRange2 = new Range("00777", "00782");
        zipRange3 = new Range("00888", "00889");
        zipRange4 = new Range("01224", "01300");
        zipRange5 = new Range("01278", "01289");
        ranges1 = new ArrayList<Range>();
        ranges1.add(zipRange1);
        ranges1.add(zipRange2);
        ranges1.add(zipRange3);
        ranges1.add(zipRange5);
        ranges1.add(zipRange4);

        rangeTool1 = new RangeTool(ranges1);

        zipRange6 = new Range("10001", "11211");
        zipRange7 = new Range("00001", "00898");
        zipRange8 = new Range("00899", "00900");
        zipRange9 = new Range("99000", "99150");
        zipRange10 = new Range("10087", "10232");
        zipRange11 = new Range("11122", "11199");

        ranges2 = new ArrayList<Range>();
        ranges2.add(zipRange6);
        ranges2.add(zipRange7);
        ranges2.add(zipRange8);
        ranges2.add(zipRange9);
        ranges2.add(zipRange10);
        ranges2.add(zipRange11);

        rangeTool2 = new RangeTool(ranges2);
    }

    // validates that the smallest zipcode is the start of the first range
    //validates that the largest zipcode is the end of the final range
    @Test
    void sortZipCodeInput() {
        Assert.assertTrue(rangeTool1.sortZipCodes().get(0).getBegZipCode() == "00733");
        Assert.assertTrue(rangeTool1.sortZipCodes().get(4).getEndZipCode() == "01289");

        Assert.assertTrue(rangeTool2.sortZipCodes().get(0).getBegZipCode() == "00001");
        Assert.assertTrue(rangeTool2.sortZipCodes().get(5).getEndZipCode() == "99150");
    }

    // tests the condensed List size & tests the start of first range and end of the final range
    @Test
    void mergeZipCodeInput() {
        Assert.assertTrue(rangeTool1.mergeZipCodes().size() == 2);
        Assert.assertTrue(rangeTool1.mergeZipCodes().get(0).getBegZipCode() == "00733");
        Assert.assertTrue(rangeTool1.mergeZipCodes().get(1).getEndZipCode() == "01300");

        System.out.print(rangeTool2.mergeZipCodes().size());
        Assert.assertTrue(rangeTool2.mergeZipCodes().size() == 4);
        Assert.assertTrue(rangeTool2.mergeZipCodes().get(0).getBegZipCode() == "00001");
        Assert.assertTrue(rangeTool2.mergeZipCodes().get(3).getEndZipCode() == "99150");
    }

    // tests at zipcodes are accurately read to be within one of the ranges (not possible for shipping)
    // vs outside of the ranges (possible for shipping)
    @Test
    void isZipCodePossible() {
        Assert.assertFalse(rangeTool1.isZipCodePossible("01275"));
        Assert.assertTrue(rangeTool1.isZipCodePossible("00725"));
        Assert.assertTrue(rangeTool1.isZipCodePossible("01200"));
        Assert.assertFalse(rangeTool1.isZipCodePossible("00733"));

        Assert.assertFalse(rangeTool2.isZipCodePossible("00008"));
        Assert.assertFalse(rangeTool2.isZipCodePossible("00899"));
        Assert.assertTrue(rangeTool2.isZipCodePossible("11212"));
        Assert.assertFalse(rangeTool2.isZipCodePossible("11211"));
    }
}