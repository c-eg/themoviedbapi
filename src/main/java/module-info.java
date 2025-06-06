module info.movito.themoviedbapi {
    requires static lombok;
    requires org.slf4j;
    requires org.apache.commons.lang3;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires java.net.http;
    requires jdk.crypto.cryptoki;

    opens info.movito.themoviedbapi.model.account to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.model.authentication to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.model.certifications to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.model.changes to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.model.collections to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.model.companies to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.model.configuration to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.model.core to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.model.core.image to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.model.core.multi to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.model.core.popularperson to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.model.core.responses to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.model.core.video to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.model.core.watchproviders to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.model.find to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.model.keywords to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.model.lists to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.model.movielists to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.model.movies to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.model.movies.changes to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.model.networks to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.model.people to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.model.people.credits to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.model.rated to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.model.reviews to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.model.search to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.model.tv.core to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.model.tv.episode to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.model.tv.episodegroups to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.model.tv.season to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.model.tv.series to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.model.tv.core.credits to com.fasterxml.jackson.databind;
    opens info.movito.themoviedbapi.model.watchproviders to com.fasterxml.jackson.databind;

    exports info.movito.themoviedbapi;
    exports info.movito.themoviedbapi.model.account;
    exports info.movito.themoviedbapi.model.authentication;
    exports info.movito.themoviedbapi.model.certifications;
    exports info.movito.themoviedbapi.model.changes;
    exports info.movito.themoviedbapi.model.collections;
    exports info.movito.themoviedbapi.model.companies;
    exports info.movito.themoviedbapi.model.configuration;
    exports info.movito.themoviedbapi.model.core;
    exports info.movito.themoviedbapi.model.core.image;
    exports info.movito.themoviedbapi.model.core.multi;
    exports info.movito.themoviedbapi.model.core.popularperson;
    exports info.movito.themoviedbapi.model.core.responses;
    exports info.movito.themoviedbapi.model.core.video;
    exports info.movito.themoviedbapi.model.core.watchproviders;
    exports info.movito.themoviedbapi.model.find;
    exports info.movito.themoviedbapi.model.keywords;
    exports info.movito.themoviedbapi.model.lists;
    exports info.movito.themoviedbapi.model.movielists;
    exports info.movito.themoviedbapi.model.movies;
    exports info.movito.themoviedbapi.model.movies.changes;
    exports info.movito.themoviedbapi.model.networks;
    exports info.movito.themoviedbapi.model.people;
    exports info.movito.themoviedbapi.model.people.credits;
    exports info.movito.themoviedbapi.model.rated;
    exports info.movito.themoviedbapi.model.reviews;
    exports info.movito.themoviedbapi.model.search;
    exports info.movito.themoviedbapi.model.tv.core;
    exports info.movito.themoviedbapi.model.tv.episode;
    exports info.movito.themoviedbapi.model.tv.episodegroups;
    exports info.movito.themoviedbapi.model.tv.season;
    exports info.movito.themoviedbapi.model.tv.series;
    exports info.movito.themoviedbapi.model.tv.core.credits;
    exports info.movito.themoviedbapi.model.watchproviders;
    exports info.movito.themoviedbapi.tools;
    exports info.movito.themoviedbapi.tools.appendtoresponse;
    exports info.movito.themoviedbapi.tools.builders;
    exports info.movito.themoviedbapi.tools.builders.discover;
    exports info.movito.themoviedbapi.tools.sortby;
    exports info.movito.themoviedbapi.tools.model.time;
    exports info.movito.themoviedbapi.util;
}
