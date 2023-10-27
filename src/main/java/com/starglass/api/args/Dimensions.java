package com.starglass.api.args;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Dimensions {

    private Float height;

    private Float width;

    public Float getLargestDimension() {
        if (this.width > this.height) return this.width;
        return this.height;
    }

}
