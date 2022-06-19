package com.oinara.controller;

import com.oinara.dto.LikeProductDto;
import com.oinara.dto.ProductLikeDetailDto;
import com.oinara.service.ProductLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductLikeController {

    private final ProductLikeService productLikeService;

    @PostMapping(value = "/productLike")
    public @ResponseBody
    ResponseEntity order(@RequestBody @Valid LikeProductDto likeProductDto, BindingResult bindingResult, Principal principal) {

        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                sb.append(fieldError.getDefaultMessage());
            }
            return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
        }
        String account = principal.getName();
        Long likeProductId;

        try {
            likeProductId = productLikeService.addProductLike(likeProductDto, account);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Long>(likeProductId, HttpStatus.OK);
    }

    @GetMapping(value = "/productLike")
    public String orderHist(Principal principal, Model model) {
        List<ProductLikeDetailDto> productLikeDetailList = productLikeService.getProductLikeList(principal.getName());
        model.addAttribute("likeProducts", productLikeDetailList);
        return "productLike/productLikeList";
    }

    @DeleteMapping(value = "/likeProduct/{likeProductId}")
    public @ResponseBody
    ResponseEntity deleteLikeProduct(@PathVariable("likeProductId") Long likeProductId, Principal principal) {

        if (!productLikeService.validateLikeProduct(likeProductId, principal.getName())) {
            return new ResponseEntity<String>("수정 권한이 없습니다", HttpStatus.FORBIDDEN);
        }

        productLikeService.deleteLikeProduct(likeProductId);
        return new ResponseEntity<Long>(likeProductId, HttpStatus.OK);
    }
}
