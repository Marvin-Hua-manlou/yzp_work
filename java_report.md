

# 

第一部分：vim

## 一、vim的基本介绍
* vi编辑器是所有Unix及Linux系统下标准的编辑器，他就相当于windows系统中的记事本一样，方便代码的编辑和一些其他的文件的书写，很方便。
vim 具有程序编辑的能力，可以以字体颜色辨别语法的正确性，方便程序设计；
***
## 二、vim的基本模式选择和操作
* 此前在使用vim的时候，了解vim的基本模式有三种，分别提供给我们**打开关闭、保存退出**；**书写校验**；以及**复制删除**等等的操作。
***
* 基本的模式之间的转化规则（俨然就是一些在vim的命令模式下的选择结果，从一个状态转化到另外一个状态的方式，就是一些键盘的快捷键转化，这人里省略）。
***
* vim基本文件操作
	*  ***vim /path/to/somefile*** ：vim后跟文件路径及文件名 ，如果文件存在，则打开编辑文件窗口，如果文件不存在，则创建文件. 
	* ***vim + # /path/to/somefile***：打开文件，并定位到第#行，#代表数字,这个操作方便我们在一直文件架构的情况之下完成一些基本的寻找工作。
	* ***vim +***：打开文件，定位到最后一行```# vim + /etc/inittab```  
	
  * ***vim +/PATTERN***：打开文件，定位到第一次被PATTERN匹配到的行的行首```# vim +/start/myfile``` ,我们称之为文件内容的查找匹配。

    ![image-20201010153208811](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\image-20201010153208811.png)
***
* 关闭文件
	* 末行模式下：
	```
　　　w：保存
　　　q：退出
　　　wq 或 x：保存退出，wq 和 x 都是保存退出
　　　q！：强制退出
　　　w！：强制保存，管理员才有权限
  ```
	* 命令模式下：
	```
　　　ZZ：保存并退出
  ```
***
* 光标的移动
	* 实际上光标的移动可以不依赖于当前的vim的命令模式的指令，因为即使是在命令模式之下，我们依然可以用键盘的上下左右移动，不过长久使用vim可以酌情使用命令完成。

```
	  ①逐字符移动
　　　　　h：向左
　　　　　l：向右
　　　　　j：向下
　　　　　k：向上
　　　　　#h：移动#个字符
　　　②以单词为单位移动
　　　　　w：移到下一个单词的词首
　　　　　e：跳至当前或下一个单词的词尾
　　　　　b：跳至当前或上一个单词的词首
　　　　　#w：表示移动#个单词
　　　③行内跳转
　　　　　0：绝对行首
　　　　　^：行首第一个非空白字符
　　　　　$：绝对行尾
　　　④行间跳转
　　　　　#G：跳转到第#行
　　　　　G：最后一行
```
***
* 粘贴命令
```
p(小写p)：如果删除的或复制的为整行内容，则粘贴到光标所在行的下方，如果复制或删除的内容为非整行，则粘贴至光标所在字符的后面
P(大写P)：如果删除的或复制的为整行内容，则粘贴到光标所在行的上方，如果复制或删除的内容为非整行，则粘贴至光标所在字符的前面
```
***
* 复制命令

```
命令模式
　　　　yy：复制当前行
　　　　yw （y#w,ye,y#e,yb,y#b）：复制光标当前位置到下个单词词首内容.#代表数字
　　　　y#G：复制当前位置当#行处的所有内容

末行模式
　　　　StartADD，EndADDy：复制StartADD到EndADDd位置的所有行，例如：10，15d：复制10到15行所有内容
　　　　+3y：复制当前位置向下3行
```
***
* vim打开多个文件
>在之前打开路径的基础上面提供多个文件的ID就可以了。

```
末行模式下
　　:next 切换到下一个文件
　　:prev 切换到上一个文件
　　:last 切换到最后一个文件
　　:first 切换到第一个文件
退出多个文件
　　qa：全部退出
```
***
* 一些其他的保存等等的win系统的基本文件操作在vim:

```
	末行模式下
    	w：表示保存当前文件
    	w /path/to/somewhere：将当前文件另存为到路径指定的地方
    	ADDR1,ADDR2w /path/to/somewhere：将ADDR1到ADDR2指定的地址文本 另存为到路径指定的地方

	末行模式下
   		r： /path/to/somefile
		例如：vim /etc/rc.sysinit 打开文件后，末行模式 输入 :r /etc/inittab ：会将 inittab文件的所有内容附加到rc.sysinit文件光标所在的位置

	跟shell交互（vim 末行模式执行shell）
	末行模式
		! command即可
		例如： ! ls /etc/：会列出/ect目录下所有文件及目录 按enter后回到vim编辑状态
```
***
* 设置喜好

