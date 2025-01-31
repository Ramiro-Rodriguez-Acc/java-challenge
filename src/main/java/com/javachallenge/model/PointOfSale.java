package com.javachallenge.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import static com.javachallenge.utils.Constants.NOMBRE;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PointOfSale  {


    @JsonProperty(NOMBRE)
    private String name;

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointOfSale that = (PointOfSale) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}
