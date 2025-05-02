package autonoma.mascotahilo.elements;

import gamebase.elements.SpriteContainer;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Kamii
 */
public class World extends SpriteContainer{
    private Pet pet;

    public World(int x, int y, int height, int width) {
        super(x, y, height, width);
    }

    
        private void addPet() {
        if (pet == null) {
            pet = new Pet(100, 100, Pet.WIDTH, Pet.HEIGHT);
            pet.setGraphicContainer(this);
            pet.setForest(this);  //referencia del Forest donde esta
            sprites.add(pet);
        }
    }
    @Override
    public void paint(Graphics g) {
    }

    @Override
    public void refresh() {
        if(gameContainer != null)
        gameContainer.refresh();
    }

    @Override
    public Rectangle getBoundaries() {
        return new Rectangle(x, y, width, height);
    }
    
}
