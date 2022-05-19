package com.oinara.service;

import com.oinara.constant.ProductSellStatus;
import com.oinara.dto.ProductFormDto;
import com.oinara.entity.Product;
import com.oinara.entity.ProductImg;
import com.oinara.repository.ProductImgRepository;
import com.oinara.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class ProductServiceTest {

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductImgRepository productImgRepository;

    List<MultipartFile> createMultipartFiles() throws Exception {

        List<MultipartFile> multipartFileList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            String path = "C:/oi/product/";
            String imageName = "image" + i + ".jpg";
            MockMultipartFile multipartFile = new MockMultipartFile(path, imageName, "image/jpg", new byte[]{1, 2, 3, 4});
            multipartFileList.add(multipartFile);
        }
        return multipartFileList;
    }

    @Test
    @DisplayName("상품 등록 테스트")
    @WithMockUser(username = "admin", roles = "ADMIN")
    void saveProduct() throws Exception {
        ProductFormDto productFormDto = new ProductFormDto();
        productFormDto.setProductNm("테스트상품");
        productFormDto.setProductSellStatus(ProductSellStatus.SELL);
        productFormDto.setProductDetail("테스트 상품입니다");
        productFormDto.setPrice(1000);

        List<MultipartFile> multipartFileList = createMultipartFiles();
        Long productId = productService.saveProduct(productFormDto, multipartFileList);

        Product product = productRepository.findById(productId).orElseThrow(EntityNotFoundException::new);
        List<ProductImg> productImgList = productImgRepository.findByProductOrderByIdAsc(product);

        assertEquals(productFormDto.getProductNm(), product.getProductName());
        assertEquals(productFormDto.getProductSellStatus(), product.getProductSellStatus());
        assertEquals(productFormDto.getProductDetail(), product.getDescription());
        assertEquals(productFormDto.getPrice(), product.getPrice());
        assertEquals(multipartFileList.get(0).getOriginalFilename(), productImgList.get(0).getOriImgName());
    }
}
