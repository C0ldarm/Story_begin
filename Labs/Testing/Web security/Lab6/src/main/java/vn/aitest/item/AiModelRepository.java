package vn.aitest.item;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AiModelRepository extends MongoRepository<AiModel, String> {
}