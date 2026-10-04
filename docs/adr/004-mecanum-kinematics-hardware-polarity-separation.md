# ADR 004: Separate mecanum math from hardware polarity

The MEB mixer is pure and outputs FL/FR/RL/RR mathematical demands. Physical inversions are configured in `RevMecanumDriveIO` through motor direction, preserving reproducible math and safer diagnosis.
