* **Does the bug always move to a new location? Explain.**
	>不会，虫子真会朝着一个有小格子存在的地方，而且这个格子是空的，或者它上面有一朵红花，只有这个情况它才会移动。
	>
>>解释：该行为被方法act()限制，所以出现这样的行走方式
	
* **In which direction does the bug move?**
	> 虫子试着朝着前方移动。
	>
> >解释：该行为还是由该程序的方法函数限制的。
	
* **What does the bug do if it does not move?**
	> 当不符合向前走得条件的时候，虫子首先向转向东北。
	>
> >turn()方法的规定。
	
* **What does a bug leave behind when it moves?**
	> 当虫子移到新的格子时，它会在原来的格子里留下一朵花，花的颜色和虫子的颜色一样。
	>
> >解释：由Bug部分的seColor()规定。
	
* **What happens when the bug is at an edge of the grid? (Consider whether the bug is facing the edge as well as
  whether the bug is facing some other direction when answering this question.)**
  > 如果虫子正对着格子边缘，并且应采取行动（act()），它将向右旋转45度（东北，后面一样）。当被通知再次行动时
  > 再右转45度。如果一个虫子正对着格子的边缘，并且它被告知要移动（move()），它就会从格子中消失，取而代之的是一朵红色的花。
  >
  > >解释：虫子遇到障碍转角45度得行为是由它的行动方法turn()决定的，之后移动想想也相应由move()限制规定。

* **What happens when a bug has a rock in the location immediately in front of it?**
	> 前面说过了，遇到障碍物向右转角45度（也就是东北方向）。
	>
> >解释：turn()方法的规定。
	
 * **Does a flower move?**
	 > 花一旦出现就不会移动。
	>
> >解释： 没有规定花移动的行为方法。
	 
* **What behavior does a flower have?**
	> 花会随着它出现的时间长短逐渐枯萎，最后由红色变成灰色。
	>
> >解释：花的行为由它的颜色变换方法决定，当中应当包含计时函数。
	
* **Does a rock move or have any other behavior?**
	> 当我们按下我们的"step"或者"run"按钮的时候我们的石头不会移动，也没有其他的行为。
	>
> >解释：石头没有随着运行时间自主进行的行为方法,act()是空的。
	
* **Can more than one actor (bug, flower, rock) be in the same location in the grid at the same time?**

	> 不能，一个位置只能拥有一个角色，不能同时存在两个或者以上的角色。
	>
	> >解释：这个是location文件规定的，不能同时存在两个位置相同的不同角色。