# ADR 002: Use small subsystem ports and adapters

Hardware dependencies sit behind narrow interfaces such as `DriveIO`, `HeadingSource`, `Localizer`, and `VisionSystem`. This supports JVM testing without framework ceremony or a DI container.
