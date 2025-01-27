# Podcasts App

A simple two-screen podcasts app built using Kotlin and Jetpack Compose for Android. This app allows users to browse a list of podcasts, view details about individual podcasts, and mark them as favourites.

## Features

### Screen 1: Podcast List
- Displays a list of podcasts fetched from the [Listen Notes API](https://www.listennotes.com/api/docs/?lang=kotlin&test=1#get-api-v2-best_podcasts).
- Each list item shows:
  - Podcast thumbnail
  - Podcast title
  - Publisher name
  - "Favourited" label (visible only for favourited podcasts).
- Supports pagination, loading 10 items at a time.

### Screen 2: Podcast Details
- Displays detailed information about the selected podcast:
  - Title
  - Publisher name
  - Thumbnail
  - Description
- Includes a Favourite button with two states:
  - "Favourite": Marks the podcast as a favourite.
  - "Favourited": Indicates the podcast has been marked as a favourite.
- The favourite state is persistent and saved locally.

## Technical Details
- **Programming Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **API**: Listen Notes Mock Server

## Setup Instructions
1. Clone this repository to your local machine.
2. Open the project in Android Studio.
3. Sync the Gradle files to ensure all dependencies are installed.
4. Run the app on an emulator or physical device.

## API Integration
- The app uses the Listen Notes Mock Server to fetch test podcast data.
- No API key is required for this implementation.

## Persistence
- Favourite states are stored locally using SharedPreferences.

## Pagination
- The app implements lazy loading to fetch 10 podcast items at a time as the user scrolls through the list.

## Dependencies
- **AGP**: 8.8.0
- **Kotlin**: 2.0.0
- **Jetpack Compose BOM**: 2025.01.00
- **Coil Compose**: 3.0.4
- **Coil Network OkHttp**: 3.0.4
- **DataStore Preferences**: 1.1.2
- **Hilt Navigation Compose**: 1.2.0
- **Core KTX**: 1.15.0
- **Kotlinx Serialization JSON**: 1.7.3
- **Lifecycle Runtime KTX**: 2.8.7
- **Activity Compose**: 1.10.0
- **Logging Interceptor**: 4.12.0
- **Navigation Compose**: 2.8.5
- **Retrofit**: 2.11.0
- **Hilt**: 2.51.1
