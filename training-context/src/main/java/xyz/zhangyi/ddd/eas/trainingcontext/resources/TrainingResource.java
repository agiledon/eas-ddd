package xyz.zhangyi.ddd.eas.trainingcontext.resources;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.zhangyi.ddd.eas.core.application.ApplicationException;
import xyz.zhangyi.ddd.eas.trainingcontext.application.TrainingAppService;
import xyz.zhangyi.ddd.eas.trainingcontext.application.messages.TrainingResponse;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/trainings")
public class TrainingResource {
    private Logger logger = Logger.getLogger(TrainingResource.class.getName());

    @Autowired
    private TrainingAppService trainingAppService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TrainingResponse> findBy(@PathVariable String id) {
        if (Strings.isNullOrEmpty(id)) {
            logger.log(Level.WARNING, "training id of reqeust is null or empty.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            TrainingResponse trainingResponse = trainingAppService.trainingOf(id);
            return new ResponseEntity<>(trainingResponse, HttpStatus.OK);
        } catch (ApplicationException ex) {
            logger.log(Level.SEVERE, "Exception raised findById REST Call", ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}