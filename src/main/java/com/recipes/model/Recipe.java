package com.recipes.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Table(name="RECIPE")
@Data
public class Recipe {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="name")
    private String name;


    @Column(name="price")
    private Float price;

	@CreationTimestamp
	@Column(name="creation_date")
	private Date creationDate ;

	@Column(name="type")
	private String type;

	@Column(name = "cooking_instructions")
	private String cookingInstructions;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Ingredient.class)
	@JoinColumn(name = "ref_id", referencedColumnName = "id", nullable = false)
	private Set<Ingredient> ingredients;
}
