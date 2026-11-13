package chnu.edu.inno.service;

import chnu.edu.inno.exception.InvalidRequestException;
import chnu.edu.inno.exception.ResourceNotFoundException;
import chnu.edu.inno.model.Item;
import org.javers.core.Javers;
import org.javers.repository.jql.JqlQuery;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AuditServiceTest {

    private final Javers javers = mock(Javers.class);
    private final AuditService service = new AuditService(javers);

    @Test
    void historyOfUnknownItemIsNotFound() {
        when(javers.getLatestSnapshot("x", Item.class)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.getItemSnapshots("x"));
        assertThrows(ResourceNotFoundException.class, () -> service.getItemChanges("x"));
        assertThrows(ResourceNotFoundException.class, () -> service.getItemShadows("x"));
        verify(javers, never()).findSnapshots(any(JqlQuery.class));
    }

    @Test
    void invalidLimitIsBadRequest() {
        assertThrows(InvalidRequestException.class, () -> service.getSnapshots(null, 0));
        assertThrows(InvalidRequestException.class,
                () -> service.getSnapshots(null, AuditService.MAX_LIMIT + 1));
    }

    @Test
    void emptyAuditLogIsNotFound() {
        when(javers.findSnapshots(any(JqlQuery.class))).thenReturn(List.of());

        assertThrows(ResourceNotFoundException.class, () -> service.getSnapshots("ivan", 10));
        assertThrows(ResourceNotFoundException.class, () -> service.getDeletedSnapshots());
    }
}
