package info.movito.themoviedbapi;

import com.google.common.collect.Lists;
import info.movito.themoviedbapi.model.config.Timezone;
import info.movito.themoviedbapi.model.core.responses.TmdbResponseException;
import info.movito.themoviedbapi.tools.ApiUrl;
import info.movito.themoviedbapi.tools.RequestType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The movie database api for configuration. See the
 * <a href="https://developer.themoviedb.org/reference/configuration-details">documentation</a> for more info.
 * TODO: Move this to config api. I believe the current endpoint is being deprecated - there's no docs for it.
 */
public class TmdbTimezones extends AbstractTmdbApi {
    public static final String TMDB_METHOD_TIMEZONESLIST = "timezones/list";

    /**
     * Create a new TmdbTimezones instance to call the timezone TMDb API methods.
     */
    TmdbTimezones(TmdbApi tmdbApi) {
        super(tmdbApi);
    }

    /**
     * Gets the timezones.
     *
     * @return the timezones.
     * @deprecated This method is being deprecated by TMDB.
     */
    @Deprecated
    @SuppressWarnings("unchecked")
    public List<Timezone> getTimezones() throws TmdbResponseException {
        ApiUrl apiUrl = new ApiUrl(TmdbTimezones.TMDB_METHOD_TIMEZONESLIST);

        String webpage = tmdbApi.getTmdbUrlReader().readUrl(apiUrl.buildUrl(), null, RequestType.GET);

        HashMap[] hashMaps1;
        try {
            hashMaps1 = getObjectMapper().readValue(webpage, HashMap[].class);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        ArrayList<Timezone> tzlist = new ArrayList<>();
        for (HashMap hm : hashMaps1) {
            String zoneCountry = hm.keySet().iterator().next().toString();
            for (String zoneName : (List<String>) hm.get(zoneCountry)) {
                tzlist.add(new Timezone(zoneName, zoneCountry));
            }
        }

        return Lists.newArrayList(tzlist);
    }
}
