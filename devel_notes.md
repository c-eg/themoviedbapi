# Developer Notes
## How to do a release?

1. Make sure to increase version number in [build.gradle.kts](build.gradle.kts), commit and push the version to the `master` branch

2. Document updates in [NEWS.md](NEWS.md)

3. Do the release on GitHub

4. Make sure the prerequisites are met that are listed below

5. Clean, publish and deploy
```bash
./gradlew clean && ./gradlew publish && ./gradlew jreleaserDeploy
```

6. Go to [Maven Central Repository](https://central.sonatype.com/publishing/deployments) and verify the deployment was successful


## Prerequisites
### GPG & PGP Keys
Make sure you have the following keys exist:

- `C:/gpg/private.pgp`
- `C:/gpg/public.pgp`
- `C:/gpg/secring.gpg`

### Gradle Properties
Make sure you have the following in your global gradle.properties file `C:\Users\<user>\.gradle\gradle.properties`:

```properties
signing.keyId = <value>
signing.password = <value>
signing.secretKeyRingFile = C:/gpg/secring.gpg
```

### JReleaser Properties
Make sure you have the following in your jreleaser.properties file `C:\Users\<user>\.jreleaser\config.properties`:

```properties
JRELEASER_GPG_PUBLIC_KEY = C:/gpg/public.pgp
JRELEASER_GPG_SECRET_KEY = C:/gpg/private.pgp
JRELEASER_GPG_PASSPHRASE = <value>
JRELEASER_GITHUB_TOKEN = <value>
JRELEASER_MAVENCENTRAL_SONATYPE_USERNAME = <value>
JRELEASER_MAVENCENTRAL_SONATYPE_PASSWORD = <value>
```

Note: `JRELEASER_GITHUB_TOKEN` does not need a valid value, it just needs a non-empty value set.

If you are setting this up for the first time, you can verify your config is configured correctly with:
```bash
./gradlew jreleaserConfig
```