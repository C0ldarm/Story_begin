package vn.aitest.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AiModelController {

    @Autowired
    private AiModelService service;

    @GetMapping("/ai-models")
    public List<AiModel> getAll() {
        return service.getAllModels();
    }

    @GetMapping("/ai-models/{id}")
    public AiModel getById(@PathVariable String id) {
        return service.getModelById(id).orElse(null);
    }

    @PostMapping("/ai-models")
    public AiModel create(@RequestBody AiModel model) {
        return service.saveModel(model);
    }
}