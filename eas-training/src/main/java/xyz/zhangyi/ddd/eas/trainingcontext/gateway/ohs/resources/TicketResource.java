package xyz.zhangyi.ddd.eas.trainingcontext.gateway.ohs.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.zhangyi.ddd.eas.core.ohs.Resources;
import xyz.zhangyi.ddd.eas.trainingcontext.application.NominationAppService;
import xyz.zhangyi.ddd.eas.trainingcontext.application.pl.NominationRequest;

import java.util.logging.Logger;

@RestController
@RequestMapping("/tickets")
public class TicketResource {
    private Logger logger = Logger.getLogger(TicketResource.class.getName());

    @Autowired
    private NominationAppService nominationAppService;

    @PutMapping
    public ResponseEntity<?> nominate(@RequestBody NominationRequest nominationRequest) {
        return Resources.with("nominate ticket")
                .onSuccess(HttpStatus.ACCEPTED)
                .onError(HttpStatus.BAD_REQUEST)
                .onFailed(HttpStatus.INTERNAL_SERVER_ERROR)
                .execute(() -> nominationAppService.nominate(nominationRequest));
    }
}