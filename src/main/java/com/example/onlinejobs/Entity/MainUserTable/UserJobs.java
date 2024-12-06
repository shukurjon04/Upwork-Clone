package com.example.onlinejobs.Entity.MainUserTable;

import com.example.onlinejobs.Entity.Category.Categories;
import com.example.onlinejobs.Entity.ProfileTable.UserProfile;
import com.example.onlinejobs.Entity.RoleTable.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;


@Entity
public class UserJobs implements UserDetails {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private  UUID id;
    private String firstname;
    private String lastname;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @OneToOne(fetch = FetchType.EAGER)
    private Categories categories;
    private String phone;
    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;
    @JsonIgnore
    private String VerificationCode;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_jobs_role", joinColumns = @JoinColumn(name = "user_jobs_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> role ;
    private boolean isBlocked = false;
    @OneToOne(fetch = FetchType.LAZY)
    private com.example.onlinejobs.Entity.ProfileTable.UserProfile UserProfile;

    public UserProfile getProfile() {
        return UserProfile;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public void setProfile(UserProfile UserProfile) {
        this.UserProfile = UserProfile;
    }

    public UserJobs() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName().toString()))
                .collect(Collectors.toSet());
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return this.password;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public com.example.onlinejobs.Entity.ProfileTable.UserProfile getUserProfile() {
        return UserProfile;
    }

    public void setUserProfile(com.example.onlinejobs.Entity.ProfileTable.UserProfile userProfile) {
        UserProfile = userProfile;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public void setVerificationCode(String verificationCode) {
        VerificationCode = verificationCode;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    @JsonIgnore
    public String getEmail() {
        return email;
    }
    @JsonIgnore
    public String getVerificationCode() {
        return VerificationCode;
    }

    public Set<Role> getRole() {
        return role;
    }

    // design pattern
    private UserJobs(UserJobsBuilder builder) {
        this.firstname = builder.firstname;
        this.lastname = builder.lastname;
        this.email = builder.email;
        this.phone = builder.phone;
        this.username = builder.username;
        this.password = builder.password;
        this.isAccountNonExpired = builder.isAccountNonExpired;
        this.isAccountNonLocked = builder.isAccountNonLocked;
        this.isCredentialsNonExpired = builder.isCredentialsNonExpired;
        this.isEnabled = builder.isEnabled;
        this.VerificationCode = builder.verificationCode;
        this.role = builder.role;
    }
    public static class UserJobsBuilder {
        private String firstname;
        private String lastname;
        private String email;
        private String username;
        private String password;
        private String phone;
        private boolean isAccountNonExpired = true;
        private boolean isAccountNonLocked = true;
        private boolean isCredentialsNonExpired = true;
        private boolean isEnabled = true;
        private String verificationCode;
        private Set<Role> role = new HashSet<>();

        public UserJobsBuilder firstname(String firstname) {
            this.firstname = firstname;
            return this;
        }
        public UserJobsBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public UserJobsBuilder lastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public UserJobsBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserJobsBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserJobsBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserJobsBuilder isAccountNonExpired(boolean isAccountNonExpired) {
            this.isAccountNonExpired = isAccountNonExpired;
            return this;
        }

        public UserJobsBuilder isAccountNonLocked(boolean isAccountNonLocked) {
            this.isAccountNonLocked = isAccountNonLocked;
            return this;
        }

        public UserJobsBuilder isCredentialsNonExpired(boolean isCredentialsNonExpired) {
            this.isCredentialsNonExpired = isCredentialsNonExpired;
            return this;
        }

        public UserJobsBuilder isEnabled(boolean isEnabled) {
            this.isEnabled = isEnabled;
            return this;
        }

        public UserJobsBuilder verificationCode(String verificationCode) {
            this.verificationCode = verificationCode;
            return this;
        }

        public UserJobsBuilder role(Set<Role> role) {
            this.role = role;
            return this;
        }

        public UserJobs build() {
            return new UserJobs(this);
        }
    }
    public static UserJobsBuilder builder() {
        return new UserJobsBuilder();
    }
}
