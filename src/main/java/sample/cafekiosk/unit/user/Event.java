package sample.cafekiosk.unit.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class Event {
    private final String eventName;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final List<Apply> userApplies = new ArrayList<>();

    /**
     * Apply 객체를 userApplies에 추가한다.
     * @param apply
     */
    public void userApply(Apply apply) {
        userApplies.add(apply);
    }

    /**
     * User를 받아 지원을 생성한다.
     * @param user
     */
    public void userApply(User user, LocalDateTime now) {
//        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(startTime) && now.isAfter(endTime)) {
            throw new IllegalArgumentException("지원 가능한 시간이 아닙니다.");
        }
        Apply apply = Apply.of(String.format("%s%s", now.toString(), user.getUserId()), user, now);
        userApply(apply);
    }
}
