# 吃食物程序
```c
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
```
