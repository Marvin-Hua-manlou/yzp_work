1.  **What is the role of the instance variable sideLength?**

   > 从<BoxBug>的代码当中我们不难看出，该变量是控制该虫子周围的空间大小的，以提供给<step>变量合理的移动空间。
   >
   > ```java
   >private int steps;
   >private int sideLength;
   > ```

2. **What is the role of the instance variable steps?**

   > 这个也简单，既然上面的<length>变量用来给空间大小，那么<step>自然是用来**计量步数**的，也就是当前移动的方向已经走了多少步。
   >
   > ```java
   >move();
   >steps++;
   >```

3. **Why is the turn method called twice when steps becomes equal to sideLength?**

   > 这个也好理解，我们让虫子一直走，直到走到<box-side>的时候需要转向，也就是当我们的**step**等于**sideLength**的时候，那么这个时候它的转向操作最少需要转动90度才能实现向着另外一边走，但是很明显我们的turn()函数只是一次转动45度，所以需要执行两次。
   >
   > ```java
   > 	Location.HALF_RIGHT
   > 	//int info.gridworld.grid.Location.HALF_RIGHT : 45 [0x2d]
   > 	//The turn angle for turning 45 degrees to the right.
   > ```

4. **Why can the move method be called in the BoxBug class when there is no move method in the BoxBug code?**

   > 这个就更加好解释了，就像是```C++```项目的文件引用，我们在**BoxBug**里面虽然未曾定义和实现```move()```函数，但是我们引用了定义以及实现它的文件:
   >
   > ```java
   > import info.gridworld.actor.Bug;
   > ```
   >
   > 所以我们可以调用该函数。

5. **After a BoxBug is constructed, will the size of its square pattern always be the same? Why or why not?**

   > 一旦一个<BoxBug>被构造以后，它的四周饭团大小就不会被改变了，是被定义好了的，不能被用户代码改变。**bug.gif**定义了它的大小。

6. **Can the path a BoxBug travels ever change? Why or why not?**

   > 会改变方向的，当它碰到另外一个障碍的时候，可能是另一个虫子，也可能是石头，那么它就会改变自己的运动轨迹。这个是```Canmove()```函数跟```move()```函数控制的。

7. **When will the value of steps be zero?**

   > 这个取决于我们的```act()```函数，只要我们的虫子遇到障碍物转变了方向，那么它的<step>就会**置0**：
   >
   > ```java
   >     public void act(){
   >         if (steps < sideLength && canMove()){
   >             move();
   >             steps++;
   >         }
   >         else{
   >             turn();
   >             turn();
   >             steps = 0;
   >         }
   >     }
   > ```
   >
   > 

