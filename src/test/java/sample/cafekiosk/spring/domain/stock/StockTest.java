package sample.cafekiosk.spring.domain.stock;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StockTest {

    @DisplayName("재고의 수량이 제공된 수량보다 적은지 확인한다.")
    @Test
    void isQuantityLessThan() {
        //given
        Stock stock = Stock.create("001", 1);
        int quantity = 2;

        //when
        boolean quantityLessThan = stock.isQuantityLessThan(quantity);

        //then
        assertThat(quantityLessThan).isTrue();
    }

    @DisplayName("재고를 주어진 갯수만큼 차감할 수 있다.")
    @Test
    void deductQuantity() {
        //given
        Stock stock = Stock.create("001", 1);
        int quantity = 1;

        //when
        stock.deductQuantity(quantity);

        //then
        assertThat(stock.getQuantity()).isZero();
    }

    @DisplayName("재고보다 많은 수를 차감하려면 예외가 발생한다.")
    @Test
    void deductQuantity2() {
        //given
        Stock stock = Stock.create("001", 1);
        int quantity = 2;

        //when //then
        assertThatThrownBy(() -> stock.deductQuantity(quantity))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("차감할 재고 수량이 없습니다.");
    }
}