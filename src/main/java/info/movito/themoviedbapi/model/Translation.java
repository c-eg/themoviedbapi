package info.movito.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.NamedElement;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


public class Translation extends NamedElement {

    @JsonProperty("iso_639_1")
    private String isoCode;


    @JsonProperty("english_name")
    private String englishName;


    public String getEnglishName() {
        return englishName;
    }


    public String getIsoCode() {
        return isoCode;
    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }
}
