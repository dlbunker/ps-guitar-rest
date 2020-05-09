package com.guitar.model.projections;

import com.guitar.model.Manufacturer;
import com.guitar.model.Model;
import com.guitar.model.ModelType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.math.BigDecimal;

@Projection(name="modelDetailView", types={Model.class})
public interface ModelDetailView {
    @Value("#{target.name}")
    String getModelName();
    BigDecimal getPrice();
    @Value("#{target.manufacturer.name}")
    String getManufacturerName();
    ModelType getModelType();
    int getFrets();
    String getWoodType();

    @Value("#{target.manufacturer.name.split(' ')[0]} #{target.name}")
    String getFullName();
}
