package onlinevoting.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import onlinevoting.entity.Election;

public interface ElectionRepository extends JpaRepository<Election, Long> {
	
	 List<Election> findByElectionName(String electionName);
	    
	 List<Election> findByElectionDate(LocalDate electionDate);
}
