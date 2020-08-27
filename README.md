# Movies test

Is a project to apply to M company. This project call an API which have movies, the main idea is get the movies and save locally, therefore we can shop the movies and send this to shopping market area in a local database.

## Technical requirements

Android Studio 3.6.2
Kotlin

## DeepLink usage

1. Go to "http://deeplink.masdomicilios.club/deeplink.html" in the navigator of the Emulator or Device
2. Click over the image which contains the link to open the deeplink content
3. Enjoy the screen with the movie content

## Architecture
1. layer1 - ui : This layer contains the fragments and viewmodel in order to get Data and present it at the view
2. layer2 - domain: This layer cointains the uses cases and transform the database entities to return data to layer1
3. layer3 - data: This layer provides a repository and DAO to access SqlLite Database and return entities from database
<img src="https://github.com/criferlo2/movietesttojob/blob/master/merqueo-arquitecture.jpg">

## License
[MIT](https://choosealicense.com/licenses/mit/)