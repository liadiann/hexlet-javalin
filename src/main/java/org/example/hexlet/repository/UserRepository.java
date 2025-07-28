package org.example.hexlet.repository;

import lombok.Getter;
import org.example.hexlet.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {

    @Getter
    private static List<User> entities = new ArrayList<>();

    public static void save(User user) {
        user.setId(entities.size() + 1);
        entities.add(user);
    }

    public static Optional<User> find(Integer id) {
        return entities.stream()
                .filter(e -> e.getId().equals(id))
                .findAny();
    }

    public static void delete(Integer id) {
        entities.remove((int)id);
    }

    public static List<User> search(String term) {
        return entities.stream()
                .filter(e -> e.getName().startsWith(term))
                .toList();
    }

}
