package chnu.edu.inno.dto;

import chnu.edu.inno.model.Item;

import java.util.Map;

/**
 * Стан товару на момент конкретного коміту (JaVers Shadow) + метадані коміту.
 */
public record ShadowView(
        String commitId,
        String author,
        String commitDate,
        Map<String, String> commitProperties,
        Item item) {
}
