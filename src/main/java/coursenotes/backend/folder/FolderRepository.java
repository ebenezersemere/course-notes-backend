package coursenotes.backend.folder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository

public interface FolderRepository<T extends Folder> extends JpaRepository<T, UUID> {
}