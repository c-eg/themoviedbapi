package info.movito.themoviedbapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.core.NamedStringIdElement;

import java.util.List;


public class MovieList extends NamedStringIdElement {


    @JsonProperty("created_by")
    private String createdBy;
    @JsonProperty("description")
    private String description;
    @JsonProperty("favorite_count")
    private int favoriteCount;

    @JsonProperty("item_count")
    private int itemCount;


    @JsonProperty("poster_path")
    private String posterPath;
    @JsonProperty("list_type")
    private String listType;


    //used for /list
    @JsonProperty("items")
    private List<MovieDb> items;


    public String getDescription() {
        return description;
    }


    public int getFavoriteCount() {
        return favoriteCount;
    }


    public int getItemCount() {
        return itemCount;
    }


    public String getPosterPath() {
        return posterPath;
    }


    public String getListType() {
        return listType;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }


    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }


    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }


    public void setListType(String listType) {
        this.listType = listType;
    }


    public List<MovieDb> getItems() {
        return items;
    }


    public void setItems(List<MovieDb> items) {
        this.items = items;
    }


    public String getCreatedBy() {
        return createdBy;
    }
}
