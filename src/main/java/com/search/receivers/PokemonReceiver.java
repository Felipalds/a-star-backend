package com.search.receivers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.search.pokejava.Pokemon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class PokemonReceiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(PokemonReceiver.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public String getKey(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public void setKey(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }
}
