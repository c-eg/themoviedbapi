package info.movito.themoviedbapi.model.certifications;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Certification {
    @JsonProperty("certification")
    private String certification;

    @JsonProperty("meaning")
    private String meaning;

    @JsonProperty("order")
    private Integer order;

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
