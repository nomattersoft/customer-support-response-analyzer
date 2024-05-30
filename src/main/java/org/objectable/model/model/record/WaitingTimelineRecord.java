package org.objectable.model.model.record;

import org.objectable.configuration.Config;
import org.objectable.model.model.QuestionType;
import org.objectable.model.model.Record;
import org.objectable.model.model.Service;

import java.util.Date;
import java.util.Objects;

public class WaitingTimelineRecord extends Record {

    private Date date;

    private Integer waitingTime;

    /**
     * Constructors
     */
    public WaitingTimelineRecord(Integer id, String symbol, Service service, QuestionType questionType, String responseType, Date date, Integer waitingTime) {
        super(id, symbol, service, questionType, responseType);
        this.date = date;
        this.waitingTime = waitingTime;
    }

    /**
     * Getters & Setters
     */
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
    public boolean matches(QueryRecord queryRecord) {
        return this.symbol.equals(queryRecord.getSymbol()) &&
                (queryRecord.getService() == null || this.service.equals(queryRecord.getService())) &&
                (queryRecord.getQuestionType() == null || this.questionType.equals(queryRecord.getQuestionType())) &&
                this.responseType.equals(queryRecord.getResponseType()) &&
                this.date.after(queryRecord.getDateFrom()) &&
                (queryRecord.getDateTo() == null || this.date.before(queryRecord.getDateTo()));
    }

    /**
     * Common methods
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WaitingTimelineRecord that = (WaitingTimelineRecord) o;
        return Objects.equals(id, that.id) && Objects.equals(symbol, that.symbol) && Objects.equals(service, that.service) && Objects.equals(questionType, that.questionType) && Objects.equals(responseType, that.responseType) && Objects.equals(date, that.date) && Objects.equals(waitingTime, that.waitingTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, symbol, service, questionType, responseType, date, waitingTime);
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s %s %s", symbol, service, questionType, responseType, Config.getDateFormat().format(date), waitingTime);
    }
}
