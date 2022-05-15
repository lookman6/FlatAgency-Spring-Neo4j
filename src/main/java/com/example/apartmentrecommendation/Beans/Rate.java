package com.example.apartmentrecommendation.Beans;

import org.springframework.data.neo4j.core.schema.*;

@RelationshipProperties
public class Rate {
    @Id @GeneratedValue
    private Long id;

    @Property("ratingnbr")
    private int ratingnbr;
    @TargetNode
   private Apartment local;

    public Long getId() {
        return id;
    }



    public int getRatingnbr() {
        return ratingnbr;
    }

    public void setRatingnbr(int ratingnbr) {
        this.ratingnbr = ratingnbr;
    }

    public Apartment getLocal() {
        return local;
    }

    public void setLocal(Apartment local) {
        this.local = local;
    }
}
