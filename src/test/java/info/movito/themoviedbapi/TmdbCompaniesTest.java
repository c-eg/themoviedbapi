package info.movito.themoviedbapi;

import java.io.IOException;

import info.movito.themoviedbapi.model.companies.AlternativeNamesResultsPage;
import info.movito.themoviedbapi.model.companies.Company;
import info.movito.themoviedbapi.model.core.image.ImageResults;
import info.movito.themoviedbapi.testutil.TestUtils;
import info.movito.themoviedbapi.tools.RequestType;
import info.movito.themoviedbapi.tools.TmdbException;
import org.junit.jupiter.api.Test;

import static info.movito.themoviedbapi.TmdbCompanies.TMDB_METHOD_COMPANY;
import static info.movito.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link TmdbCompanies}.
 */
public class TmdbCompaniesTest extends AbstractTmdbApiTest<TmdbCompanies> {
    @Override
    public TmdbCompanies createApiToTest() {
        return getTmdbApi().getCompanies();
    }

    /**
     * Tests {@link TmdbCompanies#getDetails(Integer)}.
     */
    @Test
    public void testGetDetails() throws IOException, TmdbException {
        int companyId = 1;

        String body = TestUtils.readTestFile("api_responses/companies/details.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_COMPANY + "/" + companyId;
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        Company company = getApiToTest().getDetails(companyId);
        assertNotNull(company);
        TestUtils.validateAbstractJsonMappingFields(company);
    }

    /**
     * Tests {@link TmdbCompanies#getAlternativeNames(Integer)}.
     */
    @Test
    public void testGetAlternativeNames() throws IOException, TmdbException {
        int companyId = 1;

        String body = TestUtils.readTestFile("api_responses/companies/alternative_names.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_COMPANY + "/" + companyId + "/alternative_names";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        AlternativeNamesResultsPage alternativeNamesResultsPage = getApiToTest().getAlternativeNames(1);
        assertNotNull(alternativeNamesResultsPage);
        TestUtils.validateAbstractJsonMappingFields(alternativeNamesResultsPage);
    }

    /**
     * Tests {@link TmdbCompanies#getImages(Integer)}.
     */
    @Test
    public void testGetImages() throws IOException, TmdbException {
        int companyId = 1;

        String body = TestUtils.readTestFile("api_responses/companies/images.json");
        String url = TMDB_API_BASE_URL + TMDB_METHOD_COMPANY + "/" + companyId + "/images";
        when(getTmdbUrlReader().readUrl(url, null, RequestType.GET)).thenReturn(body);

        ImageResults logoImageResults = getApiToTest().getImages(1);
        assertNotNull(logoImageResults);
        TestUtils.validateAbstractJsonMappingFields(logoImageResults);
    }
}
