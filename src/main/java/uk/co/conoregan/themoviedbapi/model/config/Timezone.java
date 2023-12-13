package uk.co.conoregan.themoviedbapi.model.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.core.AbstractJsonMapping;

@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Timezone extends AbstractJsonMapping {
    private String name;

    private String country;
}
