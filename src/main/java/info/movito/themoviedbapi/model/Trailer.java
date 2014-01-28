package info.movito.themoviedbapi.model;


import info.movito.themoviedbapi.model.core.AbstractJsonMapping;


public class Trailer extends AbstractJsonMapping {


    /*
    * Website sources
    */
    public static final String WEBSITE_YOUTUBE = "youtube";
    public static final String WEBSITE_QUICKTIME = "quicktime";

    private String name;
    private String size;
    private String source;
    private String website;    // The website of the trailer


    public String getName() {
        return name;
    }


    public String getSize() {
        return size;
    }


    public String getSource() {
        return source;
    }


    public String getWebsite() {
        return website;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void setSize(String size) {
        this.size = size;
    }


    public void setSource(String source) {
        this.source = source;
    }


    public void setWebsite(String website) {
        this.website = website;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Trailer other = (Trailer) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if ((this.size == null) ? (other.size != null) : !this.size.equals(other.size)) {
            return false;
        }
        if ((this.source == null) ? (other.source != null) : !this.source.equals(other.source)) {
            return false;
        }
        return true;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 61 * hash + (this.size != null ? this.size.hashCode() : 0);
        hash = 61 * hash + (this.source != null ? this.source.hashCode() : 0);
        hash = 61 * hash + (this.website != null ? this.website.hashCode() : 0);
        return hash;
    }

}
