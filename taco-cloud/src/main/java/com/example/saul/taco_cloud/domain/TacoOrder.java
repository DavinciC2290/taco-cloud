package com.example.saul.taco_cloud.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.CreditCardNumber;
import jakarta.persistence.*;

@Entity 
@Table(name="taco_order")
public class TacoOrder {

    //private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="placed_at")
    private Date placedAt;

    @NotBlank(message="Delivery name is required")
    @Column(name="delivery_name")
    private String deliveryName;

    @NotBlank(message="Street is required")
    @Column(name="delivery_street")
    private String deliveryStreet;

    @NotBlank(message="City is required")
    @Column(name="delivery_city")
    private String deliveryCity;

    @NotBlank(message="State is required")
    @Column(name="delivery_state")
    private String deliveryState;

    @NotBlank(message="Zip code is required")
    @Column(name="delivery_zip")
    private String deliveryZip;

    @NotBlank(message="Your phone number is necessary to contact you")
    @Size(max=10)
    @Column(name="contact_phone")
    private String contactPhone;

    @CreditCardNumber(message="Not a valid credit card number")
    @Column(name="cc_number")
    private String ccNumber;

    @Pattern(regexp="^(0[1-9]|1[0-2])([\\\\/])([2-9][0-9])$", message="Must be formatted MM/YY")
    @Column(name="cc_expiration")
    private String ccExpiration;

    @Digits(integer=3, fraction=0, message="Invalid CVV")
    @Column(name="cc_cvv")
    private String ccCVV;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @OneToMany(mappedBy = "tacoOrder", cascade = CascadeType.ALL)
    private List<Taco> tacos = new ArrayList<>();

    

    public void addTaco(Taco taco)
    {
        this.tacos.add(taco);
    }

    public TacoOrder()
    {
        
    }


    public TacoOrder(
        Long id,
        Date placedAt,
        String deliveryName, 
        String deliveryStreet, 
        String deliveryCity, 
        String deliveryState, 
        String deliveryZip, 
        String contactPhone,
        String ccNumber,
        String ccExpiration,
        String ccCVV,
        List<Taco> tacos,
        User user
    )

    {
        this.id = id;
        this.placedAt = placedAt;
        this.deliveryName = deliveryName;
        this.deliveryStreet = deliveryStreet;
        this.deliveryCity = deliveryCity;
        this.deliveryState = deliveryState;
        this.deliveryZip = deliveryZip;
        this.contactPhone = contactPhone;
        this.ccNumber = ccNumber;
        this.ccExpiration = ccExpiration;
        this.ccCVV = ccCVV;
        this.tacos = tacos;
        this.user = user;

    }

    @Override
    public String toString() {
        return "TacoOrder{" +
                "id=" + id +
                ", placedAt=" + placedAt +
                ", deliveryName='" + deliveryName + '\'' +
                ", deliveryStreet='" + deliveryStreet + '\'' +
                ", deliveryCity='" + deliveryCity + '\'' +
                ", deliveryState='" + deliveryState + '\'' +
                ", deliveryZip='" + deliveryZip + '\'' +
                ", ccNumber='" + ccNumber + '\'' +
                ", ccExpiration='" + ccExpiration + '\'' +
                ", ccCVV='" + ccCVV + '\'' +
                ", tacos=" + tacos +
                '}';
    }

    




    /**
     * @return String return the deliveryName
     */
    public String getDeliveryName() {
        return deliveryName;
    }

    /**
     * @param deliveryName the deliveryName to set
     */
    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
    }

    /**
     * @return String return the deliveryStreet
     */
    public String getDeliveryStreet() {
        return deliveryStreet;
    }

    /**
     * @param deliveryStreet the deliveryStreet to set
     */
    public void setDeliveryStreet(String deliveryStreet) {
        this.deliveryStreet = deliveryStreet;
    }

    /**
     * @return String return the deliveryCity
     */
    public String getDeliveryCity() {
        return deliveryCity;
    }

    /**
     * @param deliveryCity the deliveryCity to set
     */
    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
    }

    /**
     * @return String return the deliveryState
     */
    public String getDeliveryState() {
        return deliveryState;
    }

    /**
     * @param deliveryState the deliveryState to set
     */
    public void setDeliveryState(String deliveryState) {
        this.deliveryState = deliveryState;
    }

    /**
     * @return String return the deliveryZip
     */
    public String getDeliveryZip() {
        return deliveryZip;
    }

    /**
     * @param deliveryZip the deliveryZip to set
     */
    public void setDeliveryZip(String deliveryZip) {
        this.deliveryZip = deliveryZip;
    }

    /**
     * @return String return the ccNumber
     */
    public String getCcNumber() {
        return ccNumber;
    }

    /**
     * @param ccNumber the ccNumber to set
     */
    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    /**
     * @return String return the ccExpiration
     */
    public String getCcExpiration() {
        return ccExpiration;
    }

    /**
     * @param ccExpiration the ccExpiration to set
     */
    public void setCcExpiration(String ccExpiration) {
        this.ccExpiration = ccExpiration;
    }

    /**
     * @return String return the ccCVV
     */
    public String getCcCVV() {
        return ccCVV;
    }

    /**
     * @param ccCVV the ccCVV to set
     */
    public void setCcCVV(String ccCVV) {
        this.ccCVV = ccCVV;
    }

    /**
     * @return List<Taco> return the tacos
     */
    public List<Taco> getTacos() {
        return tacos;
    }

    /**
     * @param tacos the tacos to set
     */
    public void setTacos(List<Taco> tacos) {
        this.tacos = tacos;
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
     * @return Date return the placedAt
     */
    public Date getPlacedAt() {
        return placedAt;
    }

    /**
     * @param placedAt the placedAt to set
     */
    public void setPlacedAt(Date placedAt) {
        this.placedAt = placedAt;
    }

    


    /**
     * @return String return the contactPhone
     */
    public String getContactPhone() {
        return contactPhone;
    }

    /**
     * @param contactPhone the contactPhone to set
     */
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    /**
     * @return User return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

}
