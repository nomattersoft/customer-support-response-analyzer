package org.objectable.model.model;

import org.objectable.model.Model;

public class QuestionSubcategory extends Model {

    /**
     * Constructors
     */
    public QuestionSubcategory(Integer id) {
        super(id);
    }

    /**
     * Checks if this question subcategory matches given query question subcategory
     */
    @Override
    public boolean matches(Model queryModel) {
        return queryModel == null || super.matches(queryModel);
    }

}
