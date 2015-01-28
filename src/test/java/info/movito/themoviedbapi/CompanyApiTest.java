package info.movito.themoviedbapi;

import info.movito.themoviedbapi.model.Collection;
import info.movito.themoviedbapi.model.Company;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class CompanyApiTest extends AbstractTmdbApiTest {


    @Test
    public void testGetCompanyInfo() {
        Company company = tmdb.getCompany().getCompanyInfo(ID_COMPANY);
        assertTrue("No company information found", company.getId() > 0);
        assertNotNull("No parent company found", company.getParentCompany());
    }


    @Test
    public void testGetCompanyMovies() {
        List<Collection> result = tmdb.getCompany().getCompanyMovies(ID_COMPANY, LANGUAGE_DEFAULT, 0).getResults();
        assertTrue("No company movies found", !result.isEmpty());
    }


}
