package roomescape.time.service;

import org.springframework.stereotype.Service;
import roomescape.time.domain.ReservationTime;
import roomescape.time.dto.ReservationTimeStatusDto;
import roomescape.time.repository.ReservationTimeRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ReservationTimeService {
    private final ReservationTimeRepository reservationTimeRepository;

    public ReservationTimeService(final ReservationTimeRepository reservationTimeRepository) {
        this.reservationTimeRepository = reservationTimeRepository;
    }

    public List<ReservationTime> readAll() {
        return reservationTimeRepository.readAll();
    }

    public ReservationTime create(final ReservationTime reservationTime) {
        final long timeId = reservationTimeRepository.create(reservationTime);

        return reservationTimeRepository.find(timeId);
    }

    public void delete(final long id) {
        final int deleteCount = reservationTimeRepository.delete(id);

        validateDeletionOccurred(deleteCount);
    }

    public List<ReservationTimeStatusDto> findAvailableTime(final String date, final long themeId) {
        return reservationTimeRepository.findAvailableTime(date, themeId);
    }

    private static void validateDeletionOccurred(int deleteCount) {
        if (deleteCount == 0) {
            throw new NoSuchElementException("해당하는 시간이 없습니다.");
        }
    }
}
