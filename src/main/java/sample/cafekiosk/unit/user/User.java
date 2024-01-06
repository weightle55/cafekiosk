package sample.cafekiosk.unit.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class User {
    private final String userId;
}
