See
[releases](https://github.com/c-eg/themoviedbapi/releases)
for downloads and details

v2.3.1
-----

* move static classes for models which were inside the api classes, into the model package

v2.3.0
-----

* refactor util package with helper functions, and export it in module info
* dependency updates

v2.2.0
-----

* update readme to clarify which TMdB API key to use
* add pagination support for Trending API

v2.1.2
-----

* migrate gradle build configuration from Groovy to Kotlin
* update gradle wrapper to version 8.9
* change dependencies to use BOMs
* add serialVersionUID to Serializable implementation
* update many dependencies

v2.1.1
-----

* add crypto module in module-info - needed for images created with jlink 
because it is not included by default and will fail the SSL handshake without it

v2.1.0
-----

* replaces okhttp3 with the java standard library http client 
* fix a spelling mistake
* expand static imports for lombok generated methods (tests) - they don't work with javac

v2.0.4
-----

* fix revenue property - int -> long

v2.0.3
-----

* fix "belongs_to_collection" property mapping incorrectly
* fix trending api path params

v2.0.2
-----

* fix module info for okhttp3 - import kotlin.stdlib

v2.0.1
-----

* fix for lombok in module info - requires static
* update readme

v2.0.0
-----

This update is a major update to the library, with many breaking changes. 
* added checkstyle configuration for code style consistency
* removed deprecated apis
* updated existing apis
* added new tmdb apis
* added lombok
* replaced "WebBrowser" for okhttp3
* added unit tests for all tmdb api classes
* added github action for checking dependencies
* made each tmdb api method throw a TmdbException if an error occurs
* removed unused models & tools
* added javadocs for all tmdb api methods
* refactored many packages
* added logging
* plus many more small changes

v1.15.1
-----

* bug fix with module-info
* update gradle & dependencies

v1.15
-----

* bug fix [#138](https://github.com/holgerbrandl/themoviedbapi/pull/138)

v1.14
-----

* modernized build
* updated to tmdb api changes
* various bug fixes

v1.13
-----
 
* bug fixes and api adjustments

v1.12
-----

* moved to maven-central

v1.11
-----

* various bug fixes

v1.10
-----

* Added support for recommended movies
* Added support for `ProductionCompany`
* Improved language support for `TmdbPeople`
* Fixe: TV Keywords
* Added Vote Count and popularity to credits
* Added keywords to TV shows
* Added Content Ratings to TV Series
* Various bug-fixes

Thanks to all committers to this repo.

v1.9
----

* Added set functions to all model classes.
* Added support for release types on appendToResponse
* Misc bug-fixes

v1.8
----


* Added `gender` to PersonPeople,
* Added `getImages` method to TmdbTV
* Added support for different release types (fixes #83)
* Fixed job department json mapping
* Increased bytecode compatibility to 1.7

Thanks to @kliqkliq, @serv-inc and @JoeyPriceless for contributing these changes.

v1.7
----

* Added `region` for upcoming and now_playing movies (fixes #64)
* Fixed #70 `getSimilarMovies` returns `null`
* Replaced deprecated `credit` with `combined_credit`. #71
* Documentation improvements & bug fixes


v1.6
---

* Added "Recommendations" to TV series.


v1.5
---

* fixed original language field

v1.4
---
- Fixed: Now playing

v1.3
---
- Implemented automatic rate-limit handling

v1.2
---
- Implemented rating support for TV Shows
- Implemented multi-search (contributed by ArcherZP)
- More complete unit-test coverage

v1.1
---
- improved tv series support
- added timezones
- added gravatar support

v1.0
---
- Initial release

