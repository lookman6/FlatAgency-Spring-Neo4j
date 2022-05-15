package com.example.apartmentrecommendation.Beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Set;

@Node
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Apartment {
    @Id
    @GeneratedValue
    private Long id;

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    private String adresse;
    private String type;
    private String city;

    public String getAmbience() {
        return ambience;
    }

    public void setAmbience(String ambience) {
        this.ambience = ambience;
    }

    private String ambience;
    private String neighborhood;
    private String bedrooms;
    private String bathrooms;
    private String statut;
    private double price;

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    private double area;

    @Relationship(type="HOSTED", direction = Relationship.Direction.INCOMING)
    private Set<Person> persons;



    private int ratingnbr;

    public int getRatingnbr() {
        return ratingnbr;
    }


    public void setRatingnbr(int ratingnbr) {
        this.ratingnbr= ratingnbr;
    }
    private int img;
    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }



    public void setNeighborhood(String neighborhood)
    {
        this.neighborhood=neighborhood;
    }
    public String getNeighborhood()
    {
        return neighborhood;
    }
    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(String bedrooms) {
        this.bedrooms = bedrooms;
    }

    public String getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(String bathrooms) {
        this.bathrooms = bathrooms;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "id=" + id +
                ", adresse='" + adresse + '\'' +
                ", type='" + type + '\'' +
                ", city='" + city + '\'' +
                ", ambience='" + ambience + '\'' +
                ", neighborhood='" + neighborhood + '\'' +
                ", bedrooms='" + bedrooms + '\'' +
                ", bathrooms='" + bathrooms + '\'' +
                ", statut='" + statut + '\'' +
                ", price=" + price +
                ", area=" + area +
                ", ratingnbr=" + ratingnbr +
                ", img=" + img +
                '}';
    }
}
