package pl.wsb.fitnesstracker.training.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.training.internal.ActivityType;

import java.util.Date;
import java.util.List;
import java.util.Optional;

// TODO: Provide Implementation and correct the return type of the method getTraining
@Service
@RequiredArgsConstructor
@Slf4j
public class TrainingServiceImpl implements TrainingProvider, TrainingService {

    private final TrainingRepository trainingRepository;

    @Override
    public Optional<Training> getTraining(final Long trainingId) {
        return trainingRepository.findById(trainingId);
    }

    @Override
    public List<Training> getAllTrainings() {
        return trainingRepository.findAll();
    }

    @Override
    public List<Training> getTrainingsForUser(Long userId) {
        return trainingRepository.findTrainingsForUser(userId);
    }

    @Override
    public List<Training> getAllFinished(Date date) {
        return trainingRepository.findAllFinished(date);
    }

    @Override
    public List<Training> getAllActivity(ActivityType activity) {
        return trainingRepository.findAllActivity(activity);
    }

    @Override
    public Training createTraining(Training training) {
        log.info("Creating Training {}", training);

        if (training.getId() != null) {
            throw new IllegalArgumentException("Training has already DB ID, update is not permitted!");
        }

        return trainingRepository.save(training);
    }

    @Override
    public Training updateTraining(Training training) {
        log.info("Updating Training {}", training);

        if (training.getId() == null)
            throw new IllegalArgumentException("Training ID cannot be empty!");

        return trainingRepository.save(training);
    }

}
