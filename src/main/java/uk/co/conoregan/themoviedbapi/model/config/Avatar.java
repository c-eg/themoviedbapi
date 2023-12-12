package uk.co.conoregan.themoviedbapi.model.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

public class Avatar {
    @JsonProperty("gravatar")
    private HashMap<String, String> gravatar;

    @JsonProperty("tmdb")
    private HashMap<String, String> tmdb;

    public String getGravatarHash() {
        return gravatar.get("hash");
    }

    public String getTmdbAvatarPath() {
        return tmdb.get("avatar_path");
    }
}
