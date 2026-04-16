package vn.aitest.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ai-models")
public class AiModelController {

    @Autowired
    private AiModelService service;

    // Create
    @PostMapping
    public AiModel create(@RequestBody AiModel model) {
        return service.saveModel(model);
    }

    // Read (all)
    @GetMapping
    public List<AiModel> getAll() {
        return service.getAllModels();
    }

    // Read (single)
    @GetMapping("/{id}")
    public ResponseEntity<AiModel> getById(@PathVariable String id) {
        return service.getModelById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<AiModel> update(@PathVariable String id, @RequestBody AiModel model) {
        return service.getModelById(id)
                .map(existing -> {
                    existing.setAiName(model.getAiName());
                    existing.setStrengths(model.getStrengths());
                    AiModel updated = service.saveModel(existing);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if (service.getModelById(id).isPresent()) {
            service.deleteModel(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}