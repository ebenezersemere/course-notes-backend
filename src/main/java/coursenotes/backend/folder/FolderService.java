package coursenotes.backend.folder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FolderService<T extends Folder> {

    private final FolderRepository<T> folderRepository;

    @Autowired
    public FolderService(FolderRepository<T> folderRepository) {
        this.folderRepository = folderRepository;
    }

    // Implement your service methods here using folderRepository
}

