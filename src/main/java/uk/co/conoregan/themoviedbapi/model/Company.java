package uk.co.conoregan.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.conoregan.themoviedbapi.model.core.NamedIdElement;

@Data
@EqualsAndHashCode(callSuper = true)
public class Company extends NamedIdElement {
    @JsonProperty("description")
    private String description;

    @JsonProperty("headquarters")
    private String headquarters;

    @JsonProperty("homepage")
    private String homepage;

    @JsonProperty("logo_path")
    private String logoPath;

    @JsonProperty("parent_company")
    private Company parentCompany;

    /**
     * Sets the parent company.
     */
    public void setParentCompany(int id, String name, String logoPath) {
        Company parent = new Company();
        parent.setId(id);
        parent.setName(name);
        parent.setLogoPath(logoPath);

        this.parentCompany = parent;
    }
}
