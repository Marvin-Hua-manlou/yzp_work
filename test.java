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

  // declarations. declare varibles here so that each function can use them
  private TextField firstNum , secondNum;//两个数字
  private Label operator, equal, result;

  public void init() {
      /**
     * @param args the command line arguments
     */
    // setups the outer frame.
    JFrame mainFrame = new JFrame("Calculator");
    mainFrame.setSize(5 * size, 2 * size + margin);
    mainFrame.setVisible(true);
    mainFrame.setLayout(null);

    // setups the first line's buttons and textboxes
    Panel line1 = new Panel();
    line1.setSize(5 * size, size);
    line1.setLocation(0, 0);
    line1.setLayout(null);

    firstNum = new TextField();
    firstNum.setSize(size - 1, size - 1);
    firstNum.setLocation(0, 0);
    //firstNum.setText("12");

    operator = new Label("", Label.CENTER);
    operator.setSize(size - 1, size - 1);
    operator.setLocation(size, 0);
    //secondNum.setText("2");

    secondNum = new TextField();
    secondNum.setSize(size - 1, size - 1);
    secondNum.setLocation(2 * size, 0);

    equal = new Label("=", Label.CENTER);
    equal.setSize(size - 1, size - 1);
    equal.setLocation(3 * size, 0);

    result = new Label("", Label.CENTER);
    result.setSize(size -1, size -1);
    result.setLocation(4 * size, 0);

    line1.add(firstNum);
    line1.add(operator);
    line1.add(secondNum);
    line1.add(equal);
    line1.add(result);

    // setups the second line's buttons.
    Panel line2 = new Panel();
    line2.setSize(5 * size, size);
    line2.setLocation(0, size);
    line2.setLayout(null);

    Button plus = new Button("+");
    plus.setSize(size,size);
    plus.setLocation(0, 0);
    plus.addActionListener(new ActionListener() {
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

    Button divide = new Button("/");
    divide.setSize(size, size);
    divide.setLocation(3 * size, 0);
    divide.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        setoperator('/');
      }
    });

    Button ok = new Button("ok");
    ok.setSize(size, size);
    ok.setLocation(4 * size, 0);
    ok.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        calculate();
      }
    });

    line2.add(plus);
    line2.add(minus);
    line2.add(multiply);
    line2.add(divide);
    line2.add(ok);

    // adds the two lines to the outer frame.
    mainFrame.add(line1);
    mainFrame.add(line2);
  }


  // functions used in actionlistners.
  public void setoperator(char op) {
    operator.setText(String.valueOf(op));
    operation = op;
  }

  // Calculate the result.
  public void calculate() {
    boolean flag;
    String num1str, num2str;
    double num1 = 0, num2 = 0, answer = 0;
    try {
      firstNum.setText("12");
      //num1str = firstNum.toString();
      secondNum.setText("2");
      //num2str = secondNum.toString();
      num1str = firstNum.getText();
      num2str = secondNum.getText();
      num1 = Double.valueOf(num1str);
      num2 = Double.valueOf(num2str);
      flag = true;
    } catch (Exception e) {
      flag = false;
    }

    if (flag) {
      switch (operation) {
        case '+':
          answer = num1 + num2;
          break;
        case '-':
          answer = num1 - num2;
          break;
        case '*':
          answer = num1 * num2;
          break;
        case '/':
          answer = num1 / num2;
          break;
      }
      result.setText(Double.toString(answer));
    } else {
      result.setText("Error!");
    }
  }

  // function to find that if a string a number.
  public static boolean isNum(String str) {
    return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
  }

  public static void main(String[] args) {
    new Caculator().init();
  }
}
