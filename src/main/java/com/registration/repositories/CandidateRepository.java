package com.registration.repositories;


import com.registration.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface CandidateRepository extends JpaRepository<Candidate,Long> {

}
