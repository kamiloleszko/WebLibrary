package com.klb.entity;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;

/**
 * Created by fmkam on 06.07.2017.
 */
@Entity
@Table (name = "users")
public class User extends BaseEntity {

    @Column (name = "first_name", nullable = false, length = 255) //tutaj uzywam nullable = false zamiast adnotacji @NotNull - to jest to samo
    private String firstName;

    @Column (name = "last_name", nullable =  false, length = 255)
    private String lastName;

    //email bedzie loginem uzytkownika
    @Column (name = "email", nullable = false, unique = true)
    @Email (message = "incorrect email format") //bedzie sprawdzac czy wartosc zawiera w sobie "@"
    private String email;

    @Column (name = "password", nullable = false) //ono bedzie szyfrowane potem w SpringSecurity
    private String password;

    @Enumerated (EnumType.STRING) //zeby Hibernate przetworzyl enuma bo samego Enuma nie moze byc w bazie danych
    @Column (name = "role", nullable = false)
    private Role role = Role.CUSTOMER;


    public User (){
    }

    public User(String firstName, String lastName, String email, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
