package xyz.zhangyi.ddd.eas.trainingcontext.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import xyz.zhangyi.ddd.eas.core.application.ApplicationException;
import xyz.zhangyi.ddd.eas.core.domain.DomainException;
import xyz.zhangyi.ddd.eas.trainingcontext.application.pl.NominationRequest;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.ticket.NominationService;

@Service
@EnableTransactionManagement
public class NominationAppService {
    @Autowired
    private NominationService nominationService;

    @Transactional(rollbackFor = ApplicationException.class)
    public void nominate(NominationRequest nominationRequest) throws ApplicationException {
        try {
            nominationService.nominate(
                    nominationRequest.getTicketId(),
                    nominationRequest.getTrainingId(),
                    nominationRequest.toCandidate(),
                    nominationRequest.toNominator());
        } catch (DomainException ex) {
            throw new ApplicationException(ex.getMessage(), ex);
        }
    }
}