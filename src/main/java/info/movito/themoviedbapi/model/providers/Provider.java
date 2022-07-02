package info.movito.themoviedbapi.model.providers;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Provider {

  @JsonProperty("display_priority")
  private Integer displayPriority;

  @JsonProperty("logo_path")
  private String logoPath;

  @JsonProperty("provider_id")
  private Integer providerId;

  @JsonProperty("provider_name")
  private String providerName;

  public Integer getDisplayPriority() {
    return displayPriority;
  }

  public Provider setDisplayPriority(Integer displayPriority) {
    this.displayPriority = displayPriority;
    return this;
  }

  public String getLogoPath() {
    return logoPath;
  }

  public Provider setLogoPath(String logoPath) {
    this.logoPath = logoPath;
    return this;
  }

  public Integer getProviderId() {
    return providerId;
  }

  public Provider setProviderId(Integer providerId) {
    this.providerId = providerId;
    return this;
  }

  public String getProviderName() {
    return providerName;
  }

  public Provider setProviderName(String providerName) {
    this.providerName = providerName;
    return this;
  }
}
