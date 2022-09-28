package com.example.api.users;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public String authorization(User user) throws NoSuchAlgorithmException {
        Optional<User> row = userRepository.findByLogin(user.getLogin());
        if (row.isPresent()) {
            User entity = row.get();

            String hash = user.getLogin() + user.getPassword();
            hash = md5(md5(hash));

            if (hash.equals(entity.getPassword())) {
                String token = UUID.randomUUID().toString();
                token = md5(token);
                if (updateToken(token, entity)) {
                    return token;
                }
            }
        }
        return null;
    }

    public String registration(User user) throws NoSuchAlgorithmException {
        String hash = user.getLogin() + user.getPassword();
        hash = md5(md5(hash));
        user.setPassword(hash);
        userRepository.save(user);
        return "";
    }

    private boolean updateToken(String token, User user) {
        Optional<User> row = userRepository.findById(user.getId());
        if (row.isPresent()) {
            User entity = row.get();
            entity.setToken(token);
            userRepository.save(entity);
            return true;
        }
        return false;
    }

    private static String md5(String hash) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] bytes = md5.digest(hash.getBytes());
        StringBuilder builder = new StringBuilder();
        for (byte element : bytes) {
            builder.append(String.format("%02X", element));
        }
        return builder.toString();
    }
}