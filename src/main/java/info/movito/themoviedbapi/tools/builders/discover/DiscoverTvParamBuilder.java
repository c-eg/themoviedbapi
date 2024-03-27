package info.movito.themoviedbapi.tools.builders.discover;

import java.util.List;
import java.util.stream.Collectors;

import info.movito.themoviedbapi.tools.sortby.DiscoverTvSortBy;
import org.apache.commons.lang3.StringUtils;

/**
 * Builder for parameters to build a 'discover tv' parameter map.
 * This allows you to just add the search components you are concerned with.
 */
public class DiscoverTvParamBuilder extends DiscoverParamBuilder<DiscoverTvParamBuilder> {
    private static final String PARAM_AIR_DATE_GTE = "air_date.gte";
    private static final String PARAM_AIR_DATE_LTE = "air_date.lte";
    private static final String PARAM_FIRST_AIR_DATE_YEAR = "first_air_date_year";
    private static final String PARAM_FIRST_AIR_DATE_GTE = "first_air_date.gte";
    private static final String PARAM_FIRST_AIR_DATE_LTE = "first_air_date.lte";
    private static final String PARAM_INCLUDE_NULL_FIRST_AIR_DATES = "include_null_first_air_dates";
    private static final String PARAM_SCREENED_THEATRICALLY = "screened_theatrically";
    private static final String PARAM_TIMEZONE = "timezone";
    private static final String PARAM_WITH_NETWORKS = "with_networks";
    private static final String PARAM_WITH_STATUS = "with_status";
    private static final String PARAM_WITH_TYPE = "with_type";

    @Override
    protected DiscoverTvParamBuilder me() {
        return this;
    }

    public DiscoverTvParamBuilder airDateGte(String airDateGte) {
        if (StringUtils.isBlank(airDateGte)) {
            throw new IllegalArgumentException("airDateGte can not be null");
        }

        getParams().put(PARAM_AIR_DATE_GTE, airDateGte);
        return me();
    }

    public DiscoverTvParamBuilder airDateLte(String airDateLte) {
        if (StringUtils.isBlank(airDateLte)) {
            throw new IllegalArgumentException("airDateLte can not be null");
        }

        getParams().put(PARAM_AIR_DATE_LTE, airDateLte);
        return me();
    }

    public DiscoverTvParamBuilder firstAirDateYear(int firstAirDateYear) {
        getParams().put(PARAM_FIRST_AIR_DATE_YEAR, String.valueOf(firstAirDateYear));
        return me();
    }

    public DiscoverTvParamBuilder firstAirDateGte(String firstAirDateGte) {
        if (StringUtils.isBlank(firstAirDateGte)) {
            throw new IllegalArgumentException("firstAirDateGte can not be null");
        }

        getParams().put(PARAM_FIRST_AIR_DATE_GTE, firstAirDateGte);
        return me();
    }

    public DiscoverTvParamBuilder firstAirDateLte(String firstAirDateLte) {
        if (StringUtils.isBlank(firstAirDateLte)) {
            throw new IllegalArgumentException("firstAirDateLte can not be null");
        }

        getParams().put(PARAM_FIRST_AIR_DATE_LTE, firstAirDateLte);
        return me();
    }

    public DiscoverTvParamBuilder includeNullFirstAirDates(boolean includeNullFirstAirDates) {
        getParams().put(PARAM_INCLUDE_NULL_FIRST_AIR_DATES, String.valueOf(includeNullFirstAirDates));
        return me();
    }

    public DiscoverTvParamBuilder screenedTheatrically(boolean screenedTheatrically) {
        getParams().put(PARAM_SCREENED_THEATRICALLY, String.valueOf(screenedTheatrically));
        return me();
    }

    public DiscoverTvParamBuilder sortBy(DiscoverTvSortBy sortBy) {
        getParams().put(PARAM_SORT_BY, sortBy.getValue());
        return me();
    }

    public DiscoverTvParamBuilder timezone(String timezone) {
        if (StringUtils.isBlank(timezone)) {
            throw new IllegalArgumentException("timezone can not be null");
        }

        getParams().put(PARAM_TIMEZONE, timezone);
        return me();
    }

    public DiscoverTvParamBuilder withNetworks(int withNetworks) {
        if (withNetworks < 0) {
            throw new IllegalArgumentException("withNetworks must be be >= 0");
        }

        getParams().put(PARAM_WITH_NETWORKS, String.valueOf(withNetworks));
        return me();
    }

    public DiscoverTvParamBuilder withStatus(List<Integer> statusIds, boolean orQuery) {
        if (statusIds == null || statusIds.isEmpty()) {
            throw new IllegalArgumentException("statusIds must be set");
        }

        String query = statusIds.stream().map(Object::toString).collect(Collectors.joining(orQuery ? "|" : ","));
        getParams().put(PARAM_WITH_STATUS, query);
        return me();
    }

    public DiscoverTvParamBuilder withType(List<Integer> typeIds, boolean orQuery) {
        if (typeIds == null || typeIds.isEmpty()) {
            throw new IllegalArgumentException("typeIds must be set");
        }

        String query = typeIds.stream().map(Object::toString).collect(Collectors.joining(orQuery ? "|" : ","));
        getParams().put(PARAM_WITH_TYPE, query);
        return me();
    }
}
