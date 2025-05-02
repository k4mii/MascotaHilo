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
    public static final int WIDTH = 100;
    public static final int HEIGHT = 100;
    
    protected long delay;
    private boolean running;
    private boolean paused;
    protected Thread thread;
    private int targetX;
    private int targetY;
    //Se agrega el World  para poder perseguir a el mouse, saber donde esta  (su refrencia)
    //No se hizo en World porque el movimiento del Qbert ocurre de forma independiente
    private World world;
    private Image QbertImage;
    public Pet(int x, int y, int height, int width) {
        super(x, y, height, width);
        
        QbertImage = new ImageIcon(getClass().getResource("/autonoma/pethilo/images/Qbert.png")).getImage();
        setStep(30); // se mueve 3 pixeles por paso
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
        int dx = targetX - x;
        int dy = targetY - y;

        double distance = Math.sqrt(dx * dx + dy * dy);

        if (distance < step) return false;

        double dirX = dx / distance;
        double dirY = dy / distance;

        int nx = x + (int)(dirX * step);
        int ny = y + (int)(dirY * step);

        if (!isOutOfGraphicContainer(nx, ny, width, height)) {
            x = nx;
            y = ny;

            if (gameContainer != null)
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
            move();

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
    public void setWorld(World world) {
        this.world = world;
    }
    //Para que la mascota sepa a donde es que debe seguir el cursor
    public void setTarget(int x, int y) {
        this.targetX = x;
        this.targetY = y;
    }
}
