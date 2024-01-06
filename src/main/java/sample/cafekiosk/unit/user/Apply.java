package sample.cafekiosk.unit.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class Apply {
    private final String applyId;
    private final User user;
    private final LocalDateTime appliedTime;
}
