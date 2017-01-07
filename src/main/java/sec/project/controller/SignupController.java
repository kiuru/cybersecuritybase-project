package sec.project.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import sec.project.domain.Signup;
import sec.project.repository.SignupRepository;

@Controller
public class SignupController {

    @Autowired
    private SignupRepository signupRepository;

    @RequestMapping("*")
    public String defaultMapping() {
        return "redirect:/form";
    }
    
    @RequestMapping(value = "/done", method = RequestMethod.GET)
    public String done() {
        return "done";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, Authentication auth) {
        model.addAttribute("signups", signupRepository.findAll());
        return "login";
    }
    
    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String loadForm(Model model, Authentication auth) {
        model.addAttribute("signup", signupRepository.findByUsername(auth.getName()));
        return "form";
    }

    @RequestMapping(value = "/done", method = RequestMethod.POST)
    public String submitForm(@RequestParam String name, @RequestParam String address, @RequestParam String username, @RequestParam String password) {
        signupRepository.save(new Signup(name, address, username, password));
        return "done";
    }
    
    @RequestMapping(value = "/form/edit/{id}", method = RequestMethod.GET)
    public String editDetails(Model model, @PathVariable Long id) {
    //@RequestMapping(value = "/form/edit", method = RequestMethod.GET)
    //public String editDetails() {
        model.addAttribute("account", signupRepository.findOne(id));
        return "form";
    }

    @RequestMapping(value = "/form/edit", method = RequestMethod.GET)
    public String edit(Model model, Authentication auth, @RequestParam String name, @RequestParam String address) {
        
        Signup signup = signupRepository.findByUsername(auth.getName());
        if (!signup.getName().equals(name)) {
            signup.setName(name);
        }
        if (!signup.getAddress().equals(address)) {
            signup.setAddress(address);
        }
        signupRepository.save(signup);
        
        return "redirect:/form";
    }
    
}
