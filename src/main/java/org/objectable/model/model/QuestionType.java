package org.objectable.model.model;

import org.objectable.model.Model;

import java.util.Objects;

public class QuestionType extends Model {

    private QuestionCategory questionCategory;

    /**
     * Constructors
     */
    public QuestionType(Integer id) {
        super(id);
    }

    public QuestionType(Integer id, QuestionCategory questionCategory) {
        super(id);
        this.questionCategory = questionCategory;
    }

    /**
     * Getters & Setters
     */
    public QuestionCategory getQuestionCategory() {
        return questionCategory;
    }

    public void setQuestionCategory(QuestionCategory questionCategory) {
        this.questionCategory = questionCategory;
    }

    /**
     * Checks if this question type  matches given query records question type
     */
    @Override
    public boolean matches(Model queryModel) {
        QuestionType queryQuestionType = (QuestionType) queryModel;
        return queryModel == null ||
                (super.matches(queryQuestionType) &&
                (getQuestionCategory() != null ? getQuestionCategory().matches(queryQuestionType.getQuestionCategory()) : getQuestionCategory() == queryQuestionType.getQuestionCategory()));
    }

    /**
     * Common methods
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        QuestionType questionType = (QuestionType) o;
        return questionType.questionCategory == null || Objects.equals(questionCategory, questionType.questionCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), questionCategory);
    }

    @Override
    public String toString() {
        return String.format("%s", questionCategory != null ? super.toString() + "." + questionCategory.toString() : super.toString());
    }
}
