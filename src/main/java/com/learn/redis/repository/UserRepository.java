package com.learn.redis.repository;

import com.learn.redis.model.User;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {

  private static final String KEY = "USER";
  private final RedisTemplate<String, Object> redisTemplate;

  public User save(User user) {
    redisTemplate.opsForHash().put(KEY, user.getUserId(), user);
    return user;
  }

  public User get(String userId) {
    return (User) redisTemplate.opsForHash().get(KEY, userId);
  }

  public Map<Object, Object> findAll() {
    return redisTemplate.opsForHash().entries(KEY);
  }

  public void delete(String userId) {
    redisTemplate.opsForHash().delete(KEY, userId);
  }
}
