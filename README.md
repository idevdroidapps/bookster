# bookster
Google Books demo

## Specifications / Requirements
As a result from a Books search, the app should:
- Create a search keyword input screen with edit box and search button.
- When the search button is pressed query the API and display a nicely formatted list of returned books, on a separate screen, showing small thumbnail, title, authors, and publish date (any books without a thumbnail should show a placeholder image). The list should be paginated in groups of 20 items (Show a "Load More" button at bottom of list that when clicked appends the next set of items to the list).
- When a searched list item is clicked, launch a nicely formatted details view that shows the details above plus the description and link to the webLinkReader (see returned json data from API) that when clicked opens the link so user can read the book.  It is desirable to make it seem as though the launched external webpage is part of the native app.

## Built With
* [Kotlin](https://kotlinlang.org/)
* [Android Architecture Components](https://developer.android.com/topic/libraries/architecture)
* [Google Books Api](https://developers.google.com/books)

## Instructions
This demo uses the Gradle build system.

1. Download the demo by cloning this repository.
2. In Android Studio, create a new project and choose the "Import Project" option.
3. Select the root directory that you downloaded with this repository.
4. If prompted for a gradle configuration, accept the default settings.
- Alternatively use the gradlew build command to build the project directly.

### Get a Books API key
***This demo app requires that you add your own Google Books API key***
1. Follow instructions at [Get Started on Google Books API](https://developers.google.com/books/docs/v1/using#APIKey) to create your API key
2. Create a file in the root directory called secure.properties (this file should NOT be under version control to protect your API key)
3. Add a single line to secure.properties that looks like BOOKS_API_KEY=YOUR_API_KEY, where YOUR_API_KEY is the API key you obtained in the first step
4. Build and run

## Concerns during Development
- Providing a method for 'injecting' the API KEY so the project could be shared publicly
- Use of the DataBinding Library can often require to rebuild the project in order to generate Implementation classes.

## Testing
An Instrumented test has been recorded to demonstrate the MainActivity functionality
Instrumented tests much be run on a physical device

## Author
* **James Campbell** - *Android Developer* -
