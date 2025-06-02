package pl.wsb.fitnesstracker.training.api;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.wsb.fitnesstracker.training.internal.ActivityType;

import java.util.Date;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
public class TrainingUpdateDto {

    Optional<Long> userId;
    Optional<Date> startTime;
    Optional<Date> endTime;
    Optional<ActivityType> activityType;
    Optional<Double> distance;
    Optional<Double> averageSpeed;
}
