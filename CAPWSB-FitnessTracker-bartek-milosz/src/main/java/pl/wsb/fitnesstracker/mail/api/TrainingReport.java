package pl.wsb.fitnesstracker.mail.api;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingProvider;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserProvider;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
@EnableAsync
@EnableScheduling
public class TrainingReport {

    private final EmailSender emailSender;

    private final UserProvider userProvider;

    private final TrainingProvider trainingProvider;

    @Scheduled(cron = "0 0 0 1 * ?") // 1 month
//    @Scheduled(cron = "0 * * * * *") // 1 minute
    public void generateTrainingReportMonthly() {

        LocalDate monthFirstDay = LocalDate.now().minusMonths(1).withDayOfMonth(1);
        LocalDate monthLastDay = LocalDate.now().withDayOfMonth(1).minusDays(1);

        String subject = "Training Report";
        String content = "Your monthly training count: ";

        userProvider.findAllUsers().forEach(user -> {
            List<Training> trainings = trainingProvider.getAllTrainingsForUserInRange(
                    user.getId(),
                    toDate(monthFirstDay),
                    toDate(monthLastDay)
            );

            String contentFinal = content + trainings.size();

            emailSender.send(
                    new EmailDto(user.getEmail(), subject, contentFinal)
            );
        });
    }

    private Date toDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
