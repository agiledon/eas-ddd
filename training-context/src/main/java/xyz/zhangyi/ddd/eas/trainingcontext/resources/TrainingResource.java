package xyz.zhangyi.ddd.eas.trainingcontext.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.training.TrainingRepository;

@RestController
@RequestMapping("/trainings")
public class TrainingResource {
    @Autowired
    private TrainingRepository trainingRepo;
}