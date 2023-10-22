package org.example.db;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class DBEntityManager {
    private static EntityManager entityManager;

    private DBEntityManager() {
    }

    public static EntityManager getInstance() {
        if (entityManager == null) {
            entityManager = Persistence.createEntityManagerFactory("default").createEntityManager();
        }
        return entityManager;
    }
}
