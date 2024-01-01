package info.movito.themoviedbapi.model.config;

import info.movito.themoviedbapi.model.core.AbstractJsonMapping;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class Timezone extends AbstractJsonMapping {
    private String name;

    private String country;

    @Override
    public String toString() {
        return name;
    }
}
