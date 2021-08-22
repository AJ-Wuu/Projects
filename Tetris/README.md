*This is a Tetris Game written in Java.  
It mainly uses JFrame, JPanel and Graphics2D as interface.*

**Composition**  
To form the whole project, we need Driver.java, FrameXXMode.java, ButtonsXXMode.java, and BoardXXMode.java.  
The other .java files are used when building the project. They are not called in the final code, but I decided to upload them anyway 😜  

Mode is an "unnecessary" thing here, the key difference in code between AutoMode and ManualMode is in Board.java (however, due to the fact that Buttons.java takes board as a parameter, it has to have some minor changes between modes, too).  
In AutoMode, I initialize velY to be constantly 1 in paintComponent(), so the shapes will fall without stopping and the user can only move left or right.  
In ManualMode, there is no initialization of velY; instead, the user can move left, right, up and down.  
~~I'm switching modes because I like using ManualMode for debugging purpose~~  


