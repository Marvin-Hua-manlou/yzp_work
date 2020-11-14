package info.gridworld.actor;
import java.awt.Color;

/**
 * A <code>MazeFlower</code> is an actor used for MazBug. <br />
 * The API of this class is testable on the AP CS A and AB exams.
 */

public class MazeFlower extends Flower
{
	public MazeFlower()
    {
        setColor(Color.PINK);
    }
	public MazeFlower(Color initialColor)
    {
        setColor(initialColor);
    }
    @Override
    public void act()
    {
        Color c = getColor();
        setColor(c);
    }
}