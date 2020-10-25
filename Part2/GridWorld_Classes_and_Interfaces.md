## The Location Class
### Set 3
**Assume the following statements when answering the following questions.**

* How would you access the row value for loc1?

  ```
  	Location loc1 = new Location(4, 3);
  	Location loc2 = new Location(3, 4);
  ```

  > 我们调用Location类的方法```loc1.getRow()```方法得到行数。 

* What is the value of b after the following statement is executed?

  ```
  boolean b = loc1.equals(loc2);
  ```

  > 返回**false**,因为明显的我们看到两个位置坐标是不一样的。

* What is the value of loc3 after the following statement is executed?

  ```
  Location loc3 = loc2.getAdjacentLocation(Location.SOUTH);
  ```

  > 得到的loc3的位置是```(3,3)```.

* What is the value of dir after the following statement is executed?

  ```
  int dir = loc1.getDirectionToward(new Location(6, 5);
  ```

  > 我们可以看到loc1的位置是```(4,3)```，而我们的新位置是```(6,5)```，所以**loc1位于dir的西南方位**，所以，```dir = 135```.

* How does the getAdjacentLocation method know which adjacent location to return?

  > ```djacentLocation方法```的参数指示要查找的相邻邻居的方向。它返回指针方向的林俊当中符合条件的最近的相邻位置。
  
***
## The Grid Interface ##

### **Set 4** ###

* How can you obtain a count of the objects in a grid? How can you obtain a count of the empty locations in a bounded grid?

  > 我们看一看在**Gird**类里面有一个函数为原型：
  >
  > ```java
  > ArrayList<Location> getOccupiedLocations();
  > ```
  >
  > 用来返回Location，所以我们得到占据的size（），这里我们只要：
  >
  > ```java
  > Gird gird = new Gird();
  > gird.getOccupiedLocations().size();
  > ```
  >
  > 得到剩下的空的位置大小，只要把整**Gird**的大小减掉占据位置就好了：
  >
  > ```java
  > gird.getNumRows()*girdr.getNumCols() - gird.getOccupiedLocations().size() 
  > ```

* How can you check if location (10,10) is in a grid?

  > ```java
  > /**
  >  * Checks whether a location is valid in this grid. <br />
  >  * Precondition: <code>loc</code> is not <code>null</code>
  >  * @param loc the location to check
  >  * @return <code>true</code> if <code>loc</code> is valid in this grid,
  >  * <code>false</code> otherwise
  >  */
  > boolean isValid(Location loc);
  > ```
  >
  > 从上面的代码段能够看出来我们如果需要需要检测一个位置是否合理，只需调用```isValid()```函数就可以了，检测(10,10)是否合理：
  >
  > ```java
  > Gird gird = new Gird();
  > gird.isValid(new Location(10,10));
  > ```

* Grid contains method declarations, but no code is supplied in the methods. Why? Where can you find the implementations of these methods?

  > 我们知道```Gird```是一个接口：
  >
  > ```java
  > public interface Grid<E>
  > ```
  >
  > ```java语言```定义的接口，需要声明在它的```implements类```里面实现的方法，这里我们声明在```Gird```的所有的方法一部分在它的```implements类```里面实现，也就是：
  >
  > ```java
  > public abstract class AbstractGrid<E> implements Grid<E>
  > ```
  >
  > 另外一部分在它的继承类里面实现：
  >
  > ```java
  > public class BoundedGrid<E> extends AbstractGrid<E>
  > //第一个继承类
  > public class UnboundedGrid<E> extends AbstractGrid<E>  
  > //第二个继承类
  > ```

* All methods that return multiple objects return them in an ArrayList. Do you think it would be a better design to return the objects in an array? Explain your answer.

  > 显然这会是一个比较好的方式，就像是```C++```，我们知道，我们要对一个数组赋值，那么我们必须首先知道它的**size**，这是一个必须的过程，因为你要申请空间来为储存数据做好准备，而我们知道在我们的```BoundedGrid类```里面，我们的网格空间的大小必须要你计算被占据的那部分大小，然后得到空闲的部分，最后才能决定```虫子```能够去到哪里，这是一个必经的过程，加入你用数组的话，那么实现的步骤将会是你要首先计数被占据的空间，以此来决定接下来的```array```你要给他多大的空间来存储数据，这将会很麻烦，而链表不会，你在存储的时候，并不需要先直接定义它的大小，这样就会使得操作简单很多。
***

## The Actor Class ##
### **Set 5**

