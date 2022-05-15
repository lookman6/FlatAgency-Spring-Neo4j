package com.example.apartmentrecommendation.DAO;

import com.example.apartmentrecommendation.Beans.Apartment;
import com.example.apartmentrecommendation.Beans.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends Neo4jRepository<Person,Long> {

    Person findByUserName(String userName);
    @Query("MATCH (p:Person)<-[r:OWNED]-(a:Apartment) RETURN p, collect(r), collect(a)")
    List<Person> getAllAgents();

    @Query("MATCH (p:Person{userName: $uName})<-[r:OWNED]-(a:Apartment) " +
            "RETURN *")
    List<Apartment> getPersonByUserNameWithApartments(@Param("uName") String userName);

    /*@Query("match (p:Person) where id(p)=$aId\n" +
            "match (a:Apartment) where id(a)=$pId\n" +
            "merge (p) - [r:OWNED] -> (a)\n" +
            "return a, collect(r), collect(p)")*/
    @Query("match (p:Person) where id(p)=$aId\n" +
                  "match (a:Apartment) where id(a)=$pId\n" +
                  "merge (p) <- [r:OWNED{ownerId: $pId}] - (a)\n" +
                  "return a, collect(r), collect(p)")
    List<Apartment> createAndGetApartement(@Param("aId") Long aId, @Param("pId") Long pId);

   /* @Query("MATCH (p:Person)<-[r:OWNED]-(a:Apartment{id: $aId}) " +
            "RETURN p.id")
    Long ownerId(@Param("$aId") Long aId);*/

}
