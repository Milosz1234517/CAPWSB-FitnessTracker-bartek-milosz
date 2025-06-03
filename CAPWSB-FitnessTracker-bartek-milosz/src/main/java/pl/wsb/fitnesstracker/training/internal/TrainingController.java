package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.training.api.*;
import pl.wsb.fitnesstracker.user.api.UserNotFoundException;
import pl.wsb.fitnesstracker.user.api.UserProvider;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

@RestController
@RequestMapping("/v1/trainings")
@RequiredArgsConstructor
class TrainingController {

    private final TrainingServiceImpl trainingService;

    private final UserProvider userService;

    private final TrainingMapper trainingMapper;

    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return trainingService.getAllTrainings()
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @GetMapping("/{userId}")
    public List<TrainingDto> getTrainingsForUser(@PathVariable Long userId) {
        return trainingService.getTrainingsForUser(userId)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @GetMapping("/finished/{afterTime}")
    public List<TrainingDto> getAllFinished(@PathVariable String afterTime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

        return trainingService.getAllFinished(sdf.parse(afterTime))
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @GetMapping("/activityType")
    public List<TrainingDto> getAllActivity(@RequestParam ActivityType activityType) throws ParseException {
        return trainingService.getAllActivity(activityType)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrainingDto addTraining(@RequestBody TrainingCreateDto trainingCreateDto) {
        Training training = new Training(
                userService.getUser(trainingCreateDto.getUserId()).orElseThrow(() -> new UserNotFoundException(trainingCreateDto.getUserId())),
                trainingCreateDto.getStartTime(),
                trainingCreateDto.getEndTime(),
                trainingCreateDto.getActivityType(),
                trainingCreateDto.getDistance(),
                trainingCreateDto.getAverageSpeed());
        return trainingMapper.toDto(trainingService.createTraining(training));
    }

    @PutMapping("/{trainingId}")
    public TrainingDto updateTraining(@PathVariable Long trainingId, @RequestBody TrainingUpdateDto trainingUpdateDto) {
        Training training = trainingService.getTraining(trainingId)
                .orElseThrow(() -> new TrainingNotFoundException(trainingId));

        if(trainingUpdateDto.getUserId().isPresent())
            training.setUser(
                    userService.getUser(trainingUpdateDto.getUserId().get())
                            .orElseThrow(() -> new UserNotFoundException(trainingUpdateDto.getUserId().get()))
            );

        if(trainingUpdateDto.getStartTime().isPresent())
            training.setStartTime(trainingUpdateDto.getStartTime().get());

        if(trainingUpdateDto.getEndTime().isPresent())
            training.setEndTime(trainingUpdateDto.getEndTime().get());

        if(trainingUpdateDto.getActivityType().isPresent())
            training.setActivityType(trainingUpdateDto.getActivityType().get());

        if(trainingUpdateDto.getDistance().isPresent())
            training.setDistance(trainingUpdateDto.getDistance().get());

        if(trainingUpdateDto.getAverageSpeed().isPresent())
            training.setAverageSpeed(trainingUpdateDto.getAverageSpeed().get());

        return trainingMapper.toDto(trainingService.updateTraining(training));
    }

}
