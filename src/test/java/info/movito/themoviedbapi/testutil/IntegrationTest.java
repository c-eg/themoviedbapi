package info.movito.themoviedbapi.testutil;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.jupiter.api.Tag;

/**
 * Marks a test as an integration test by tagging it with {@code "integration"}.
 * The Gradle {@code integrationTest} task includes this tag.
 * See <a href="https://docs.junit.org/6.1.0/writing-tests/annotations.html#annotations">JUnit docs</a>.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Tag("integration")
public @interface IntegrationTest {
}
