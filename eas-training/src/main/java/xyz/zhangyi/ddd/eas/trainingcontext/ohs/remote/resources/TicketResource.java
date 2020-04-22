package xyz.zhangyi.ddd.eas.trainingcontext.ohs.remote.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.zhangyi.ddd.eas.core.gateway.ohs.Resources;
import xyz.zhangyi.ddd.eas.trainingcontext.ohs.local.appservices.NominationAppService;
import xyz.zhangyi.ddd.eas.trainingcontext.ohs.local.pl.NominatingCandidateRequest;

import java.util.logging.Logger;

@RestController
@RequestMapping("/tickets")
public class TicketResource {
    private Logger logger = Logger.getLogger(TicketResource.class.getName());

    @Autowired
    private NominationAppService nominationAppService;

    @PutMapping
    public ResponseEntity<?> nominate(@RequestBody NominatingCandidateRequest nominatingCandidateRequest) {
        return Resources.with("nominate ticket")
                .onSuccess(HttpStatus.ACCEPTED)
                .onError(HttpStatus.BAD_REQUEST)
                .onFailed(HttpStatus.INTERNAL_SERVER_ERROR)
                .execute(() -> nominationAppService.nominate(nominatingCandidateRequest));
    }
}