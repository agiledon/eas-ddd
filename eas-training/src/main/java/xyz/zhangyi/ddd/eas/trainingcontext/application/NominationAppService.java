package xyz.zhangyi.ddd.eas.trainingcontext.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import xyz.zhangyi.ddd.eas.core.application.exceptions.ApplicationDomainException;
import xyz.zhangyi.ddd.eas.core.application.exceptions.ApplicationException;
import xyz.zhangyi.ddd.eas.core.application.exceptions.ApplicationInfrastructureException;
import xyz.zhangyi.ddd.eas.core.application.exceptions.ApplicationValidationException;
import xyz.zhangyi.ddd.eas.core.domain.exceptions.DomainException;
import xyz.zhangyi.ddd.eas.trainingcontext.application.pl.NominationRequest;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.ticket.NominationService;

import java.util.Objects;

@Service
@EnableTransactionManagement
public class NominationAppService {
    @Autowired
    private NominationService nominationService;

    @Transactional(rollbackFor = ApplicationException.class)
    public void nominate(NominationRequest nominationRequest) {
        if (Objects.isNull(nominationRequest)) {
            throw new ApplicationValidationException("nomination request can not be null");
        }
        try {
            nominationService.nominate(
                    nominationRequest.getTicketId(),
                    nominationRequest.getTrainingId(),
                    nominationRequest.toCandidate(),
                    nominationRequest.toNominator());
        } catch (DomainException ex) {
            throw new ApplicationDomainException(ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new ApplicationInfrastructureException("Infrastructure Error", ex);
        }
    }
}