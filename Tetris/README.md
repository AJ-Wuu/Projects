***This is a Tetris Game written in Java.  
It mainly uses JFrame, JPanel and Graphics2D as interface.***

**Composition of the Project**  
To form the whole project, we need Driver.java, FrameXXMode.java, ButtonsXXMode.java, and BoardXXMode.java.  
The other .java files are used when building the project. They are not called in the final code, but I decided to upload them anyway 😜  
Mode is an "unnecessary" thing here, the key difference in code between AutoMode and ManualMode is in Board.java (however, due to the fact that Buttons.java takes board as a parameter, it has to have some minor changes between modes, too).  
In AutoMode, I initialize velY in paintComponent(), so the shapes will fall without stopping and the user can only move left or right.  
In ManualMode, there is no initialization of velY; instead, the user can move left, right, up and down.  
~~I'm switching modes because I like using ManualMode for debugging purpose~~  

**How This Game Works**  
See *Frame.java instruction()*  

**Reflection on the Project**  
1. My shapes do not move per square, but move in length of 1, so it's a little bit too tricky to align them together and get a full line.  
2. Board.java may not be in the perfect looking, as I didn't collect the relative position information of each square of a shape in an optimal way.  
3. I found this code after finishing mine: https://zetcode.com/javagames/tetris/. It's not running as it should be on my end somehow, but I believe it's a good reference for later comparasion.  

**Soliloquy**  
First time using Java GUI and first time building a game. Very exciting and very tiring. I could think of several different improvements (like how to optimize the structure in Board.java), but I'll save them now. This game has taken me four days, so I think it's the best (for both of us LOL) to cool down a little bit with other things.  
It feels GREAT to do a whole small project on my own. At the beginning I thought this should be one of the simplest game, but it turns out that things always bite you in an unexpected situation. I learned by myself for all the problems, and I got to know so much more about Java GUI.  
All in all, I'm so glad I decided to write this instead of letting it pass by reading sample codes ~~(mainly because there isn't any)~~. Maybe I'll come back for it in the future to make it better.
