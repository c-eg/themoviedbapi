package info.movito.themoviedbapi.tools.builders.discover;

import java.util.List;
import java.util.stream.Collectors;

import info.movito.themoviedbapi.tools.sortby.DiscoverMovieSortBy;
import org.apache.commons.lang3.StringUtils;

/**
 * Builder for parameters to build a 'discover movie' parameter map.
 * This allows you to just add the search components you are concerned with.
 */
public class DiscoverMovieParamBuilder extends DiscoverParamBuilder<DiscoverMovieParamBuilder> {
    private static final String PARAM_CERTIFICATION = "certification";
    private static final String PARAM_CERTIFICATION_GTE = "certification.gte";
    private static final String PARAM_CERTIFICATION_LTE = "certification.lte";
    private static final String PARAM_CERTIFICATION_COUNTRY = "certification_country";
    private static final String PARAM_INCLUDE_VIDEO = "include_video";
    private static final String PARAM_PRIMARY_RELEASE_YEAR = "primary_release_year";
    private static final String PARAM_PRIMARY_RELEASE_DATE_GTE = "primary_release_date.gte";
    private static final String PARAM_PRIMARY_RELEASE_DATE_LTE = "primary_release_date.lte";
    private static final String PARAM_REGION = "region";
    private static final String PARAM_RELEASE_DATE_GTE = "release_date.gte";
    private static final String PARAM_RELEASE_DATE_LTE = "release_date.lte";
    private static final String PARAM_WITH_CAST = "with_cast";
    private static final String PARAM_WITH_CREW = "with_crew";
    private static final String PARAM_WITH_PEOPLE = "with_people";
    private static final String PARAM_WITH_RELEASE_TYPE = "with_release_type";
    private static final String PARAM_YEAR = "year";

    @Override
    protected DiscoverMovieParamBuilder me() {
        return this;
    }

    public DiscoverMovieParamBuilder certification(String certification) {
        if (StringUtils.isBlank(certification)) {
            throw new IllegalArgumentException("certification can not be blank");
        }

        getParams().put(PARAM_CERTIFICATION, certification);
        return me();
    }

    public DiscoverMovieParamBuilder certificationGte(String certificationGte) {
        if (StringUtils.isBlank(certificationGte)) {
            throw new IllegalArgumentException("certificationGte can not be blank");
        }

        getParams().put(PARAM_CERTIFICATION_GTE, certificationGte);
        return me();
    }

    public DiscoverMovieParamBuilder certificationLte(String certificationLte) {
        if (StringUtils.isBlank(certificationLte)) {
            throw new IllegalArgumentException("certificationLte can not be blank");
        }

        getParams().put(PARAM_CERTIFICATION_LTE, certificationLte);
        return me();
    }

    public DiscoverMovieParamBuilder certificationCountry(String certificationCountry) {
        if (StringUtils.isBlank(certificationCountry)) {
            throw new IllegalArgumentException("certificationCountry can not be blank");
        }

        getParams().put(PARAM_CERTIFICATION_COUNTRY, certificationCountry);
        return me();
    }

    public DiscoverMovieParamBuilder includeVideo(boolean includeVideo) {
        getParams().put(PARAM_INCLUDE_VIDEO, String.valueOf(includeVideo));
        return me();
    }

    public DiscoverMovieParamBuilder primaryReleaseYear(int primaryReleaseYear) {
        getParams().put(PARAM_PRIMARY_RELEASE_YEAR, String.valueOf(primaryReleaseYear));
        return me();
    }

    public DiscoverMovieParamBuilder primaryReleaseDateGte(String primaryReleaseDateGte) {
        if (StringUtils.isBlank(primaryReleaseDateGte)) {
            throw new IllegalArgumentException("primaryReleaseDateGte can not be blank");
        }

        getParams().put(PARAM_PRIMARY_RELEASE_DATE_GTE, primaryReleaseDateGte);
        return me();
    }

    public DiscoverMovieParamBuilder primaryReleaseDateLte(String primaryReleaseDateLte) {
        if (StringUtils.isBlank(primaryReleaseDateLte)) {
            throw new IllegalArgumentException("primaryReleaseDateLte can not be blank");
        }

        getParams().put(PARAM_PRIMARY_RELEASE_DATE_LTE, primaryReleaseDateLte);
        return me();
    }

    public DiscoverMovieParamBuilder region(String region) {
        if (StringUtils.isBlank(region)) {
            throw new IllegalArgumentException("region can not be blank");
        }

        getParams().put(PARAM_REGION, region);
        return me();
    }

    public DiscoverMovieParamBuilder releaseDateGte(String releaseDateGte) {
        if (StringUtils.isBlank(releaseDateGte)) {
            throw new IllegalArgumentException("releaseDateGte can not be blank");
        }

        getParams().put(PARAM_RELEASE_DATE_GTE, releaseDateGte);
        return me();
    }

    public DiscoverMovieParamBuilder releaseDateLte(String releaseDateLte) {
        if (StringUtils.isBlank(releaseDateLte)) {
            throw new IllegalArgumentException("releaseDateLte can not be blank");
        }

        getParams().put(PARAM_RELEASE_DATE_LTE, releaseDateLte);
        return me();
    }

    public DiscoverMovieParamBuilder sortBy(DiscoverMovieSortBy sortBy) {
        getParams().put(PARAM_SORT_BY, sortBy.getValue());
        return me();
    }

    public DiscoverMovieParamBuilder withCast(List<Integer> castIds, boolean orQuery) {
        if (castIds == null || castIds.isEmpty()) {
            throw new IllegalArgumentException("Cast must be set");
        }

        String query = castIds.stream().map(Object::toString).collect(Collectors.joining(orQuery ? "|" : ","));
        getParams().put(PARAM_WITH_CAST, query);
        return me();
    }

    public DiscoverMovieParamBuilder withCrew(List<Integer> crewIds, boolean orQuery) {
        if (crewIds == null || crewIds.isEmpty()) {
            throw new IllegalArgumentException("crewIds must be set");
        }

        String query = crewIds.stream().map(Object::toString).collect(Collectors.joining(orQuery ? "|" : ","));
        getParams().put(PARAM_WITH_CREW, query);
        return me();
    }

    public DiscoverMovieParamBuilder withPeople(List<Integer> peopleIds, boolean orQuery) {
        if (peopleIds == null || peopleIds.isEmpty()) {
            throw new IllegalArgumentException("peopleIds must be set");
        }

        String query = peopleIds.stream().map(Object::toString).collect(Collectors.joining(orQuery ? "|" : ","));
        getParams().put(PARAM_WITH_PEOPLE, query);
        return me();
    }

    public DiscoverMovieParamBuilder withReleaseType(List<Integer> releaseTypeIds, boolean orQuery) {
        if (releaseTypeIds == null || releaseTypeIds.isEmpty()) {
            throw new IllegalArgumentException("releaseTypeIds must be set");
        }

        String query = releaseTypeIds.stream().map(Object::toString).collect(Collectors.joining(orQuery ? "|" : ","));
        getParams().put(PARAM_WITH_RELEASE_TYPE, query);
        return me();
    }

    public DiscoverMovieParamBuilder year(int year) {
        getParams().put(PARAM_YEAR, String.valueOf(year));
        return me();
    }
}
