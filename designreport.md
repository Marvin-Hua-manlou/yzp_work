
[TOC]

## Jumper 设计报告

#### 一、需求分析

> 本部分主要说明一下对于```Jmper类```需求分析，题目的意思是，我们需要在原来接口的基础上面，实现一个基于```Actor类```的新的类，主要需要实现的功能是：
>
> > 1. 能够跟```Bug```一样在合理的情况之下向前走，在碰到石头的时候能够跳的过去，前提是石头身后的位置合理.
> > 2. 在**jump**的时候，如若碰到目标位置存在一个目标，假如是花儿，就清除花儿，如果是空的，直接执行跳跃。
> > 3. 如果目标位置是一个```Rock```或者```Bug```或者地图的边界，需要转向。
***

#### 二、实现思路（第一部分）

> 1. 做完需求分析我们就需要为实现需求做实现函数，对于```Jumper```，我们需要实现的是在合理的情况之下连续向前走两个位置，也就是物理**跳**,模拟```Bug类```的实现方式，我们在这里首先定义一个```jump_to_next函数```,为了实现该函数，我们需要判断是否能够进行该步骤，那么我们需要一个```canJump_to_next函数```,判断是否能够跳。
> 2. 第二步就是在空白的时候跳过去，如果不合理，就转向，要么是花儿的时候，直接跳过去清除花儿，不合理转向的需求在```jup_to_next```里面补全，清除花儿的操作只需要模拟之前``Bug``类里面的实现当中，虫子离开之后出现花儿的操作，我们只需要在下一步不执行任何花儿的操作就可以了，也就达到了移除花儿的目的。
> 3. 第三步，Rock或者Bug或者边界的时候，我们转向。
> 4. 所有上面三步执行的操作，我们需要在```act函数```里面调用。
> 5. 另外，我们最好实现**构造函数**，你可以写一个空构造函数，或者写一个有参数的构造函数，用来赋值，为此我们可以定义私有成员变量，但是为了兼容特性，我们调用包就可以了，我们这里主要实现对各个对象的颜色的初始化，可以默认，也可以自己设置。
> 6. 最后结合上面五步骤，我们需要实现函数归结如下：

```java
public Jumper()；//构造函数
public Jumper(Color Color_of_Jumper)；//构造函数
public void turns()；//重载turn()函数
public boolean canJump_to_next()；//判断是否能跳跃函数
public void jump_to_next()；//跳跃函数
public void act()；//重载act()函数
```
> * 有必要说明一下，我们上面提及的所有的方法，都应该在```Jumper.java```里面实现，上面写的函数声明只是为了易于理解，在我写的Jumper实现里面，并不涉及一个单独的**interface**，所有的函数声明和定义都是在前面说到的类里面实现的。
> * 还有就是下面第三部分的思路主要是说明如何实现Jumper类，```JumperRunner```在后面说明。
***

#### 三、实现思路（第二部分）

