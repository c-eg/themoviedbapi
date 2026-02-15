package info.movito.themoviedbapi.testutil;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

/**
 * Builder for the validator config.
 */
@Builder
@Getter
public class ValidatorConfig {
    private List<String> nullFieldsToIgnore;
    private List<String> emptyCollectionFieldsToIgnore;
    private List<String> nullContainingCollectionFieldsToIgnore;
    private List<String> emptyMapFieldsToIgnore;
    private List<String> nullContainingMapFieldsToIgnore;
}
