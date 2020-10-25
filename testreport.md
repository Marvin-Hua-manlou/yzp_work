## 测试文件报告

1. 先贴代码（JumperTest)

    ```java
    import java.util.Scanner;
    import org.junit.jupiter.api.Test;
    import info.gridworld.actor.ActorWorld;
    import info.gridworld.actor.Bug;
    import info.gridworld.actor.Flower;
    import info.gridworld.actor.Rock;
    class JumperTest {
        @Test
        void test() {
            @SuppressWarnings("resource")
             Scanner src = new Scanner(System.in);
             System.out.print("Please input a number stands for how many Rocks you want:");
             System.out.println();
             int len = src.nextInt();
             ActorWorld world = new ActorWorld();
             for(int i = 0; i < len; i++) {
                 world.add(new Rock());
             }
             world.add(new Jumper());
             world.add(new Bug());
             world.add(new Flower());
             world.show();
             System.out.print("The program compiled and ran successfully!!");
             System.out.println();
        }

    }
    ```
***
2. 测试文件的主要目的就是利用```Junit```来完成测试工作，检查代码，那么我们需要建立一个类来测试我们写的```Jumper```类，Jumper的代码写在之前的```design```文件，我们利用**Eclipse**来完成对于代码的测试工作，主要的测试方法就是在IDE里面建立一个新的```class```，选择Junit类型，最后把我们的代码写在Test里面，文件结构如下：

   ![文件结构](https://s1.ax1x.com/2020/10/20/B9C7hq.png)

   ***

3. **思路：**

   主要就是调用```Jumper```里面的方法来测试代码，最好设置一个回显：如代码当中的第**23**行，给控制台一个信息。
***
4. 运行结果

   ![结果](https://s1.ax1x.com/2020/10/20/B9PorD.png)

![Console](https://s1.ax1x.com/2020/10/20/B9PrKU.png)

***