> 本部分主要说明如何实现第一部分的函数。
>
> 1. ```public Jumper()```
>
>    构造函数设置属性，模拟Bug类的行为，构造函数给对象一个默认的颜色。
>
>    ```java
>        public Bug()
>        {
>            setColor(Color.RED);
>        }
>    ```
>
> 2. ```public Jumper(Color Color_of_Jumper)```
>
>    ```java
>        public Bug(Color bugColor)
>        {
>            setColor(bugColor);
>        }
>    ```
>    
> 3. ```public void turns()```
>
>    ```java
>        public void turn()
>        {
>            setDirection(getDirection() + Location.HALF_LEFT);
>        }
>    
>    ```
>    这个是重载的转向函数，这里就是在当前方向的基础上面，向**西北方**转动，注意Bug的行为是右转。
>    
> 4. ```public boolean canJump_to_next()```
>
>    ```java
>    public boolean canJump_to_next(){
>    		 Grid<Actor> gird = getGrid();
>    		 if (gird == null)
>    			 return false;
>    		 
>    		 Location pass_prv = getLocation();
>    		 Location next_prv = pass_prv.getAdjacentLocation(getDirection());//第一个格子
>    		 if (!gird.isValid(next_prv))
>    			 return false;
>    		 
>    		 Location next = next_prv.getAdjacentLocation(getDirection());//第二个格子
>    		 if (!gird.isValid(next))
>    			 return false;
>    		 
>    		 Actor neighbor = gird.get(next);
>    		 return (neighbor == null) || (neighbor instanceof Flower);	//空的或者花儿	 
>    	 }
>    ```
>    
>    * 这个函数的主要功能是判断该对象是否能够实现到下一步的一个判断函数，还是模拟```can_Move()```函数的行为来实现，现在我们来分析实现的思路，声明一个```Gird<Actor>```对象，获得gird，如果不存在，那么也就不存在跳的行为了；
>    * 接下来需要判断我们下一个位置的合理情况了。我们借助于```isValid()```函数,函数当中6-7行的两个变量**pass_prv**和**next_prv**分别指的是可能的下一个位置和下下一个位置，注意我们这里是一个jumper，所以我们命名为pass和next,prv指的是“前一个”,第一个和第二个判断是在判断是否到达地图的边界，注意因为是在"Jump"，所以我们不仅需要判断下一个格子是否是边界，还需要判断下下一个格子是否是边界；
>    * 最后一个判断是在对象的邻居对象（也就是下一个可能格子）存在的情况之下，下下一个位置是否合理，合理的条件就是，那个位置是空的，或者是一个花儿；
>    
> 5. ```public void jump_to_next()```
>     接下来就是执行跳跃的函数了：
>
>    ```java
>     //控制在下一个位置出现的对象应该是跳两个格子。
>     	 public void jump_to_next(){
>     		 Grid<Actor> gird = getGrid();
>     		 if (gird == null)
>     			 return;
>     		 Location pass = > >getLocation().getAdjacentLocation(getDirection());//第一个格子
>     		 Location aim_next = pass.getAdjacentLocation(getDirection());
>     		 //第二个格子
>     		 if (gird.isValid(aim_next))
>     			 moveTo(aim_next);
>     		 else
>     			 removeSelfFromGrid();
>     //		 Flower flower = new Flower(getColor());
>     //	     flower.putSelfInGrid(gr, loc);
>     		 //去掉花儿出现的行为
>     	 }
>    ```
>
>    * 这个思路跟上面一个函数差不了太多，主要也是仿照```Bug类```的方法，需要连续判断两个格子的合理性，合理，就```moveTo（）```；
>    * 否则就移除自己，为什么要移除自己？注意我们如果下一个位置不合理的话，我们需要"转弯儿",那么就要调整位置，此时在用户看来是调整位置，在机器来说其实就是移除原来的对象，新生成一个新的设置好方向的对象，严格来说转弯以后的对象跟之前的不是同一个，所以需要移除操作；
> 6. ```public void act()```
>     这个就是**行为函数**，决定向前还是转弯。
>
>    ```java
>    public void act()
>        {
>            if (canMove())
>                move();
>            else
>                turn();
>        } 
>    ```
***

#### 四、JumperRunner测试

> 显然我们这里就是声明对象，让对象能够在图里面跑起来就好了：
>
> ```java
> import java.util.Scanner;
> import info.gridworld.actor.ActorWorld;
> import info.gridworld.actor.Bug;
> import info.gridworld.actor.Rock;
> import info.gridworld.actor.Flower;
> //import java.util.Scanner; 
> //测试Jumper
> public class JumperRunner
> {
> 	public static void main(String[] args)
> 	{
> 		 @SuppressWarnings("resource")
> 		 Scanner src = new Scanner(System.in);
> 		 System.out.print("Please input a number stands for how many Rocks you want:");
> 		 System.out.println();
> 		 int len = src.nextInt();
> 		 ActorWorld world = new ActorWorld();
> 		 for(int i = 0; i < len; i++) {
> 			 world.add(new Rock());
> 		 }
> 		 world.add(new Jumper());
> 		 world.add(new Bug());
> 		 world.add(new Flower());
> //		 int[] direction = {1,1,1,2,0,0,2,0,0,2,1,1};
> //		 DancingBug alice = new DancingBug(direction);
> //		 world.add(new Location(3, 4), alice);
> 		 world.show();
> 	}
> }
> ```
>
> 这里我们发现项目的实现里面接口生成```Rock()```是随机的，虽然我们可以控制我们的Jumper的位置，但是由于Rock的位置不可控，所以最好的方式是我们生成多个Rock来测试：
>
> ```java
> for(int i = 0; i < len; i++) {
> 			 world.add(new Rock());
> }
> ```
>
> 这个可以由用户控制数量，在地图里面生成```Jumper```、```Bug```、```Flower```、```Rock```等等多个对象，然后运行就可以了。
***
