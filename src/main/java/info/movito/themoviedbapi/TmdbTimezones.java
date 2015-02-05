package info.movito.themoviedbapi;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

import info.movito.themoviedbapi.model.config.Timezone;
import info.movito.themoviedbapi.tools.ApiUrl;
import info.movito.themoviedbapi.tools.RequestMethod;

import java.io.IOException;
import java.util.ArrayList;
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

        ArrayList<Timezone> tzlist = new  ArrayList<Timezone>();
        for(HashMap hm : Arrays.asList(hashMaps1)) {
        	String zoneCountry = hm.keySet().iterator().next().toString();
        	for( String zoneName : (List<String>)hm.get(zoneCountry)) {
        		tzlist.add(new Timezone(zoneName,zoneCountry));
        	}
        }

        return Lists.newArrayList(tzlist);

    }
}
