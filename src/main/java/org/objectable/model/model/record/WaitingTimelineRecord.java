package org.objectable.model.model.record;

import org.objectable.configuration.Config;
import org.objectable.model.Model;
import org.objectable.model.model.QuestionType;
import org.objectable.model.model.Record;
import org.objectable.model.model.Service;
import org.objectable.util.Matcher;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class WaitingTimelineRecord extends Record {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Config.getProperty("DATE_FORMAT_LOGGER_PATTERN"));
    private static final Matcher matcher = new Matcher();

    private LocalDate date;
    private Integer waitingTime;

    /**
     * Constructors
     */
    public WaitingTimelineRecord(Integer id, String symbol, Service service, QuestionType questionType,
                                 String responseType, LocalDate date, Integer waitingTime) {
        super(id, symbol, service, questionType, responseType);
        this.date = date;
        this.waitingTime = waitingTime;
    }

    /**
     * Getters & Setters
     */
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(Integer waitingTime) {
        this.waitingTime = waitingTime;
    }

    /**
     * Check if this Waiting Timeline Record matches given Query Record
     */
    @Override
    public boolean matches(Model queryRecord) {
        if (queryRecord == null || queryRecord.getClass() != QueryRecord.class) return false;
        QueryRecord query = ((QueryRecord) queryRecord);
        return getService() != null && getService().matches(query.getService()) &&
                getQuestionType() != null && getQuestionType().matches(query.getQuestionType()) &&
                getResponseType().equals(query.getResponseType()) &&
                matcher.dateMatchesInclusively(getDate(), query.getDateFrom(), query.getDateTo());
    }

    /**
     * Common methods
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WaitingTimelineRecord that = (WaitingTimelineRecord) o;
        return Objects.equals(id, that.id) && Objects.equals(symbol, that.symbol) &&
                Objects.equals(service, that.service) && Objects.equals(questionType, that.questionType) &&
                Objects.equals(responseType, that.responseType) && Objects.equals(date, that.date) &&
                Objects.equals(waitingTime, that.waitingTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, symbol, service, questionType, responseType, date, waitingTime);
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s %s %s", symbol, service, questionType, responseType, formatter.format(date), waitingTime);
    }
}
