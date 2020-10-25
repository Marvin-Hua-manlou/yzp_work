import info.gridworld.actor.Bug;

/**
 * A <code>BoxBug</code> traces out a square "box" of a given size. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class SpiralBug extends Bug
{
    private int step;
    private int sideLen;

    /**
     * Constructs a box bug that traces a square of a given side length
     * @param length the side length
     */
    public SpiralBug(int len)
    {
        step = 0;
        sideLen = len;
    }

    /**
     * Moves to the next location of the square.
     */
    public void act()//基本代码前面没变，在每一次转角之后，转角走的边长加一，效果跟给的例子差不多
    {
	        if (step < sideLen && canMove())
	        {
	            move();
	            step++;
	        }
	        else
	        {
	            turn();
	            turn();
	            sideLen++;
	            step = 0;
	        }
    }
}
