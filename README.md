Janigma (Java Enigma cipher machine)
====================================

[![Build Status](https://travis-ci.org/fredooo/janigma.svg?branch=master)](https://travis-ci.org/fredooo/janigma) [![Coverage Status](https://coveralls.io/repos/github/fredooo/Janigma/badge.svg?branch=master)](https://coveralls.io/github/fredooo/Janigma?branch=master) [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

A simulator of the Enigma M3 and M4 electro-mechanical rotor cipher machines from World War II.

![Screenshots](https://raw.githubusercontent.com/fredooo/janigma/master/img/screenshots.png)

#### Build and run:

To build this project Apache Maven is required. Simply run `mvn clean package` in the root directory of the project. The file `janigma-X.X.X-SNAPSHOT-jar-with-dependencies.jar` in the target directory is runnable (double-click or launch with `java -jar [file]`).

#### Examples

This implementation is tested with original messages. Some example configurations of the Enigma M3 and M4 are located in the `data` directory. The corresponding inputs can be found in the test code.

#### Goals:

- [x] Simulation of ...
    * [x] Enigma M3 with rotors I to VIII and reflectors A to C
    * [x] Enigma M4 with greek rotors and thin reflectors B and C
- [x] Graphical user interface
- [ ] CLI tool for direct file encryption and decryption
- [ ] Custom character rotors and reflectors
- [ ] Simple text-based console interface

#### Current task:

Implementation of a text-based console interface.

