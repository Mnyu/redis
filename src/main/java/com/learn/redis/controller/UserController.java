package com.learn.redis.controller;

import com.learn.redis.model.User;
import com.learn.redis.repository.UserRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserRepository userRepository;

  @PostMapping
  public User createUser(@RequestBody User user) {
    user.setUserId(UUID.randomUUID().toString());
    return userRepository.save(user);
  }

  @GetMapping("/{userId}")
  public User getUser(@PathVariable String userId) {
    return userRepository.get(userId);
  }

  @GetMapping
  public List<User> getAll() {
    return userRepository.findAll().values().stream().map(value -> (User) value).collect(Collectors.toList());
  }

  @DeleteMapping("/{userId}")
  public void deleteUser(@PathVariable String userId) {
    userRepository.delete(userId);
  }
}
