package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Model.User;
import Repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository repo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/home")
    public String user() {
        return "Hello, User!";
    }
    
	@PostMapping("/register") 							//To add user
	public User user(@RequestParam("id")Long id,
			@RequestParam("username")String username,
			@RequestParam("roles")String roles,
			@RequestParam("password")String password
			) {	
		User user = new User();
		user.setId(id);
		user.setUsername(username);
		user.setRoles(roles);
		user.setPassword(encoder.encode(password));			
		return repo.save(user);
		
	}
}
