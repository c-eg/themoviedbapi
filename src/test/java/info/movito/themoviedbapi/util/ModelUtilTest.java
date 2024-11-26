package info.movito.themoviedbapi.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import info.movito.themoviedbapi.model.keywords.Keyword;
import info.movito.themoviedbapi.model.tv.series.EpisodeGroup;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for {@link ModelUtil}.
 */
public class ModelUtilTest {
    /**
     * Tests {@link ModelUtil#getNames(Collection)}.
     */
    @Test
    public void testGetNames() {
        Keyword name1 = new Keyword();
        name1.setName("name1");
        Keyword name2 = new Keyword();
        name2.setName("name2");
        Keyword name3 = new Keyword();
        name3.setName("name3");

        List<Keyword> keywords = Arrays.asList(name1, name2, name3);

        List<String> names = ModelUtil.getNames(keywords);
        assertEquals("name1", names.get(0));
        assertEquals("name2", names.get(1));
        assertEquals("name3", names.get(2));
    }

    /**
     * Tests {@link ModelUtil#getNamesString(Collection)}.
     */
    @Test
    public void testGetNamesString() {
        EpisodeGroup name1 = new EpisodeGroup();
        name1.setName("name1");
        EpisodeGroup name2 = new EpisodeGroup();
        name2.setName("name2");
        EpisodeGroup name3 = new EpisodeGroup();
        name3.setName("name3");

        List<EpisodeGroup> episodeGroups = Arrays.asList(name1, name2, name3);

        List<String> names = ModelUtil.getNamesString(episodeGroups);
        assertEquals("name1", names.get(0));
        assertEquals("name2", names.get(1));
        assertEquals("name3", names.get(2));
    }
}
