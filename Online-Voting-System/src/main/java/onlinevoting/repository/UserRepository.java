package onlinevoting.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import onlinevoting.entity.Party;
import onlinevoting.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByUserVotingCardNumber(String userVotingCardNumber);

	List<User> findByUserMobileNumber(String userMobileNumber);

	List<User> findByUserName(String userName);

	List<User> findByUserRole(String userRole);

	Optional<User> findByUserEmailAndUserPassword(String userEmail, String userPassword);

	Optional<User> findByUserVotingCardNumberAndUserPassword(String userVotingCardNumber, String userPassword);
}

