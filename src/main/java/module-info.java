module info.movito.themoviedbapi {
    requires org.slf4j;
    requires org.apache.commons.codec;
    requires org.apache.commons.lang3;
    requires com.google.common;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    opens info.movito.themoviedbapi to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.model to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.model.changes to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.model.config to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.model.core to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.model.keywords to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.model.people to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.model.providers to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.model.tv to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.tools to com.fasterxml.jackson.databind;

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