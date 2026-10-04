# Branching and upstream updates

`master` is stable. Use `feat/*`, `fix/*`, `chore/*`, and `docs/*` branches for focused work. `platform/2027-v1` is the initial Platform V1 integration branch; do not introduce a permanent `develop` branch without a demonstrated workflow need.

For an FTC SDK update, fetch `FIRST-Tech-Challenge/FtcRobotController` as `upstream`, create an integration branch from the target team branch, merge the official release, preserve `TeamCode` and team docs, and run the full checks. Review all shared Gradle and `FtcRobotController` changes carefully; team features belong in TeamCode.
