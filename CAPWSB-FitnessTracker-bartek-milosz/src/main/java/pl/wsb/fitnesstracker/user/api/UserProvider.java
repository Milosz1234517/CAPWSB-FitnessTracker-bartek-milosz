package pl.wsb.fitnesstracker.user.api;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserProvider {

    /**
     * Retrieves a user based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param userId id of the user to be searched
     * @return An {@link Optional} containing the located user, or {@link Optional#empty()} if not found
     */
    Optional<User> getUser(Long userId);

    /**
     * Retrieves a user based on their email.
     * If the user with given email is not found, then {@link Optional#empty()} will be returned.
     *
     * @param email The email of the user to be searched
     * @return An {@link Optional} containing the located user, or {@link Optional#empty()} if not found
     */
    Optional<User> getUserByEmail(String email);

    /**
     * Retrieves all users.
     *
     * @return An {@link Optional} containing the all users,
     */
    List<User> findAllUsers();

    /**
     * Retrieves a list of users associated with the specified email address.
     *
     * @param email the email address to search for
     * @return a list of users with the given email address, or an empty list if none are found
     */
    List<User> getUsersByEmail(String email);


    /**
     * Retrieves a list of users who are older than the specified date.
     *
     * @param date the cutoff date; users born before this date will be included
     * @return a list of users older than the given date, or an empty list if none are found
     */
    List<User> getUsersOlderThan(LocalDate date);

}
