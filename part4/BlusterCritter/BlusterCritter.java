import info.gridworld.actor.Actor;
//import info.gridworld.actor.Rock;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Location;
import java.util.ArrayList;
import java.awt.Color;
public class BlusterCritter extends Critter{
	 private int numFactor;//当前的输入的需求数目，用来判断周围数量是多还是少
	 //多的时候变亮，少的时候变暗
	
	 public BlusterCritter(int c,Color col){//构造函数
		 super();
		 numFactor = c; //数量
		 setColor(col);//颜色
	 }
	 
	 //getActor()函数，用来获得周围的对象的一个list(如果存在)，不存在返回空值
	 public ArrayList<Actor> getActors(){
		 ArrayList<Actor> actors = new ArrayList<Actor>();
		
		 Location loc = getLocation();//获得当前的位置
		 for(int r = loc.getRow() - 2; r <= loc.getRow() + 2; r++ ){//周围两个单元的范围检索
			 for(int c = loc.getCol() - 2; c <= loc.getCol() + 2; c++){
				 Location tempLoc = new Location(r,c);
				 
				 if(getGrid().isValid(tempLoc)){//判断在咒怨两个单元的合理性
					 Actor a = getGrid().get(tempLoc);
					 if(a != null && a != this)
						 actors.add(a);
				 }
			 }
		 }
		 return actors;
	 }
	 
	 public void processActors(ArrayList<Actor> actors){
		 int count = 0;
		 for(Actor a: actors)
			 if(a instanceof Flower)//这里改一下，把题目要求的生物改成花儿，因为生物生成就需要移动，不好观察。
				 count++;
		 if(count < numFactor)//执行明暗色彩
			turn_light();
		 else
			 turn_dark();
	 }
	 
	 private void turn_dark(){//变暗的函数
		 Color c = getColor();
		 int red = c.getRed();
		 int green = c.getGreen();
		 int blue = c.getBlue();
		 if(red > 0) red -= 5;//跨度大一点，变化会明显
		 if(green > 0) green -= 5;
		 if(blue > 0) blue -= 5;//虽然RGB设置成为5也可能滨化很慢，所以有需要可以设置再大一些
		 setColor(new Color(red, green, blue));
	 }
	 
	 private void turn_light(){//变量函数
		 Color color = getColor();
		 int red = color.getRed();//获得RGB值,可以再大一点，变化比较明显
		 int green = color.getGreen();
		 int blue = color.getBlue();
		 if(red < 255) red += 5;//逐渐加大RGB，
		 if(green < 255) green += 5;
		 if(blue < 255) blue += 5;
		 setColor(new Color(red, green, blue)); //获得RGB的值
	}
}