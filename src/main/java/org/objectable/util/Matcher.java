package org.objectable.util;

import java.time.LocalDate;

public class Matcher {


    public boolean dateMatchesInclusively(LocalDate dateToCompare, LocalDate dateFrom, LocalDate dateTo) {
        return dateTo == null ? dateToCompare.isEqual(dateFrom) : !dateToCompare.isAfter(dateTo) && !dateToCompare.isBefore(dateFrom);
    }

    public boolean dateMatchesExclusively(LocalDate dateToCompare, LocalDate dateFrom, LocalDate dateTo) {
        return dateTo == null ? dateToCompare.isEqual(dateFrom) : !dateToCompare.isAfter(dateFrom) && !dateToCompare.isBefore(dateTo);
    }
}
