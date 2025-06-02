package pl.wsb.fitnesstracker.training.api;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.UserDetailsDto;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class TrainingDto {

    Long id;
    UserDetailsDto user;
    Date startTime;
    Date endTime;
    ActivityType activityType;
    double distance;
    double averageSpeed;
}
