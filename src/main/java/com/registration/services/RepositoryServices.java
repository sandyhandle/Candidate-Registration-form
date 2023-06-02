package com.registration.services;

import com.registration.model.Candidate;
import com.registration.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RepositoryServices {

    @Autowired
    private CandidateRepository repository;

    public RepositoryServices(CandidateRepository repository) {
        this.repository = repository;
    }

    public void save(Candidate candidate){
        repository.save(candidate);
    }

    public Candidate getCandidate(String candidateName, String email){
        for (Candidate c: repository.findAll()){
            if (c.getCandidateName().equals(candidateName) && c.getEmail().equals(email)){
                return c;
            }
        }
        return null;
    }

    public List<Candidate> getAllCandidate(){
        return repository.findAll();
    }

    public Candidate getCandidateById(long id){
        System.out.println(id);

        for (Candidate c: repository.findAll()){
            if(c.getId() == id){
                return c;
            }
        }
        return null;
    }
}
