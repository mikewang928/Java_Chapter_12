package Chapter_12;

import java.awt.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class TextTest {
   public static void main(String[] args){
       EventQueue.invokeLater(()->{
           TextComponentFrame frame = new TextComponentFrame();
           frame.setTitle("TextField test");
           frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           frame.setVisible(true);
       });
   }
}


class TextComponentFrame extends JFrame{
    Toolkit kit = Toolkit.getDefaultToolkit();
    Dimension screenSize = kit.getScreenSize();
    public final int DEFAULT_WIDTH = (int)(screenSize.getWidth()*0.6);
    public final int DEFAULT_HEIGHT =(int)(screenSize.getHeight()*0.6);
    public final int TEXTAREA_ROWS = (int)(DEFAULT_HEIGHT*0.05);
    public final int TEXTAREA_COLUMNS = (int)(DEFAULT_WIDTH*0.1);

    public TextComponentFrame(){
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
        // annancing the textField and passwordField
        JTextField textField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        // adding the textField and passwordField to hte northPanel
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(2,2));
        northPanel.add(new JLabel("User name: ", SwingConstants.RIGHT));
        northPanel.add(textField);
        northPanel.add(new JLabel("Password: ", SwingConstants.RIGHT));
        northPanel.add(passwordField);

        add(northPanel, BorderLayout.NORTH);

        // annancing the textArea
        JTextArea textArea = new JTextArea(TEXTAREA_ROWS,TEXTAREA_COLUMNS);
        JScrollPane scrollPane = new JScrollPane(textArea);

        // adding the textArea to ScrollPane and located at the center
        add(scrollPane, BorderLayout.CENTER);

        //add button to append text into the text area
        JPanel southPanel = new JPanel();

        // create a insert button named as "insert"
        JButton insertButton = new JButton("insert");
        southPanel.add(insertButton);
        insertButton.addActionListener(event ->
                textArea.append("User name: " +textField.getText()+ "\n"+ "Password: "
                    + new String(passwordField.getPassword()) +"\n"));

        add(southPanel, BorderLayout.SOUTH);
        pack();

    }
}
