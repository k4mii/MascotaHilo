package qbertgame;

import autonoma.mascotahilo.elements.World;
import autonoma.mascotahilo.ui.GameWindow;

/**
 *
 * @author Kamii
 */
public class Main {
    public static void main(String[] args)
    {
        World world = new World(0, 0, 500, 500);
        
        GameWindow window = new GameWindow();
        window.setWorld(world);
        world.setGraphicContainer(window);
        window.setSize(500, 500);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }    
}
