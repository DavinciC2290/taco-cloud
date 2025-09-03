package com.example.saul.taco_cloud.domain;

import java.util.List;
import java.util.ArrayList;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name ="username", unique = true, nullable = false)
    private String username;

    @NotBlank
    @Column(name = "password", nullable = false)
    private String password;

    @NotBlank
    @Column(name = "fullname")
    private String fullname;

    @NotBlank
    @Size(max=10)
    @Column(name = "phone")
    private String phone;

    @NotBlank
    @Column(name = "street")
    private String street;

    @NotBlank
    @Column(name = "city")
    private String city;


    @NotBlank
    @Column(name = "user_state")
    private String state;

    @NotBlank
    @Column(name = "zip")
    private String zip;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<TacoOrder> orders = new ArrayList<>();



    public void addOrder(TacoOrder order)
    {
        orders.add(order);
    }

    public User(
        Long id, 
        String username, 
        String password, 
        String fullname, 
        String phone, 
        String street, 
        String city, 
        String state, 
        String zip,
        List<TacoOrder> orders
        )
    {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.phone = phone;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.orders = orders;

    }

    public User()
    {

    }

    public User(
           String username,
            String password,
            String fullname,
            String phone,
            String street,
            String city,
            String state,
            String zip
        ){
            this.username = username;
            this.password = password;
            this.fullname = fullname;
            this.phone = phone;
            this.street = street;
            this.city = city;
            this.state = state;
            this.zip = zip;

        }

    
    

    /**
     * @return Long return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return String return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return String return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return String return the fullname
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * @param fullname the fullname to set
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     * @return String return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return String return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street the street to set
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return String return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return String return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return String return the zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * @param zip the zip to set
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * @return List<TacoOrder> return the orders
     */
    public List<TacoOrder> getOrders() {
        return orders;
    }

    /**
     * @param orders the orders to set
     */
    public void setOrders(List<TacoOrder> orders) {
        this.orders = orders;
    }

}
