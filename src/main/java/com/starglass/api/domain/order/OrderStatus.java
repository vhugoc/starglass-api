package com.starglass.api.domain.order;

import com.starglass.api.infra.exception.custom.BuilderException;

public enum OrderStatus {

    OPEN {
        @Override
        public void validate(Order.Builder orderBuilder) {
        }
    },

    CLOSED {
        @Override
        public void validate(Order.Builder orderBuilder) {
            if (orderBuilder.getInstallDate() == null)
                throw new BuilderException("Install date is required");
            if (orderBuilder.getInstallAddress() == null)
                throw new BuilderException("Install adress is required");
        }
    },

    FINISHED {
        @Override
        public void validate(Order.Builder orderBuilder) {
        }
    };

    public abstract void validate(Order.Builder orderBuilder);

}
