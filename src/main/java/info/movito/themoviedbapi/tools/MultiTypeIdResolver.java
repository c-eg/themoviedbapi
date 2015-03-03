package info.movito.themoviedbapi.tools;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.type.TypeFactory;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.people.Person;
import info.movito.themoviedbapi.model.tv.TvSeries;

/**
 * Used by Jackson TypeDeserializer for determine type of object in /search/multi request
 */
public class MultiTypeIdResolver implements TypeIdResolver {

    private static final String BASE_PACKAGE = "info.movito.themoviedbapi";

    private JavaType mBaseType;

    @Override
    public void init(JavaType baseType) {
        mBaseType = baseType;
    }

    @Override
    public String idFromValue(Object value) {
        return idFromValueAndType(value, value.getClass());
    }

    @Override
    public String idFromValueAndType(Object value, Class<?> suggestedType) {
        String name = suggestedType.getName();
        if ( name.startsWith(BASE_PACKAGE) ) {
            return name.substring(BASE_PACKAGE.length() + 1);
        }
        throw new IllegalStateException("class " + suggestedType + " is not in the package " + BASE_PACKAGE);
    }

    @Override
    public String idFromBaseType() {
        return idFromValueAndType(null, mBaseType.getRawClass());
    }

    @Override
    public JavaType typeFromId(String id) {
        Class<?> clazz = null;
        if ("movie".equals(id)) {
            clazz = MovieDb.class;
        } else if ("tv".equals(id)) {
            clazz = TvSeries.class;
        } else if ("person".equals(id)) {
            clazz = Person.class;
        }

        if (clazz != null) {
            return TypeFactory.defaultInstance().constructSpecializedType(mBaseType, clazz);
        } else {
            throw new IllegalStateException("Do not know which class have to be used for id: '" + id + "'");
        }
    }

    @Override
    public JsonTypeInfo.Id getMechanism() {
        return JsonTypeInfo.Id.CUSTOM;
    }
}
