# Developer Notes

Releases and snapshots are published automatically via GitHub Actions. The
manual/local process is still possible and documented at the end as a fallback.

## How to do a release?

Releases are handled by the [Release to Maven Central](.github/workflows/release.yml)
workflow, triggered by pushing a version tag.

1. Bump the version in [build.gradle.kts](build.gradle.kts) to the release value
   (e.g. `2.7.0`, **no** `-SNAPSHOT` suffix). Commit and push to `master`.

2. Create and push a matching `v<version>` tag:
   ```bash
   git tag v2.7.0
   git push origin v2.7.0
   ```

3. The workflow then automatically:
   - stages the artifacts,
   - GPG-signs them and deploys to Maven Central via JReleaser,
   - creates a GitHub release with auto-generated changelog notes.

4. Verify the deployment at the
   [Maven Central Repository](https://central.sonatype.com/publishing/deployments).

> The git tag should match the version in `build.gradle.kts`. Snapshot-suffixed
> tags (`v*-SNAPSHOT`) are ignored by this workflow and handled by the snapshot
> workflow instead.

## How to publish a snapshot?

Snapshots are handled by the
[Publish Snapshot to Maven Central](.github/workflows/snapshot.yml) workflow.
They are deployed directly via `maven-publish` (no signing, no validation) to the
[Central Portal snapshot repository](https://central.sonatype.com/repository/maven-snapshots/).

1. Set the version in [build.gradle.kts](build.gradle.kts) to a `-SNAPSHOT` value
   (e.g. `2.7.0-SNAPSHOT`). Commit and push to `master`.

2. Trigger the workflow either by:
   - pushing a `v*-SNAPSHOT` tag (e.g. `v2.7.0-SNAPSHOT`), or
   - running it manually from the GitHub **Actions** tab (Run workflow).


## Required GitHub repository secrets

The release and snapshot workflows read credentials from repository secrets
(**Settings → Secrets and variables → Actions**):

| Secret                  | Used by            | Description                                          |
|-------------------------|--------------------|------------------------------------------------------|
| `GPG_PUBLIC_KEY`        | release            | ASCII-armored public key **contents**                |
| `GPG_SECRET_KEY`        | release            | ASCII-armored private key **contents**               |
| `GPG_PASSPHRASE`        | release            | Passphrase for the private key                       |
| `MAVENCENTRAL_USERNAME` | release + snapshot | Sonatype Central Portal token username               |
| `MAVENCENTRAL_PASSWORD` | release + snapshot | Sonatype Central Portal token password               |

`GITHUB_TOKEN` is provided automatically by GitHub Actions; no secret needed.

> Signing uses JReleaser **MEMORY** mode, so `GPG_PUBLIC_KEY` / `GPG_SECRET_KEY`
> hold the armored key *contents*, not file paths. Export them with:
> ```bash
> gpg --armor --export <key-id>            # -> GPG_PUBLIC_KEY
> gpg --armor --export-secret-keys <key-id> # -> GPG_SECRET_KEY
> ```
