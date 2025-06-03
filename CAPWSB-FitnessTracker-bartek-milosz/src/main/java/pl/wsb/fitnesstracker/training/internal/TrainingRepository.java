package pl.wsb.fitnesstracker.training.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.internal.ActivityType;

import java.util.Date;

import java.util.List;
import java.util.Objects;

/**
 * Repository interface for managing {@link Training} entities.
 * <p>
 * Extends {@link JpaRepository} to provide basic CRUD operations and includes
 * custom default methods for filtering training records based on specific criteria.
 * </p>
 */
interface TrainingRepository extends JpaRepository<Training, Long> {

    /**
     * Finds all training sessions associated with a specific user.
     *
     * @param userId the ID of the user whose training sessions should be returned
     * @return a list of {@link Training} entities belonging to the specified user
     */
    default List<Training> findTrainingsForUser(Long userId) {
        return findAll().stream()
                .filter(training -> training.getUser() != null && training.getUser().getId().equals(userId))
                .toList();
    }

    /**
     * Finds all training sessions that finished after a given date.
     *
     * @param date the date to compare with the training end time
     * @return a list of {@link Training} entities that ended after the specified date
     */
    default List<Training> findAllFinished(Date date) {
        return findAll().stream()
                .filter(training -> training.getEndTime().after(date))
                .toList();
    }

    /**
     * Finds all training sessions of a specific activity type.
     *
     * @param activity the activity type to filter by
     * @return a list of {@link Training} entities with the specified activity type
     */
    default List<Training> findAllActivity(ActivityType activity) {
        return findAll().stream()
                .filter(training -> training.getActivityType().equals(activity))
                .toList();
    }

    default List<Training> findAllForUserInRange(Long userId, Date firstDay, Date lastDay) {
        return findAll().stream()
                .filter(training -> !training.getEndTime().before(firstDay)
                        && !training.getEndTime().after(lastDay) && training.getUser().getId().equals(userId))
                .toList();
    }
}