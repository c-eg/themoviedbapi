
package info.movito.themoviedbapi.model.core;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ResultDates extends AbstractJsonMapping {


    private static final Logger logger = LoggerFactory.getLogger(ResultDates.class);

    @JsonProperty("minimum")
    private String minimum = "";
    @JsonProperty("maximum")
    private String maximum = "";


    public String getMinimum() {
        return minimum;
    }


    public String getMaximum() {
        return maximum;
    }


    public void setMinimum(String minimum) {
        this.minimum = minimum;
    }


    public void setMaximum(String maximum) {
        this.maximum = maximum;
    }


    /**
     * Handle unknown properties and print a message
     *
     * @param key
     * @param value
     */
    @JsonAnySetter
    public void handleUnknown(String key, Object value) {
        StringBuilder sb = new StringBuilder();
        sb.append("Unknown property: '").append(key);
        sb.append("' value: '").append(value).append("'");
        logger.trace(sb.toString());
    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }
}
