package uk.co.conoregan.themoviedbapi;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import uk.co.conoregan.themoviedbapi.model.companies.AlternativeNamesResultsPage;
import uk.co.conoregan.themoviedbapi.model.companies.Company;
import uk.co.conoregan.themoviedbapi.model.core.image.ImageResults;
import uk.co.conoregan.themoviedbapi.testutil.TestUtils;
import uk.co.conoregan.themoviedbapi.tools.RequestType;
import uk.co.conoregan.themoviedbapi.tools.TmdbException;
import uk.co.conoregan.themoviedbapi.tools.TmdbRequest;
import uk.co.conoregan.themoviedbapi.tools.TmdbResponse;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static uk.co.conoregan.themoviedbapi.TmdbCompanies.TMDB_METHOD_COMPANY;
import static uk.co.conoregan.themoviedbapi.tools.ApiUrl.TMDB_API_BASE_URL;

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
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

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
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

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
        when(getRequestExecutor().execute(new TmdbRequest(url, RequestType.GET))).thenReturn(new TmdbResponse(200, body));

        ImageResults logoImageResults = getApiToTest().getImages(1);
        assertNotNull(logoImageResults);
        TestUtils.validateAbstractJsonMappingFields(logoImageResults);
    }
}
