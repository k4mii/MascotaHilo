package autonoma.mascotahilo.elements;

import gamebase.elements.SpriteMobile;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Kamii
 */
public class Pet extends SpriteMobile implements Runnable{
    public static final int WIDTH = 40;
    public static final int HEIGHT = 40;
    
    protected long delay;
    private boolean running;
    private boolean paused;
    protected Thread thread;
    //Se agrega el Forest  para poder perseguir a el velociraptor, saber donde esta  (su refrencia)
    //No se hizo en Forest porque el movimiento del dinoDanger ocurre de forma independiente
    private World world;
    private Image QbertImage;
    public Pet(int x, int y, int height, int width) {
        super(x, y, height, width);
        
        QbertImage = new ImageIcon(getClass().getResource("/autonoma/pethilo/images/Qbert.png")).getImage();
        setStep(3); // se mueve 3 pixeles por paso
        setDelay(100); // espera 1000 ms (1 segundo) entre pasos
        
        thread = new Thread(this);
        thread.start();
        
        running = false;
        paused = false;
    }

    
    
    @Override
    public void paint(Graphics g) {
        g.drawImage(QbertImage, x, y, width, height, null);
    }

    
    private boolean move()
    {
        int direction = (int)(Math.random() * 4);
        
        int nx = x;
        int ny = y;
        
        switch(direction)
        {
            case 0:     // UP
                ny -= step;
            break;

            case 1:     // DOWN
                ny += step;
            break;

            case 2:     // LEFT
                nx -= step;
            break;

            case 3:     // RIGHT
                nx += step;
            break;
            
            default:
                System.err.println("ERROR: Troll.move  Unknown direction.");
            break;
        }
        
        if(!isOutOfGraphicContainer(nx, ny, width, height))
        {
            x = nx;
            y = ny;

            if(gameContainer != null)
                gameContainer.refresh();
            
            return true;
        }
        
        return false;
    }
    
    @Override
    public void run() {
        running = true;

        while (isRunning()) {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException ex) {}

            if (isPaused()) continue;


        }
    }


    public boolean isRunning() {
        return running;
    }

    public void stop() {
        this.running = false;
    }

    public boolean isPaused() {
        return paused;
    }

    public void pause() {
        this.paused = true;
    }

    public void unpause() {
        this.paused = false;
    }

    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }
    
    public int getStep(){
        return step;
    }
    public void setForest(World world) {
        this.world = world;
    }
}
