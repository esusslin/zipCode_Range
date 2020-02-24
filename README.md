# zipCode_Range
a simple alorithm to condense a list of assorted zip code ranges into a subset list which comprises the total 'covered' ranges of the original list

## Concerns and Design Decisions

### ZipCodes as Strings
The first concern for this problem is the format of a single zip code.  In the US all zip codes are 5 digits, but certainly don't behave like standard integers.  For example the leading zeros in 00123 always need to be taken into account.  So I made the decision to implement a solution for which zipcodes are strings.  This provides the scalability to handle international zip codes of different lengths, etc. 

### Comparing ZipCodes as Strings
Comparing zipcodes is obviously central to this problem so evaluating zip codes as strings against each other is essential.  To do this I've utilized the [string.compareTo()](https://www.geeksforgeeks.org/java-lang-string-compareto/) method, which evalutes one string to the another lexographically.

### ZipCode Range Format
The prompt describes a list of zip code ranges as an array of arrays, with each range being represented by an array with 2 indices.  I made the decision to represent a single zip code range as an instance of a Range object, which behaves basically the same way, except the beginning of the range and end of the range are represented as instance variables with getters and setters rather than indices.  If for nothing else, I did this to make my solution more readable.


## Breakdown
The solution to this problem consists of 2 major parts.  First an array of zip code ranges need to be sorted by the beginning of each range - this will make merging into a single condensed list (the 2nd part) which covers the sum of ranges a simple left-to-right iteration.
The sorting itself will involve linear space time complexity 0(n) as will the merging / condensing itself.  As such the complete solution has a complexity of O(n lg n).

I included a simple function to look up whether any given zipcode falls within one of the ranges or not.  In practical use a user could find out if a specific zipcode is "off limits" given a set of zip codes which cannot be used.

## Unit testing

I used JUnit to test each of the functions in this solution.
