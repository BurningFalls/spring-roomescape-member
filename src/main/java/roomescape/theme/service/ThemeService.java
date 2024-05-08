package roomescape.theme.service;

import org.springframework.stereotype.Service;
import roomescape.theme.domain.Theme;
import roomescape.theme.repository.ThemeRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ThemeService {
    private final ThemeRepository themeRepository;

    public ThemeService(final ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    public List<Theme> readAll() {
        return themeRepository.readAll();
    }

    public Theme create(final Theme theme) {
        final long themeId = themeRepository.create(theme);

        return themeRepository.read(themeId);
    }

    public void delete(final long id) {
        final int deleteCount = themeRepository.delete(id);

        validateDeletionOccurred(deleteCount);
    }

    public List<Theme> findPopular() {
        final LocalDate today = LocalDate.now();

        return themeRepository.findPopular(today.minusWeeks(1).toString(), today.minusDays(1).toString());
    }

    private void validateDeletionOccurred(int deleteCount) {
        if (deleteCount == 0) {
            throw new NoSuchElementException("해당하는 테마가 없습니다.");
        }
    }
}