* Name three properties of every actor. 

  > ```java
  >private Grid<Actor> grid;
  >private Location location;
  >private int direction;
  >private Color color;
  > ```
  >
  > 从上面的类的成员变量我们可以看到：一个```actor```应当具有属性应当有```方向```，```位置```,```颜色```,以及声明的一个```Gird类方法```.

* When an actor is constructed, what is its direction and color?

  > ```java
  > 	public Actor()
  >     {
  >         color = Color.BLUE;
  >         direction = Location.NORTH;
  >         grid = null;
  >         location = null;
  >     }
  > ```
  >
  > 从上面的代码可以看到，我们的颜色初始为**蓝色**，方向朝向**北方**。

* Why do you think that the Actor class was created as a class instead of an interface?

  > 这个前面已经说过了，它确实是一个```class```不是一个**interface**，因为它里面既有函数的声明，也有函数的定义，而且还有私有变量。而```interface```不允许除了函数声明以外的其他内容。

* Can an actor put itself into a grid twice without first removing itself? Can an actor remove itself from a grid twice? Can an actor be placed into a grid, remove itself, and then put itself back? Try it out. What happens?

  > 1. 这个可以想见是不可以的，因为你在函数终不能同时声明两个**同样ID**的对象，所以我们使用下面的方法在相同的位置插入一个下相同的对象：
  >
  > >```java
  > >public class BoxBugRunner
  > >{
  > >public static void main(String[] args){
  > >	ActorWorld world = new ActorWorld();
  > >	Bug alice = new Bug();
  > >	world.add(alice);
  > >	alice.putSelfInGrid(alice.getGrid(),alice.getLocation());//二次插入
  > >	world.show(); 
  > >}
  > >}
  > >```

  > >上面的代码是把```BugRunner```修改之后的，尝试编译运行，得到结果:
  > >```java
  > >Exception in thread "main" java.lang.IllegalStateException: This actor is already contained in a grid.
  > >	at info.gridworld.actor.Actor.putSelfInGrid(Actor.java:118)
  > >	at BoxBugRunner.main(BoxBugRunner.java:42)
  > >```
  > ***
  > 2. 一个对象在声明出现以后，是不能够在同一个位置被删除两次的，尝试吧```BugRunner```的内容修改为：
  > >```java
  > > ActorWorld world = new ActorWorld();
  > > Bug alice = new Bug();
  > > world.add(alice);
  > > alice.removeSelfFromGrid();
  > > alice.removeSelfFromGrid();
  > > world.show(); 
  > >```
  > >尝试编译运行得到的结果是:
  > >
  > >```java
  > >Exception in thread "main" java.lang.IllegalStateException: This actor is not contained in a grid.
  > >	at info.gridworld.actor.Actor.removeSelfFromGrid(Actor.java:136)
  > >	at BoxBugRunner.main(BoxBugRunner.java:43)
  > >
  > >```
  > ***
  > 3. 一个```Actor```可以被放到```Guid```里面，宁缺可以删除以后在放回去，没有问题，可以编译运行,新建一个文件夹下一个```ActorRunner```来测试：
  > >   ```java
  > >   import info.gridworld.actor.ActorWorld;
  > >   import info.gridworld.actor.Actor;
  > >   import info.gridworld.grid.Grid;
  > >   import info.gridworld.grid.Location;
  > >   public class ActorRunner{
  > >   	public static void main(String[] args){
  > >   		 ActorWorld world = new ActorWorld();
  > >   		 Actor alice = new Actor();
  > >   		 world.add(alice);
  > >   		 Grid<Actor> grid = alice.getGrid(); 
  > >   		 world.show();
  > >   		 Location ans = alice.getLocation();
  > >   		 alice.removeSelfFromGrid();
  > >   		 alice.putSelfInGrid(grid,ans); 
  > >   	}
  > >   }
  > >   ```
  > >   测试的结果：
  > >
  > >   [![0Xy2gP.th.png](https://s1.ax1x.com/2020/10/18/0Xy2gP.th.png)](https://imgchr.com/i/0Xy2gP)


* How can an actor turn 90 degrees to the right?

  > 这个在测试的时候要实现的话，我们只能够修改该```Actor类```的```act()函数```，因为在测试的时候，它不碰到边界的话，不会执行act()下面的语句：
  >
  > ```java
  > alice.setDirection(alice.getDirection()+Location.RIGHT); 
  > ```
  >
  > 所以我们只能修改如下的语句：
  >
  > ```java
  > 	public void act(){
  >         setDirection(getDirection() + Location.HALF_CIRCLE);
  >     }
  > ```
  >
  > 成为：
  >
  > ```java
  > 	public void act(){
  >         setDirection(getDirection() + Location.RIGHT);
  >     }
  > ```
  >
  > 最后再```ActorRunner```里面测试一下：
  >
  > ```java
  > public class ActorRunner{
  > 	public static void main(String[] args){
  > 		ActorWorld world = new ActorWorld();
  > 		 Actor alice = new Actor();
  > 		 world.add(alice);
  > 		 world.show();
  > 		 //alice.setDirection(alice.getDirection()+Location.RIGHT); 
  > 	}
  > }
  > ```
  >
  > 结果没错。
***
	
## Extending the Actor Class ##
### Set 6 ###

1. Which statement(s) in the canMove method ensures that a bug does not try to move out of its grid?

   > ```java
   > if (!gr.isValid(next))            
   >     return false;
   > ```
   >
   > 这个语句检测是否到了边界。

2. Which statement(s) in the canMove method determines that a bug will not walk into a rock?

   > ```java
   > Actor neighbor = gr.get(next);
   > return (neighbor == null) || (neighbor instanceof Flower);
   > ```
   >
   > 这个语句得到当前目标的下一个**笛卡尔坐标位置**，除非是***花***或者***可用空间***，否则都不能走，当然也包括```Rock```。

3. Which methods of the Grid interface are invoked by the canMove method and why?

   > ```java
   > if (!gr.isValid(next))
   >     return false;
   > Actor neighbor = gr.get(next);
   > ```
   >
   > 从上面的代码当中就可以看出来，```isValid(Location loc)方法```和```get(Location loc)方法```应用到```canMove```当中，调用这几个函数的意义在于：**确保下一个Location的位置是否有效，即是否是空的或者是可以被虫子代替的位置**。

4. Which method of the Location class is invoked by the canMove method and why?

   > ```java
   > Location loc = getLocation();
   > Location next = loc.getAdjacentLocation(getDirection());
   > ```
   >
   > 从上面的代码当中可以看到，应用到的方法主要有```getAdjacentLocation()方法```,调用这个函数的目的是为了执行下一步前找到可能的合适的位置。

5. Which methods inherited from the Actor class are invoked in the canMove method?

   > ```getGrid方法```,```getLocation方法```,``` getDirection方法```三个方法，主要的目的是辅助找到合理的位置和得到整个的```Gird图```。 

6. What happens in the move method when the location immediately in front of the bug is out of the grid?

   > ```java
   > if (gr.isValid(next))
   >     moveTo(next);
   > else
   >     removeSelfFromGrid();
   > ```
   >
   > 从上面的代码当中可以看到：当到达边界的时候，他将自己从图当中移除。

7. Is the variable loc needed in the move method, or could it be avoided by calling getLocation() multiple times?

   > ```java
   > Location loc = getLocation();
   > flower.putSelfInGrid(gr, loc);
   > ```
   >
   > ```loc```是必要的，它是一个临时寄存变量，用来记录虫子移动之前的位置，虫子移动之后，它记录到的位置被留下一朵花，否则多次调用```getDrection方法```无法在后面留下花朵。

8. Why do you think the flowers that are dropped by a bug have the same color as the bug?

   > ```java
   > Flower flower = new Flower(getColor());
   > ```
   >
   > 主要的原因是调用了上面的```getColor()方法```,这个是```Actor```的方法，返回```Actor对象```的颜色，所以```Bug```的颜色决定了花的颜色。

9. When a bug removes itself from the grid, will it place a flower into its previous location?

   > 这个儿就要看你在哪里调用了，运行程序你也能看到，铺放的话能够被```Bug```代替的，原因是：
   >
   > ```java
   > if (gr.isValid(next))
   >     moveTo(next);
   > else
   >     removeSelfFromGrid();
   > Flower flower = new Flower(getColor());
   > flower.putSelfInGrid(gr, loc);
   > ```
   >
   > 也就是说，在```Bug类```的```move()方法```里面调用的时候，就会代替之前的花，要是你新建一个```Runner class```来调用的话，就不能代替之前的花了，整个过程将会停止。

10. Which statement(s) in the move method places the flower into the grid at the bug’s previous location?

    > ```java
    > Flower flower = new Flower(getColor());
    > flower.putSelfInGrid(gr, loc);
    > ```
    >
    > 这一句代码将会把```Bug```之前的位置用花朵代替，记住```loc```的必要性，上面说过了。

11. If a bug needs to turn 180 degrees, how many times should it call the turn method?

    > **4次**，$4 = \frac{180}{45}$,每转动一次是45度。

