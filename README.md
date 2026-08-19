# Where Am I Locator

A simple Android application to locate your current coordinates and display them on the screen.

## Features
- Real-time location tracking
- Display Latitude and Longitude
- Easy to use interface

## Tech Stack
- Kotlin
- Jetpack Compose
- Fused Location Provider API

# Where Am I? Locator

An Android app that fetches the device's current location once and displays
latitude, longitude, accuracy, and timestamp.

## How it works
1. User taps "Get My Location".
2. The app checks for `ACCESS_FINE_LOCATION` permission (`PermissionHelper.kt`).
    - If not granted, it requests it at runtime.
    - If denied, a message is shown and the app does not crash.
3. Once permission is confirmed, `LocationRepository.kt` uses the Fused
   Location Provider (`getCurrentLocation`) to fetch a one-time location.
4. `MainActivity.kt` displays the result in four TextViews.

## Project structure
- `MainActivity.kt` — UI wiring and permission flow
- `PermissionHelper.kt` — runtime permission check/request
- `LocationRepository.kt` — Fused Location Provider call
- `activity_main.xml` — layout

## Testing
Run on an emulator and use Extended Controls → Location to set a mock GPS
location, then tap "Get My Location" to verify the values update.

## Team
- Member 1 — Project/Gradle/Manifest
- Member 2 — UI
- Member 3 — Permission handling
- Member 4 — Fused Location Provider
- Member 5 — Display, testing, documentation
