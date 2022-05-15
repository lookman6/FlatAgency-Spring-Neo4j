package com.example.apartmentrecommendation.Controller;

import com.example.apartmentrecommendation.Beans.Apartment;
import com.example.apartmentrecommendation.Beans.Person;
import com.example.apartmentrecommendation.Services.ApartmentService;
import com.example.apartmentrecommendation.Services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@Controller
public class ApartmentController {

    @Autowired
    private ApartmentService apartmentService;

    @Autowired
    private PersonService personService;

    @GetMapping("/search")
    public String search( Apartment flat, Model model)
    {
            List<Apartment> apartments = new ArrayList<>();
            String resultat;
            System.out.println(apartmentService.searchApartmentAllCritere(flat));
            if(!apartmentService.searchApartmentAllCritere(flat).isEmpty()){
                apartments = apartmentService.searchApartmentAllCritere(flat);
                resultat = apartments.size()+" apartments found";
            }
            else if (!apartmentService.searchApartment(flat).isEmpty()) {
                apartments = apartmentService.searchApartment(flat);
                resultat = apartments.size()+" apartments found";
            }
            else{
                apartments = apartmentService.getApartments();
                resultat = apartments.size()+" apartments found. You can do another research!!!\n Others apartments";
            }
            //model.addAttribute("all", apartmentService.searchApartment(flat));
            model.addAttribute("all", apartments);
            model.addAttribute("result", resultat);
            //return "property-grid";
            return "search_result";
    }
    @PostMapping("/search")
    public String search_success(@ModelAttribute("all") Apartment flat, Model model)
    {
        List<Apartment> apartments = new ArrayList<>();
        String resultat;
        System.out.println(apartmentService.searchApartmentAllCritere(flat));
        if(!apartmentService.searchApartmentAllCritere(flat).isEmpty()){
            apartments = apartmentService.searchApartmentAllCritere(flat);
            resultat = apartments.size()+" results";
        }
        else if (!apartmentService.searchApartment(flat).isEmpty()) {
            apartments = apartmentService.searchApartment(flat);
            resultat = apartments.size()+" results";
        }
        else{
            apartments = apartmentService.getApartments();
            resultat = "0 apartments found. Please do another research or browse the apartments below";
        }
        model.addAttribute("all", apartments);
        model.addAttribute("result", resultat);
        //model.addAttribute("all", apartmentService.searchApartment(flat));
            //return "property-grid";
            return "search_result";
    }


    @GetMapping("/flats")
    public String findAll(Model model){
        model.addAttribute("flats", apartmentService.getApartments());
        return "property-grid";
    }
    //@GetMapping
    //public Collection<Apartment> getAll()
    // {
    //   return apartmentService.getAll();
    //  }

 /*   @GetMapping("/test")
    private List<Apartment> getAllApartment(){
        return apartmentService.getApartments();
    }

    @GetMapping("/{id}")
    private Apartment getApartmentById(@PathVariable Long id){
        return apartmentService.getApartmentById(id);
    }
*/
    @PostMapping
    private Apartment saveApartment(@RequestBody Apartment apartment){
        return apartmentService.saveApartment(apartment);
    }

   /* @PatchMapping("/{id}")
    private Apartment updateApartment(@PathVariable Long id, @RequestBody Apartment apartment){
        return apartmentService.updateApartment(id, apartment);
    }

    @DeleteMapping("/{id}")
    private Apartment deleteApartment(@PathVariable Long id){
        return apartmentService.deleteApartment(id);
    }
*/
    /*-------------------------------------------------------------------------------------*/

