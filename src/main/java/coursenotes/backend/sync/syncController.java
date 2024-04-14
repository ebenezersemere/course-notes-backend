package coursenotes.backend.sync;

public class syncController {
    @PostMapping("/{documentId}")
    public ResponseEntity<?> syncDocument(@PathVariable Long documentId) {
        syncService.syncDocument(documentId);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{documentId}")
    public SyncStatus checkSyncStatus(@PathVariable Long documentId) {
        return syncService.checkSyncStatus(documentId);
    }
    //get timestamp of lastupdated
    @GetMapping("/{documentId}/last-update")
    public Timestamp getLastUpdate(@PathVariable Long documentId) {
        return syncService.getLastUpdate(documentId);
    }

    @GetMapping("/check-connection")
    public ConnectionStatus checkInternetConnection() {
        return syncService.checkInternetConnection();
    }

    @GetMapping("/{documentId}/pending-changes")
    public List<Change> getPendingChanges(@PathVariable Long documentId) {
        return syncService.getPendingChanges(documentId);
    }

    @GetMapping("/{documentId}/conflicts")
    public List<Conflict> listDocumentConflicts(@PathVariable Long documentId) {
        return syncService.listDocumentConflicts(documentId);
    }

    @PostMapping("/{documentId}/resolve")
    public ResponseEntity<?> resolveConflict(@PathVariable Long documentId, @RequestBody ConflictResolution resolution) {
        syncService.resolveConflict(documentId, resolution);
        return ResponseEntity.ok().build();
    }

}
