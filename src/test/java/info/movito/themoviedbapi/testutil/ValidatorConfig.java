package info.movito.themoviedbapi.testutil;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

/**
 * Validation configuration to customise how {@link AbstractJsonMappingValidator} validates.
 */
@Builder
@Getter
public class ValidatorConfig {
    @Builder.Default
    private List<String> nullFieldsToIgnore = List.of();
    @Builder.Default
    private List<String> emptyCollectionFieldsToIgnore = List.of();
    @Builder.Default
    private List<String> nullContainingCollectionFieldsToIgnore = List.of();
    @Builder.Default
    private List<String> emptyMapFieldsToIgnore = List.of();
    @Builder.Default
    private List<String> nullContainingMapFieldsToIgnore = List.of();
}
