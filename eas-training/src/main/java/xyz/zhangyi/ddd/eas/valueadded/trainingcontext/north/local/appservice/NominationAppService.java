package xyz.zhangyi.ddd.eas.valueadded.trainingcontext.north.local.appservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import xyz.zhangyi.ddd.core.exception.ApplicationDomainException;
import xyz.zhangyi.ddd.core.exception.ApplicationException;
import xyz.zhangyi.ddd.core.exception.ApplicationInfrastructureException;
import xyz.zhangyi.ddd.core.exception.ApplicationValidationException;
import xyz.zhangyi.ddd.core.exception.DomainException;
import xyz.zhangyi.ddd.core.stereotype.Local;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.message.NominatingCandidateRequest;
import xyz.zhangyi.ddd.eas.valueadded.trainingcontext.domain.ticket.NominationService;

import java.util.Objects;

@Service
@EnableTransactionManagement
@Local
public class NominationAppService {
    @Autowired
    private NominationService nominationService;

    @Transactional(rollbackFor = ApplicationException.class)
    public void nominate(NominatingCandidateRequest nominatingCandidateRequest) {
        if (Objects.isNull(nominatingCandidateRequest)) {
            throw new ApplicationValidationException("nomination request can not be null");
        }
        try {
            nominationService.nominate(
                    nominatingCandidateRequest.getTicketId(),
                    nominatingCandidateRequest.getTrainingId(),
                    nominatingCandidateRequest.toCandidate(),
                    nominatingCandidateRequest.toNominator());
        } catch (DomainException ex) {
            throw new ApplicationDomainException(ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new ApplicationInfrastructureException("Infrastructure Error", ex);
        }
    }
}