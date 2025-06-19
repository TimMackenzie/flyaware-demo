# FlyAware – Android Sample Project
Android take home assessment: Let users enter specific airports (ICAO/IATA codes) they care about and monitor condition updates.

## Project Overview

**FlyAware** is a sample Android app that consumes aviation weather data from [AviationWeather.gov](https://aviationweather.gov/data/api/). The app enables users to monitor real-time METARs, TAFs, and hazard advisories for their favorite airports.

It's designed to demonstrate:
- Clean, scalable Android architecture
- Modern Jetpack Compose UI
- Real-time data polling and meaningful background notifications

---
## Candidate notes

Due to requested time constraints, a subset of the described functionality could be completed
- Material 3 Compose UI with 
-- METAR and flight rules data display for selected ICAOs
-- Add/remove for ICAOs
- MVVM and Clean architecture
- KTOR for network interaction
- Room storage for user-selected ICAOs
- Hilt for DI

Some items were not completed
- Workmanager integration
- Full METAR and TAF detail screens
- TAF summary
- SIGMET and AIRMET live monitoring

### Setup/Build notes
  No additional setup is necessary beyond standard setup in Android studio.  This app has been tested on API 36.  It may have issues with Android 7 due to networking security measures.

  The app starts with no data, but example codes include KPHX, KSFO, and KLAX.  The only validation is for length, so invalid 4 letter combinations will show no data without an error message.

### Notes on implementation
Additional work would be needed to identify or track down some missing fields in the transformer, which for example leaves the flight rules as "UNKNOWN" in all cases.  I have the mechanism to read TAF from the API but it is not used in this iteration.
I mostly started at the data layer and moved up, so there are more tests at that level.  Some work was done to move strings into a resource file along with other production-friendly actions, but this effort is incomplete.  I also had a number of plans for final clean up were left incomplete including moving further logic into the domain layer. Complications involving Workmanager interaction with Hilt ate up remaining time, so no further features were added. 

-- END candidate notes

## Technologies Used
- **Kotlin**
- **Jetpack Compose** (Material 3)
- **MVVM + Clean Architecture**
- **Kotlin Flow**
- **HILT** for DI
- **Ktor** for network communication
- **WorkManager** / Foreground services
- **DataStore** or Room (depending on branch)

---

## Assessment Expectations
This assessment is targeted at senior Android developers. You are expected to demonstrate not only implementation skills, but also thoughtful architectural decisions, modularity, and maintainability. Clean, idiomatic Kotlin and clear structure are just as important as feature completeness.

Make a copy of this Template Repository to your own GitHub account and work within it. Please push early and often though you will be submitting a single compressed artifact of the repository (see Expected Deliverables below).

### Expected Deliverables
Please build on the provided skeleton application found in `FlyAware/` and build the features listed below.

When you are done please return a .tar.gz or .zip compressed copy of your work (including the .git/ directory) to the hiring manager by way of email.  We will assess the contents of your copy of the template but will have the compressed artifact as a backup plan.

Be sure to update the README with 
- Setup/Build instructions
- Brief notes on implementation
- Any assumptions made in the course of completing the exercise

#### Core Features
- Allow users to add and remove airports by ICAO code
- Display a list of saved airports with:
- Latest decoded METAR (including wind, visibility, ceiling, temp)
- Visual badge or color to indicate current flight rules (VFR/MVFR/IFR/LIFR)
- Tapping an airport opens a detail screen with full METAR + TAF
- Show next 6–12 hours of forecast conditions (TAF summary acceptable)

#### Background Data Refresh
- Use WorkManager or a foreground service to poll the AviationWeather.gov API every 15 minutes
- If API updates where active on device restart, restart the poll
- Notify the user when:
- - A new SIGMET or AIRMET appears in the vicinity

#### Architecture & Code Quality
- Follow MVVM + Clean Architecture principles:
    - Clearly separated Data, Domain, and Presentation layers
    - UseCases to encapsulate logic
- Use HILT for dependency injection
- Use Ktor for network requests and serialization
- Manage UI state with Kotlin Flow / StateFlow

#### Persistence
- Store user-selected airports using DataStore or Room
- Optionally cache the last known weather data for offline viewing

#### Bonus Enhancements
If time permits, the following additions are welcome:
- ICAO code auto-complete or validation
- SIGMET/PIREPs displayed in the detail screen
- Modular structure (e.g., separate features or libraries)
- Unit tests for at least one ViewModel or UseCase
- Themed Compose UI with Material 3 and dark mode
- Support for tablets or landscape mode
- Markdown or HTML documentation in the app for weather codes


## License
This project is provided for educational and evaluation purposes only. Not for production use.

---

## Questions?
If you have any questions about the FlyAware exercise please reach out to your recruiter and they will connect you to an engineer to support

