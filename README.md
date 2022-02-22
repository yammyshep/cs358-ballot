# cs358-ballot

## Build Instructions (Command Line)
1. Clone this repository
2. Open the command line, navigate to project folder and type `./gradlew shadowJar` or `gradlew.bat shadowJar` (Windows)
3. Run the jar file by typing `java -jar build/libs/RankedBallots-all.jar`

## Build Instructions (Eclipse)
1. Clone this repository
2. Import project into eclipse as an existing Gradle project
3. Add a new Run Configuration using the project and `BallotCounter` as the main class
4. Run

## Usage
1. Type 'a' then enter, then enter the filename of the ballot
2. Repeat until all ballot files have been entered
3. Type 'c' to compute election, the program will print information about it
4. Type 'w' to view the winner
5. Type 's' followed by a filename to save results to disk
6. Type 'e' to quit or 'n' to start over