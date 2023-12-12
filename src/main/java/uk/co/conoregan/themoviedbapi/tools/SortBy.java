package uk.co.conoregan.themoviedbapi.tools;

import lombok.Getter;

/**
 * Some Tmdb methods allow you to specify the order that the results are returned in.
 */
@Getter
public enum SortBy {
    ASC("created_at.asc"),
    DESC("created_at.desc");

    private final String value;

    SortBy(String value) {
        this.value = value;
    }
}
