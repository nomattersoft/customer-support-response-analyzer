package org.objectable.model.model;

import org.objectable.model.Model;

public class ServiceVariation extends Model {

    /**
     * Constructors
     */
    public ServiceVariation(Integer id) {
        super(id);
    }

    /**
     * Check if this service variation matches given query service variation
     */
    @Override
    public boolean matches(Model queryModel) {
        return queryModel == null || super.matches(queryModel);
    }
}
