package info.movito.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.NamedIdElement;


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


    public String getDescription() {
        return description;
    }


    public String getHeadquarters() {
        return headquarters;
    }


    public String getHomepage() {
        return homepage;
    }


    public String getLogoPath() {
        return logoPath;
    }


    public Company getParentCompany() {
        return parentCompany;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public void setHeadquarters(String headquarters) {
        this.headquarters = headquarters;
    }


    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }


    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }


    public void setParentCompany(Company parentCompany) {
        this.parentCompany = parentCompany;
    }


    public void setParentCompany(int id, String name, String logoPath) {
        Company parent = new Company();
        parent.setId(id);
        parent.setName(name);
        parent.setLogoPath(logoPath);

        this.parentCompany = parent;
    }
}
