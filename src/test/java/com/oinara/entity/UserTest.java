package com.oinara.entity;

import com.oinara.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserTest {

    @Autowired
    UserRepository userRepository;

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("Auditing 테스트")
    @WithMockUser(username = "gaeun", roles = "USER")
    public void auditingTest() {
        User newUser = new User();
        userRepository.save(newUser);

        em.flush();
        em.clear();

        User user = userRepository.findById(newUser.getId()).orElseThrow(EntityNotFoundException::new);

        System.out.println("register time : " + user.getRegTime());
        System.out.println("update time : " + user.getUpdateTime());
        System.out.println("create user : " + user.getCreatedBy());
        System.out.println("modify user : " + user.getModifiedBy());

    }
}
