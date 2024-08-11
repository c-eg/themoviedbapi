# Developer Notes
## How to do a release?

1. Make sure to increase version number in [build.gradle.kts](build.gradle.kts)

2. Document updates in [NEWS.md](NEWS.md)

3. Do the release on GitHub

4. Make sure the following Gradle properties are set
```
signing.keyId=
signing.password=
signing.secretKeyRingFile=/secring.gpg
```

5. Publish
```bash
gradle publish
```

5. Close and Release on sonatype
   1. Go to: [Sonatype](https://s01.oss.sonatype.org/#stagingRepositories)
   2. Click on the staging repository
   3. Click on Close
   4. After checks, you can release
   5. Click on Release

## Generating a new signing key
1. Generate the key
```bash
gpg --gen-key
gpg --list-keys --keyid-format short
```

2. Then register the key on a keyserver
```bash
gpg --keyserver keyserver.ubuntu.com --send-keys <keyId>
```

3. Then export it and move it to project base dir (.gitignore will ignore it)
```bash
gpg --export-secret-keys -o secring.gpg
```
