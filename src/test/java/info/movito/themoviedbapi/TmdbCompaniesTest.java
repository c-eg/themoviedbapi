package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.AlternativeName;
import info.movito.themoviedbapi.model.Company;
import info.movito.themoviedbapi.model.core.image.LogoImage;
import info.movito.themoviedbapi.model.core.image.LogoImageResults;
import info.movito.themoviedbapi.tools.TmdbException;
import info.movito.themoviedbapi.util.TestUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Tests for {@link TmdbCompanies}.
 */
public class TmdbCompaniesTest extends AbstractTmdbApiTest {
    /**
     * Tests {@link TmdbCompanies#getDetails(Integer)}.
     */
    @Test
    public void testGetDetails() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/companies/details.json");
        mockResponse(body, 200);

        TmdbCompanies tmdbCompanies = getTmdbApi().getCompanies();
        Company company = tmdbCompanies.getDetails(1);
        assertNotNull(company);
        testForNullFieldsAndUnknownProperties(company);
    }

    /**
     * Tests {@link TmdbCompanies#getAlternativeNames(Integer)}.
     */
    @Test
    public void testGetAlternativeNames() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/companies/alternative_names.json");
        mockResponse(body, 200);

        TmdbCompanies tmdbCompanies = getTmdbApi().getCompanies();
        TmdbCompanies.AlternativeNamesResultsPage alternativeNamesResultsPage = tmdbCompanies.getAlternativeNames(1);
        assertNotNull(alternativeNamesResultsPage);
        testForNullFieldsAndUnknownProperties(alternativeNamesResultsPage);

        AlternativeName alternativeName = alternativeNamesResultsPage.getResults().get(0);
        assertNotNull(alternativeName);
        testForNullFieldsAndUnknownProperties(alternativeName);
    }

    /**
     * Tests {@link TmdbCompanies#getImages(Integer)}.
     */
    @Test
    public void testGetImages() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/companies/images.json");
        mockResponse(body, 200);

        TmdbCompanies tmdbCompanies = getTmdbApi().getCompanies();
        LogoImageResults logoImageResults = tmdbCompanies.getImages(1);
        assertNotNull(logoImageResults);
        testForNullFieldsAndUnknownProperties(logoImageResults);

        LogoImage logoImage = logoImageResults.getLogos().get(0);
        assertNotNull(logoImage);
        testForNullFieldsAndUnknownProperties(logoImage);
    }
}
