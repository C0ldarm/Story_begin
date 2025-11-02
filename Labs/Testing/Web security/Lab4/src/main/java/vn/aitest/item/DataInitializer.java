package vn.aitest.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private AiModelRepository repository;

    @Override
    public void run(String... args) throws Exception {
        // Clear existing data to avoid duplicates on restart
        repository.deleteAll();

        // Insert sample AI models
        repository.save(new AiModel("ChatGPT", "good listener"));
        repository.save(new AiModel("DeepSeek", "good in coding"));
        repository.save(new AiModel("Claude", "good with long work"));
        repository.save(new AiModel("Gemini", "no pluses"));
    }
}