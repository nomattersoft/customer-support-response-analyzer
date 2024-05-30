package org.objectable.model.model;

import org.objectable.model.Model;

import java.util.Objects;

public class Record extends Model {

    protected String symbol;

    protected Service service;

    protected QuestionType questionType;

    protected String responseType;

    /**
     * Constructors
     */
    public Record(Integer id, String symbol, Service service, QuestionType questionType, String responseType) {
        super(id);
        this.symbol = symbol;
        this.service = service;
        this.questionType = questionType;
        this.responseType = responseType;
    }

    /**
     * Getters & Setters
     */
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    /**
     * Common methods
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Record record = (Record) o;
        return Objects.equals(symbol, record.symbol) && Objects.equals(service, record.service) && Objects.equals(questionType, record.questionType) && Objects.equals(responseType, record.responseType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), symbol, service, questionType, responseType);
    }
}
