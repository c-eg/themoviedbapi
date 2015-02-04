package info.movito.themoviedbapi;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import info.movito.themoviedbapi.model.config.Timezone;
import info.movito.themoviedbapi.tools.ApiUrl;
import info.movito.themoviedbapi.tools.RequestMethod;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TmdbTimezones extends AbstractTmdbApi {

    public static final String TMDB_METHOD_TIMEZONESLIST = "timezones/list";


    TmdbTimezones(TmdbApi tmdbApi) {
        super(tmdbApi);
    }


    @SuppressWarnings("unchecked")
    public List<Timezone> getTimezones() {
        ApiUrl apiUrl = new ApiUrl(TmdbTimezones.TMDB_METHOD_TIMEZONESLIST);

        String webpage = tmdbApi.requestWebPage(apiUrl, null, RequestMethod.GET);

        HashMap[] hashMaps1;
        try {
            hashMaps1 = jsonMapper.readValue(webpage, HashMap[].class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Timezone> timezones = Lists.transform(Arrays.asList(hashMaps1), new Function<HashMap, Timezone>() {
            @Override
            public Timezone apply(HashMap input) {
                String zoneName = input.keySet().iterator().next().toString();
                return new Timezone(zoneName, (List<String>) input.get(zoneName));
            }
        });

        return Lists.newArrayList(timezones);

    }
}
