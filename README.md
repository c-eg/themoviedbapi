TheMovieDB API
================

[![Build Status](https://travis-ci.org/holgerbrandl/themoviedbapi.svg?branch=master)](https://travis-ci.org/holgerbrandl/themoviedbapi)

This library provides a java-wrapper around the [JSON API](http://api.themoviedb.org/) provided by
[TMdB](http://themoviedb.org), which is an open database for movie and film content.



Setup
-----

[ ![Download](https://api.bintray.com/packages/holgerbrandl/movito-utils/themoviedbapi/images/download.svg) ](https://bintray.com/holgerbrandl/movito-utils/themoviedbapi/_latestVersion)

Just add it as dependency to your project. It's available via [jcenter](https://bintray.com/bintray/jcenter)

```
<dependency>
    <groupId>info.movito</groupId>
    <artifactId>themoviedbapi</artifactId>
    <version>1.9</version>
</dependency>
```
Read the [Set me up](https://bintray.com/bintray/jcenter) section first, if you haven't addded jcenter as a repository to your pom.xml yet.

Or just add it via gradle when developing for Android:

```
repositories {
    jcenter()
}

dependencies{
    compile group: 'info.movito', name: 'themoviedbapi', version:'1.9'
}
```


Usage
-----

To get started you need a [TMdB API key](http://docs.themoviedb.apiary.io/). With this you can instantiate
`info.movito.themoviedbapi.TmdbApi`, which has getters for all subcategories of the API.

For instance to get a movie by ID, do the following:

```
TmdbMovies movies = new TmdbApi("<apikey>").getMovies();
MovieDb movie = movies.getMovie(5353, "en");
```

The wrapper implements all major parts of the JSON API as detailed out in the [TMdB API documentation]((http://api.themoviedb.org/)).
However, as the latter is subject to constant change, feel welcome to point out missing bits by submitting a ticket.
Or even better, just send us a pull request!

Most common question: Why does `getSomething()` returns null?
---------------

Every API element has a number of query flags that need to be set in order to pull a particular piece of information.

#### Example 1: Make `getCast()` to return cast and not `null` 

```
TmdbMovies movies = tmdbApi.getMovies();
movies.getMovie(293660, "en", MovieMethod.credits)
```

#### Example 2: Get image urls, videos and similar movies
```
TmdbMovies movies = tmdbApi.getMovies();
movies.getMovie(78, "en", MovieMethod.credits, MovieMethod.images, MovieMethod.similar)
```


#### Example 3: Get all info about a sesaon
 
Instead of individual item we can also simply provide all method values.

```
TmdbTvSeasons tvSeasons = tmdb.getTvSeasons();
TvSeason result = tvSeasons.getSeason(seriesId, seasonNumber, "en", SeasonMethod.values());
```

See the corresponding `*Method` enums or the REST API-documentation for possible values.

This concept is part of the underlying [REST API](http://api.themoviedb.org/), our wrapper justs mimicks the scheme.



Project Logging
---------------

This project uses [SLF4J](http://www.slf4j.org) to abstract the logging in the project. To use the logging in your own
project you should add one of the provided [adapter bindings](http://www.slf4j.org/manual.html).



Notes & Acknowledgements
-------------------------

The library was developed for [Movito](http://www.movito.info) to interact with tmdb services. This library has been
inspired by [api-themoviedb](https://github.com/Omertron/api-themoviedb) but has been rewritten to provide a more open
license, a more clean API and to expose more features of the TMDB json api.
