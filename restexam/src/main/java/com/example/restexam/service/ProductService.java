package com.example.restexam.service;

import com.example.restexam.domain.Product;
import com.example.restexam.dto.ProductDTO;
import com.example.restexam.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    // 1. 상품 추가
    @Transactional
    public ProductDTO createProduct(ProductDTO productDTO){
        // ProductDTO 매개변수는 ProductController로부터 ProductDTO를 갖고 들어왔기때문

        // 1번 변환 방법. DTO를 Entity로 변환
        // id는 가져올 필요없으므로 null로 설정
        Product product = new Product(null, productDTO.getName(), productDTO.getPrice());

        // 2번 변환 방법. Setter메소드 활용
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());

        // 1번 변환 방법의 단점은 생성자를 통해 받아오기때문에, 필드가 많아질 경우
        // 이 순서를 꼭 지켜주어야하는 반면 2번 변환 방법은 순서가 상관없으므로 더 나은 방법일 수 있다.

        // 실제 저장하는 메소드 (save)
        Product createProduct = productRepository.save(product);

        // 반환값으로 DTO를 새로 만들며 이 메소드에서 생성해주었던 Product 타입의 (ID, NAME, PRICE)를 전달
        //return new ProductDTO(createProduct.getId(), createProduct.getName(), createProduct.getPrice());

        // 빌더 패턴 사용 후 반환
        return ProductDTO.fromEntity(createProduct);
    }

    // 2. 상품 가져오기
    @Transactional(readOnly = true)
    public List<ProductDTO> getProducts(){
        return productRepository.findAll().stream()
                .map(ProductDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // 3. 특정 상품 조회
    @Transactional(readOnly = true)
    public ProductDTO getProductById(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("상품이 없습니다! 아이디 : " + id));
        return ProductDTO.fromEntity(product);
    }

    // 4. 상품 정보 업데이트
    @Transactional
    public ProductDTO updateProduct(ProductDTO productDTO){
        Product product = productRepository.findById(productDTO.getId())
                .orElseThrow( () -> new RuntimeException("상품이 없습니다! 아이디 : " + productDTO.getId()));

        if(productDTO.getName() != null){
            product.setName(productDTO.getName());
        }
        product.setPrice(productDTO.getPrice());

        return ProductDTO.fromEntity(product);
    }

    // 5. 상품 삭제
    @Transactional
    public void deleteProduct(Long id){
        if(!productRepository.existsById(id)){
            throw new RuntimeException("삭제할 상품이 없습니다! 아이디 : " + id);
        }
        productRepository.deleteById(id);
    }
}
