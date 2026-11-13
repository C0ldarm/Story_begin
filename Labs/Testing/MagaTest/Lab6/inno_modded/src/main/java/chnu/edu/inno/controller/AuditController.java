package chnu.edu.inno.controller;

import chnu.edu.inno.dto.ApiResponse;
import chnu.edu.inno.dto.ShadowView;
import chnu.edu.inno.service.AuditService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Запити до журналу аудиту JaVers.
 * Автор змін береться із заголовка X-Author у запитах на зміну товарів (POST/PUT/DELETE).
 */
@RestController
@AllArgsConstructor
@RequestMapping("api/audit")
public class AuditController {

    private final AuditService auditService;

    /** 1. Історія версій товару (snapshots). */
    @GetMapping("/items/{id}/snapshots")
    public ResponseEntity<String> itemSnapshots(@PathVariable String id) {
        return json(auditService.getItemSnapshots(id));
    }

    /** 2. Що і як змінювалось у товарі (changes). */
    @GetMapping("/items/{id}/changes")
    public ResponseEntity<String> itemChanges(@PathVariable String id) {
        return json(auditService.getItemChanges(id));
    }

    /** 3. Стани товару на момент кожного коміту (shadows). */
    @GetMapping("/items/{id}/shadows")
    public ResponseEntity<ApiResponse<List<ShadowView>>> itemShadows(@PathVariable String id) {
        List<ShadowView> shadows = auditService.getItemShadows(id);
        return ResponseEntity.ok(ApiResponse.of(HttpStatus.OK.value(),
                "Стани товару за історією змін отримано", shadows));
    }

    /** 4. Усі знімки, опційно за автором: /api/audit/snapshots?author=ivan&amp;limit=20. */
    @GetMapping("/snapshots")
    public ResponseEntity<String> snapshots(
            @RequestParam(required = false) String author,
            @RequestParam(defaultValue = "20") int limit) {
        return json(auditService.getSnapshots(author, limit));
    }

    /** 5. Видалені товари (термінальні знімки). */
    @GetMapping("/deleted")
    public ResponseEntity<String> deleted() {
        return json(auditService.getDeletedSnapshots());
    }

    private static ResponseEntity<String> json(String body) {
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(body);
    }
}
