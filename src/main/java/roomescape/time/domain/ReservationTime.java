package roomescape.time.domain;

import roomescape.exception.NullPointTimeException;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class ReservationTime {
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");

    private final Long id;
    private final LocalTime startAt;
    private final Boolean alreadyBooked;

    public ReservationTime(final Long id, final String startAt) {
        validateTimeIsNotNull(startAt);
        this.id = id;
        this.startAt = LocalTime.parse(startAt, TIME_FORMAT);
        this.alreadyBooked = null;
    }

    public ReservationTime(final Long id, final String startAt, Boolean alreadyBooked) {
        validateTimeIsNotNull(startAt);
        this.id = id;
        this.startAt = LocalTime.parse(startAt, TIME_FORMAT);
        this.alreadyBooked = alreadyBooked;
    }

    private void validateTimeIsNotNull(final String time) {
        if (Objects.isNull(time)) {
            throw new NullPointTimeException("시간인 null인 경우 저장을 할 수 없습니다.");
        }
    }

    public boolean checkPastTime() {
        return startAt.isBefore(LocalTime.now());
    }

    public Long getId() {
        return id;
    }

    public LocalTime getStartAt() {
        return startAt;
    }

    public Boolean getAlreadyBooked() {
        return alreadyBooked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationTime that = (ReservationTime) o;
        return Objects.equals(id, that.id) && Objects.equals(startAt, that.startAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startAt);
    }
}
