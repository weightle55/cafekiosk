package sample.cafekiosk.spring.domain.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {

    INIT("주문생성"),
    CANCELED("주문취소"),
    PAYMENT_COMPLETED("주문취소"),
    PAYMENT_FAILED("주문취소"),
    RECEIVED("주문취소"),
    COMPLETED("주문취소");

    private final String text;
}
