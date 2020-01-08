package xyz.zhangyi.ddd.eas.trainingcontext.gateway.ohs.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.zhangyi.ddd.eas.core.application.ApplicationException;
import xyz.zhangyi.ddd.eas.trainingcontext.application.NominationAppService;
import xyz.zhangyi.ddd.eas.trainingcontext.application.pl.NominationRequest;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/tickets")
public class TicketResource {
    private Logger logger = Logger.getLogger(TicketResource.class.getName());

    @Autowired
    private NominationAppService nominationAppService;

    @PutMapping
    public ResponseEntity<?> nominate(@RequestBody NominationRequest nominationRequest) {
        if (Objects.isNull(nominationRequest)) {
            logger.log(Level.WARNING,"Nomination Request is Null.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            nominationAppService.nominate(nominationRequest);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (ApplicationException e) {
            logger.log(Level.SEVERE, "Exception raised by nominate REST Call.", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}