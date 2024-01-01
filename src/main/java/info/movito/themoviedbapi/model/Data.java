package info.movito.themoviedbapi.model;

import lombok.EqualsAndHashCode;

@lombok.Data
@EqualsAndHashCode(callSuper = false)
public class Data {
    private String title;

    private String overview;

    private String homepage;
}
