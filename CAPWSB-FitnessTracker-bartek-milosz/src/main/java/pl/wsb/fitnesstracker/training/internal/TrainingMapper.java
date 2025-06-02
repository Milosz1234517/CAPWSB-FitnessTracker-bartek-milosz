package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingDto;
import pl.wsb.fitnesstracker.user.internal.UserMapper;

/**
 * Mapper class responsible for converting between {@link Training} entities and {@link TrainingDto} data transfer objects.
 * <p>
 * This component facilitates the transformation of data between the internal domain model and the external representation
 * used for client communication, ensuring separation of concerns and encapsulation of mapping logic.
 * </p>
 */
@Component
@RequiredArgsConstructor
public class TrainingMapper {

    /**
     * Mapper for converting between user-related entities and DTOs.
     */
    private final UserMapper userMapper;

    /**
     * Converts a {@link Training} entity to a {@link TrainingDto}.
     *
     * @param training the {@link Training} entity to convert
     * @return the corresponding {@link TrainingDto} with mapped data
     */
    public TrainingDto toDto(Training training) {
        return new TrainingDto(
                training.getId(),
                userMapper.toDetailsDto(training.getUser()),
                training.getStartTime(),
                training.getEndTime(),
                training.getActivityType(),
                training.getDistance(),
                training.getAverageSpeed()
        );
    }

    /**
     * Converts a {@link TrainingDto} to a {@link Training} entity.
     *
     * @param trainingDto the {@link TrainingDto} to convert
     * @return the corresponding {@link Training} entity with mapped data
     */
    public Training toEntity(TrainingDto trainingDto) {
        return new Training(
                userMapper.toEntity(trainingDto.getUser()),
                trainingDto.getStartTime(),
                trainingDto.getEndTime(),
                trainingDto.getActivityType(),
                trainingDto.getDistance(),
                trainingDto.getAverageSpeed()
        );
    }
}