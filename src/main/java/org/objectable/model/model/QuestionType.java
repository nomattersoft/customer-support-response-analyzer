package org.objectable.model.model;

import org.objectable.model.Model;

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
     * Common methods
     */
    @Override
    public String toString() {
        return questionCategory != null ? String.format("%s.%s", super.toString(), questionCategory.toString()) : super.toString();
    }
}