```
在末行模式下
①显示或取消行号
        set nu（set number）：显示行号
        set nonu：取消显示行号
②显示忽略或区分字符大小写
        set ic （set ignorecase）：忽略大小写
        set noignorecase：区分大小写
③设定自动缩进
       set ai（set autoindent）：自动缩进
        set noai：取消自动缩进
④查找到的文本高亮或取消高亮显示
    set hlsearch：高亮显示搜索到的文本
    set nohlsearch：取消高亮显示搜索到的文本
⑤语法高亮
    syntax on：打开语法高亮显示
    syntax off：关闭语法高亮显示
```
***

## 三、学习vim的心得

vim在功能上面非常强大，这也就是为什么大多数的大牛喜欢使用vim的原因，vim把一切的设计工作全部集成到了一块儿，实现了
真正的一体化，命令很多，配合到linux的终端使用，将会是我们的编辑代码的重要工具，
还有待加强。

***

# 第二部分：ant

## 打开ant
首先我们打开**ant**文件内夹的时候，也就是我们的云桌面放置的那个文件夹的时候，我们ls一下，可以看到它的目录结构：
```
bin
etc
lib
manual
...
```
![image-20201010153424710](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\image-20201010153424710.png)

还有有一些其他的文件，都是已经下载好了的东西（如果没有安装，需要在自己的电脑上面安装一下）。

## 文件夹的作用：
>这里我主要说明下前四个文件夹的作用
>* bin 是 ant 的程序运行入口，如果没有配置 ANT_HOME 的情况下，可以通过 bin 目录中的 bat 程序进行运行 build 任务。如：在 cmd 中运行 ant.bat 就可以执行程序，当然你选择要当前目录中存在一个 build.xml （build.xml是默认的ant执行文件，当然你可以指定其他文件）
>* etc 目录中存放的都是一些xsl的输出模板，创建一个加强的导出各种任务的 XML 输出，使你的 build 文件摆脱过时的警告
>* lib 目录中存放的是 ant 程序需要依赖的 jar 包
>* manual 目录是 ant 程序的帮助文档

## 环境变量

接下来就是我们需要学习如何使用它的问题了，上网看了很多资料，说来说去就是跟<makefile>差不多的功能，但是在我们使用之前，我们需要**配置环境变量**，分成两个部分：

> 1. jdk的配置问题；
>
> 2. ant的配置；
>
>    > 两个配置工作涉及的工作差不多，值过是在Linux环境下面来配置的，这里先在终端检查一下jdk和ant的安装情况，因为云平台已经预装了两部分，所以直接进行我们的配置工作就可以了。
>    >
>    > 根据同学们的讨论结果和TA的提示，我们需要建立在**ant/etc/profile**完成配置工作，之与配置命令在网上查一下就可以了：
>    >
>    > ![image-20201010173657205](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\image-20201010173657205.png)
>    >
>    > jdk应该是已经配置完成了，不需要重新配置了的。

## ant文件的编写

>接下来就是要完成对于ant的<profile>文件里面的<build>文件的编写，这个就是通过编写将一个项目的包含路径，打包路径等等的信息，定义文件夹归属以及编译运行的规则，最终不用手动**javac**和**java**每一个文件，以达到编译整个项目的目的，这里我们利用ant，在etc里面编写我们需要运行的<build>就可以了。关于编写的规则上网大致了解了一点，不算全面，但是hello_world绝对够用：
>
>    > ```php+HTML
>    > <?xml version="1.0" encoding="gbk" ?>
>    > ```
>    >
>    > * 这里是版本号跟编码的方式，gbk可以防止中文乱码（也就是代码注释的乱码）。
>    > ```php+HTML
>    > <project name="HelloWorld" default="run" basedir=".">
>    > ```
>    >
>    > * 接下来就是关于class的名字和基础的依赖目录等等的信息，```default = run```这一句主要是说明这个文件的作用，我们这里就是运行这个程序，因为hello_world的内容比较简单，我们不用生成各种可能的调试信息。
>    > ```php+HTML
>    > <property name="src" value="src"/>
>    > ```
>    > * 然后就是定义资源，资源就是文件目录以及依赖的jar文件，之与说jar文件的作用，在上面的额说明里有所提及，不做赘述。
>    > ```php+HTML
>    > <target name="compile" depends="init">
>    > ```
>    > * 接下来就是生成目标，说到底，我们之前定义的资源类型，就只是生成目标的时候依赖的规则，具体就是生成具体的文件和执行操作的时候需要得到的指示命令。那么我们的文件的编译运行的过程就是在这个阶段完成的，需要注意的问题就是我们应当在此阶段注意依赖关系```depends```,因为运行是建立在编译完成的基础上的，依赖关系类似于c语言的```.o```和```.out```文件，大概就是这么个意思。
>    > ```php+HTML
>    > <java classname="yzp.antdemo.hello_world" classpath="${hello_jar}"/>
>    > ```
>    > * 这个就是我们开头需要的文件包，这里我们运行hello_world定义的```hello.jar```文件，在开头的时候package部分的**包名**就是这个。至此我们编写的简单的文件就完成了，注意build的位置应该位于根目录，当然也可以自行调整，但最好位于根目录下。
>    >
>    > * 接下来简单写一下build文件中涉及到的和没涉及到的**关键字**：
>    >
>    >   <mkdir>是建立目录,<delete>是删除目录,<javac>是编译命令,<java>是运行命令,<classname>是要运行的类，<classpath>是要运行的类jar文件的路径。<srcdir>是源文件所在目录,<destdir>是编译后目标文件所在目录。<jar>是打包命令,<basedir>是要打包文件所在目录,<jarfile>是编译后生成的jar文件。<depends>是target之间相互依赖的关系。default属性是ant默认执行的参数.。<ant> 和 <ant run>执行的结果是一样的,另外还有<ant all>属性，这个就是所有操作（看你涉及的操作有多少）。
***

