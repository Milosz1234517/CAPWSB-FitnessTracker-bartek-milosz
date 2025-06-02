package pl.wsb.fitnesstracker.training.api;

/**
 * Service interface for handling operations related to {@link Training} entities.
 * <p>
 * Defines business logic for creating and updating training sessions.
 * </p>
 */
public interface TrainingService {

    /**
     * Creates a new training session using the provided {@link Training} entity.
     *
     * @param training the {@link Training} object containing session details to be persisted
     * @return the persisted {@link Training} entity, typically with a generated ID and other initialized fields
     */
    Training createTraining(Training training);

    /**
     * Updates an existing training session with new data.
     *
     * @param training the {@link Training} entity containing updated training session details
     * @return the updated {@link Training} entity after persistence
     */
    Training updateTraining(Training training);
}