package com.example.apartmentrecommendation.Beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Node
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude="id")
public class Person {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String firstname;
    private String profession;

    /*private gender gender;
    private enum gender{
        male,
        female
    }*/
    private String gender;
    private String nationality;
    private String telephone;
    private String email;

    private String userName;
    private String password;

    private int img;


    /*@Relationship(type="RENTED", direction=Relationship.Direction.INCOMING)
    private Set<Apartment> apartments;*/
    @Relationship(type="OWNED", direction=Relationship.Direction.INCOMING)
    private Set<Apartment> apartments;



    @Relationship(type="RATE")
    private List<Rate> ratedlocals;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public List<Rate> getRatedlocals() {
        return ratedlocals;
    }

    public void setRatedlocals(List<Rate> ratedlocals) {
        this.ratedlocals = ratedlocals;
    }




    /*public int nbApartment = apartments.size();*/

    public void setUserName(String userName)
    {
        this.userName=userName;
    }
    public void setPassword(String password)
    {
        this.password=password;
    }
    public String getName() {
        return name;
    }

    public String getUserName()
    {
        return userName;
    }
    public String getPassword()
    {
        return password;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }


    public void setApartments(Set<Apartment> apartments) {
        this.apartments = apartments;
    }

    public void setProfession(String profession)
    {
        this.profession=profession;
    }
    /*public void setGender(Person.gender gender) {
        this.gender = gender;
    }*/
    public void setTel(String tel)
    {
        this.telephone=tel;
    }
    public void setEmail(String email)
    {
        this.email=email;
    }

    public String getProfession()
    {
        return profession;
    }


   /* public Person.gender getGender() {
        return gender;
    }*/

    public String getTel()
    {
        return telephone;
    }
    public String getEmail()
    {
        return email;
    }

    public void setFlats(Set<Apartment> apartments)
    {
        this.apartments=apartments;
    }
    public Set<Apartment> getFlat()
    {
        return apartments;
    }


    public Collection<Object> getMyAuthorities() {
        return MyAuthorities;
    }

    public Collection<Object> MyAuthorities;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", firstname='" + firstname + '\'' +
                ", profession='" + profession + '\'' +
                ", gender='" + gender + '\'' +
                ", nationality='" + nationality + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", img=" + img +
                '}';
    }
}
