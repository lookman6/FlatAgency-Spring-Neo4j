package com.example.apartmentrecommendation.DAO;

import com.example.apartmentrecommendation.Beans.Apartment;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApartmentRepository extends Neo4jRepository<Apartment,Long> {
    List<Apartment> findAllByTypeOrCityOrNeighborhoodOrAmbienceOrBedroomsOrBathroomsOrPrice(String type, String city, String neighborhood, String ambience, String bedrooms, String bathrooms, double price);

    List<Apartment> findAllByTypeAndCityAndNeighborhoodAndAmbienceAndBedroomsAndBathroomsAndPrice(String type, String city, String neighborhood, String ambience, String bedrooms, String bathrooms, double price);

    @Query("match(n:Apartment) - [r:RATE] - (p:Person) where id(p)=$pid and id(n)=$lid return r.ratingnbr")
    int showratingnbr(@Param("lid") long idlocal , @Param("pid") long idperson);


    //    @Query("MATCH (n:Local {type:\"duplex\"}) RETURN n ")
//    List<Local> findByType(String type);
    @Query("MATCH (n:Apartment) - [r:RATE] - (p:Person ) where id(p)= $id RETURN n ORDER BY r.ratingnbr desc limit 3 ")
    List<Apartment> bestlocals(@Param("id") long personid);

    @Query("MATCH (n:Apartment) RETURN n limit 3 ")
    List<Apartment> findAllLimit();

    @Query("MATCH (n:Apartment) WITH n  MATCH (p:Person )  where id(p)= $Uid  and id(n) =$Lid merge (n) < - [r:RATE] - (p) set r.ratingnbr= $rate ")
    void addrating(@Param("Uid") Long personid ,@Param("Lid") Long localid , @Param("rate") int ratingnbr);

    @Query("MATCH (p:Person{id: $Uid}) - [r:RATE] - (a:Apartment{id: $Lid}) return r.ratingnbr")
    int getRating(@Param("Uid") Long personid ,@Param("Lid") Long localid);
    @Query("match(n:Apartment {adresse: $adresse}) return id(n)")
    Long idlocalbyAdresse(@Param("adresse") String type);

    @Query("match (n:Apartment) - [r:POSSEDE] -(p:Person) where id(n)=$id return id(p)")
    Long idPersondeLocal(@Param("id") Long idlocal);

    @Query("match(n:Apartment) where id(n)= $id return n.img")
    int imglocal(@Param("id") Long id);

    @Query("match(n:Apartment) -[r:OWNED] - (p:Person) where id(n)= $id return id(p)")
    Long proprietaire(@Param("id") Long idhouse);



}
