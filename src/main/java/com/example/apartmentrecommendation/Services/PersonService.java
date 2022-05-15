package com.example.apartmentrecommendation.Services;


import com.example.apartmentrecommendation.Beans.Apartment;
import com.example.apartmentrecommendation.Beans.Person;
import com.example.apartmentrecommendation.Beans.UserMain;
import com.example.apartmentrecommendation.DAO.ApartmentRepository;
import com.example.apartmentrecommendation.DAO.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.data.neo4j.core.Neo4jTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ApartmentRepository apartmentRepository;



    /*public Collection<User> getAll()
    {
        return userRepository.getAllUsers();
    }*/

   /* public ModelAndView getUsers(){
        ModelAndView mav = new ModelAndView("list-users");
        mav.addObject("users", userRepository.findAll());
        return mav;
    }*/


    public List<Person> findAll(){
        return personRepository.findAll();
    }
    /*public List<User> findAll(){
        return userRepository.getAllUsers();
    }*/

    public Person findById(Long id){
        return personRepository.findById(id).get();
    }

    public List<Person> findAgents(){
        return personRepository.getAllAgents();
    }

    public Person saveUser(Person user) {
        return personRepository.save(user);
    }

    public Person deleteUser(Long id) {
        Optional<Person> user = personRepository.findById(id);
        personRepository.deleteById(id);
        /*Set<User> users = flat.get().getUsers();
        users.forEach(user -> {
            userRepository.deleteById(user.getId());
            //.getEntityId());
        });*/
        return user.get();

    }

    public Person deleteOwner(Long id) {
        Optional<Person> user = personRepository.findById(id);
        personRepository.deleteById(id);
        Set<Apartment> flats = user.get().getApartments();
        flats.forEach(flat -> {
            apartmentRepository.deleteById(flat.getId());
            //.getEntityId());
        });
        return user.get();

    }

    public Person getUserById(Long id) {
        Optional<Person> user = personRepository.findById(id);
        return user.get();
    }

    public Person updateUser(Long id, Person user) {
        Optional<Person> userOpt = personRepository.findById(id);
        Person userEntity = userOpt.get();
        updateUserEntity(user, userEntity);
        return personRepository.save(userEntity);
    }

    private void updateUserEntity(Person request, Person userEntity) {
        if(request.getName() != null && !request.getName().isEmpty())
            userEntity.setName(request.getName());
        if(request.getFirstname() != null && !request.getFirstname().isEmpty())
            userEntity.setFirstname(request.getFirstname());
        if(request.getFlat() != null && !request.getFlat().isEmpty()){
            Set<Apartment> flats = new HashSet<>();
            flats.addAll(userEntity.getFlat());
            flats.addAll(request.getFlat());
            userEntity.setFlats(flats);
        }
    }

    public Person findCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserMain userMain = (UserMain) auth.getPrincipal();
        //System.out.println(userMain.getUsername());
        Person ConnectedUser = userMain.getUser();
        return ConnectedUser;
    }
    public void addApartment(Apartment apartment){
        //Person person = (Person) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        apartmentRepository.save(apartment);
        //findCurrentUser().getApartments().add(apartment);
        //System.out.println(apartment.getId() +" "+ findCurrentUser().getId());
        personRepository.createAndGetApartement(apartment.getId(), findCurrentUser().getId());
        Set<Apartment> newList = findCurrentUser().getApartments();
        newList.add(apartment);
        findCurrentUser().setApartments(newList);
        personRepository.save(findCurrentUser());
    }

    /*public List<Apartment> findPersonAppartments(String username){
        return personRepository.getPersonByUserNameWithApartments(username);
    }*/

   /* public Object findAgentAppartments(String user) {
        return personRepository.getPersonByUserNameWithApartments(username);
    }*/

    /*public Person findOwner(Long id)
    {
        return personRepository.;
    }*/

}
