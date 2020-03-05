package xyz.zhangyi.ddd.eas.trainingcontext.acl.ports.repositories;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.candidate.Candidate;

@Mapper
@Repository
public interface CandidateRepository {
    void remove(Candidate candidate);
}