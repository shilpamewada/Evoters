package onlinevoting.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import onlinevoting.entity.Election;
import onlinevoting.repository.ElectionRepository;

@Service
public class ElectionService {
    @Autowired
    private ElectionRepository electionRepository;
    
    public List<Election> getAllElections() {
        return electionRepository.findAll();
    }

    public Election addElection(Election election) {
        return electionRepository.save(election);
    }
    
    public Optional<Election> getElectionById(Long electionId) {
        return electionRepository.findById(electionId);
    }

    public List<Election> getElectionByName(String electionName) {
        return electionRepository.findByElectionName(electionName);
    }

    public List<Election> getElectionByDate(LocalDate electionDate) {
        return electionRepository.findByElectionDate(electionDate);
    }

    public void deleteElectionById(Long electionId) {
        electionRepository.deleteById(electionId);
    }
}
