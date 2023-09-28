package com.starglass.api.infra.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(of = "id")
@Getter
public abstract class BaseEntity<T extends BaseEntity, B extends BaseEntity.Builder> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    protected String id;

    @CreatedDate
    @Column(updatable = false)
    protected Date createdAt;

    @LastModifiedDate
    protected Date updatedAt;

    protected boolean isActive;

    protected BaseEntity() {
    }

    protected BaseEntity(Builder builder) {
        this.id = builder.id;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
        this.isActive = builder.isActive;
    }

    public abstract Builder toBuilder();

    public boolean getIsActive() {
        return isActive;
    }

    @Getter
    public abstract static class Builder<T extends BaseEntity, B extends Builder> {

        private String id;

        private Date createdAt;

        private Date updatedAt;

        private boolean isActive;

        protected Builder() {
        }

        protected Builder(T entity) {
            this.id = entity.id;
            this.createdAt = entity.createdAt;
            this.updatedAt = entity.updatedAt;
            this.isActive = entity.isActive;
        }

        public abstract T build();

        public B withId(String id) {
            this.id = id;
            return (B) this;
        }

        public B withCreatedAt(Date createdAt) {
            this.createdAt = createdAt;
            return (B) this;
        }

        public B withUpdatedAt(Date updatedAt) {
            this.updatedAt = updatedAt;
            return (B) this;
        }

        public B withIsActive(boolean isActive) {
            this.isActive = isActive;
            return (B) this;
        }

        public boolean getIsActive() {
            return isActive;
        }

    }

}
