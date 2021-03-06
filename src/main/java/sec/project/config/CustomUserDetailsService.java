package sec.project.config;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sec.project.domain.Signup;
import sec.project.repository.SignupRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private SignupRepository signupRepository;

    @PostConstruct
    public void init() {
        // this data would typically be retrieved from a database
        //this.accountDetails = new TreeMap<>();
        //this.accountDetails.put("ted", "$2a$06$rtacOjuBuSlhnqMO2GKxW.Bs8J6KI0kYjw/gtF0bfErYgFyNTZRDm");
        
        Signup signup = new Signup();
        signup.setName("Niko");
        signup.setAddress("Address 1");
        signup.setWebsite("http://localhost");
        signup.setUsername("niko");
        signup.setPassword("niko");
        signupRepository.save(signup);
        
        //signup.setUsername("niko");
        //signup.setPassword("niko");
        //signupRepository.save(signup);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Signup signup = signupRepository.findByUsername(username);
        
        if (signup == null) {
            throw new UsernameNotFoundException("No such user: " + username);
        }
        
        return new org.springframework.security.core.userdetails.User(
                signup.getUsername(),
                signup.getPassword(),
                true,
                true,
                true,
                true,
                Arrays.asList(new SimpleGrantedAuthority("USER")));
        
        /*if (!this.accountDetails.containsKey(username)) {
            throw new UsernameNotFoundException("No such user: " + username);
        }

        return new org.springframework.security.core.userdetails.User(
                username,
                this.accountDetails.get(username),
                true,
                true,
                true,
                true,
                Arrays.asList(new SimpleGrantedAuthority("USER")));*/
    }
}
