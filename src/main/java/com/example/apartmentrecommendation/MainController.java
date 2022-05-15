package com.example.apartmentrecommendation;

import com.example.apartmentrecommendation.Services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @GetMapping("/list")
    public String goList()
    {
        return "redirect:GetPersons";
    }

    @GetMapping
    public String goHome()
    {
        return "redirect:index";
    }

    @GetMapping("/about")
    public String goAbout()
    {
        return "about";
    }
    @GetMapping("/apartment")
    public String goContact()
    {
        return "redirect:propertyGrid";
    }

    @GetMapping("/agentsGrid")
    public String goAgentsGrid()
    {
        return "redirect:GetAgents";
    }

    /*@GetMapping("/myPage")
    public String goMyPage()
    {
        return "redirect:/GetPersonById/{id}";
    }*/
    @GetMapping("/myPage")
    public String goMyPage()
    {
        return "redirect:/GetInfos";
    }

    /*@GetMapping("/AddApartment")
    public String addApartment() {
        return "redirect:/AddPersonApart";
    }*/
    @GetMapping("/AddApartment")
    public String addApartment() {
        return "redirect:/AddPersonApart";
    }



    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String login_success(){
        return this.goHome();
    }
    @GetMapping("/logout")
    public String logout(){
        return "login";
    }
    @GetMapping("/register")
    public String register(){
        return "registernew";
    }
    @PostMapping("/register")
    public String register_success(){
        return login();
    }

    /*@RequestMapping("/**")
    public String notFound() {
        return "error_page";
    }*/




    /*@Autowired
    PersonService personService;

    @GetMapping("/test")
    public String goTest(Model model)
    {
        *//*model.addAttribute("something", "this is the model");
        model.addAttribute("users", Arrays.asList(
            new User("Ali", "etudiant"),
            new User("Sarah", "etudiant"),
            new User("Benyamin", "etudiant")
        ));*//*
        return "test";
    }
*/
    /*@GetMapping("/list")
    public String goList()
    {
        return "redirect:GetPersons";
    }*/


   /* public List<Local> ratingsofbest()
    {
        List<Local> a = new ArrayList<Local>();
        for(Local l : ls.bestlocals(31L))
        {
            l.setRatingnbr(ls.showratingnbr(ls.findidbynom(l.getType()), 31L));
            System.out.println(l.getRatingnbr());
            a.add(l);
        }
        return a;
    }
    public List<Local> allratings()
    {
        List<Local> a = new ArrayList<Local>();
        for(Local l : ls.findall())
        {
            try {
                l.setRatingnbr(ls.showratingnbr(ls.findidbynom(l.getType()), 31L));
            }catch(Exception ex)
            {
                l.setRatingnbr(0);

            }
            System.out.println(l.getRatingnbr());
            a.add(l);
        }
        return a;

    }
    @GetMapping
    public String testing(Model model)
    {

        List<Local> a = ratingsofbest();

        model.addAttribute("locals", a);

        return "index";
    }
    @GetMapping("property-grid")
    public String property_grid_page(Model model)
    {
        List<Local> a = allratings();
        model.addAttribute("all" , a);
        return "property-grid";
    }
    @GetMapping("property-single")
    public String property_single_page()
    {
        return "property-single";
    }

    @RequestMapping(value="propertySingle")
    public ModelAndView propertysignlepage(@ModelAttribute  Local local, String rating)
    {
        int rate =(int) rating.charAt(0);
        ModelAndView mav = new ModelAndView();
        System.out.println(local.getType());

        //j'ai besoin de la personne connecté
        Person p = ps.adduser();
        //image du local
        int img = ls.findimg(ls.findidbynom(local.getType()));
        local.setImg(img);


        Person person= ps.finduser(31L);

        ls.addratingnbr(ls.findidbynom(local.getType()) , p , rate);
//        System.out.println(rating);
//        System.out.println(local.getType()+local.getAddress()+local.getNom()+local.getArea()+local.getImg());

        mav.addObject("local",local);
        mav.addObject("owner" , person);
        mav.setViewName("property-single");
//

        return mav;
    }*/

}
