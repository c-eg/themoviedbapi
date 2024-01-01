package info.movito.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.NamedElement;
import lombok.EqualsAndHashCode;

@lombok.Data
@EqualsAndHashCode(callSuper = true)
public class Translation extends NamedElement {
    @JsonProperty("iso_3166_1")
    private String countryCode;

    @JsonProperty("iso_639_1")
    private String languageCode;

    @JsonProperty("english_name")
    private String englishName;

    private Data data;

    /**
     * @deprecated use {@code getLanguageCode} as iso_3166_1 (country iso code) was added.
     */
    @Deprecated
    public String getIsoCode() {
        return getLanguageCode();
    }

    /**
     * @deprecated use {@code setLanguageCode} as iso_3166_1 (country iso code) was added.
     */
    @Deprecated
    public void setIsoCode(String isoCode) {
        setLanguageCode(isoCode);
    }
}
