Janigma (Java Enigma cipher machine)
------------------------------------

A simulator of the Enigma M3 and M4 electro-mechanical rotor cipher machines from World War II. The Enigma M3 and M4 simulation and a simple GUI are implemented, but unverified and untested.

##### Current task:

Testing and refactoring of all existing components.

##### Goals:

* Clean and simple implementation of
    - Enigma M3 with rotors I to VIII and reflector A to C
    - Enigma M4 with reflector B and C
    - Interface for custom character rotors and reflectors
* Graphical user interface built with Swing
* Simple text-based console interface
* CLI tool for direct file encyption and decryption

##### Build and run:

Simply run `mvn package` in the root directory of the project. The file *janigma-X.X.X-SNAPSHOT-jar-with-dependencies.jar* in the target directory is runnable (double-click or launch with `java -jar [file]`).