    public List<Apartment> ratingsofbest()
    {
        List<Apartment> a = new ArrayList<Apartment>();
        for(Apartment l : apartmentService.bestlocals(personService.findCurrentUser().getId()))
        {
            l.setRatingnbr(apartmentService.showratingnbr(apartmentService.findIdByAdresse(l.getAdresse()), personService.findCurrentUser().getId()));
            System.out.println(l.getRatingnbr());
            a.add(l);
        }
        return a;
    }
    public List<Apartment> allratings()
    {
        List<Apartment> a = new ArrayList<>();
        for(Apartment l : apartmentService.findall())
        {
            try
            {
                l.setRatingnbr(apartmentService.showratingnbr(apartmentService.findIdByAdresse(l.getAdresse()), personService.findCurrentUser().getId()));
            }
            catch(Exception ex)
            {
                l.setRatingnbr(0);
            }
            System.out.println(l.getRatingnbr());
            a.add(l);
        }
        return a;

    }
    @GetMapping("/index")
    public String testing(Model model)
    {
        try{
            List<Apartment> a = ratingsofbest();

            model.addAttribute("locals", a);
            System.out.println(a.get(1).getAdresse());
            return "index";
        }
        catch (Exception exception)
        {
            model.addAttribute("locals", apartmentService.findAllLimit());
            System.out.println(apartmentService.findAllLimit());
            return "index";
        }
    }
    @GetMapping("propertyGrid")
    public String property_grid_page(Model model)
    {
        try{
            //List<Apartment> a = allratings();
            model.addAttribute("all" , allratings());
            return "property-grid";
        }
        catch (Exception exception)
        {
            model.addAttribute("all", apartmentService.findAll());
            //System.out.println(apartmentService.findAllLimit());
            return "property-grid";
        }
    }

    @GetMapping("property-single")
    public String property_single_page()
    {
        return "property-single";
    }

    @RequestMapping(value="/propertySingle")
    public ModelAndView propertysignlepage(@ModelAttribute  Apartment local)/*, int rating)*/
    {
        ModelAndView mav = new ModelAndView();
        local = apartmentService.getApartmentById(local.getId());
        Person person = personService.findCurrentUser();
        System.out.println(local);
        /*try{*/
            //System.out.println(local.getId());
            //image du local
            //int img = apartmentService.findimg(apartmentService.findIdByAdresse(local.getAdresse()));
            int img = apartmentService.findimg(local.getId());
            local.setImg(img);

            //int rate =(int) rating.charAt(0);
           /* int rate = rating;*/
            //System.out.println(local.getType());


            //int rate = apartmentService.GetRating(apartmentService.findIdByName(local.getType()) , person);

            //apartmentService.addratingnbr(apartmentService.findIdByAdresse(local.getAdresse()) , person , rate);
       /* apartmentService.addratingnbr(local.getId(),  person , rate);*/

            //Person owner = personService.findById(a)
            /*Person owner = new Person();
            List<Person> agents = personService.findAgents();
            for(Person p : agents){
                //if()
                for(Apartment a : p.getApartments()){
                    if(a.equals(local))
                    {
                        owner = p;
                        System.out.println(p);
                    }
                    System.out.println(a);
                }
            }*/

            //System.out.println(local);

        //Person owner = personService.findById(apartmentService.proprietaire(apartmentService.findIdByAdresse(local.getAdresse())));
        Person owner = personService.findById(apartmentService.proprietaire(local.getId()));
        //System.out.println(apartmentService.proprietaire(apartmentService.findIdByAdresse(local.getAdresse())));
        //System.out.println(apartmentService.proprietaire(apartmentService.findIdByAdresse(local.getAdresse())));
//        System.out.println(rating);
//        System.out.println(local.getType()+local.getAddress()+local.getNom()+local.getArea()+local.getImg());

            mav.addObject("local",local);
            mav.addObject("owner" , owner);
            mav.setViewName("property-single");

            return mav;
        /*}
        catch(Exception exception){
            try{
                List<Apartment> a = ratingsofbest();

                mav.addObject("locals", a);
                System.out.println(a.get(1).getAdresse());
            }
            catch (Exception exception1){
                mav.addObject("locals", apartmentService.findAllLimit());
            }
            finally {
                mav.setViewName("property-single");
                return mav;
            }
        }*/
    //}
    }

