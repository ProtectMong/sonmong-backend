package poten012.sonmong.Poten403.domain.aiconsulting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import poten012.sonmong.Poten403.domain.aiconsulting.domain.Curation;

import java.util.List;

public interface AiConsultingRepository extends JpaRepository<Curation, Long> {
    List<Curation> getCurationByUserId(Long userId);
}
