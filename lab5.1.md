第一个贪吃蛇游戏
=
### 代码程序如下：
```c
#include<stdio.h>
#include <stdlib.h>
#include <time.h>

#define SNAKE_MAX_LENGTH 20
#define SNAKE_HEAD 'H'
#define SNAKE_BODY 'X'
#define BLANK_CELL ' '
#define SNAKE_FOOD '$'
#define WALL_CELL '*'

void snakeMove(int,int);//移动蛇
void put_money(void);//放食物 
void output(void);//输出图形框
int gameover(void);//判断游戏是否结束

//初始状态 
	char map[12][13]=
	{"************",
	"*XXXXH     *",
	"*          *",
	"*          *",
	"*          *",
	"*          *",
	"*          *",
	"*          *",
	"*          *",
	"*          *",
	"*          *",
	"************"};

//初始化蛇身位置（横着是X，竖着是Y） 
	int snakeY[SNAKE_MAX_LENGTH]={5,4,3,2,1};
	int snakeX[SNAKE_MAX_LENGTH]={1,1,1,1,1};
	int snakeLength=5;
//定义食物的位置 
	int moneyX;
	int moneyY; 
//设置mark,是0的话表示暂时没有食物，不是就说明有了食物。 
	int mark = 0; 
	
int main()
{
	
	printf(" =====\n");
	printf("| = = |\n");
	printf(" =====\n");
	printf("欢迎来玩贪吃蛇,请把输入方式调为英语小写字母形式\n");
	printf("开始游戏呗！\n"); 
	
	//打印游戏开始的图像 
	output();
	
	//默认右行的蛇 
	char ch = 'd';
	
	while(1)
	{
		//放下一个食物
		 put_money();
		
		//根据输入的数据让蛇移动 
		switch(ch)
		{
			case 'a':
				snakeMove(0,-1);
				break;
			case 'w':
				snakeMove(-1,0);
				break;
			case 's':
				snakeMove(1,0);
				break;
			case 'd':
				snakeMove(0,1);
				break;
		}
		
		//从键盘输入 
		ch=getchar();
		
		//判断游戏是否结束
		int end = gameover(); 
		if(end)
		{
			break;
		}
	}
	 
	return 0;
} 

//打印图案的函数 
void output(void)
{
	//打印出二维数组 
	for(int i=0;i<12;i++){
		for(int j=0;j<12;j++)
			printf("%c",map[i][j]);
		printf("\n"); 
	}
}

//放食物的函数  
void put_money(void)
{
	srand(time(NULL));
	//随机产生食物
	while(!mark)
	{
		moneyX=rand()%10+1;
		moneyY=rand()%10+1;
		
		if(map[moneyX][moneyY]==' ')
		{
			mark = 1; 
		}
	}
	
	map[moneyX][moneyY]=SNAKE_FOOD;
} 
 
//移动蛇的函数 
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

//判断游戏结束的条件 
int gameover(void)
{
	int flag1=0;
	
	//如果蛇的长度达到30，结束 
	if(snakeLength==25)
	{
		flag1=1;
	}
	
	//如果蛇撞了南墙，就回头 
	if(snakeX[0]==0||snakeX[0]==12||snakeY[0]==0||snakeY[0]==12)
	{
		flag1=2;
	} 
	
	//如果蛇撞到自己，就结束 
	for(int i=1;i<snakeLength;i++)
	{
		if(snakeX[0]==snakeX[i] && snakeY[0]==snakeY[i])
		{
			flag1=1;
		}
	}
	
	//打印游戏结束 
	if(flag1 == 1)
	{	
		printf("牛啤!!\n");
		printf("再来一局？\n");
		return 1;
	}
	if(flag1 == 2){
		printf("额，你死了耶!!\n");
		printf("再来一局？\n");
		return 1;
	}
	
	return 0;
}
```
![](images/%E5%9B%BE%E5%BD%A2.gif)
