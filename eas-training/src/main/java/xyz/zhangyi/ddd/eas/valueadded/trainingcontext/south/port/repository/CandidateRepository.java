package xyz.zhangyi.ddd.eas.valueadded.trainingcontext.south.port.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import xyz.zhangyi.ddd.core.stereotype.Port;
import xyz.zhangyi.ddd.core.stereotype.PortType;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.candidate.Candidate;

@Mapper
@Repository
@Port(PortType.Repository)
public interface CandidateRepository {
    void remove(Candidate candidate);
}