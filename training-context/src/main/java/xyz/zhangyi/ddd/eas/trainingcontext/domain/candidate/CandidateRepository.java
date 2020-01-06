package xyz.zhangyi.ddd.eas.trainingcontext.domain.candidate;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CandidateRepository {
    void remove(Candidate candidate);
}