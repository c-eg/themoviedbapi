## How to do a release?

1. Make sure to increase version number in [build.gradle.kts](../build.gradle.kts)

2. Document [changes](../CHANGES.md)

3. Do the release
```bash
# adjust to te path of your working copy
export TMDB_HOME=/d/projects/misc/private/themoviedbapi

cd $TMDB_HOME

# run tests
./gradlew check


trim() { while read -r line; do echo "$line"; done; }
tmdb_version='v'$(grep '^version' ${TMDB_HOME}/build.gradle | cut -f2 -d' ' | tr -d "'" | trim)

echo "new version is $tmdb_version !"


if [[ $tmdb_version == *"-SNAPSHOT" ]]; then
  echo "ERROR: Won't publish snapshot build $tmdb_version!" 1>&2
  exit 1
fi

#kscript src/test/kotlin/org/kalasim/misc/PatchVersion.kts "${tmdb_version:1}"

git status
git commit -am "${tmdb_version} release"
#git diff --exit-code  || echo "There are uncomitted changes"

git tag "${tmdb_version}"

git push origin 
git push origin --tags


### Build and publish the binary release to maven central
./gradlew install

./gradlew publishToMavenLocal

#./gradlew publishToSonatype closeSonatypeStagingRepository
./gradlew publishToSonatype closeAndReleaseSonatypeStagingRepository
```



