# Contributing to ITOBOT FTC

`master` is the stable team integration branch. `platform/2027-v1` is the initial platform integration branch. Use short-lived `feat/*`, `fix/*`, `chore/*`, or `docs/*` branches and merge reviewed pull requests into the current integration branch.

Keep production robot code under `TeamCode/src/main/java/org/firstinspires/ftc/teamcode`. Avoid modifying shared FTC SDK files and `FtcRobotController`; platform code should depend on FTC APIs only through small adapters where practical.

Every change should compile, and pure logic changes need JVM tests. Hardware changes must identify the configuration names and physical calibration steps in `RobotConfig` and `docs/HARDWARE.md`. Never encode motor inversion in `MecanumKinematics`.

Before requesting review, run:

```powershell
./gradlew :TeamCode:testDebugUnitTest
./gradlew :TeamCode:assembleDebug
```
