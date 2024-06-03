package org.objectable.model.model;

import org.objectable.model.Model;

import java.util.Objects;

public class QuestionCategory extends Model {

    private QuestionSubcategory subcategory;

    /**
     * Constructors
     */
    public QuestionCategory(Integer id) {
        super(id);
    }

    public QuestionCategory(Integer id, QuestionSubcategory subcategory) {
        super(id);
        this.subcategory = subcategory;
    }

    /**
     * Getters & Setters
     */
    public QuestionSubcategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(QuestionSubcategory subcategory) {
        this.subcategory = subcategory;
    }

    /**
     * Checks if this question category matches given query question category
     */
    @Override
    public boolean matches(Model queryModel) {
        QuestionCategory queryCategory = (QuestionCategory) queryModel;
        return queryModel == null ||
                (super.matches(queryCategory) &&
                (getSubcategory() != null ? getSubcategory().matches(queryCategory.getSubcategory()) : getSubcategory() == queryCategory.getSubcategory()));
    }

    /**
     * Common methods
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        QuestionCategory questionCategory = (QuestionCategory) o;
        return questionCategory.subcategory == null || Objects.equals(subcategory, questionCategory.subcategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), subcategory);
    }

    @Override
    public String toString() {
        return subcategory != null ? String.format("%s.%s", super.toString(), subcategory.toString()) : super.toString();
    }
}
