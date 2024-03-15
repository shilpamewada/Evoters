package onlinevoting.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import onlinevoting.entity.Party;
import onlinevoting.entity.User;
import onlinevoting.exception.ResourceNotFoundException;
import onlinevoting.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> displayUser()
    {
    	return userRepository.findAll();
    }
    
    public User registerUser(User user) {
    	if (user.getUserRole().equals("admin")) {
    		user.setActivateAccount(true);
    	} else {
    		user.setActivateAccount(false);
    	}
        return userRepository.save(user);
    }
    
    public List<User> getUserByVotingCardNumber(String userVotingCardNumber) {
        return userRepository.findByUserVotingCardNumber(userVotingCardNumber);
    }

    public List<User> getUserByMobileNumber(String userMobileNumber) {
        return userRepository.findByUserMobileNumber(userMobileNumber);
    }

    public List<User> getUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
    
    public List<User> getUserByRole(String userRole) {
        return userRepository.findByUserRole(userRole);
    }    
    
     public void deleteByUserId(long userId) {
		userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
		userRepository.deleteById(userId);
		
	} 
   
    public Optional<User> loginUserByVotingCardNumber(String userVotingCardNumber, String userPassword) {
     return userRepository.findByUserVotingCardNumberAndUserPassword(userVotingCardNumber, userPassword);
  }
    
    public Optional<User> loginUserByEmail(String userEmail, String userPassword) {
      return userRepository.findByUserEmailAndUserPassword(userEmail, userPassword);
  }
    
    /*
      public User activateUserInformation(Integer id, User user) {
		User muser = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User Id " + id + " doesn't exist!"));
		muser.setEmail(user.getEmail());
		muser.setDob(user.getDob());
		muser.setFirstName(user.getFirstName());
		muser.setGender(user.getGender());
		muser.setLastName(user.getLastName());
		muser.setAddress(user.getAddress());
		muser.setActivateAccount(user.isActivateAccount());
		User us = userRepository.save(muser);
		return activateuser(us);
	}
     */
}
