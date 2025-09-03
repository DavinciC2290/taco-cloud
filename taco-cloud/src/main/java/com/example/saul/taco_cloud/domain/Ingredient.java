package com.example.saul.taco_cloud.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;


@Entity
@Table(name="ingredient")
public class Ingredient {

    @Id
    private String id;

    @Column(name="name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name="type")
    private Type type;

    public enum Type{

        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }

    public Ingredient()
    {

    }

    public Ingredient(String id, String name, Type type)
    {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setType(Type type)
    {
        this.type = type;
    }

    public Type getType()
    {
        return type;
    }


}
