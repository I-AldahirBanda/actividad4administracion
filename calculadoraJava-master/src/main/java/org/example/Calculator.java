package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {
    private JFrame frame;
    private JTextField textField;
    private JButton[] numberButtons = new JButton[10];
    private JButton addButton, subButton, mulButton, divButton, equButton, clrButton;
    private JPanel panel;

    private double num1 = 0, num2 = 0, result = 0;
    private char operator;

    public Calculator() {
        frame = new JFrame("Calculator");
        textField = new JTextField();
        textField.setEditable(false);
        textField.setFont(new Font("Arial", Font.PLAIN, 24));

        // Initialize buttons
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setFont(new Font("Arial", Font.PLAIN, 24));
            numberButtons[i].addActionListener(new NumberButtonListener());
        }

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        equButton = new JButton("=");
        clrButton = new JButton("C");

        // Set fonts for operator buttons
        addButton.setFont(new Font("Arial", Font.PLAIN, 24));
        subButton.setFont(new Font("Arial", Font.PLAIN, 24));
        mulButton.setFont(new Font("Arial", Font.PLAIN, 24));
        divButton.setFont(new Font("Arial", Font.PLAIN, 24));
        equButton.setFont(new Font("Arial", Font.PLAIN, 24));
        clrButton.setFont(new Font("Arial", Font.PLAIN, 24));

        // Add action listeners for operator buttons
        addButton.addActionListener(new OperatorButtonListener());
        subButton.addActionListener(new OperatorButtonListener());
        mulButton.addActionListener(new OperatorButtonListener());
        divButton.addActionListener(new OperatorButtonListener());
        equButton.addActionListener(new EqualButtonListener());
        clrButton.addActionListener(new ClearButtonListener());

        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        // Add buttons to panel
        for (int i = 1; i <= 9; i++) {
            panel.add(numberButtons[i]);
        }
        panel.add(addButton);
        panel.add(numberButtons[0]);
        panel.add(subButton);
        panel.add(mulButton);
        panel.add(divButton);
        panel.add(equButton);
        panel.add(clrButton);

        // Add components to frame
        frame.setLayout(new BorderLayout());
        frame.add(textField, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);

        // Finalize frame
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private class NumberButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            textField.setText(textField.getText() + source.getText());
        }
    }

    private class OperatorButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            num1 = Double.parseDouble(textField.getText());
            operator = source.getText().charAt(0);
            textField.setText("");
        }
    }

    private class EqualButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            num2 = Double.parseDouble(textField.getText());

            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        textField.setText("Error: Div by 0");
                        return;
                    }
                    break;
            }
            textField.setText(String.valueOf(result));
            num1 = result;
        }
    }

    private class ClearButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            textField.setText("");
            num1 = 0;
            num2 = 0;
            result = 0;
            operator = ' ';
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
