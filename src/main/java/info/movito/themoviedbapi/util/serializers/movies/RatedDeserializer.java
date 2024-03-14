package info.movito.themoviedbapi.util.serializers.movies;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import info.movito.themoviedbapi.model.core.accountstates.Rated;

import java.io.IOException;

/**
 * Custom deserializer for movie Rated. Rated can either be "false" (boolean) or an object.
 */
public class RatedDeserializer extends JsonDeserializer<Rated> {
    @Override
    public Rated deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.readValueAsTree();
        if (node == null || node.isBoolean()) {
            return null;
        }

        JsonNode value = node.get("value");
        if (value == null) {
            return null;
        }

        Rated rated = new Rated();
        rated.setValue(value.asInt());
        return rated;
    }
}
