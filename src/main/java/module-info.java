module uk.co.conoregan.themoviedbapi {
    requires lombok;
    requires com.google.common;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires okhttp3;
    requires org.slf4j;
    requires org.apache.commons.codec;
    requires org.apache.commons.lang3;

    opens uk.co.conoregan.themoviedbapi.api to com.fasterxml.jackson.databind;  // todo: maybe remove this
    opens uk.co.conoregan.themoviedbapi.model to com.fasterxml.jackson.databind;
    opens uk.co.conoregan.themoviedbapi.model.changes to com.fasterxml.jackson.databind;
    opens uk.co.conoregan.themoviedbapi.model.movies.changes to com.fasterxml.jackson.databind;
    opens uk.co.conoregan.themoviedbapi.model.certifications to com.fasterxml.jackson.databind;
    opens uk.co.conoregan.themoviedbapi.model.config to com.fasterxml.jackson.databind;
    opens uk.co.conoregan.themoviedbapi.model.core to com.fasterxml.jackson.databind;
    opens uk.co.conoregan.themoviedbapi.model.core.responses to com.fasterxml.jackson.databind;
    opens uk.co.conoregan.themoviedbapi.model.keywords to com.fasterxml.jackson.databind;
    opens uk.co.conoregan.themoviedbapi.model.people to com.fasterxml.jackson.databind;
    opens uk.co.conoregan.themoviedbapi.model.providers to com.fasterxml.jackson.databind;
    opens uk.co.conoregan.themoviedbapi.model.tv to com.fasterxml.jackson.databind;
    opens uk.co.conoregan.themoviedbapi.tools to com.fasterxml.jackson.databind;  // todo: maybe remove this
    opens uk.co.conoregan.themoviedbapi.util to com.fasterxml.jackson.databind;  // todo: maybe remove this

    exports uk.co.conoregan.themoviedbapi.api;
    exports uk.co.conoregan.themoviedbapi.model;
    exports uk.co.conoregan.themoviedbapi.model.account;
    exports uk.co.conoregan.themoviedbapi.model.changes;
    exports uk.co.conoregan.themoviedbapi.model.certifications;
    exports uk.co.conoregan.themoviedbapi.model.movies.changes;
    exports uk.co.conoregan.themoviedbapi.model.config;
    exports uk.co.conoregan.themoviedbapi.model.core;
    exports uk.co.conoregan.themoviedbapi.model.core.responses;
    exports uk.co.conoregan.themoviedbapi.model.keywords;
    exports uk.co.conoregan.themoviedbapi.model.people;
    exports uk.co.conoregan.themoviedbapi.model.providers;
    exports uk.co.conoregan.themoviedbapi.model.tv;
    exports uk.co.conoregan.themoviedbapi.tools;
    exports uk.co.conoregan.themoviedbapi.util;
    exports uk.co.conoregan.themoviedbapi.model.rated;
    opens uk.co.conoregan.themoviedbapi.model.rated to com.fasterxml.jackson.databind;
}