package pl.wsb.fitnesstracker.training.api;

import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Provider interface for retrieving and querying {@link Training} data.
 * <p>
 * Defines various read-only operations for accessing training sessions,
 * including retrieval by ID, user, completion status, and activity type.
 * </p>
 */
public interface TrainingProvider {

    /**
     * Retrieves a training session by its unique identifier.
     * <p>
     * Returns an {@link Optional} which will be empty if no training session is found for the given ID.
     * </p>
     *
     * @param trainingId the ID of the training session to retrieve
     * @return an {@link Optional} containing the found {@link Training}, or empty if not found
     */
    Optional<Training> getTraining(Long trainingId);

    /**
     * Retrieves all training sessions in the system.
     *
     * @return a list of all {@link Training} entities
     */
    List<Training> getAllTrainings();

    /**
     * Retrieves all training sessions associated with a specific user.
     *
     * @param userId the ID of the user whose training sessions are to be retrieved
     * @return a list of {@link Training} entities belonging to the specified user
     */
    List<Training> getTrainingsForUser(Long userId);

    /**
     * Retrieves all training sessions that finished after the specified date.
     *
     * @param date the date to compare with each training session's end time
     * @return a list of {@link Training} entities that ended after the given date
     */
    List<Training> getAllFinished(Date date);

    /**
     * Retrieves all training sessions that match the specified activity type.
     *
     * @param activity the {@link ActivityType} to filter training sessions by
     * @return a list of {@link Training} entities with the specified activity type
     */
    List<Training> getAllActivity(ActivityType activity);

    /**
     * Retrieves all training sessions assigned to the given user within the specified date range.
     *
     * @param userId    the ID of the user whose trainings are to be retrieved
     * @param firstDay  the start date of the range (inclusive)
     * @param lastDay   the end date of the range (inclusive)
     * @return a list of {@code Training} objects for the specified user within the given date range;
     *         the list may be empty but will never be {@code null}
     */
    List<Training> getAllTrainingsForUserInRange(Long userId, Date firstDay, Date lastDay);
}