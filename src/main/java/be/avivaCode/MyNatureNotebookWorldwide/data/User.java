package be.avivaCode.MyNatureNotebookWorldwide.data;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table(name = "users")
@Entity
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long userId;
        @NotEmpty(message = "You must enter a user name.")
        @Column(nullable = false, updatable = false, unique = true)
        private String userName;
        @NotEmpty(message = "You must enter an email address.")
        @Email(message = "Please enter a valid email address.")
        private String email;
        @NotEmpty(message = "You must enter a password.")
        private String password;
        @NotEmpty(message = "Please re-enter your password.")
        private String passwordCheck;
        private boolean passwordsMatch= true;

        private boolean enabled;

        @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
        private List<Sighting> sightings;

        @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
        @JoinTable(
                name = "users_roles",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id"))
        private Set<Role> roles = new HashSet<>();

        public User() {
        }
        public User(String userName){
            this.userName = userName;
        }

    public User(long userId, String userName, String email, String password, String passwordCheck, boolean passwordsMatch, boolean enabled, List<Sighting> sightings, Set<Role> roles) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.passwordsMatch = passwordsMatch;
        this.enabled = enabled;
        this.sightings = sightings;
        this.roles = roles;
    }

    public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPasswordCheck(){
            return passwordCheck;
        }
        public void setPasswordCheck(String passwordCheck){
            this.passwordCheck = passwordCheck;
        }
        @AssertTrue(message = "Passwords don't match. Please re-enter.")
        public boolean isPasswordsMatch() {
            return (password == null) ? false : password.equals(passwordCheck);
        }

        public void setPasswordsMatch(boolean passwordsMatch) {
            this.passwordsMatch = passwordsMatch;
        }

        public List<Sighting> getSightings() {
            return sightings;
        }

        public void setSightings(List<Sighting> sightings) {
            this.sightings = sightings;
        }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
        public String toString() {
            return "User{" +
                    "userName='" + userName + '\'' +
                    ", email='" + email + '\'' +
                    ", password='" + password + '\'' +
                    ", sightings=" + sightings +
                    '}';
        }

}
