package onlinevoting.entity;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Entity
@Data
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

	@NotNull(message = "Name Can Not Be Empty")
	@Size(min=3,message="person name should create atleast 3character")
	private String userName;
	
	@Column(name="VotingCardNumber",unique=true,length=30)
	@NotNull(message = "Voting Number Can Not Be Empty")
	@Size(min=10, max=10, message = "Voter Number should be exact 10 letters and it should be combination of letters and numbers")
    private String userVotingCardNumber;
	
	@NotNull(message = "Address Can Not Be Empty")
	private String userAddress;
	
	@Column(name="MobileNumber", unique=true)
	@NotNull(message = " Mobile Number Can Not Be Empty")
	@Pattern(regexp="^[6-9][0-9]{9}$")
	@Size(min=10, max=10, message ="Mobile Number Should Contains 10 Digits")
    private String userMobileNumber;
	
	@Column(name="emailid",unique=true,length=25)
	@NotEmpty
	@Email(message="Email is not valid")
	private String userEmail;
	
	@Column(name="password",length=20)
	@NotNull(message = "Password Can Not Be Empty")
	@Size(min=8,message="password length must be 8 characters and upparcase,lowercase,digit")
	@Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}")
    private String userPassword;
    
	@NotNull
    private LocalDate userDateOfBirth;
    
    @NotNull
    @Size(min=4,message="person gender should have atleast 4 characters")
    private String userGender;
    	
	@NotNull
	private String userRole;
	
	private String status;
	
	private boolean isActivateAccount;
}

/*
 * @Column(name = "isactivateaccount", length = 20)
private boolean isActivateAccount;
 */
