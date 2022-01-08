# LDTS_T08_G07 - Jetpack Joyride

## Game Description

This project is based on the popular Android game Jetpack Joyride where the player, who has just stolen a jetpack, needs to escape from a laboratory. To achieve that, the player needs to dodge from obstacles like lasers and missiles. The game has no levels: the objective is to survive the longest time.

## Implemented Features

- **Player movement**: when the space bar is pressed, the player moves to one unit higher and when there is no input from the user, the player starts to fall
- **Obstacles**: As the game progresses, the obstacles (lasers and missiles) are generated randomly

## Planned Features

- **Coins**: The map will have coins spread through the map that can be caught by the player
- **Menu**: A menu to start the game or to quit
- **Reset**: The player will have a chance to replay the game when it looses
- **Distance and Coins Counter**: There will be a counter to inform the player of the coins it has collected so far and the distance it has travelled

## Design
#### THE VIEW AND THE MODEL OF THE GAME SHOULD BE INDEPENDENT (the model doesn't need to know how the game will be displayed)
**Problem in context**
To make the code more modular and to minimize the dependencies between the several components of the game, we chose to use the MVC design pattern, so that multiple students can work at the same time without existing conflicts. Besides, as the model doesn't need to know how to draw itself, makes sense to separate those aspects of the game, in order to make the code more organized.


**Problem in context**

When running our game, there is the constant need of creating obstacles and these for the time being are Lasers and Rockets. By not implementing the factory pattern, if we decide to remove, change or add a different type of obstacle, it will be required to change the bulk of our code, making our program highly unhandy.

**The Pattern**

The factory pattern replaces the direct object construction calls (using the new operator) with calls to a special factory method. This way we can easily change the behaviour of our obstacles (products), as well as, add and remove obstacles, and allows for simpler client code.

**Implementation**

To be implemented.

**Consequences**

- Avoid tight coupling between the creator and the concrete products.
- Make it possible to introduce new types of products into the program without breaking existing client code.


### General Structure
![UML Diagram](UML.png)

## Known Code Smells And Refactoring Suggestions
### Alternative Classes with different Interfaces
Two classes perform identical functions but have different method names. This code smell can be seen in our project on the element class and obstacle interface, in which their shared attribute (Position) can be same by making the obstacle an extension of the element class.
###Switch statements
The methods of the class Laser are legislated by the orientation of the laser and these to be longer and full of the switch statements, which can be hard to work on the future. This situation asks for polymorphism to simplify new implementations. 
###Messages Chains
This code smell happens when methods rely on the other methods of other classes repeatedly. As stated before, if the obstacles were a subclass of element this wouldn't happen because this can fix the need to call numerous methods just to get a position of an element in the game.
## Testing

### Link to mutation testing report
[Mutation Test](https://github.com/FEUP-LDTS-2021/ldts-project-assignment-g0807/tree/master/build/reports/pitest/202201081711)

## Self-evaluation

