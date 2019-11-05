See
[releases](https://github.com/holgerbrandl/themoviedbapi/releases)
for downloads and details
    
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

