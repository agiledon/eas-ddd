package xyz.zhangyi.ddd.eas.trainingcontext.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.zhangyi.ddd.eas.trainingcontext.application.messages.NominationRequest;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.ticket.NominationService;

@Service
public class NominationAppService {
    @Autowired
    private NominationService nominationService;

    public void nominate(NominationRequest nominationRequest) {
        nominationService.nominate(
                nominationRequest.ticketId(),
                nominationRequest.trainingId(),
                nominationRequest.toCandidate(),
                nominationRequest.toNominator());
    }
}