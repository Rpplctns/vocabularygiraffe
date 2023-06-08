# Vocabulary Giraffe

Web application for language learning. Users can learn words saved on their account by doing gap-filling excercises provided by Open AI model.

## Setting up the project
### With IntelliJ Idea and WebStorm

After cloning the repository open server folder as IntelliJ Idea project. Wait for Gradle and IntelliJ to process the code. For the server to work properly you have to set up an environmental variable with your OpenAI API key named `OPENAI-API-KEY`. I also recommend overwriting the constant `SECRET_KEY` in file `Config.kt` for the safety ensurance.

Secondly, open client folder as a WebStorm project and wait for IDE to process it. IDE should automatically install node modules required. If necessary, add new run configuration, select npm and set script property to `start`

### With console

Make sure you have all required tools installed. 

To run the client enter the client directory and type in the command `gradle bootRun`. For the server to work properly you have to set up an environmental variable with your OpenAI API key named `OPENAI-API-KEY`. I also recommend overwriting the constant `SECRET_KEY` in file `Config.kt` for the safety ensurance.

To run the client enter the client directory. First install required modules by typing `npm install package.json`. After installing modules you should be able to start the client by typing `npm start`.

### Accessing the database

You can access the database on adress `http://localhost:8080/h2-console`. To access it you have to setup password in the file `application.properties`.
