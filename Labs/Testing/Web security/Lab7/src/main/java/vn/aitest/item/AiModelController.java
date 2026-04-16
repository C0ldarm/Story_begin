package vn.aitest.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/ai-models")
public class AiModelController {

    @Autowired private AiModelService service;

    // READ ALL – USER+
    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'PUBLISHER', 'ADMIN')")
    public List<AiModel> getAll() { return service.getAllModels(); }

    // READ ONE – USER+
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'PUBLISHER', 'ADMIN')")
    public ResponseEntity<AiModel> getById(@PathVariable String id) {
        return service.getModelById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // CREATE – PUBLISHER+
    @PostMapping
    @PreAuthorize("hasAnyRole('PUBLISHER', 'ADMIN')")
    public AiModel create(@RequestBody AiModel model) {
        return service.saveModel(model);
    }

    // UPDATE – ADMIN only
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AiModel> update(@PathVariable String id, @RequestBody AiModel model) {
        return service.getModelById(id)
                .map(existing -> {
                    existing.setAiName(model.getAiName());
                    existing.setStrengths(model.getStrengths());
                    return ResponseEntity.ok(service.saveModel(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE – ADMIN only
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        return service.getModelById(id)
                .map(m -> { service.deleteModel(id); return ResponseEntity.<Void>ok(null); })
                .orElse(ResponseEntity.notFound().build());
    }
}