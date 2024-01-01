package info.movito.themoviedbapi.model.core;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Setter
@Getter
public class ResultDates extends AbstractJsonMapping {
    private static final Logger logger = LoggerFactory.getLogger(ResultDates.class);

    @JsonProperty("minimum")
    private String minimum = "";

    @JsonProperty("maximum")
    private String maximum = "";

    /**
     * Handle unknown properties and print a message.
     */
    @JsonAnySetter
    public void handleUnknown(String key, Object value) {
        String sb = "Unknown property: '" + key + "' value: '" + value + "'";
        logger.trace(sb);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }
}
