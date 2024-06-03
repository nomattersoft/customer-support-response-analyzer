package org.objectable.model;

import java.util.Objects;

public class Model {

    protected Integer id;

    /**
     * Constructors
     */
    public Model(Integer id) {
        this.id = Objects.requireNonNull(id);
    }

    /**
     * Getters & Setters
     */
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Check if this model matches given query model
     */
    public boolean matches(Model queryModel) {
        return queryModel.getClass() == getClass() && queryModel.getId().equals(getId());
    }

    /**
     * Common methods
     */
    @Override
    public String toString() {
        return this.id.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Model model = (Model) o;
        return Objects.equals(id, model.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
