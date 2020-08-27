package Chapter_12;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Calculator v2.1
 * TODO: fix unsigned double issues (-20 +/- also equals to -20) suspect place parseDouble method
 * TODO: fix A/C no response issue
 * TODO: Change the Calculate logic (when hit '=' returns result not x)
 */

public class Calculator_2_0 {
    public static void main(String[] args) {
        CalculatorFrame_2_0 frame = new CalculatorFrame_2_0();
        frame.setTitle("CalculatorV2.0");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}

/**
 * creat  a frame which contains a Cpanel
 */
class CalculatorFrame_2_0 extends JFrame {
    Toolkit kit = Toolkit.getDefaultToolkit();
    Dimension screenSize = kit.getScreenSize();
    final int DEFAULT_WIDTH = (int) (screenSize.getWidth() * 0.2);
    final int DEFAULT_HEIGHT = (int) (screenSize.getHeight() * 0.2);

    public CalculatorFrame_2_0() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        CalculatorPanel_2_0 Cpanel = new CalculatorPanel_2_0();
        add(Cpanel);
    }
}

/**
 * A panel with calculator buttons and a result display
 */

class CalculatorPanel_2_0 extends JPanel {
    private JButton display;
    private JPanel panel;
    private double result;
    private String lastCommand;
    private boolean start;

    public CalculatorPanel_2_0() {
        setLayout(new BorderLayout());

        result = 0;
        lastCommand = "=";
        start = true;

        //add the display

        display = new JButton("");
        display.setEnabled(false);
        add(display, BorderLayout.NORTH);

        ActionListener insert = new InsertAction();
//        ActionListener del = new DeletAction();
//        ActionListerer clear = new ClearAction();
        ActionListener command = new CommandAction();

        // add the buttons in a 4*4 grid
        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4));

        addButton("A/C", command);
        addButton("%", command);
        addButton("+/-", command);
        addButton("<-", insert);

        addButton("7", insert);
        addButton("8", insert);
        addButton("9", insert);
        addButton("/", command);

        addButton("4", insert);
        addButton("5", insert);
        addButton("6", insert);
        addButton("*", command);

        addButton("1", insert);
        addButton("2", insert);
        addButton("3", insert);
        addButton("-", command);

        addButton(".", insert);
        addButton("0", insert);
        addButton("=", command);
        addButton("+", command);

        add(panel, BorderLayout.CENTER);
    }

    /**
     * Adds a button to the center
     *
     * @param label
     * @param listener
     */
    private void addButton(String label, ActionListener listener) {
        JButton button = new JButton(label);
        button.addActionListener(listener);
        panel.add(button);
    }


    /**
     * this class inserts the button action string to the end of the display text.
     */
    private class InsertAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String input = event.getActionCommand();
            if (start) {
//                if(display.getText().length()==0) {
                    display.setText("");
                    start = false;
//                }
            }
            if (input.equals("<-")) {
                if ("".equals(display.getText())) {
                    System.out.println("display空 = " + display.getText());
                    display.setText("");
                } else {
                    System.out.println("display！ = " + display.getText());
                    display.setText(display.getText().substring(0, display.getText().length() - 1));
                }
            } else {
                System.out.println("input = " + input);
                display.setText(display.getText() + input);
            }
        }
    }

    /**
     * This action executes the command that the button action string denotes
     */
    private class CommandAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String command = event.getActionCommand();

            if (start) {
                System.out.println("true：start = " + start);
                if (display.getText().length() == 0) {
                    if (command.equals("-") || command.equals("+") || command.equals("*") || command.equals("/")) {
                        display.setText("Error");
                        start = true;
                    } else if (command.equals("A/C")) {
                        display.setText("");
                    } else {
                        start = false;
                    }
                } else lastCommand = command;
            } else {
                System.out.println("false：start = " + start);
                lastCommand = command;
                calculate(Double.parseDouble(display.getText()));

                start =  true;
            }
        }
    }

    /**
     * Carries out the pending calculation.
     *
     * @param x the value to be accumulated with the prior result.
     */
    public void calculate(double x) {
        if (lastCommand.equals("+")) result += x;
        else if (lastCommand.equals("*")) result *= x;
        else if (lastCommand.equals("-")) result -= x;
        else if (lastCommand.equals("/")) result /= x;
        else if (lastCommand.equals("=")) result = x;
        else if (lastCommand.equals("%")) {
            result = x * 0.01;
        } else if (lastCommand.equals("+/-")) {
            result = x * (-1);
        } else if (lastCommand.equals("A/C")) result = 0;
        display.setText("" + result);


    }
}

