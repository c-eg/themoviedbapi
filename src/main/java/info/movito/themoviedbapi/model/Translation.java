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


    private Data data;


    public String getEnglishName() {
        return englishName;
    }


    public String getIsoCode() {
        return isoCode;
    }


    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }


    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }


    public Data getData() {
        return data;
    }


    public void setData(Data data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }
}
