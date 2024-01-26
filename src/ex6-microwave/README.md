# Microwave Model

This example represents a simple microwave controller modeled as a finite state machine using the following state variables:

- `Door: {Open, Closed}` -- sensor input indicating state of the door
- `Button: {None, Start, Stop}` -- button press (assumes at most one at a time)
- `Timer: 0...999` -- (remaining) seconds to cook
- `Cooking: Boolean` -- state of the heating element

The model is written in the language used by the NuSMV model checker.
