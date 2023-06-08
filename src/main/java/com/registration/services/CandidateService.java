package com.registration.services;

import com.registration.model.Candidate;
import com.registration.repositories.CandidateRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CandidateService {

    @Autowired
    private CandidateRepository repository;

    public void save(Candidate candidate) {
        repository.save(candidate);
    }

    public Candidate getCandidateByNameAndEmail(String candidateName, String email) {
        for (Candidate c : repository.findAll()) {
            if (c.getCandidateName().equals(candidateName) && c.getEmail().equals(email)) {
                return c;
            }
        }
        return null;
    }

    public List<Candidate> getAllCandidates() {
        return repository.findAll();
    }

    public Candidate getCandidateById(long id) {
        System.out.println(id);

        for (Candidate c : repository.findAll()) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }
}
