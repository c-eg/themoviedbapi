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
 * Tests for {@link TmdbCompany}.
 */
public class TmdbCompanyTest extends AbstractTmdbApiTest {
    /**
     * Tests {@link TmdbCompany#getDetails(Integer)}.
     */
    @Test
    public void testGetDetails() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/companies/details.json");
        mockResponse(body, 200);

        TmdbCompany tmdbCompany = getTmdbApi().getCompany();
        Company company = tmdbCompany.getDetails(1);
        assertNotNull(company);
        testForNullFieldsAndUnknownProperties(company);
    }

    /**
     * Tests {@link TmdbCompany#getAlternativeNames(Integer)}.
     */
    @Test
    public void testGetAlternativeNames() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/companies/alternative_names.json");
        mockResponse(body, 200);

        TmdbCompany tmdbCompany = getTmdbApi().getCompany();
        TmdbCompany.AlternativeNamesResultsPage alternativeNamesResultsPage = tmdbCompany.getAlternativeNames(1);
        assertNotNull(alternativeNamesResultsPage);
        testForNullFieldsAndUnknownProperties(alternativeNamesResultsPage);

        AlternativeName alternativeName = alternativeNamesResultsPage.getResults().get(0);
        assertNotNull(alternativeName);
        testForNullFieldsAndUnknownProperties(alternativeName);
    }

    /**
     * Tests {@link TmdbCompany#getImages(Integer)}.
     */
    @Test
    public void testGetImages() throws IOException, TmdbException {
        String body = TestUtils.readTestFile("api_responses/companies/images.json");
        mockResponse(body, 200);

        TmdbCompany tmdbCompany = getTmdbApi().getCompany();
        LogoImageResults logoImageResults = tmdbCompany.getImages(1);
        assertNotNull(logoImageResults);
        testForNullFieldsAndUnknownProperties(logoImageResults);

        LogoImage logoImage = logoImageResults.getLogos().get(0);
        assertNotNull(logoImage);
        testForNullFieldsAndUnknownProperties(logoImage);
    }
}
