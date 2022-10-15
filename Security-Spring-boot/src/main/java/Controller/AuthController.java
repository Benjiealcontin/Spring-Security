package Controller;

import javax.security.sasl.AuthenticationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import Model.LoginRequest;
import Model.User;
import Repository.UserRepository;
import Service.TokenService;


@RestController
public class AuthController {

	private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);
	
    private final TokenService tokenService;

    private final AuthenticationManager authenticationManager;

    public AuthController(TokenService tokenService, AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }
    
	 @RequestMapping("/")
	    public String home() {
	        return "home";
	 }
	 
	 @GetMapping("/login")
	    public ModelAndView login() {
	        return new ModelAndView("LoginForm");
	 }
	 
	 		 
	    @PostMapping("/api/login")		//Request to login
	    public String token(@RequestBody LoginRequest userLogin) throws AuthenticationException {
	        Authentication authentication = 
	        		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.username(), userLogin.password())); // Login using username and password
	        return tokenService.generateToken(authentication);
	    }
	   
}
