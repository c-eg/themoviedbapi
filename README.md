The Movie DB API
================

Author: Holger Brandl

This library provides a java-wrapper around the json API provided by [TheMovieDB.org API](http://api.themoviedb.org/), which is  an open database for movie and film content.



Api Coverage
------------

The wrapper implements all methods as detailed out in the [tmdb api doc](http://docs.themoviedb.apiary.io/#search).

However, as the TMDB API is subject to constant change, feel welcome to point out missing bits, and we'll add them asap.

Usage Example
-------------

```
TmdbMovies movies = new TmdbApi("<apikey>").getMovies();
MovieDb movie = movies.getMovie(5353, "en");
```

Project Logging
---------------

This project uses [SLF4J](http://www.slf4j.org) to abstract the logging in the project.

To use the logging in your own project you should add one of their adapters [bindings](http://www.slf4j.org/manual.html).


Project Usage
-------------

The library was developed for [Movito](http://www.movito.info) to interact with tmdb services.  '


Acknowledgements
----------------

This library has been inspired by [api-themoviedb](https://github.com/Omertron/api-themoviedb) but has been rewritten to provide a more open license, a more clean API and to expose more features of the TMDB json api.