# better-sat-solver

Checks whether a given well-formed formula of Propositional Logic in Clausal Normal Form is satisfiable, and if so, produces a satisfying interpretation.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.
See deployment for notes on how to deploy the project on a live system.

### Prerequisites

* Java Development Kit

### Compile and Run
* Clone project into your own folder.
* Compile with <b>javac Main.java </b>
* Run using <b>java Main</b> and follow instructions.

## Features
* Create maximum invalid clause-set, given a number of literals.
* Create maximum satisfiable clause-set, given a number of literals.
* Create clause-set given a String-representation of a propositional formula in CNF.
* Brute-force a satisfiable interpretation (if one exist) for a given clause-set.

## TODO
* Check if a formula is in CNF.
* Convert an arbitrary propositional formula to CNF.
* Add a better algorithm for satisfiability checking (e.g., CDCL).
* Add support for Modal operators.
* Add support for First-Order quantifiers.

## Built With

* [Java SE 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

## Contributing

Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code of conduct, and the process for submitting pull requests to us.

## Authors

* **Henrik Torland Klev** - *Initial work* - [torland-klev](https://github.com/torland-klev)

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details

## Acknowledgments

* [M. Giese](https://www.mn.uio.no/ifi/personer/vit/martingi/index.html) for teaching me Propositional Logic.
