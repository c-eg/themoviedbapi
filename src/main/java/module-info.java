module info.movito.themoviedbapi {
    requires com.google.common;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires lombok;
    requires okhttp3;
    requires org.slf4j;
    requires org.apache.commons.codec;
    requires org.apache.commons.lang3;

    opens info.movito.themoviedbapi.api to com.fasterxml.jackson.databind;  // todo: maybe remove this
    opens info.movito.themoviedbapi.model to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.model.account to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.model.authentication to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.model.changes to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.model.movies.changes to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.model.certifications to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.model.config to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.model.core to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.model.core.responses to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.model.keywords to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.model.people to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.model.providers to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.model.rated to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.model.tv to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.util to com.fasterxml.jackson.databind;

    exports info.movito.themoviedbapi.api;
    exports info.movito.themoviedbapi.model;
    exports info.movito.themoviedbapi.model.account;
    exports info.movito.themoviedbapi.model.authentication;
    exports info.movito.themoviedbapi.model.changes;
    exports info.movito.themoviedbapi.model.certifications;
    exports info.movito.themoviedbapi.model.movies.changes;
    exports info.movito.themoviedbapi.model.config;
    exports info.movito.themoviedbapi.model.core;
    exports info.movito.themoviedbapi.model.core.responses;
    exports info.movito.themoviedbapi.model.keywords;
    exports info.movito.themoviedbapi.model.people;
    exports info.movito.themoviedbapi.model.providers;
    exports info.movito.themoviedbapi.model.rated;
    exports info.movito.themoviedbapi.model.tv;
    exports info.movito.themoviedbapi.tools;
    exports info.movito.themoviedbapi.util;
}