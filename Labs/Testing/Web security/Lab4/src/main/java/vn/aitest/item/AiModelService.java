package vn.aitest.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AiModelService {

    @Autowired
    private AiModelRepository repository;

    public List<AiModel> getAllModels() {
        return repository.findAll();
    }

    public Optional<AiModel> getModelById(String id) {
        return repository.findById(id);
    }

    public AiModel saveModel(AiModel model) {
        return repository.save(model);
    }

    public void deleteModel(String id) {
        repository.deleteById(id);
    }
}