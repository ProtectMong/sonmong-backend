package poten012.sonmong.Poten403.domain.aiconsulting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import poten012.sonmong.Poten403.domain.aiconsulting.domain.Curation;

import java.util.List;

public interface CurationRepository extends JpaRepository<Curation, Long> {
    Curation getCurationById(Long curationId);
    List<Curation> getCurationByUserId(Long userId);
}
