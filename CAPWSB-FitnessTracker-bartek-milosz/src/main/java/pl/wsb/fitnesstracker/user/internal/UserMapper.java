package pl.wsb.fitnesstracker.user.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.user.api.User;

@Component
class UserMapper {

    UserDto toDto(User user) {
        return new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail());
    }

    UserDetailsDto toDetailsDto(User user) {
        return new UserDetailsDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail());
    }

    SimpleUserDto toSimpleDto(User user) {
        return new SimpleUserDto(user.getId(), user.getFirstName(), user.getLastName());
    }

    User toEntity(UserDto userDto) {
        return new User(
                userDto.firstName(),
                userDto.lastName(),
                userDto.birthdate(),
                userDto.email());
    }

    User toEntity(UserDetailsDto userDto) {
        return new User(
                userDto.id(),
                userDto.firstName(),
                userDto.lastName(),
                userDto.birthdate(),
                userDto.email());
    }

}
