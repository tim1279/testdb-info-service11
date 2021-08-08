package com.example.demo;

import demo.entity.Quiz;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.aspectj.weaver.ast.Call;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.data.relational.core.sql.In;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RequiredArgsConstructor
class DbTestServiceApplicationTests {

    RestTemplate restTemplate = new RestTemplate();
    List<Long> listLongs = new CopyOnWriteArrayList<>();
    static EntityManager em;

    @Test
    void contextLoads() throws InterruptedException {
    }

    @Test
    public void givenInvalidInput_whenValidating_thenInvalid() throws ValidationException {
        JSONObject jsonSchema = new JSONObject(
                new JSONTokener(DbTestServiceApplicationTests.class.getResourceAsStream("/schema.json")));
        JSONObject jsonSubject = new JSONObject(
                new JSONTokener(DbTestServiceApplicationTests.class.getResourceAsStream("/product_invalid.json")));

        Schema schema = SchemaLoader.load(jsonSchema);
        schema.validate(jsonSubject);
    }

    @Test
    public void givenValidInput_whenValidating_thenValid() throws ValidationException {
        JSONObject jsonSchema = new JSONObject(
                new JSONTokener(DbTestServiceApplicationTests.class.getResourceAsStream("/schema.json")));
        JSONObject jsonSubject = new JSONObject(
                new JSONTokener(DbTestServiceApplicationTests.class.getResourceAsStream("/product_valid.json")));

        // создаем квиз
        // делаем рест запрос по id
        // проверяем поля по json схеме
        Schema schema = SchemaLoader.load(jsonSchema);
        schema.validate(jsonSubject);
    }
    @BeforeAll
    public static void shouldExistsEntityManager() {
        EntityManagerFactory efm = Persistence.createEntityManagerFactory("TimursFactory");
        em = efm.createEntityManager();
        em.getTransaction().begin();

    }

    @Test
    public void shouldPersist() {
        Quiz quiz = new Quiz();
        quiz.setActive(true);
        em.persist(quiz);
    }

    @AfterAll
    public static void close() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
        em.getEntityManagerFactory().close();
        em.close();
    }
}
