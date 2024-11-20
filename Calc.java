import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;
public class Calc {
    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setSize(1120,700);
        frame.setTitle("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        frame.setLayout(new BorderLayout());
        //input panel displays and shows the input and output
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        JTextField inputField = new JTextField();
        inputField.setEditable(false);
        inputField.setFocusable(false);
        inputField.setPreferredSize(new Dimension(200, 50)); // Set size of input field
        inputPanel.add(inputField, BorderLayout.CENTER);
        // number buttons 
        JPanel numPanel = new JPanel();
        frame.add(numPanel);
        numPanel.setLayout(new GridLayout(4,3,10,10));
        numPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        for (int i =9; i>0; i--){
            JButton button = new JButton(String.valueOf(i));
            button.setPreferredSize(new Dimension(80, 80)); // Set size of number buttons
            numPanel.add(button);
            button.addActionListener(e -> add(inputField, button.getText()));
        }
        JButton button_clear = new JButton("C");
        button_clear.setPreferredSize(new Dimension(80, 80)); // Set size of clear button
        numPanel.add(button_clear);
        button_clear.addActionListener(e -> clear(inputField));
        JButton button_0 = new JButton("0");
        button_0.setPreferredSize(new Dimension(80, 80)); // Set size of 0 button
        numPanel.add(button_0);
        button_0.addActionListener(e -> add(inputField, button_0.getText()));
        JButton button_equal = new JButton("=");
        button_equal.setPreferredSize(new Dimension(80, 80)); // Set size of equals button
        numPanel.add(button_equal);
        button_equal.addActionListener(e -> equal(inputField));
        // sybmols panel
        JPanel symPanel = new JPanel();
        // frame.add(symPanel, BorderLayout.EAST);
        symPanel.setLayout(new GridLayout(4,1,10,10));



        //add button
        JButton button = new JButton("+");
        symPanel.add(button);
        button.addActionListener(e -> add(inputField, button.getText()));
        button.setPreferredSize(new Dimension(80, 80)); // Set size of add button
        button.setBackground(Color.ORANGE);
        // substract button
        JButton button_sub = new JButton("-");
        symPanel.add(button_sub);
        button_sub.addActionListener(e -> add(inputField, button_sub.getText()));
        button_sub.setPreferredSize(new Dimension(80, 80)); // Set size of substract button

        //divide button
        JButton button_div = new JButton("/");
        symPanel.add(button_div);
        button_div.setPreferredSize(new Dimension(80, 80)); // Set size of divide buttonj
        button_div.addActionListener(e -> add(inputField, button_div.getText()));
        //multiply button
        JButton button_mul = new JButton("*");
        symPanel.add(button_mul);
        button_mul.setPreferredSize(new Dimension(80, 80)); // Set size of multiply button
        button_mul.addActionListener(e -> add(inputField, button_mul.getText()));
        // frame.getContentPane().setLayout(new BorderLayout());

        frame.getContentPane().add(inputPanel, BorderLayout.NORTH);
        frame.getContentPane().add(numPanel, BorderLayout.CENTER);
        frame.getContentPane().add(symPanel, BorderLayout.EAST);

        frame.setVisible(true);
    }
    public static void add(JTextField inputField, String text){
        inputField.setText(inputField.getText() + text);
    }
    public static void clear(JTextField inputField){
        inputField.setText("");
    }
    public static void equal(JTextField inputField){
        String input = inputField.getText();
        inputField.setText(String.valueOf(eval(input)));
    }
    public static double eval(String input){
       String[] tokens = input.split("(?<=[-+*/])|(?=[-+*/])");

    //    System.out.println(Arrays.toString(tokens));

       String  calc = Arrays.toString(tokens);

       ArrayList<Integer> numbers = new ArrayList<>();
       ArrayList<String> symbols = new ArrayList<>();
       for (int i =0; i<tokens.length;i++ ){


        if(tokens[i].matches("\\d+")){
            System.out.println("is digit"+tokens[i]);
            numbers.add(Integer.parseInt(tokens[i]));

        }
        else {
            System.out.println("is symbol"+tokens[i]);
            symbols.add(tokens[i]);
        }
       }
      return calcation(numbers, symbols);
    }
    public static double calcation(ArrayList<Integer> numbers, ArrayList<String> operators) {
        // First handle multiplication and division
        for (int i = 0; i < operators.size(); i++) {
            if (operators.get(i).equals("*") || operators.get(i).equals("/")) {
                double result;
                if (operators.get(i).equals("*")) {
                    result = numbers.get(i) * numbers.get(i + 1);
                } else {
                    result = numbers.get(i) / (double)numbers.get(i + 1);
                }
                // Replace the two numbers with their result
                numbers.set(i, (int)result);
                numbers.remove(i + 1);
                operators.remove(i);
                i--; // Step back one position since we removed an operator
            }
        }
        
        // Then handle addition and subtraction
        double result = numbers.get(0);
        for (int i = 0; i < operators.size(); i++) {
            if (operators.get(i).equals("+")) {
                result += numbers.get(i + 1);
            } else if (operators.get(i).equals("-")) {
                result -= numbers.get(i + 1);
            }
        }
        
        return result;
    }
}

