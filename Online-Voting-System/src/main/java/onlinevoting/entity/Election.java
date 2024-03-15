
package onlinevoting.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import jakarta.persistence.GenerationType;

@Entity
@Data
public class Election {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long electionId;

    @NotNull
    @Column(name="ElectionName", unique=true)
    private String electionName;
    
    @NotNull
    private LocalDate electionDate;
    
    /*
     how to add candidate who is standing for evry party
     */
    
    }

