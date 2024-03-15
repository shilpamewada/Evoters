package onlinevoting.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import onlinevoting.entity.Party;
import onlinevoting.entity.User;
import onlinevoting.exception.ResourceNotFoundException;
import onlinevoting.repository.PartyRepository;
import onlinevoting.repository.UserRepository;
import onlinevoting.service.PartyService;
import onlinevoting.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    private UserService userService;
    
    @Autowired
    private PartyService pService;
    
    @Autowired
    private PartyRepository pRepository;
    
    @Autowired
    private UserRepository uRepository;
    
    @GetMapping("/alluser")
    public List<User> getUser(){
    	return userService.displayUser();
    }
    @PostMapping("/registeruser")
    public ResponseEntity<String> registerUser(@Valid @RequestBody User user) {
        userService.registerUser(user);
        return ResponseEntity.ok("User Registered Successfully");
    }
    
    @GetMapping("/findByVotingCardNumber/{userVotingCardNumber}")
    public List<User> getUserByVotingCardNumber(@PathVariable String userVotingCardNumber) {
        return userService.getUserByVotingCardNumber(userVotingCardNumber);
    }
    
    @GetMapping("/findByUserId/{userId}")
    public User getUserByVotingCardNumber(@PathVariable Long userId) {
        return uRepository.findById(userId).get();
    }

    @GetMapping("/findByMobileNumber/{userMobileNumber}")
    public List<User> getUserByMobileNumber(@PathVariable String userMobileNumber) {
        return userService.getUserByMobileNumber(userMobileNumber);
    }

    @GetMapping("/findByUserName/{userName}")
    public List<User> getUserByUserName(@PathVariable String userName) {
        return userService.getUserByUserName(userName);
    }
    
    
     @DeleteMapping("/deleteuser/{id}")
		public ResponseEntity<Boolean> deleteUser(@PathVariable("id") long userId) {
			userService.deleteByUserId(userId);
			boolean flag = true;
			return new ResponseEntity<Boolean>(flag, HttpStatus.OK);
		}
  
    @GetMapping("/findByRole/{userRole}")
    public List<User> getUserByRole(@PathVariable String userRole) {
        return userService.getUserByRole(userRole);
    }
    
//    @GetMapping("/adminlogin/{userEmail}/{userPassword}")
//    public User loginUserByEmail(User user) {
//        return this.userService.loginUserByEmail(user.getUserEmail(), user.getUserPassword()).orElseThrow(()
//        -> new ResourceNotFoundException("User", "Email and Password", user.getUserEmail() + " and password " + user.getUserPassword()));
//    }

    @GetMapping("/voterlogin/{userVotingCardNumber}/{userPassword}")
    public User loginUserByVotingCardNumber(User user) {
        return this.userService.loginUserByVotingCardNumber(user.getUserVotingCardNumber(), user.getUserPassword())
        .orElseThrow(() -> new ResourceNotFoundException("User", "Voting Card Number and Password", user.getUserVotingCardNumber() + " and password " + user.getUserPassword()));
    }
    
    @PostMapping("/addvote/{partyId}/{userId}")
    public ResponseEntity<HashMap<String, String>> addCandidate(@PathVariable Long partyId, @PathVariable Long userId) {
    	Party p = pRepository.findById(partyId).get();
    	User u = uRepository.findById(userId).get();
    	String voteStatus = "";
    	if (p != null && u != null) {
    		if (u.getStatus() == null) {
    			p.setVotes(p.getVotes() + 1);
    			u.setStatus("voted");
    			pRepository.save(p);
    			uRepository.save(u);
    			voteStatus = "Vote Added";
    		} else  {
    			voteStatus = "Vote Already Added";
    		}
    	}
    	HashMap h = new HashMap<String, String>();
    	h.put("vote", voteStatus);
    	return ResponseEntity.ok(h);
    }
    
    @PostMapping("/activateUser")
    public ResponseEntity<String> activateUser(@Valid @RequestBody User user) {
        user.setActivateAccount(true);
        uRepository.save(user);
        return ResponseEntity.ok("User Activated Successfully");
    }
    
    @PostMapping("/activateUser2/{userId}")
    public ResponseEntity<String> activateUser2(@PathVariable Long userId) {
    	User u = uRepository.findById(userId).get();
    	if (u != null) {
    		u.setActivateAccount(true);
    		 uRepository.save(u);
    		 return ResponseEntity.ok("User Activated Successfully");
    	}
    	 return ResponseEntity.ok("User Not Found");
       
    }
    
    @PostMapping("/loginByCardNumber")
    public ResponseEntity<User> loginByCardNumber(@RequestBody User user) {
        User u = userService.loginUserByVotingCardNumber(user.getUserVotingCardNumber(), user.getUserPassword()).get();
        return ResponseEntity.ok(u);
    }
    
    @PostMapping("/loginByEmail")
    public ResponseEntity<User> loginUserByEmail(@RequestBody User user) {
        User u = userService.loginUserByEmail(user.getUserEmail(), user.getUserPassword()).get();
        return ResponseEntity.ok(u);
    }
    
    @GetMapping("/getUserById/{userId}")
    public  ResponseEntity<User>  getUserById(@PathVariable Long userId){
    	User u = uRepository.findById(userId).get();
    	return ResponseEntity.ok(u);
    }
    
    @PutMapping("/updateUser")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User u = uRepository.findById(user.getUserId()).get();
        u.setUserAddress(user.getUserAddress());
        u.setUserMobileNumber(user.getUserMobileNumber());
        u.setUserDateOfBirth(user.getUserDateOfBirth());
        u.setUserEmail(user.getUserEmail());
        u.setUserName(user.getUserName());
        uRepository.save(u);
        return ResponseEntity.ok(u);
    }
    
}

