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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;


/*@RestController
@RequestMapping("/neo4j/User")*/
@Controller
public class PersonController {

    @Autowired
    private PersonService personService;

    private ApartmentService apartmentService;

    //private static Person user;

    @GetMapping("/GetAgents")
    public String AllAgents(Model model){
        model.addAttribute("persons", personService.findAgents());
        return "agents-grid";
    }

   /* @GetMapping("/GetAgentSingle/{agentId}")
    public String AgentPage(@PathVariable(value = "agentId")Long agentId, Model model){
        model.addAttribute("persons", personService.findById(agentId));
        model.addAttribute("apartments", personService.findById(agentId).getApartments());
        return "agent-single";
    }*/


    @RequestMapping(value="/agentSingle")
    public ModelAndView AgentPage(@ModelAttribute  Person person){
        ModelAndView mav = new ModelAndView();
        //System.out.println(person);
        person = personService.findById(person.getId());
        mav.addObject("persons",person);
        mav.addObject("apartments" , person.getApartments());
        mav.setViewName("agent-single");

        return mav;
    }

    @GetMapping("/GetPersons")
    public String AllPersons(Model model){
        model.addAttribute("persons", personService.findAll());
        return "list-persons";
    }
    @GetMapping("/GetPersonById/{id}")
    public String PersonById(Model model, @PathVariable Long id){
        model.addAttribute("persons", personService.findById(id));
        return "my-page";
    }


    /*@PostMapping("/AddPersonApart")
    public String ApartmentSubmit(@ModelAttribute Apartment apartment, Model model) {
        model.addAttribute("apartement", apartment);
        personService.addApartment(apartment);
        return "AddApartment";
    }*/

    /*@GetMapping("/AddPersonApart")
    public String ApartmentSubmit(@RequestBody Apartment apartment, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "my-page";
        }
        personService.addApartment(apartment);
        return "redirect:/index";
    }*/


    @GetMapping("/AddPersonApart")
    public String ApartmentShow(Model model){
        Apartment apartment = new Apartment();
        model.addAttribute("apartment", apartment);
        return "AddApartment";
    }
    @PostMapping("/AddPersonApart")
    public String SavePersonApartment(@ModelAttribute("apartment") Apartment apartment){
       // System.out.println(apartment);
        personService.addApartment(apartment);
        return "AddorUpdateApartment_success";
    }

    @PostMapping(value="/users/addNewR")
    public RedirectView addNew(Person user, RedirectAttributes redir) {
        personService.saveUser(user);
       // PersonController.user = user;
        RedirectView  redirectView= new RedirectView("/login",true);
        redir.addFlashAttribute("message",
                "Vous vous êtes inscrit avec succès! Vous pouvez maintenant vous connecter.");
        return redirectView;
    }

    /*@GetMapping("/GetInfos")
    public String PersonById(Model model, Principal principal){
        model.addAttribute("persons", personService.findAll());
        String id = principal.getName();
        return "my-page";
    }*/


    @GetMapping("/GetInfos")
    public String PersonPage(Model model){
       model.addAttribute("persons", personService.findCurrentUser());
        System.out.println(personService.findCurrentUser());
        //model.addAttribute("apartments", apartmentService.findAll());
        System.out.println(personService.findCurrentUser().getApartments().toString());
        model.addAttribute("apartments", personService.findById(personService.findCurrentUser().getId()).getApartments());
        return "my-page";
    }
    /*@GetMapping
    public Collection<Person> getAll()
    {
        return personService.getAll();
    }*/

    /*@GetMapping({"/list", "/"})
    public ModelAndView getAllUsers(){
        return personService.getUsers();
    }*/


    /*@GetMapping("/GetPersons")
    public ModelAndView getMessage() {

        var mav = new ModelAndView();
        mav.addObject("persons", personService.findAll());
        mav.setViewName("list-persons");

        return mav;
    }*/
}
