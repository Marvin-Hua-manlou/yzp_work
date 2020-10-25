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
    public void act()//��������ǰ��û�䣬��ÿһ��ת��֮��ת���ߵı߳���һ��Ч�����������Ӳ��
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
