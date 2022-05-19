package io.ssafy.ssafyland.db.repository;

import io.ssafy.ssafyland.db.entity.FilePDF;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FilePDFRepository extends JpaRepository<FilePDF, Long>, FilePDFCustomRepository {
    Optional<FilePDF> findByFileName(String fileName);
}
