# Angry Birds
___
Angry Birds is a popular puzzle video game. It has a simple story: the pigs stole the birds’ eggs, so the birds try to get them back. In every level, the player uses a slingshot to launch the birds at the pigs that are standing on buildings made of blocks. Each level gives the player a line of birds; the bird sitting on the slingshot is launched when the player pulls the bird back. The bird behind it jumps up, and it is the next bird to be launched. When a bird is launched, it can hit the buildings and make the blocks break. This can make the pigs fall and disappear. The bird can also hit the pig itself to destroy it. The player has to destroy all the pigs in the map to unlock another level.

## Content
___
1. [Classes](#classes)
2. [Use Case](#use-case)
3. [UML](#uml)
4. [Learning Sources](#learning-resources)
5. [Assets](#assets)
6. [How to Run](#how-to-run)
7. [Github Link](#github-link)

## Classes
___
1. ### `Bird`
   Interface for various types of birds.
2. ### `BlackBird`
   Helps in drawing black bird with and without slingshot.
3. ### `BlueBird`
   Helps in drawing blue bird with and without slingshot.
4. ### `RedBird`
   Helps in drawing red bird with and without slingshot.
5. ### `YellowBird`
   Helps in drawing yellow bird with and without slingshot.
6. ### `Materials`
   Interface for various types of materials.
7. ### `Log`
   Helps in drawing log.
8. ### `Box`
   Helps in drawing wood box.
9. ### `ExitPage`
   Helps to set exit page to the game screen.
10. ### `Pig`
    Helps to draw pig on screen.
11. ### `KingPig`
    Helps to draw a big pig on screen.
12. ### `LevelFailed`
    Helps to set level failed on screen when no bird is left.
13. ### `Levelone`
    Helps to play level one on screen.
14. ### `LevelPassed`
    Helps to set level passed on screen when no bird is left.
15. ### `LevelScreen`
    Helps in setting game screen to the screen to choose levels.
16. ### `LevelStructure`
    Helps in setting up the blocks of the level.
17. ### `Leveltwo`
    Helps to play level two on screen.
18. ### `Levelthree`
    Helps to play level three on screen.
19. ### `LoadingScreen`
    Helps to load the login screen with loading bar.
20. ### `Login`
    Login Screen.
21. ### `Login_or_Signup`
    Screen to ask user for login or signup.
22. ### `Main`
    Helps in running the program.
23. ### `MenuScreen`
    Helps in setting the screen to play the game.
24. ### `PauseScreen`
    Helps in pausing the game in between.
25. ### `SavingPage`
    Helps in setting the page to save data.
26. ### `SettingPage`
    Helps to set the screen to setting page.
27. ### `Signup`
    Helps in signup.
28. ### `VerticalLog`
    Helps in drawing vertical log.
29. ### `MusicHandler`
    Handles the saving and loading of music settings.
30. ### `RandomLevel`
    Generates a random level with different birds and structures.
31. ### `Leveltwoload`
    Loads the second level of the game, including its structures and birds.
32. ### `Levelthreeload`
    Loads the third level of the game, including its structures and birds.
33. ### `Leveloneload`
    Loads the first level of the game, including its structures and birds.
34. ### `CheckCollision`
    Checks for collisions between birds and structures, updating the game state accordingly.
35. ### `SoundHandler`
    Handles the saving and loading of sound settings.
36. ### `StartHandler`
    Handles the start screen, allowing users to start the game or exit.
37. ### `Stonebox`
    Represents a stone box in the game, including its rendering and interactions.
38. ### `Glassbox`
    Represents a glass box in the game, including its rendering and interactions.
39. ### `Container`
    Class that stores the game state, including the current level, score, and number of birds, it attributes to the game, boxes.

## Use Case
___
![image](usecase.png)

## UML
___
![image2](UML_class_Diagrma_2.png)

## Learning Resources
___
1. [LibGDX](https://libgdx.com/dev/)
2. [LibGDX Wiki](https://libgdx.com/wiki/)
3. [LibGDX GitHub](https://github.com/libgdx/libgdx)
4. [YouTube Tutorial](https://www.youtube.com/watch?v=YCGmXVCvogY&list=PLrnO5Pu2zAHKAIjRtTLAXtZKMSA6JWnmf&ab_channel=HollowBit)

## Assets
___
1. [Angry Birds Wiki](https://angrybirds.fandom.com/wiki/Angry_Birds_Wiki)
2. [Vecteezy](https://www.vecteezy.com/free-vector/angry-birds-game)
3. [Angry Birds Fanon](https://angrybirdsfanon.fandom.com/wiki/Angry_Birds_Survival?file=71AB7876-462E-419B-B06C-711C6EFCBC35.png)
4. [Travis Ruiz](https://travisruiz.tumblr.com/post/144657042173/the-angry-bird-movie-world-premiere-a-little)
5. [DeviantArt](https://www.deviantart.com/camarasketch/art/Naboo-Grass-Plains-430336300)
6. [Pinterest](https://in.pinterest.com/pin/angry-birds-toons-backgrounds-on-behance--269371621454537528/)

## How to Run
___
- Unzip the file.
- Go to lwjgl3.
- Then src.
- Then main.
- Then java.
- Then com.AngryBirds.lwjgl3.
- Then Lwjgl3Launcher.
- Then click on Run.


## Github Link
___
[GitHub Repository](https://github.com/heckerm4n/Angry-Birds)

## Creators
___
- Jatin Aggarwal (2023258)
- Anant Gyan Singhal (2023082)
