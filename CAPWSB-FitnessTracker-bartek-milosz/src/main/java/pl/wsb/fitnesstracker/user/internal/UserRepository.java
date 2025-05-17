package pl.wsb.fitnesstracker.user.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Query searching users by email address. It matches by exact match.
     *
     * @param email email of the user to search
     * @return {@link Optional} containing found user or {@link Optional#empty()} if none matched
     */
    default Optional<User> findByEmail(String email) {
        return findAll().stream()
                .filter(user -> Objects.equals(user.getEmail(), email))
                .findFirst();
    }

    /**
     * Finds all users whose email address contains the specified string, case-insensitively.
     * <p>
     * The search is performed on all users returned by {@code findAll()}.
     *
     * @param email the email substring to search for
     * @return a list of users whose email contains the given substring (case-insensitive),
     *         or an empty list if no matches are found
     */
    default List<User> findMultipleByEmail(String email) {
        return findAll()
                .stream()
                .filter(user -> user.getEmail().toLowerCase().contains(email.toLowerCase()))
                .toList();
    }

    /**
     * Finds all users who were born before the specified date.
     * <p>
     * This effectively retrieves users older than the given date based on their birthdate.
     *
     * @param date the cutoff date; users born before this date will be included
     * @return a list of users older than the given date, or an empty list if none are found
     */
    default List<User> findOlderThan(LocalDate date){
        return findAll()
                .stream()
                .filter(user -> user.getBirthdate().isBefore(date))
                .toList();
    }

}
