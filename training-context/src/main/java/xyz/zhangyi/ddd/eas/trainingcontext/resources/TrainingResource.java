package xyz.zhangyi.ddd.eas.trainingcontext.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.training.Training;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.training.TrainingId;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.training.TrainingRepository;

import java.util.Optional;

@RestController
@RequestMapping("/trainings")
public class TrainingResource {
    @Autowired
    private TrainingRepository trainingRepo;

    @GetMapping(value = "/{id}")
    public String getTrainingTitle(@PathVariable String id) {
        Optional<Training> training = trainingRepo.trainingOf(TrainingId.from(id));
        return training.get().title();
    }
}