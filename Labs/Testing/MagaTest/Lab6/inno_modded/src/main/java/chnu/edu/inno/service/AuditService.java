package chnu.edu.inno.service;

import chnu.edu.inno.dto.ShadowView;
import chnu.edu.inno.exception.InvalidRequestException;
import chnu.edu.inno.exception.ResourceNotFoundException;
import chnu.edu.inno.model.Item;
import lombok.AllArgsConstructor;
import org.javers.core.Changes;
import org.javers.core.Javers;
import org.javers.core.metamodel.object.CdoSnapshot;
import org.javers.core.metamodel.object.SnapshotType;
import org.javers.repository.jql.QueryBuilder;
import org.javers.shadow.Shadow;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 5 JQL-запитів до журналу аудиту JaVers для сутності {@link Item}.
 * Методи, що повертають String, віддають рідний JSON JaVers (його і видно на скриншотах).
 */
@Service
@AllArgsConstructor
public class AuditService {

    public static final int MAX_LIMIT = 100;

    private final Javers javers;

    /** Запит 1: усі знімки (snapshots) одного товару - повна історія версій. */
    public String getItemSnapshots(String id) {
        requireAudited(id);
        List<CdoSnapshot> snapshots = javers.findSnapshots(
                QueryBuilder.byInstanceId(id, Item.class).build());
        return javers.getJsonConverter().toJson(snapshots);
    }

    /** Запит 2: зміни (changes) одного товару - що саме і з якого значення на яке змінилось. */
    public String getItemChanges(String id) {
        requireAudited(id);
        Changes changes = javers.findChanges(
                QueryBuilder.byInstanceId(id, Item.class).build());
        return javers.getJsonConverter().toJson(changes);
    }

    /** Запит 3: shadows - відновлені об'єкти Item на момент кожного коміту. */
    public List<ShadowView> getItemShadows(String id) {
        requireAudited(id);
        List<Shadow<Item>> shadows = javers.<Item>findShadows(
                QueryBuilder.byInstanceId(id, Item.class).build());
        return shadows.stream()
                .map(shadow -> new ShadowView(
                        String.valueOf(shadow.getCommitMetadata().getId()),
                        shadow.getCommitMetadata().getAuthor(),
                        String.valueOf(shadow.getCommitMetadata().getCommitDate()),
                        shadow.getCommitMetadata().getProperties(),
                        shadow.get()))
                .toList();
    }

    /** Запит 4: знімки всіх товарів, опційно лише за автором коміту (заголовок X-Author). */
    public String getSnapshots(String author, int limit) {
        if (limit < 1 || limit > MAX_LIMIT) {
            throw new InvalidRequestException(
                    "Параметр limit має бути в межах 1.." + MAX_LIMIT + " (отримано: " + limit + ")");
        }
        QueryBuilder query = QueryBuilder.byClass(Item.class).limit(limit);
        if (author != null && !author.isBlank()) {
            query = query.byAuthor(author);
        }
        List<CdoSnapshot> snapshots = javers.findSnapshots(query.build());
        if (snapshots.isEmpty()) {
            throw new ResourceNotFoundException(author == null || author.isBlank()
                    ? "Журнал аудиту порожній"
                    : "Записів аудиту від автора '" + author + "' не знайдено");
        }
        return javers.getJsonConverter().toJson(snapshots);
    }

    /** Запит 5: видалені товари - термінальні (TERMINATION) знімки. */
    public String getDeletedSnapshots() {
        List<CdoSnapshot> snapshots = javers.findSnapshots(
                QueryBuilder.byClass(Item.class)
                        .withSnapshotType(SnapshotType.TERMINAL)
                        .build());
        if (snapshots.isEmpty()) {
            throw new ResourceNotFoundException("Видалених товарів у журналі аудиту немає");
        }
        return javers.getJsonConverter().toJson(snapshots);
    }

    /** 404, якщо для id немає жодного запису аудиту (товар працює і після видалення - історія лишається). */
    private void requireAudited(String id) {
        if (javers.getLatestSnapshot(id, Item.class).isEmpty()) {
            throw new ResourceNotFoundException("Для товару з id=" + id + " немає записів аудиту");
        }
    }
}
