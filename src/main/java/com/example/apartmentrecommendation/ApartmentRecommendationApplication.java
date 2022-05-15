package com.example.apartmentrecommendation;

import com.example.apartmentrecommendation.Beans.Apartment;
import com.example.apartmentrecommendation.DAO.ApartmentRepository;
import com.example.apartmentrecommendation.DAO.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.example.ApartmentRecommendation.Beans")
public class ApartmentRecommendationApplication implements CommandLineRunner {

    public final PersonRepository person;
    public final ApartmentRepository lr;

    public ApartmentRecommendationApplication(PersonRepository person, ApartmentRepository lr) {
        this.person = person;
        this.lr = lr;
    }

    public static void main(String[] args) {

        SpringApplication.run(ApartmentRecommendationApplication.class, args);
    }

   /* @Override
    public void run(String... args) throws Exception {
       // System.out.println(person.findAll());
        //System.out.println(person.getAllAgents());
        System.out.println(person.createAndGetApartement(6L,1L));
    }*/

    @Override
    public void run(String... args) throws Exception {
        //System.out.println(lr.proprietaire(25L));
        for(Apartment l : lr.bestlocals(31L))
        {
            System.out.println(l.getType());
            System.out.println("id de ce local : "+lr.idlocalbyAdresse(l.getAdresse()));
            l.setRatingnbr(lr.showratingnbr(lr.idlocalbyAdresse(l.getAdresse()) , 31L));
            System.out.println(l.getRatingnbr());
            System.out.println("id de la person possedant ce local : " +lr.idPersondeLocal(lr.idlocalbyAdresse(l.getAdresse())));
        }
        //lr.addrating(29L , 32L , 1);
        System.out.println("done");

        /*try{
            System.out.println(person.findById(lr.proprietaire(25l)).get().getUserName());
            //System.out.println(lr.findById(25l).get().getNom());
        }
        catch (Exception exception)
        {
            System.out.println(exception);
        }*/
    }
}
