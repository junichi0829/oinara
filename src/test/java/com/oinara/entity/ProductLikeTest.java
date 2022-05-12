package com.oinara.entity;

import com.oinara.dto.UserFormDto;
import com.oinara.repository.ProductLikeRepository;
import com.oinara.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class ProductLikeTest {

    @Autowired
    ProductLikeRepository productLikeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PersistenceContext
    EntityManager em;

    public User createUser() {
        UserFormDto userFormDto = new UserFormDto();
        userFormDto.setAccount("lee");
        userFormDto.setName("이가은");
        userFormDto.setEmail("lee@email.com");
        userFormDto.setPhoneNumber("010-1234-5678");
        userFormDto.setPassword("12345678");
        return User.createUser(userFormDto, passwordEncoder);
    }

    @Test
    @DisplayName("찜하기 엔티티 매핑 조회 테스트")
    public void findProductLikeAndUserTest() {
        User user = createUser();
        userRepository.save(user);

        ProductLike productLike = new ProductLike();
        productLike.setUser(user);
        productLikeRepository.save(productLike);

        em.flush();
        em.clear();

        ProductLike savedProductLike = productLikeRepository.findById(productLike.getId()).orElseThrow(EntityNotFoundException::new);
        assertEquals(savedProductLike.getUser().getId(), user.getId());
    }
}
