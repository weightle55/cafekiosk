package sample.cafekiosk.spring.domain.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static sample.cafekiosk.spring.domain.product.ProductSellingStatus.*;
import static sample.cafekiosk.spring.domain.product.ProductType.HANDMADE;

@ActiveProfiles("test")
//@SpringBootTest
@DataJpaTest //Data 관련된 Bean만 띄워줘서 SpringBootTest 보다 좀 더 가벼움
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @DisplayName("원하는 판매상태를 가진 상품들을 조회한다.")
    @Test
    void test() {
        //given
        Product product1 = createProduct(SELLING, "아메리카노", 4000, HANDMADE, "001");
        Product product2 = createProduct(HOLD, "카페라떼", 4500, HANDMADE, "002");
        Product product3 = createProduct(STOP_SELLING, "팥빙수", 7000, HANDMADE, "003");
        productRepository.saveAll(List.of(product1, product2, product3));

        //when
        List<Product> products = productRepository.findAllBySellingStatusIn(List.of(SELLING, HOLD));

        //then
        assertThat(products).hasSize(2)
                .extracting("productNumber", "name", "sellingStatus")//검증하고자 하는 필드만 추출
                .containsExactlyInAnyOrder(
                        tuple("001", "아메리카노", SELLING),
                        tuple("002", "카페라떼", HOLD)
                ); //containsExactly는 순서까지 봄, InAnyOrder는 순서 노상관
    }

    @DisplayName("원하는 판매상태를 가진 상품들을 조회한다.")
    @Test
    void findAllByProductNumberInTest() {
        //given
        Product product1 = createProduct(SELLING, "아메리카노", 4000, HANDMADE, "001");
        Product product2 = createProduct(HOLD, "카페라떼", 4500, HANDMADE, "002");
        Product product3 = createProduct(STOP_SELLING, "팥빙수", 7000, HANDMADE, "003");
        productRepository.saveAll(List.of(product1, product2, product3));

        //when
        List<Product> products = productRepository.findAllByProductNumberIn(List.of("001", "002"));

        //then
        assertThat(products).hasSize(2)
                .extracting("productNumber", "name", "sellingStatus")//검증하고자 하는 필드만 추출
                .containsExactlyInAnyOrder(
                        tuple("001", "아메리카노", SELLING),
                        tuple("002", "카페라떼", HOLD)
                ); //containsExactly는 순서까지 봄, InAnyOrder는 순서 노상관
    }

    @DisplayName("가장 마지막 저장한 상품 번호를 가져온다.")
    @Test
    void findLatestProductNumber() {
        //given
        String targetProductNumber = "003";
        Product product1 = createProduct(SELLING, "아메리카노", 4000, HANDMADE, "001");
        Product product2 = createProduct(HOLD, "카페라떼", 4500, HANDMADE, "002");
        Product product3 = createProduct(STOP_SELLING, "팥빙수", 7000, HANDMADE, "003");
        productRepository.saveAll(List.of(product1, product2, product3));

        //when
        String latestProductNumber = productRepository.findLatestProduct();

        //then
        assertThat(latestProductNumber).isEqualTo(targetProductNumber);
    }

    @DisplayName("가장 마지막 저장한 상품을 읽어올때 하나도 없을때는 null을 반환한다.")
    @Test
    void findLatestProductNumberWhenProductIsEmpty() {
        //given

        //when
        String latestProductNumber = productRepository.findLatestProduct();

        //then
        assertThat(latestProductNumber).isEqualTo(null);
    }

    private Product createProduct(ProductSellingStatus productSellingStatus, String name, int price, ProductType productType, String number) {
        return Product.builder()
                .productNumber(number)
                .type(productType)
                .sellingStatus(productSellingStatus)
                .name(name)
                .price(price)
                .build();
    }

}