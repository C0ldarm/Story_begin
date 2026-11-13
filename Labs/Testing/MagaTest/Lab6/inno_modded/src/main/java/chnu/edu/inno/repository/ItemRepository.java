package chnu.edu.inno.repository;


/*
  @author   george
  @project   inno
  @class  ItemRepository
  @version  1.0.0 
  @since 27/09/2026 - 19.04
*/

import chnu.edu.inno.model.Item;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Кожне save/saveAll/delete(entity) через цей репозиторій автоматично потрапляє в аудит JaVers.
 * Увага: deleteById аудитор не підтримує (кидає виняток) - видаляти потрібно лише об'єктом.
 */
@Repository
@JaversSpringDataAuditable
public interface ItemRepository extends MongoRepository<Item, String> {
}
