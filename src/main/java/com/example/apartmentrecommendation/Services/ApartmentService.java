package com.example.apartmentrecommendation.Services;

import com.example.apartmentrecommendation.Beans.Apartment;
import com.example.apartmentrecommendation.Beans.Person;
import com.example.apartmentrecommendation.DAO.ApartmentRepository;
import com.example.apartmentrecommendation.DAO.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;*/
import java.util.*;


@Service
public class ApartmentService {

    //@Autowired
    // FlatRepository flatRepository;

    // public Collection<Flat> getAll()
    //{
    //  return flatRepository.getAllFlats();
    //}
    /*--------------------------------------------------------------------------------------*/


    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private PersonRepository userRepository;

    public Apartment findById(Long id){
        return apartmentRepository.findById(id).get();
    }
    public List<Apartment> searchApartment(Apartment flat)
    {
        return apartmentRepository.findAllByTypeOrCityOrNeighborhoodOrAmbienceOrBedroomsOrBathroomsOrPrice(flat.getType(),flat.getCity(),flat.getNeighborhood(),flat.getAmbience(),flat.getBedrooms(),flat.getBathrooms(),flat.getPrice());
    }
    public List<Apartment> searchApartmentAllCritere(Apartment flat)
    {
        return apartmentRepository.findAllByTypeAndCityAndNeighborhoodAndAmbienceAndBedroomsAndBathroomsAndPrice(flat.getType(),flat.getCity(),flat.getNeighborhood(),flat.getAmbience(),flat.getBedrooms(),flat.getBathrooms(),flat.getPrice());
    }

    public List<Apartment> searchFlat(Apartment flat)
    {
        List<Apartment> gety = new ArrayList<>();
        //List<Apartment> listFlat = getApartments();
        Iterable<Apartment> listFlat = apartmentRepository.findAll();
        Iterator i=listFlat.iterator();
        for(Apartment f : listFlat)
        {
            if(f.getType()==flat.getType() && f.getCity()==flat.getCity() && f.getNeighborhood()==flat.getNeighborhood() &&  f.getBedrooms()==flat.getBedrooms() && f.getBathrooms()==flat.getBathrooms() && f.getPrice()<=flat.getPrice())
            {
                gety.add(f);
            }
        }
       /* while(i.hasNext())
        {
            if(i.next().getType()==flat.getType() && i.next().getCity()==flat.getCity() && i.next().getNeighborhood()==flat.getNeighborhood() && i.next().getBedrooms()==flat.getBedrooms() && i.next().getBathrooms()==flat.getBathrooms() && i.next().getMaxPrice()<=flat.getMaxPrice())
            {
                gety.add(i.next());
            }
        }*/
        return gety;
    }

    //(List<Flat>)
    public List<Apartment> getApartments() {return  apartmentRepository.findAll();}

    public Apartment saveApartment(Apartment apartment) {return apartmentRepository.save(apartment);}

    public Apartment deleteApartment(Long id) {
        Optional<Apartment> apartment = apartmentRepository.findById(id);
        apartmentRepository.deleteById(id);
        Set<Person> persons = apartment.get().getPersons();
        persons.forEach(person -> {
            userRepository.deleteById(person.getId());
            //.getEntityId());
        });
        return apartment.get();

    }

    public Apartment getApartmentById(Long id) {
        Optional<Apartment> apartment = apartmentRepository.findById(id);
        return apartment.get();
    }

    public Apartment updateApartment(Long id, Apartment apartment) {
        Optional<Apartment> apartmentOpt = apartmentRepository.findById(id);
        Apartment apartmentEntity = apartmentOpt.get();
        updateApartmentEntity(apartment, apartmentEntity);
        return apartmentRepository.save(apartmentEntity);
    }

    private void updateApartmentEntity(Apartment request, Apartment apartmentEntity) {
        if(request.getType() != null && !request.getType().isEmpty())
            apartmentEntity.setType(request.getType());
        if(request.getCity() != null && !request.getCity().isEmpty())
            apartmentEntity.setCity(request.getCity());
        if(request.getPersons() != null && !request.getPersons().isEmpty()){
            Set<Person> persons = new HashSet<>();
            persons.addAll(apartmentEntity.getPersons());
            persons.addAll(request.getPersons());
            apartmentEntity.setPersons(persons);
        }
    }


    public int showratingnbr(long lid , long pid)
    {
        return apartmentRepository.showratingnbr(lid,pid);
    }


    public Apartment add(Apartment local)
    {
        return apartmentRepository.save(local);
    }
    public void delete(Apartment local)
    {
        apartmentRepository.delete(local);
    }
    public void deleteApartmentById(Long id){
        apartmentRepository.deleteById(id);
    }

    public List<Apartment> findall()
    {
        return apartmentRepository.findAll();
    }
    //    public  List<Local> findby()
//    {
//        return lr.findBy();
//    }
//
//    public List<Local> findbytype(String type)
//    {
//        return lr.findByType(type);
//    }
    public int findimg(Long id)
    {
        return apartmentRepository.imglocal(id);
    }
    public long proprietaire(Long id)
    {
        return apartmentRepository.proprietaire(id);
    }

    public List<Apartment> bestlocals(Long id )
    {
        return apartmentRepository.bestlocals(id);
    }

    public void setrating(Apartment a , Person b , int rate)
    {
        apartmentRepository.addrating(a.getId() , b.getId()  , rate);
    }
    public long findIdByAdresse(String adresse)
    {
        return apartmentRepository.idlocalbyAdresse(adresse);
    }

    public void addratingnbr(long localid , Person person , int rating)
    {
        apartmentRepository.addrating(person.getId(),localid, rating);
    }

    public List<Apartment> findAllLimit(){
        return apartmentRepository.findAllLimit();
    }

    public List<Apartment> findAll() {
        return apartmentRepository.findAll();
    }

   /* public int GetRating(long localid , Person person){
        return apartmentRepository.getRating(person.getId(),localid);
    }*/



    /*-------------------------------------------------------------------------------------*/


}
