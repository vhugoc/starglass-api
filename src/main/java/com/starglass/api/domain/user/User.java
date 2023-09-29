package com.starglass.api.domain.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.starglass.api.domain.address.Address;
import com.starglass.api.domain.merchant.Merchant;
import com.starglass.api.infra.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity(name = "users")
@Getter
@ToString(exclude = {"password"})
public class User extends BaseEntity<User, User.Builder> implements UserDetails {

    @ManyToOne
    @JoinColumn(name = "merchant_id")
    private Merchant merchant;

    private String firstName;

    private String lastName;

    private Address address;

    private String email;

    private String password;

    private String phone;

    private String photo;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    protected User() {
        super();
    }

    protected User(Builder builder) {
        super(builder);
        this.merchant = builder.merchant;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.address = builder.address;
        this.email = builder.email;
        this.password = builder.password;
        this.phone = builder.phone;
        this.photo = builder.photo;
        this.role = builder.role;
    }

    @Override
    public Builder toBuilder() {
        return new Builder(this);
    }

    public static Builder of() {
        return new Builder();
    }

    public static Builder of(User user) {
        return new Builder(user);
    }

    public String getFullName() {
        return String.format("%s %s", this.firstName, this.lastName);
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Getter
    public static class Builder extends BaseEntity.Builder<User, Builder> {

        private Merchant merchant;

        private String firstName;

        private String lastName;

        private Address address;

        private String email;

        private String password;

        private String phone;

        private String photo;

        private UserRole role;

        public Builder() {
            super();
        }

        public Builder(User user) {
            super(user);
            this.merchant = user.merchant;
            this.firstName = user.firstName;
            this.lastName = user.lastName;
            this.address = user.address;
            this.email = user.email;
            this.password = user.password;
            this.phone = user.phone;
            this.photo = user.photo;
            this.role = user.role;
        }

        public User build() {
            if (this.role == null) this.role = UserRole.USER;
            return new User(this);
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

    }

}
