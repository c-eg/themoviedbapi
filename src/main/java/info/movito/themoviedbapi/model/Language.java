package info.movito.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import info.movito.themoviedbapi.model.core.AbstractJsonMapping;


@JsonRootName("spoken_language")
public class Language extends AbstractJsonMapping {


    @JsonProperty("iso_639_1")
    private String isoCode;
    @JsonProperty("name")
    private String name;


    public String getIsoCode() {
        return isoCode;
    }


    public String getName() {
        return name;
    }


    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }


    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Language other = (Language) obj;
        if ((this.isoCode == null) ? (other.isoCode != null) : !this.isoCode.equals(other.isoCode)) {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return true;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (this.isoCode != null ? this.isoCode.hashCode() : 0);
        hash = 71 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }

}