# java语言
java是一门程序语言，向对于C++来说，java的语言特性决定了它并没有C++那么难以理解，而且java语言在一定的程度上，与C++还是相互之间互通的，所以很多的问题只是语法的问题。主要来看，java基础语法中包含**对象**、**类**、**方法**、**实例变量**等等的内容。
> 1. java是大小写敏感的，类似的一些操作在java当中是要注意大小写的，```Sting[]```等等都是大小写敏感的。
> 2. 变量类型组成和C++一样，唯一不同的是，加入了```byte```类型，范围-128~127，其他的就没有了。
> 3. 修饰符号：
> > * default (即默认，什么也不写）: 在同一包内可见，不使用任何修饰符。使用对象：类、接口、变量、方法。
> > * private : 在同一类内可见。使用对象：变量、方法。 注意：不能修饰类（外部类）
> > * public : 对所有类可见。使用对象：类、接口、变量、方法
> > * protected : 对同一包内的类和所有子类可见。使用对象：变量、方法。 注意：不能修饰类（外部类）
> 4. java的类：
> > * 提供一个比较简易的可以直接引用的类型
> >
> >   ![image-20201010203922944](C:\Users\HP\AppData\Roaming\Typora\typora-user-images\image-20201010203922944.png)
> >   * Number类：
> >     主要是数据类型
> > 	  
> >   * Math类：
> >     提供C++STL当中的<math>函数类似的功能。
> >   * Character类：
> >     提供从type类似的功能……
> ***
> 5. java的数据结构等等
>
> > * arraylist:
```java
import java.util.ArrayList; // 引入 ArrayList 类
ArrayList<E> objectName =new ArrayList<>();　 // 初始化 
```
>> * 使用：
```java
import java.util.ArrayList;

public class RunoobTest {
    public static void main(String[] args) {
        ArrayList<String> sites = new ArrayList<String>();
        sites.add("Google");
        sites.add("Runoob");
        sites.add("Taobao");
        sites.add("Weibo");
        System.out.println(sites);
    }
}
```
> >* linkedlist:
```java
// 引入 LinkedList 类
import java.util.LinkedList; 

LinkedList<E> list = new LinkedList<E>();   // 普通创建方法
或者
LinkedList<E> list = new LinkedList(Collection<? extends E> c); // 使用集合创建链表
```
>> * 使用：
```java
import java.util.LinkedList;

public class RunoobTest {
    public static void main(String[] args) {
        LinkedList<String> sites = new LinkedList<String>();
        sites.add("Google");
        sites.add("Runoob");
        sites.add("Taobao");
        sites.add("Weibo");
        System.out.println(sites);
    }
}
```
总的来说我们需要注意的就是```java```跟```C++```在于宁实例上的一些微小的差别，除了这个```java```语言在组成上跟我们的```C++```还是很类似的。
***

# Junit学习

* **Junit**介绍：

  > Junit是一个Java语言的单元测试框架。它由Kent Beck和Erich Gamma建立，逐渐成为源于Kent Beck的<sUnit>的<xUnit>家族中最为成功的一个JUnit有它自己的JUnit扩展生态圈。多数Java的开发环境都已经集成了JUnit作为单元测试的工具。

  > 注意：Junit 测试也是程序员测试，即所谓的白盒测试，它需要程序员知道被测试的代码如何完成功能，以及完成什么样的功能
* **Junit的作用**
> 为了学习了解junit，看了很多的教程，翻来覆去其实就一个点，junit的功能跟之前的C++的调试测试用的gdb功能差不多，就是用来调试代码，生成调试的信息的一个工具，但是说到底还是得结合ant的使用才比较简洁和高效，它剪短了我们调试所有可能出现的问题的时间。最终我们的信息如果结合使用ant的话，我们还得在build文件当中做手脚。
> 
>当然如果我们使用一些集成的IDE的话，可以直接使用集成环境的junit，而不用自己动手配置和书写，这也就是在终端编辑代码我们必须要学会的操作。

* **学习的文件内容**：
> 下面是学习书写的文件的内容，提示我们再次基础上面如何生成调试信息，当然只是运行hello_world显然没有这么复杂；
```php+HTML
<?xml version="1.0" encoding="gb2312"?>
<project name="Test Project" default="all" basedir=".">

    <property name="src.code" value="src"/>
    <property name="src.junit" value="junit"/>
    <property name="lib.dir" value="lib"/>
    <property name="lib.jar" value="TestClass.jar"/>

    <!-- checkstyle configuration -->
    <property name="checkstyle.config" value="checkstyle.xml"/>
    <taskdef resource="checkstyletask.properties"
             classpath="${lib.dir}/checkstyle-all-3.1.jar"/>


    <!-- 输出文档 -->
    <property name="doc.dir" value="doc"/>
    <property name="doc.api" value="${doc.dir}/api"/>
    <property name="javadoc.package" value="com.*"/>

    <!-- 输出二进制文件 -->
    <property name="dist.root" value="dist"/>
    <property name="dist.proj" value="${dist.root}/proj"/>
    <property name="dist.classes" value="${dist.proj}/classes"/>
    <property name="dist.lib" value="${dist.proj}/lib"/>
    <property name="dist.junit" value="${dist.root}/junit"/>

    <!-- classpath -->
    <path id="classpath">
        <fileset dir="${lib.dir}">
            <include name="**/*.jar"/>
        </fileset>
    </path>

    <path id="proj.libs">
        <fileset dir="${dist.lib}">
            <include name="**/*.jar"/>
        </fileset>      
    </path>

    <target name="init">
        <mkdir dir="${doc.dir}"/>
        <mkdir dir="${dist.root}"/>
        <mkdir dir="${dist.proj}"/>
        <mkdir dir="${dist.lib}"/>
        <tstamp/>
        <echo message="${TSTAMP}"></echo>
    </target>

    <target name="all" depends="compilesrc, javadoc, checkstyle"/>

    <!-- 编译源文件 -->
    <target name="compilesrc" depends="init">
        <mkdir dir="${dist.classes}"/>

        <javac destdir="${dist.classes}" deprecation="on">
            <src path="${src.code}"/>
            <classpath refid="classpath"/>
        </javac>

        <jar jarfile="${dist.lib}/${lib.jar}" basedir="${dist.classes}">
            <include name="**/*.class"/>
        </jar>      
    </target>

    <!--  产生javadoc -->
    <target name="javadoc" depends="init">
        <mkdir dir="${doc.api}"/>

        <javadoc packagenames="${javadoc.package}" sourcepath="${src.code}"
             private="yes" defaultexcludes="yes" destdir="${doc.dir}/api">
            <classpath refid="classpath"/>
        </javadoc>
    </target>

    <!--  编译Junit文件 -->
    <target name="compilejunit" depends="compilesrc">
        <mkdir dir="${dist.junit}"/>

        <javac destdir="${dist.junit}" deprecation="on">
            <src path="${src.junit}"/>
            <classpath refid="classpath"/>
            <classpath refid="proj.libs"/>
        </javac>        
    </target>

    <!-- 运行checkstyle检查代码规范 -->
    <target name="checkstyle" depends="init">
        <checkstyle config="${checkstyle.config}">
            <fileset dir="${src.code}" includes="**/*.java"/>
            <formatter type="plain"/>
            <formatter type="xml" toFile="${dist.root}/checkstyle_errors.xml"/>
        </checkstyle>
    </target>

    <!--  运行junit  -->
    <target name="junit" depends="compilejunit">
        <junit printsummary="yes" haltonfailure="yes">
            <classpath>
                <path refid="classpath"/>
                <pathelement location="${dist.junit}"/>
            </classpath>

            <formatter type="plain"/>

            <!-- test name="com.TestClassTest" haltonfailure="no" outfile="result"/ -->

            <batchtest todir="${dist.junit}">
                <fileset dir="${dist.junit}" includes="**/Test*.class" />
            </batchtest>
        </junit>
    </target>

    <!-- 清除产生的类、junit相关类、文档 -->
    <target name="clean">
        <delete dir="${dist.classes}"/>
        <delete dir="${dist.junit}"/>
        <delete dir="${doc.api}"/>
    </target>

    <!-- 清除所有输出结果 -->
    <target name="cleanall" depends="clean">
        <delete dir="${doc.dir}"/>
        <delete dir="${dist.root}"/>
    </target>
</project> 
```
* **junit的学习心得**
> 自我感觉<junit>就是一个调试工具，要结合我们的ant才能发挥出来更加出色亮眼的性能。

   





 