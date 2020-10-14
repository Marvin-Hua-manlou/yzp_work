* **Test the setDirection method with the following inputs and complete the table, giving the compass direction each input represents.**

	| Degrees | Compass Direction |
	| ------- | ----------------- |
	| 0       | North             |
	| 45      | Northeast         |
	| 90      | East              |
	| 135     | Southeast         |
	| 180     | South             |
	| 225     | Southwest         |
	| 270     | West              |
	| 315     | Northwest         |
	| 360     | North             |
	
* **Move a bug to a different location using the moveTo method. In which directions can you move it? How far can you move it? What happens if you try to move the bug outside the grid?**
	
	> 可以使用```moveTo方法```将虫子移动到格子中的任何合理的位置。在使用```moveTo方法```移动虫子的时候，虫子不会更改原来的移动方向，也就是说，这个方向实际上由```turn()方法```决定，除非说你用了```setDirection方法```更改bug的方向,此时你可以按照任何合理方向移动，只要不超出边界而且合理，可以一直移动，能多远就多远，移动出边界它就会消失，代之以红色的花。
	
* **Change the color of a bug, a flower, and a rock. Which method did you use?**
	
>重置（设置）他们的颜色，我们应用到了```setColor方法```。
	
* **Move a rock on top of a bug and then move the rock again. What happened to the bug?**

	>当一块石头被移到虫子身上时，该虫子就会消失。此时当石头移动到另一个位置时，原来位置的虫子不会再出现在地图当中的任何地方，简而言之就是直接没了。