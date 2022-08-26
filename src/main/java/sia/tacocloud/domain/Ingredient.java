package sia.tacocloud.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access= AccessLevel.PROTECTED, force=true)
@Entity
public class Ingredient {
    @Id
    private final String id;
    private final String name;
    private final Type type;

//    protected Ingredient() {
//        this.id = null;
//        this.name = null;
//        this.type = null;
//    }

    public static enum Type{
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}

