package bitrix.repository;

import bitrix.entity.IFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFileRepository extends JpaRepository<IFile, Integer> {
}
