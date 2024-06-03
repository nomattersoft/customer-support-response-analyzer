package org.objectable.model.model.record;

import org.objectable.configuration.Config;
import org.objectable.model.model.QuestionType;
import org.objectable.model.model.Record;
import org.objectable.model.model.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class QueryRecord extends Record {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Config.getProperty("DATE_FORMAT_LOGGER_PATTERN"));

    private LocalDate dateFrom;
    private LocalDate dateTo;

    /**
     * Constructors
     */
    public QueryRecord(Integer id, String symbol, Service service, QuestionType questionType, String responseType, LocalDate dateFrom, LocalDate dateTo) {
        super(id, symbol, service, questionType, responseType);
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    /**
     * Getters & Setters
     */
    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    /**
     * Common methods
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        QueryRecord that = (QueryRecord) o;
        return Objects.equals(dateFrom, that.dateFrom) && Objects.equals(dateTo, that.dateTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dateFrom, dateTo);
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s %s%s",
                symbol,
                service == null ? Config.getProperty("ID_WILDCARD_SYMBOL") : service.toString(),
                questionType == null ? Config.getProperty("ID_WILDCARD_SYMBOL") : questionType.toString(),
                responseType,
                formatter.format(dateFrom),
                (dateTo != null ? Config.getProperty("DATE_RANGE_SPLITTER_SYMBOL") +  formatter.format(dateTo) : "")
        );
    }
}
