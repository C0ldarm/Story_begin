package chnu.edu.inno.service;


/*
  @author   george
  @project   inno
  @class  ItemService
  @version  1.0.0
  @since 27/09/2026 - 19.05
*/

import chnu.edu.inno.dto.PageResponse;
import chnu.edu.inno.exception.EmptyListException;
import chnu.edu.inno.exception.InvalidRequestException;
import chnu.edu.inno.exception.PageNotFoundException;
import chnu.edu.inno.exception.ResourceNotFoundException;
import chnu.edu.inno.model.Item;
import chnu.edu.inno.repository.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ItemService {

    public static final int MAX_PAGE_SIZE = 100;

    private final ItemRepository itemRepository;

    @PostConstruct
    public void init() {
     // Заповнюємо лише порожню базу: інакше кожен перезапуск дублює товари
     // і засмічує журнал аудиту новими INITIAL-записами.
     if (itemRepository.count() > 0) {
         return;
     }
     List<Item> items = List.of(
             new Item("Milk", "000001", 1),
             new Item("Bread", "00002", 2),
             new Item("Sugar", "00003", 3)
     );
     itemRepository.saveAll(items);
    }

    /**
     * Повертає сторінку товарів (нумерація сторінок з 0).
     *
     * @throws InvalidRequestException якщо page &lt; 0, size &lt; 1 або size &gt; {@link #MAX_PAGE_SIZE}
     * @throws EmptyListException      якщо в базі немає жодного товару
     * @throws PageNotFoundException   якщо запитано сторінку за межами діапазону
     */
    public PageResponse<Item> getItemsPage(int page, int size) {
        if (page < 0) {
            throw new InvalidRequestException(
                    "Номер сторінки не може бути від'ємним (отримано: " + page + ")");
        }
        if (size < 1) {
            throw new InvalidRequestException(
                    "Розмір сторінки має бути додатним числом (отримано: " + size + ")");
        }
        if (size > MAX_PAGE_SIZE) {
            throw new InvalidRequestException(
                    "Розмір сторінки не може перевищувати " + MAX_PAGE_SIZE + " (отримано: " + size + ")");
        }

        Page<Item> result = itemRepository.findAll(PageRequest.of(page, size));

        if (result.getTotalElements() == 0) {
            throw new EmptyListException("Список товарів порожній");
        }
        if (page >= result.getTotalPages()) {
            throw new PageNotFoundException(page, result.getTotalPages());
        }
        return PageResponse.from(result);
    }

    public Item getItemById(String id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Товар з id=" + id + " не знайдено"));
    }

    public Item createItem(Item item) {
        validate(item);
        item.setId(null); // id генерує база, клієнтський id ігноруємо
        return itemRepository.save(item);
    }

    public void deleteItemById(String id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Товар з id=" + id + " не знайдено, видаляти нічого"));
        // JaVers: видаляємо цілим об'єктом, а не deleteById (той кидає виняток при аудиті)
        itemRepository.delete(item);
    }

    public Item updateItem(Item item) {
        validate(item);
        if (item.getId() == null || item.getId().isBlank()) {
            throw new InvalidRequestException("Для оновлення потрібно вказати id товару");
        }
        if (!itemRepository.existsById(item.getId())) {
            throw new ResourceNotFoundException(
                    "Товар з id=" + item.getId() + " не знайдено, оновлювати нічого");
        }
        return itemRepository.save(item);
    }

    /** Перевірка обов'язкових полів: name, code та quantity (не від'ємна). */
    private void validate(Item item) {
        List<String> errors = new ArrayList<>();
        if (item.getName() == null || item.getName().isBlank()) {
            errors.add("Поле 'name' є обов'язковим");
        }
        if (item.getCode() == null || item.getCode().isBlank()) {
            errors.add("Поле 'code' є обов'язковим");
        }
        if (item.getQuantity() < 0) {
            errors.add("Поле 'quantity' не може бути від'ємним (отримано: " + item.getQuantity() + ")");
        }
        if (!errors.isEmpty()) {
            throw new InvalidRequestException("Некоректні дані товару", errors);
        }
    }

}
