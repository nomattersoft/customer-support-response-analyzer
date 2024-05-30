package org.objectable.model.model;

import org.objectable.model.Model;

import java.util.Objects;

public class Service extends Model {

    private ServiceVariation variation;

    /**
     * Constructors
     */
    public Service(Integer id) {
        super(id);
    }

    public Service(Integer id, ServiceVariation variation) {
        super(id);
        this.variation = variation;
    }

    /**
     * Getters & Setters
     */
    public ServiceVariation getVariation() {
        return variation;
    }

    public void setVariation(ServiceVariation variation) {
        this.variation = variation;
    }

    /**
     * Common methods
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Service service = (Service) o;
        return Objects.equals(variation, service.variation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), variation);
    }

    @Override
    public String toString() {
        return String.format("%s%s", id, variation != null ? "." + variation.getId() : "");
    }
}
