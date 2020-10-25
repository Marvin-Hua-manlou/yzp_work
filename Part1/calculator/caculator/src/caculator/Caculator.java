/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caculator;//包名字
import javax.swing.*;//导入要用到的包
import java.awt.*;
import java.awt.event.ActionEvent;//类似于javascript,增加事件和监听
import java.awt.event.ActionListener;
/**
 *
 * @author HP
 */

class Caculator {
  private static char operation = '\0';
  private static int size = 70;//button的大小
  private static int margin = 30;//设置边界的大小

  // 声明变量类型
  private TextField firstNumber , secondNumber;//两个数字
  private Label operator, equal, result;

  public void init() {
      /**
     * @param args the command line arguments
     */
    // 设置外部轮廓
    JFrame my_Frame = new JFrame("Calculator");
    my_Frame.setSize(5 * size, 2 * size + margin);
    my_Frame.setVisible(true);
    my_Frame.setLayout(null);

    // 设置数字处理行和显示行
    Panel first = new Panel();//开辟一个新的行
    first.setSize(5 * size, size);//数字加上运算符、结果、总共五个字符位置
    first.setLocation(0, 0);
    first.setLayout(null);

    firstNumber = new TextField();
    firstNumber.setSize(size - 1, size - 1);//留一个框框的形状
    firstNumber.setLocation(0, 0);
    //firstNumber.setText("12");

    operator = new Label("", Label.CENTER);//居中
    operator.setSize(size - 1, size - 1);
    operator.setLocation(size, 0);
    //secondNumber.setText("2");

    secondNumber = new TextField();
    secondNumber.setSize(size - 1, size - 1);
    secondNumber.setLocation(2 * size, 0);

    equal = new Label("=", Label.CENTER);
    equal.setSize(size - 1, size - 1);
    equal.setLocation(3 * size, 0);

    result = new Label("", Label.CENTER);
    result.setSize(size -1, size -1);
    result.setLocation(4 * size, 0);

    first.add(firstNumber);
    first.add(operator);
    first.add(secondNumber);
    first.add(equal);
    first.add(result);
    // 符号行
    Panel second = new Panel();
    second.setSize(5 * size, size);
    second.setLocation(0, size);
    second.setLayout(null);
    Button adder = new Button("+");
    adder.setSize(size,size);
    adder.setLocation(0, 0);
    adder.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        setoperator('+');
      }
    });
    Button minus = new Button("-");
    minus.setSize(size, size);
    minus.setLocation(size, 0);
    minus.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        setoperator('-');
      }
    });
    Button multiply = new Button("*");
    multiply.setSize(size, size);
    multiply.setLocation(2 * size, 0);
    multiply.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        setoperator('*');
      }
    });
    Button divideby = new Button("/");
    divideby.setSize(size, size);
    divideby.setLocation(3 * size, 0);
    divideby.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        setoperator('/');
      }
    });
    Button ok = new Button("OK");
    ok.setSize(size, size);
    ok.setLocation(4 * size, 0);
    ok.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        calculate();//计算结果
      }
    });
    second.add(adder);
    second.add(minus);
    second.add(multiply);
    second.add(divideby);
    second.add(ok);
    //写好两行加到主图层
    my_Frame.add(first);
    my_Frame.add(second);
  }
  //设置用到的操作符的函数
  public void setoperator(char op) {
    operator.setText(String.valueOf(op));
    operation = op;
  }
  // 计算结果的函数
  public void calculate() {
    boolean flag;
    String num1_str, num2_str;
    double num1 = 0, num2 = 0, ans = 0;
    try {
      //firstNum.setText("12");
      //num1str = firstNum.toString();
      //secondNum.setText("2");
      //num2str = secondNum.toString();
      num1_str = firstNumber.getText();
      num2_str = secondNumber.getText();
      num1 = Double.valueOf(num1_str);
      num2 = Double.valueOf(num2_str);
      flag = true;
    } catch (Exception e) {
      flag = false;
    }

    if (flag) {
      switch (operation) {
        case '+':
          ans = num1 + num2;
          break;
        case '-':
          ans = num1 - num2;
          break;
        case '*':
          ans = num1 * num2;
          break;
        case '/':
          ans = num1 / num2;
          break;
      }
      result.setText(Double.toString(ans));
    } else {
      result.setText("Error!");
    }
  }
  //检测输入的数据合理性
  public static boolean isNum(String str) {
    return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
  }

  public static void main(String[] args) {
    new Caculator().init();
  }
}
