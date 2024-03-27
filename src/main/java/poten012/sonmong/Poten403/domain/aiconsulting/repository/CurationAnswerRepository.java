package poten012.sonmong.Poten403.domain.aiconsulting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import poten012.sonmong.Poten403.domain.aiconsulting.domain.CurationAnswer;

public interface CurationAnswerRepository extends JpaRepository<CurationAnswer, Long> {
    CurationAnswer getCurationAnswerById(Long curationAnswerId);
    CurationAnswer getCurationAnswerByCurationId(Long curationId);
}
