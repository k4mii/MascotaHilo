package autonoma.mascotahilo.elements;

import gamebase.elements.Sprite;
import gamebase.elements.SpriteContainer;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Kamii
 */
public class World extends SpriteContainer{
    //esta null
    private Pet pet;

    public World(int x, int y, int height, int width) {
        super(x, y, height, width);
        //Como es null entonces al hacer el addpet y es null lo crea.
        addPet();
    }

    
    private void addPet() {
        if (pet == null) {
            pet = new Pet(100, 100, Pet.WIDTH, Pet.HEIGHT);
            pet.setGraphicContainer(this);
            pet.setWorld(this);  //referencia del World donde esta
            sprites.add(pet);
        }
    }
    @Override
    public void paint(Graphics g) {
        // Painting the floor        
        g.setColor(color);
        g.fillRect(x, y, width, height);

        // Painting the meats (sprites in general)
        for(Sprite sprite : sprites)
        {
            sprite.paint(g);
        }
        
        // Painting the dino (player)
        if (pet != null) {
            pet.paint(g);
        }
    }
    //	Para acceder al Pet desde fuera de World
    public Pet getPet() {
        return this.pet;
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
