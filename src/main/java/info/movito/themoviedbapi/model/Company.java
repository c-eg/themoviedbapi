package info.movito.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.NamedIdElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Company extends NamedIdElement {
    @JsonProperty("description")
    private String description;

    @JsonProperty("headquarters")
    private String headquarters;

    @JsonProperty("homepage")
    private String homepage;

    @JsonProperty("logo_path")
    private String logoPath;

    // TODO: is this field still supported? We need an example for info.movito.themoviedbapi.CompanyApiTest.testGetCompanyInfo
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
