贪吃蛇二
=
### 代码块
---
```c
#include <stdio.h>
#include<cmath>
#include <string.h>
#include <sys/time.h>
#include <sys/types.h>
#include <unistd.h>
#include <termios.h>
#include <unistd.h>
#define SNAKE_MAX_LENGTH 20
#define SNAKE_HEAD 'H'
#define SNAKE_BODY 'X'
#define BLANK_CELL ' '
#define SNAKE_FOOD '$'
#define WALL_CELL '*'

static struct termios ori_attr, cur_attr;

static __inline 
int tty_reset(void)
{
        if (tcsetattr(STDIN_FILENO, TCSANOW, &ori_attr) != 0)
                return -1;

        return 0;
}


static __inline
int tty_set(void)
{
        
        if ( tcgetattr(STDIN_FILENO, &ori_attr) )
                return -1;
        
        memcpy(&cur_attr, &ori_attr, sizeof(cur_attr) );
        cur_attr.c_lflag &= ~ICANON;
//        cur_attr.c_lflag |= ECHO;
        cur_attr.c_lflag &= ~ECHO;
        cur_attr.c_cc[VMIN] = 1;
        cur_attr.c_cc[VTIME] = 0;

        if (tcsetattr(STDIN_FILENO, TCSANOW, &cur_attr) != 0)
                return -1;

        return 0;
}

static __inline
int kbhit(void) 
{
                   
        fd_set rfds;
        struct timeval tv;
        int retval;

        /* Watch stdin (fd 0) to see when it has input. */
        FD_ZERO(&rfds);
        FD_SET(0, &rfds);
        /* Wait up to five seconds. */
        tv.tv_sec  = 0;
        tv.tv_usec = 0;

        retval = select(1, &rfds, NULL, NULL, &tv);
        /* Don't rely on the value of tv now! */

        if (retval == -1) {
                perror("select()");
                return 0;
        } else if (retval)
                return 1;
        /* FD_ISSET(0, &rfds) will be true. */
        else
                return 0;
        return 0;
}

void output(void)
{
	for(int i=0;i<12;i++){
		for(int j=0;j<12;j++)
			printf("%c",map[i][j]);
		printf("\n"); 
	}
}

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

char wheregonext(int hx, int hy, int fx, int fy) {
    int distance[4]={0,0,0,0};
	char movable[4]={'a','d','w','s'};
	int n = 0, num = 9999;
    int i;
    distance[0] = abs(fx - (hx - 1)) + abs(fy - hy);
    distance[1] = abs(fx - (hx + 1)) + abs(fy - hy);
    distance[2] = abs(fx - hx) + abs(fy - (hy - 1));
    distance[3] = abs(fx - hx) + abs(fy - (hy + 1));
    // 分别计算蛇头周边四个位置到食物的距离。H头的位置，F食物位置
    for (i = 0; i < 4; i++){
        if (distance[i] <= num) {
            num = distance[i];
            n = i;
        }
    }// 选择distance中存最小距离的下标p，注意最小距离不能是9999
    min = 9999;
    return movable[p];  // 返回 movable[p]
}


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
	

int main()
{
        //设置终端进入非缓冲状态
    int tty_set_flag;
    tty_set_flag = tty_set();

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
    snackMove(x,y);	
    wheregonext(hx,hy,fx,fy);
    int moneyX;
    int moneyY; 
    int mark = 0; 
	
    printf("pressed `q` to quit!\n");
    while(1) {
    		
	    put_money();
            if( kbhit() ) {
                    const int key = getchar();
                    printf("%c pressed\n", key);
                    if(key == 'q')
                            break;
					} 
			else {
                       ;// fprintf(stderr, "<no key detected>\n");
                }
        }

        //恢复终端设置
        if(tty_set_flag == 0) 
                tty_reset();
        return 0;
}
```
