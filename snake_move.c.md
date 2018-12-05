# 移动程序
 ```c
 void output(void)
{
	//打印出二维数组 
	for(int i=0;i<12;i++){
		for(int j=0;j<12;j++)
			printf("%c",map[i][j]);
		printf("\n"); 
	}
}

 void snakeMove(int x,int y)
{
	//暂时保存设的尾巴位置 
	int tempX,tempY;
	tempX=snakeX[snakeLength-1];
	tempY=snakeY[snakeLength-1];
	
	//重新计算蛇整个身体的位置
	for(int i=snakeLength-1;i>0;i--)
	{
		snakeX[i]=snakeX[i-1];
		snakeY[i]=snakeY[i-1];
	} 
	snakeX[0] += x;
	snakeY[0] += y;
	
	//头出现的位置有食物时 
	if(snakeX[0]==moneyX && snakeY[0]==moneyY)
	{
		//mark = 0，意味着食物被吃掉，需要一个新的食物
		mark = 0;
		snakeLength++;
		snakeX[snakeLength-1]=tempX;
		snakeY[snakeLength-1]=tempY;
	} 
	//若没有食物 ,把蛇的尾巴位置变成空格 
	else
	{
		map[tempX][tempY]=BLANK_CELL;
	}
	
	//把蛇的位置全部变成对应的符号 
	map[snakeX[0]][snakeY[0]]=SNAKE_HEAD;
	for(int i=1;i<snakeLength;i++)
	{
		map[snakeX[i]][snakeY[i]]=SNAKE_BODY;
	} 
	
	//输出图像
	output(); 
}
```
