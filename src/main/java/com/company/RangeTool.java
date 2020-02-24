package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RangeTool {

    // private variable for the input:
    // a collection of Range objects
    private List<Range> zipCodeInput;

    RangeTool(List<Range> zipCodeInput) {
        this.zipCodeInput = zipCodeInput;
    }

    // function to sort input of Range objects
    // sorted by the start of the range (begZipCode)
    // returns new Collection of the same ranges, but sorted
    public List<Range> sortZipCodes() {
        List<Range> copyOfInput = new ArrayList<>();

        for (Range Range : zipCodeInput) {
            Range RangeCopy = new Range(Range.getBegZipCode(), Range.getEndZipCode());
            copyOfInput.add(RangeCopy);
        }

        Collections.sort(copyOfInput, new Comparator<Range>() {
            @Override
            public int compare(Range o1, Range o2) {
                return (o1.getBegZipCode().compareTo(o2.getBegZipCode()));
            }
        });
        return copyOfInput;
    }

    public List<Range> mergeZipCodes() {
        List<Range> sortedZips = this.sortZipCodes();

        // new collection for condensed ZipCode list
        List<Range> mergedRanges = new ArrayList<>();

        // first ZipCode from sorted zipcodes is added to new List
        mergedRanges.add(sortedZips.get(0));

        // each 'current' ZipCode range in sorted List is compared
        // to the last merged zipcode
        // if the endZipCode of the current overlaps with the last merged zip's begZipCode..
        // .. we use the latter endZipCode
        for (Range currentRange : sortedZips) {
            Range lastMergedZipCode = mergedRanges.get( mergedRanges.size() - 1);
            String lastEndZipCode = lastMergedZipCode.getEndZipCode();

            if (currentRange.getBegZipCode().compareTo(lastEndZipCode) < 0.5) {
                lastMergedZipCode.setEndZipCode(returnLargestZip(lastEndZipCode, currentRange.getEndZipCode()));
            } else {
                mergedRanges.add(currentRange);
            }
        }
        return  mergedRanges;
    }

    // helper function which takes in 2 zip codes and returns the largest zip
    private String returnLargestZip(String zipA, String zipB) {
        if (zipA.compareTo(zipB) < 0) {
            return zipB;
        } else {
            return zipA;
        }
    }

    // boolean function which takes in a zipCode and returns TRUE if the zipcode DOES NOT fall within one of the ranges of zips
    public boolean isZipCodePossible(String zip) {
        List<Range> mergedZips = mergeZipCodes();
        for (Range current : mergedZips) {
            String currentBeg = current.getBegZipCode();
            String currentEnd = current.getEndZipCode();

            if ((zip.compareTo(currentBeg) > -0.5) && (zip.compareTo(currentEnd) < 0.5)) {
                return false;
            }
        }
        return true;
    }

}
