package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.AlternativeName;
import info.movito.themoviedbapi.model.Company;
import info.movito.themoviedbapi.model.core.image.Image;
import info.movito.themoviedbapi.model.core.image.ImageResults;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.util.TestUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;

import static info.movito.themoviedbapi.TmdbCompanies.TMDB_METHOD_COMPANY;
import static info.movito.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;
import static info.movito.themoviedbapi.util.TestUtils.checkForNullAndEmptyFieldsAndNewItems;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link TmdbCompanies}.
 */
public class TmdbCompaniesTest extends AbstractTmdbApiTest {
    /**
     * Tests {@link TmdbCompanies#getDetails(Integer)}.
     */
    @Test
    public void testGetDetails() throws IOException, TmdbException {
        int companyId = 1;

        String body = TestUtils.readTestFile("api_responses/companies/details.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_COMPANY + "/" + companyId);
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbCompanies tmdbCompanies = getTmdbApi().getCompanies();
        Company company = tmdbCompanies.getDetails(companyId);
        assertNotNull(company);
        checkForNullAndEmptyFieldsAndNewItems(company);
    }

    /**
     * Tests {@link TmdbCompanies#getAlternativeNames(Integer)}.
     */
    @Test
    public void testGetAlternativeNames() throws IOException, TmdbException {
        int companyId = 1;

        String body = TestUtils.readTestFile("api_responses/companies/alternative_names.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_COMPANY + "/" + companyId + "/alternative_names");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbCompanies tmdbCompanies = getTmdbApi().getCompanies();
        TmdbCompanies.AlternativeNamesResultsPage alternativeNamesResultsPage = tmdbCompanies.getAlternativeNames(1);
        assertNotNull(alternativeNamesResultsPage);
        checkForNullAndEmptyFieldsAndNewItems(alternativeNamesResultsPage);

        AlternativeName alternativeName = alternativeNamesResultsPage.getResults().get(0);
        assertNotNull(alternativeName);
        checkForNullAndEmptyFieldsAndNewItems(alternativeName);
    }

    /**
     * Tests {@link TmdbCompanies#getImages(Integer)}.
     */
    @Test
    public void testGetImages() throws IOException, TmdbException {
        int companyId = 1;

        String body = TestUtils.readTestFile("api_responses/companies/images.json");
        URL url = new URL(TMDB_API_BASE_URL + TMDB_METHOD_COMPANY + "/" + companyId + "/images");
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        TmdbCompanies tmdbCompanies = getTmdbApi().getCompanies();
        ImageResults logoImageResults = tmdbCompanies.getImages(1);
        assertNotNull(logoImageResults);
        checkForNullAndEmptyFieldsAndNewItems(logoImageResults);

        Image image = logoImageResults.getLogos().get(0);
        assertNotNull(image);
        checkForNullAndEmptyFieldsAndNewItems(image);
    }
}
