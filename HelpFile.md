<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Activity 2.5</title>
</head>
<body>
<h2> Drag and Drop Help File </h2>

<p> The following steps were followed to create a 2D GUI Drag and Drop Application  </p>

<h3> Project Setup </h3>
<p> 1. Go to File > New > Project > Java > "dragAndDrop" > Finish
<br>2. Add Classes and Test cases
<br>3. Make sure the SDK module is selected to latest available SDK : Go to File -> Project Structure -> Project, and set the project SDK to 14. You can also set the language level to 14.
<br>4. Add dependencies : Go to File > Project Structure > Modules > Dependencies and select the project dependency
<br>5. Add libraries: Go to File > Project Structure > Libraries > Add libraries from the library folder containing the relevant library files
<br>6. Library files required : <b>junit-4.13.jar</b> & <b>hamcrest-all-1.3.jar</b></p>

<h3> Installing Java FX </h3>
<p> 1. Download the appropriate JavaFX SDK for your operating system and unzip it to a desired location
<br>2. Create Library: Go to File -> Project Structure -> Libraries and add the JavaFX 14 SDK as a library to the project. Point to the lib folder of the JavaFX SDK
<br>3. Add VM options: Click on Run -> Edit Configurations... and add these VM options:
    <br>    <b>--module-path "C:\Program Files\Java\openjfx-11.0.2_windows-x64_bin-sdk\javafx-sdk-11.0.2\lib" --add-modules javafx.controls,javafx.fxml</b>
<br>5.Click Run -> Run... to run the project
<br>6. Please refer to <b>FXInstallationHandbook.docx</b> provided in the application folder</p>
<p>Reference: <a href="https://openjfx.io/openjfx-docs/#install-java">openjavafx.io</a></p>

<h3> Guide for Drag and Drop GUI </h3>
<p> Right Click on ToDo.java and run the application. When the GUI pop up appears, it consists of two columns "Available Tasks" and "To Do"
<br>Click and hold the chosen Item on the "Available Tasks" and drag it across to "To Do" and leave the mouse button to drop the item and vice versa
<br>The list box below displays a log of action carried out.</p>

<h3> Additional Documentation </h3>
<p> For UML, Source control details, Debugging and Test Tables please refer to documentation file <b>30007873At2.5.docx</b>. </p>

</body>
</html>