# TheMovieDB API
[![Download](https://img.shields.io/github/v/release/c-eg/themoviedbapi)](https://github.com/c-eg/themoviedbapi/releases)
[![BSD 2 License](http://img.shields.io/badge/license-BSD_2_Clause-green.svg)](https://opensource.org/licenses/BSD-2-Clause)

This library provides a Java-wrapper around the [JSON API](https://developer.themoviedb.org/docs/getting-started) provided by
[TMdB](https://www.themoviedb.org/), which is an open database for movie and tv content.

The wrapper implements most, if not all, of the JSON API. However, because the API is subject to constantly change, new functionality may 
not be implemented, or current functionality may break. Please point this out by submitting an issue, or even better, just send us a pull 
request!

It's available via [Maven Central](https://central.sonatype.com/artifact/uk.co.conoregan/themoviedbapi). Just add it as dependency to your project.

## Usage
To register for a TMdB API key, click the [API link](https://www.themoviedb.org/settings/api) from within your account settings page. There are two types of API keys currently provided by TMdB, please ensure you are using the `API Read Access Token` key.

With this you can instantiate `info.movito.themoviedbapi.TmdbApi`, which has getters for all subcategories of the API, e.g.
```java
TmdbApi tmdbApi = new TmdbApi("<apikey>");
```

By default this uses the library's built-in HTTP client. If you would rather plug in your own, see
[Using your own HTTP client](#using-your-own-http-client).

### Examples
#### Get movie details
```java
TmdbMovies tmdbMovies = tmdbApi.getMovies();
MovieDb movie = tmdbMovies.getDetails(5353, "en-US");
```

#### Append to response
Some of the API methods support appending additional requests to the response. This concept is part of the underlying 
[TMdB API - Append To Response](https://developer.themoviedb.org/docs/append-to-response), our wrapper just mimics the scheme.

If you try to call the getter for a model that has fields for appendable responses, without providing the append to response parameter to 
the function, it will return  `null`.

```java
TmdbMovies tmdbMovies = tmdbApi.getMovies();

MovieDb movie = tmdbMovies.getDetails(5353, "en-US");
Images images = movie.getImages();  // this will be null

MovieDb movie = tmdbMovies.getDetails(5353, "en-US", MovieAppendToResponse.IMAGES);
Images images = movie.getImages();  // this will NOT be null
```

You can also append multiple responses to the same request by providing multiple append to response values.

```java
TmdbMovies tmdbMovies = tmdbApi.getMovies();

MovieDb movie = tmdbMovies.getDetails(5353, "en-US", MovieAppendToResponse.IMAGES, MovieAppendToResponse.VIDEOS);
MovieDb movie = tmdbMovies.getDetails(5353, "en-US", MovieAppendToResponse.values());
```

To find all methods that use append to response, see the `info.movito.themoviedbapi.tools.appendtoresponse.AppendToResponse` interface 
implementations.

### Exception handling
Every API method can throw a `info.movito.themoviedbapi.tools.TmdbException` if the request fails for any reason. You should catch this 
exception and handle it appropriately.

Some exceptions are caused because the response status provided by the TMdB API is not successful. To see more details, see the 
`info.movito.themoviedbapi.tools.TmdbResponseCode` and [TMdB Errors](https://developer.themoviedb.org/docs/errors).

In the example below, the response was successful, but response code returned by TMdB API was not successful due to an authentication
failure. 

```java
TmdbMovies tmdbMovies = tmdbApi.getMovies();

try {
    AccountStates accountStates = tmdbMovies.getAccountStates(-1, "accountId", null);
}
catch (TmdbResponseException exception) {
    TmdbResponseCode responseCode = exception.getResponseCode();
    // handle unsuccessful TMdB response code
}
catch (TmdbException exception) {
    // handle unknown-cause exception
}
```

We chose to throw exceptions rather than returning `null`, so you have more control over what you do with each failure case. E.g. with the
example above, you may want to display an error message to the user about failing authentication.

### Using your own HTTP client
By default, `TmdbApi` uses the built-in `info.movito.themoviedbapi.tools.TmdbHttpClient`, which is backed by the JDK's
`java.net.http.HttpClient`. If you would rather use a different HTTP client, you can provide your own
implementation of the `info.movito.themoviedbapi.tools.TmdbRequestExecutor` interface.

Your implementation only has to make the call and hand back the raw response. If the request fails (e.g. an `IOException`), wrap it in a 
`info.movito.themoviedbapi.tools.TmdbException`. 

Interpreting TMdB status codes and retrying rate-limited requests is handled by the library on top of your executor, so you do not need to 
deal with that (see `info.movito.themoviedbapi.tools.TmdbApiClient` for more information).

Then pass your implementation to `TmdbApi` instead of the API key:
```java
TmdbApi tmdbApi = new TmdbApi(new CustomHttpClient());
```

## Project Logging

This project uses [SLF4J](http://www.slf4j.org) to abstract the logging in the project. To use the logging in your own
project you should add one of the provided [adapter bindings](http://www.slf4j.org/manual.html).

## Prooguard / R8 rules
Model classes are placed under `info.movito.themoviedbapi.model` package. Add this to `proguard-rules.pro` so that, these classes can 
survive minification.
```
-keep class info.movito.themoviedbapi.model.** { *; }
```

## Notes & Acknowledgements
The library was developed for [Movito](http://www.movito.info) to interact with TMdB services. This library has been inspired by [api-themoviedb](https://github.com/Omertron/api-themoviedb) but
has been rewritten to provide a more open license, a more clean API, and to expose more features of the TMdB JSON API.
