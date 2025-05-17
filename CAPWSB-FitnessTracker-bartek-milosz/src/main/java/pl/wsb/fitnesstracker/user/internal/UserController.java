package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("/simple")
    public List<SimpleUserDto> getAllSimpleUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toSimpleDto)
                .toList();
    }

    @GetMapping("/email")
    public List<UserDetailsDto> getUsersByEmail(String email) {
        return userService.getUsersByEmail(email)
                .stream()
                .map(userMapper::toDetailsDto)
                .toList();
    }

    @GetMapping("/older/{time}")
    public List<UserDetailsDto> getUsersOlderThan(@PathVariable LocalDate time) {
        return userService.getUsersOlderThan(time)
                .stream()
                .map(userMapper::toDetailsDto)
                .toList();
    }

    @GetMapping(value = "/{id}")
    public UserDetailsDto getUserById(@PathVariable Long id) {
        User user = userService.getUser(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found!"));
        return userMapper.toDetailsDto(user);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto addUser(@RequestBody UserDto userDto) throws InterruptedException {
        User created = userService.createUser(userMapper.toEntity(userDto));
        return userMapper.toDto(created);
    }

    @PutMapping("/{userId}")
    public UserDetailsDto updateUser(@PathVariable Long userId, @RequestBody UserDetailsDto userDto) throws InterruptedException {
        User user = userMapper.toEntity(userDto);
        user.setId(userId);

        User updated = userService.updateUser(user);
        return userMapper.toDetailsDto(updated);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

}