package chnu.edu.inno.controller;


/*
  @author   george
  @project   inno
  @class  ItemController
  @version  1.0.0
  @since 27/09/2026 - 19.09
*/

import chnu.edu.inno.dto.ApiResponse;
import chnu.edu.inno.dto.PageResponse;
import chnu.edu.inno.model.Item;
import chnu.edu.inno.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("api/items")
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Item>> getItemById(@PathVariable String id) {
        Item item = itemService.getItemById(id);
        return ResponseEntity.ok(ApiResponse.of(HttpStatus.OK.value(), "Товар знайдено", item));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<Item>>> getItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        PageResponse<Item> result = itemService.getItemsPage(page, size);
        return ResponseEntity.ok(ApiResponse.of(HttpStatus.OK.value(), "Список товарів отримано", result));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Item>> createItem(@RequestBody Item item) {
        Item created = itemService.createItem(item);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location)
                .body(ApiResponse.of(HttpStatus.CREATED.value(), "Товар успішно створено", created));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<Item>> updateItem(@RequestBody Item item) {
        Item updated = itemService.updateItem(item);
        return ResponseEntity.ok(ApiResponse.of(HttpStatus.OK.value(), "Товар успішно оновлено", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable String id) {
        itemService.deleteItemById(id);
        return ResponseEntity.noContent().build();
    }
}
