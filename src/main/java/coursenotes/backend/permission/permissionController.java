package coursenotes.backend.permission;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class permissionController {
    @PostMapping
    public Permission createPermission(@RequestBody Permission newPermission) {
        return permissionService.createPermission(newPermission);
    }

    @GetMapping("/{permissionId}")
    public Permission getPermission(@PathVariable Long permissionId) {
        return permissionService.getPermission(permissionId);
    }

    @PutMapping("/{permissionId}")
    public Permission updatePermission(@PathVariable Long permissionId, @RequestBody Permission updatedPermission) {
        return permissionService.updatePermission(permissionId, updatedPermission);
    }

    @DeleteMapping("/{permissionId}")
    public ResponseEntity<?> deletePermission(@PathVariable Long permissionId) {
        permissionService.deletePermission(permissionId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<Permission> getAllPermissions() {
        return permissionService.getAllPermissions();
    }



}
