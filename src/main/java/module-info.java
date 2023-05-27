module info.movito.themoviedbapi {
    requires org.slf4j;
    requires org.apache.commons.codec;
    requires org.apache.commons.lang3;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    exports info.movito.themoviedbapi;
    exports info.movito.themoviedbapi.model;
    exports info.movito.themoviedbapi.model.changes;
    exports info.movito.themoviedbapi.model.config;
    exports info.movito.themoviedbapi.model.core;
    exports info.movito.themoviedbapi.model.keywords;
    exports info.movito.themoviedbapi.model.people;
    exports info.movito.themoviedbapi.model.providers;
    exports info.movito.themoviedbapi.model.tv;
    exports info.movito.themoviedbapi.tools;
}