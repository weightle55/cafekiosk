package sample.cafekiosk.unit.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @DisplayName("지원가능한 시간(AM 11:00:00 ~ PM 09:00:00)에 이벤트에 응모하여, 정상 응모 된다.")
    @Test
    public void userApplyTestByUser() {
        //given
        Event event = Event.of("테스트이벤트",
                LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0, 0)),
                LocalDateTime.of(LocalDate.now(), LocalTime.of(21, 0, 0))
        );
        User user = User.of("testUser");
        //now를 로직 외부에서 설정
        LocalDateTime now = LocalDateTime.now();
        
        //when
        event.userApply(user, now);

        //then
        Assertions.assertThat(event.getUserApplies().size()).isEqualTo(1);
        Assertions.assertThat(event.getUserApplies().get(0).getUser().getUserId()).isEqualTo("testUser");
        //Assertions.assertThat(event.getUserApplies().get(0).getAppliedTime()).isEqualTo(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        Assertions.assertThat(event.getUserApplies().get(0).getAppliedTime()).isEqualTo(now);
    }
}