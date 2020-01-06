package xyz.zhangyi.ddd.eas.trainingcontext.domain.candidate;

import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository {
    void remove(Candidate candidate);
}