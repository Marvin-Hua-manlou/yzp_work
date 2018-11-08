# 介绍“自顶向下，逐步求精”的编程方法
---
#### 自上而下的编程方法是正规编程设计的基础,下图中说明了步骤细节:

![](https://github.com/yangzhanp/yangzhanp----homework/blob/gh-pages/20160111161606048_%E5%89%AF%E6%9C%AC.png)

1. 清晰地陈述你所要解决的问题
2. 定义程序所需的输入量和程序所产生的输出量
3. 设计你的程序得以实现的算法
4. 把算法转化为代码
5. 检测产生的 MATLAB 程序

## 洗衣机的例子
### 洗衣机

![](https://github.com/yangzhanp/yangzhanp----homework/blob/gh-pages/u%3D3882030335%2C2942438075%26fm%3D11%26gp%3D0.jpg)
1. 注水：用户需要选择水量，并注入对应水量。 
2. 洗涤：通过底部转盘旋转，直筒摇动使衣物与筒壁摩擦以达到洗涤的目的。 
3. 漂洗：放水，将桶中的水顺管排出，再次注水淹没衣物，通过底部转盘旋转，将衣物中残余洗涤剂洗出，再次排水。 
4. 脱水：滚筒高速旋转，通过离心作用将衣物中残留水分甩出。

#### 如何在达到既定水量的时候停止注水？ 
> 通过传感器反馈的数据作为指标，当水到达一定高度触发传感器时就停止注水。

#### 如何检测漂洗得干不干净？不干净的话要怎么办？ 
> 每次漂洗过后通过传感器检测排出的水的ph值，若达到指标（即漂洗干净）就停止注水排水的过程，若未达到指标（即漂洗不干净），则重复漂洗过程。

#### 要是水管无法排水怎么办？ 
> 若在漂洗过程中通过传感器检测到的水位一直不下降，则中断洗衣过程并发出警报提醒用户。

通过以上的问答过程，我们可以不断地“自顶向下，逐步求精”。

#### 伪代码
```c
begin:
input 水量 
water_in_switch（open);
if（现水量>=输入水量）
    water_in_switch(close)
end if
while(time_counter为4的倍数）
    if（电机正在左转）
        motor_run（right）
    else（电机正在右转）
        motor_run（left）
    end if
    if（time_counter()>=一定时间）
    结束循环
motor_run(stop);
water_out_switch(open);
if(水量不变即水无法排出）
    water_out_switch(close);
    发出警报声
    halt
else
    继续程序
end if
repeat
    if（水流极小）
        water_out_switch(close);
        water_in_switch（open）;
    if（现水量>=输入水量）
        water_in_switch(close);
    end if
        water_out_switch(open);
    if(水量不变即水无法排出）
        water_out_switch(close);
        发出警报声'
     halt;
    else
        继续程序;
    end if
until（水的ph值达到干净标准）
do
    motor_run（left）;
until（水流极小）
then 
    发出警报;
    halt;
```
