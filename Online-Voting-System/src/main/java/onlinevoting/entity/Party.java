package onlinevoting.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Party {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partyId;

    @NotNull(message = "Please Give Unique Party Name")
    @Column(name="PartyName", unique=true)
    private String partyName;
    
    @NotNull(message = "Please Insert Party Logo")
    @Column(name="PartyLogo", unique=true)
    private String partyLogo;
    
    private String partyLeaderName;
    
    private int votes;

  
}

