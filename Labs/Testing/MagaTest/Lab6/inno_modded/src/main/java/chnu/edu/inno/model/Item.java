package chnu.edu.inno.model;


/*
  @author   george
  @project   inno
  @class  Item
  @version  1.0.0 
  @since 27/09/2026 - 18.59
*/

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.javers.core.metamodel.annotation.TypeName;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Сутність, що аудується JaVers.
 * {@code @TypeName} - стабільна назва типу в журналі аудиту (не залежить від пакета/класу).
 * {@code @Id} від JaVers явно позначає ідентифікатор сутності для аудиту.
 */
@Document
@TypeName("Item")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Item {
    @Id
    @org.javers.core.metamodel.annotation.Id
    private String id;
    private String name;
    private String code;
    private  int quantity;

    public Item(String name, String code, int quantity) {
        this.name = name;
        this.code = code;
        this.quantity = quantity;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item item)) return false;

        // null-safe: у ще не збереженого товару id == null
        return getId() != null && getId().equals(item.getId());
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