    //*******Rating in my-page*********//
    @RequestMapping(value="/Rating_appart")
    public ModelAndView ratingofappart(@ModelAttribute  Apartment local, int rating)
    {
        ModelAndView mav = new ModelAndView();
        local = apartmentService.getApartmentById(local.getId());
        Person person = personService.findCurrentUser();
        //System.out.println(local);
        int rate = rating;
        System.out.println(rate);
        apartmentService.addratingnbr(local.getId(),  person , rate);
        //local.setRatingnbr(rate);
        //apartmentService.saveApartment(local);
        System.out.println(local);
        mav.addObject("apartments",person.getApartments());
        mav.addObject("persons" , person);
        mav.setViewName("my-page");

        return mav;
    }
    //*******Rating in property-grid*********//
    @RequestMapping(value="/Rating_appartProp")
    public ModelAndView ratingofappartproperty(@ModelAttribute  Apartment local, int rating)
    {
        ModelAndView mav = new ModelAndView();
        local = apartmentService.getApartmentById(local.getId());
        Person person = personService.findCurrentUser();
        //System.out.println(local);
        int rate = rating;
        //System.out.println(rate);
        apartmentService.addratingnbr(local.getId(),  person , rate);
        local.setRatingnbr(rating);
        apartmentService.saveApartment(local);
        mav.addObject("one",local);
        mav.addObject("all" , allratings());
        mav.setViewName("property-grid");

        return mav;
    }

    //*******Rating in home*********//
    @RequestMapping(value="/Rating_appartHome")
    public ModelAndView ratingofapparthome(@ModelAttribute  Apartment local, int rating)
    {
        ModelAndView mav = new ModelAndView();
        local = apartmentService.getApartmentById(local.getId());
        Person person = personService.findCurrentUser();
        //System.out.println(local);
        int rate = rating;
        //System.out.println(rate);
        apartmentService.addratingnbr(local.getId(),  person , rate);
        System.out.println(local.getRatingnbr());
        apartmentService.saveApartment(local);
        try{
            List<Apartment> a = ratingsofbest();
            mav.addObject("locals", a);
            mav.setViewName("index");
            return mav;
        }
        catch (Exception exception)
        {
            mav.addObject("locals", apartmentService.findAllLimit());
            mav.setViewName("index");
            return mav;
        }
    }

   /*  @PutMapping("/UpdateApartment")
    private Apartment updateApartment(@RequestBody Apartment apartment){
        return apartmentService.updateApartment(id, apartment);
    }*/
    @GetMapping("/UpdateApartment")
    public String ApartmentShow(@ModelAttribute  Apartment local, Model model){
        local = apartmentService.getApartmentById(local.getId());
        model.addAttribute("apartment", local);
        apartmentService.updateApartment(local.getId(), local);
        return "UpdateApartment";
    }
    @PostMapping("/UpdateApartment")
    public String SavePersonApartment(@ModelAttribute("apartment") Apartment local){
         System.out.println(local);
        //local = apartmentService.getApartmentById(local.getId());
        //apartmentService.updateApartment(local.getId(), local);
        apartmentService.saveApartment(local);
        System.out.println(local);
        return "AddorUpdateApartment_success";
    }

    @GetMapping("/DeleteApartment")
    private String deleteApartment(@ModelAttribute("apartment") Apartment local){
        apartmentService.deleteApartmentById(local.getId());
        return "delete_success";
    }
    @PostMapping("/DeleteApartment")
    private String deleteApartment_success(@ModelAttribute("apartment") Apartment local){
        apartmentService.deleteApartmentById(local.getId());
        return "delete_success";
    }

    /*@DeleteMapping("/DeleteApartment/{id}")
    private String deleteApartment(@PathVariable("id")  Long id){
        System.out.println(id);
        apartmentService.deleteApartmentById(id);
        return "my-page";
    }*/
}
