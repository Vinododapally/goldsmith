package com.gold.smith.users.repository;

import com.gold.smith.users.model.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileUploadRepository extends JpaRepository<FileUpload, Long> {
	Optional<FileUpload> findByName(String name);
}
