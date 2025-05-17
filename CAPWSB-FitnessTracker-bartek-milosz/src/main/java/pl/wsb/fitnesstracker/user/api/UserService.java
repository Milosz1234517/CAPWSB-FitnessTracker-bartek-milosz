package pl.wsb.fitnesstracker.user.api;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {

    /**
     * Creates a new user in the system.
     *
     * @param user the user object to be created
     * @return the created user, typically with a generated ID and any system-defined fields populated
     * @throws IllegalArgumentException if the user is null or contains invalid data
     */
    User createUser(User user);

    /**
     * Updates an existing user in the system.
     *
     * @param user the user object containing updated information; must include a valid ID
     * @return the updated user object
     * @throws NoSuchElementException if no user with the specified ID exists
     * @throws IllegalArgumentException if the user is null or contains invalid data
     */
    User updateUser(User user);

    /**
     * Deletes the user with the specified ID.
     *
     * @param id the unique identifier of the user to delete
     * @throws NoSuchElementException if no user with the given ID exists
     */
    void deleteUser(Long id);

}
