
package info.movito.themoviedbapi.model.movie;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.movito.themoviedbapi.model.Trailer;
import info.movito.themoviedbapi.model.core.IdElement;

import java.util.ArrayList;
import java.util.List;


public class MovieTrailers extends IdElement {

    @JsonProperty("quicktime")
    private List<Trailer> quicktime;
    @JsonProperty("youtube")
    private List<Trailer> youtube;


    public List<Trailer> getQuicktime() {
        return quicktime;
    }


    public List<Trailer> getYoutube() {
        return youtube;
    }


    public void setQuicktime(List<Trailer> quicktime) {
        this.quicktime = quicktime;
    }


    public void setYoutube(List<Trailer> youtube) {
        this.youtube = youtube;
    }


    /**
     * Get a combined list of the trailers with their source website
     *
     * @return
     */
    public List<Trailer> getAll() {
        List<Trailer> trailers = new ArrayList<Trailer>();

        // Add the trailer to the return list along with it's source
        if (quicktime != null) {
            for (Trailer trailer : quicktime) {
                trailer.setWebsite(Trailer.WEBSITE_QUICKTIME);
                trailers.add(trailer);
            }
        }
        if (youtube != null) {
            // Add the trailer to the return list along with it's source
            for (Trailer trailer : youtube) {
                trailer.setWebsite(Trailer.WEBSITE_YOUTUBE);
                trailers.add(trailer);
            }
        }

        return trailers;
    }
}
