package roomescape.reservation.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import roomescape.exception.InvalidNameException;
import roomescape.exception.NullPointDateException;
import roomescape.exception.PastDateReservationException;
import roomescape.exception.PastTimeReservationException;
import roomescape.theme.domain.Theme;
import roomescape.time.domain.ReservationTime;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class ReservationTest {

    private final Theme theme = new Theme(1L, "red", "It is red", "R");
    private final ReservationTime reservationTime = new ReservationTime(1L, "15:46");

    @DisplayName("이름이 null 혹은 공백인 경우 예외가 발생한다")
    @Test
    void validateNameExist() {
        assertAll(
                () -> assertThatThrownBy(() -> new Reservation(1L, null, "2024-04-30", reservationTime, theme))
                        .isInstanceOf(InvalidNameException.class),
                () -> assertThatThrownBy(() -> new Reservation(1L, "", "2024-04-30", reservationTime, theme))
                        .isInstanceOf(InvalidNameException.class),
                () -> assertThatThrownBy(() -> new Reservation(1L, " ", "2024-04-30", reservationTime, theme))
                        .isInstanceOf(InvalidNameException.class)
        );
    }

    @DisplayName("존재하지 않는 날짜를 선택했을 경우 예외가 발생한다")
    @Test
    void validateDateAndTimeExist() {
        ReservationTime reservationTime = new ReservationTime(1L, "15:46");

        assertAll(
                () -> assertThatThrownBy(() -> new Reservation(1L, "hotea", null, reservationTime, theme))
                        .isInstanceOf(NullPointDateException.class),
                () -> assertThatThrownBy(() -> new Reservation(1L, "hotea", "2024-14-30", reservationTime, theme))
                        .isInstanceOf(DateTimeParseException.class),
                () -> assertThatThrownBy(() -> new Reservation(1L, "hotea", "2024-04-50", reservationTime, theme))
                        .isInstanceOf(DateTimeParseException.class)
        );
    }

    @DisplayName("지나간 날짜에 대한 예약 생성의 경우 예외가 발생한다")
    @Test
    void validateNoReservationsForPastDates() {
        ReservationTime reservationTime = new ReservationTime(1L, "15:46");
        assertThatThrownBy(() -> new Reservation(1L, "hotea", "2022-02-12", reservationTime, theme))
                .isInstanceOf(PastDateReservationException.class);
    }

    @DisplayName("날짜가 오늘인 경우 지나간 시간에 대한 예약이 불가능하다")
    @Test
    void validateNoReservationsForPastTimesToday() {
        ReservationTime reservationTime = new ReservationTime(1L, LocalTime.MIN.toString());
        assertThatThrownBy(() -> new Reservation(1L, "hotea", LocalDate.now().toString(), reservationTime, theme))
                .isInstanceOf(PastTimeReservationException.class);
    }
}