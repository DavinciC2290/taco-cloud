package com.example.saul.taco_cloud.domain;


import java.util.Date;
import java.util.List;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.persistence.*;

@Entity
@Table(name="taco")
public class Taco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="created_at")
    private Date createdAt;

    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    @Column(name="name")
    private String name;

    @NotNull
    @Size(min=1, message="You must choose at least 1 ingredient")
    @ManyToMany
    @JoinTable(
        name = "taco_ingredients",
        joinColumns = @JoinColumn(name = "taco_id"),
        inverseJoinColumns = @JoinColumn(name = "ingredient_id")

    )
    private List<Ingredient> ingredients;


    @ManyToOne
    @JoinColumn(name = "taco_order_id")
    private TacoOrder tacoOrder;


    public Taco()
    {
        
    }

    
    public Taco(Long id, Date createdAt, String name, List<Ingredient> ingredients, TacoOrder tacoOrder)
    {
        this.id = id;
        this.createdAt = createdAt;
        this.name = name;
        this.ingredients = ingredients;
        this.tacoOrder = tacoOrder;
    }

    @Override
    public String toString() {
        return "Taco{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                ", ingredients=" + ingredients +
                ", tacoOrderId=" + (tacoOrder != null ? tacoOrder.getId() : null) +
                '}';
    }



    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return List<Ingredient> return the ingredients
     */
    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    /**
     * @param ingredients the ingredients to set
     */
    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
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
     * @return Date return the createdAt
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt the createdAt to set
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }


    /**
     * @return TacoOrder return the tacoOrder
     */
    public TacoOrder getTacoOrder() {
        return tacoOrder;
    }

    /**
     * @param tacoOrder the tacoOrder to set
     */
    public void setTacoOrder(TacoOrder tacoOrder) {
        this.tacoOrder = tacoOrder;
    }

}
