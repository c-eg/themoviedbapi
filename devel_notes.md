How to relase a new version of themoviedbpi?
---------------------------

1. Update NEWS.md and pom.xml
2. Release on github

3. Install locally into repository including sources

```
mvn  source:jar install -Dmaven.test.skip=true
```

4. Create new version on bintray, upload files (pom, jar, src-jar) from
`/Users/holger/.m2/repository/info/movito/themoviedbapi` to path
info/movito/themoviedbapi/1.3

5. Reupdate news.md and pom.xml for next iteration


* https://bintray.com/docs/usermanual/uploads/uploads_includingyourpackagesinjcenter.html
* https://bintray.com/movito/movito-utils/tmdb-api


