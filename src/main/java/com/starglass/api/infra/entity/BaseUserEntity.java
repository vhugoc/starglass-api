package com.starglass.api.infra.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.starglass.api.domain.user.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@MappedSuperclass
@Getter
public abstract class BaseUserEntity<T extends BaseUserEntity, B extends BaseUserEntity.Builder> extends BaseEntity<T, B> {

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    protected User user;

    protected BaseUserEntity() {
    }

    protected BaseUserEntity(Builder builder) {
        super(builder);
        this.user = builder.user;
    }

    @Getter
    public abstract static class Builder<T extends BaseUserEntity, B extends Builder> extends BaseEntity.Builder<T, B> {

        private User user;

        protected Builder() {
        }

        protected Builder(User user) {
            super();
            this.user = user;
        }

        protected Builder(T baseUserEntity) {
            super(baseUserEntity);
            this.user = baseUserEntity.user;
        }

        public abstract T build();

        public B withUser(User user) {
            this.user = user;
            return (B) this;
        }

    }

}
