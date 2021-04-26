package Objects;
import java.util.Random;

import javax.swing.ImageIcon;

public abstract class Brick extends Sprite implements Cloneable{

    private boolean destroyed;
    protected int HP;
    protected int score;
    protected String path;
    protected boolean CanMove;

    public Brick(int x, int y) {
        initBrick(x, y);
    }

    private void initBrick(int x, int y) {
    	setX(x);
    	setY(y);
        setXDir(2);
        destroyed = false; 
        CanMove = true;

        Random rand = new Random();

        setCanMove(rand.nextInt(5) == 1);
    }

    public void loadImage() {
        var ii = new ImageIcon(path);
        image = ii.getImage();
        getImageDimensions();
    }

    public void setCanMove(boolean val){
        CanMove=val;
    }
    
    protected void SetPath(String path){
        this.path = path;
    }

    public boolean isDestroyed() {
        return destroyed;
    }
    public void setDestroyed(boolean val) {
        destroyed = val;
        Player.getPaddleInstance().setScore(Player.getPaddleInstance().getScore()+this.score);
    }

    public void DecreaseHP(){
        if(HP>0) {
            HP = HP-1;
        }
        if(HP==0){
          setDestroyed(true);
        }
    }
    public abstract void updateImage();
    public void setBreakable(){
        HP=1;
    }

    public void move(){
        if(CanMove){
            setX(getX()+getXDir());
        }
    }
    public void ChangeDirection(String direction){
        if(direction.equalsIgnoreCase("left"))    
            setXDir(-1*Math.abs(getXDir()));
        else if(direction.equalsIgnoreCase("right"))
            setXDir(Math.abs(getXDir()));
        else {
            System.out.println("There is an error in brick Class Change Direction");
        }
    }
    @Override
    public Brick clone() throws CloneNotSupportedException {
        return (Brick) super.clone();
    }
}