import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

public class GUI implements ActionListener {

    private static JFrame frame;
    private static JPanel main;
    private static JPanel panel;
    private static JPanel panel2;
    private static JTextField userText1;
    private static JTextField userText2;
    private static JTextField userText3;
    private static JLabel userLabel5;
    private static JLabel userLabel7;
    private static JLabel userLabel9;
    private static JButton StartButton;


    GUI(){
        frame = new JFrame("Assignment 1");
        frame.setSize(350, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        main = new JPanel(new GridLayout(2,1));

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.CYAN);

        JLabel userLabel1 = new JLabel("N");
        userLabel1.setBounds(10,20,80,25);
        panel.add(userLabel1);

        userText1 = new JTextField(20);
        userText1.setBounds(100,20,165,25);
        panel.add(userText1);

        JLabel userLabel2 = new JLabel("Buffer size");
        userLabel2.setBounds(10,50,80,25);
        panel.add(userLabel2);

        userText2 = new JTextField(20);
        userText2.setBounds(100,50,165,25);
        panel.add(userText2);
        
        JLabel userLabel3 = new JLabel("Output file");
        userLabel3.setBounds(10,80,80,25);
        panel.add(userLabel3);

        userText3 = new JTextField(20);
        userText3.setBounds(100,80,165,25);
        panel.add(userText3);

        JButton StartButton = new JButton("Start");
        StartButton.setBounds(10, 110, 80, 25);
        StartButton.addActionListener(this);
        panel.add(StartButton);

        ///////////////////////////////////////////////////////////////

        panel2 = new JPanel();
        panel2.setBackground(Color.GRAY);
        panel2.setLayout(null);

        JLabel userLabel4 = new JLabel("The largest prime number :");
        userLabel4.setBounds(10,20,80,25);
        panel2.add(userLabel4);

        userLabel5 = new JLabel();
        userLabel5.setBounds(100,20,165,25);
        panel2.add(userLabel5);

        JLabel userLabel6 = new JLabel("# of primes generated :");
        userLabel6.setBounds(10,50,80,25);
        panel2.add(userLabel6);

        userLabel7 = new JLabel();
        userLabel7.setBounds(100,50,165,25);
        panel2.add(userLabel7);

        JLabel userLabel8 = new JLabel("Time elapsed :");
        userLabel8.setBounds(10,80,80,25);
        panel2.add(userLabel8);

        userLabel9 = new JLabel();
        userLabel9.setBounds(100,80,165,25);
        panel2.add(userLabel9);

        ///////////////////////////////////////////////////////////////
        
        main.add(panel);
        main.add(panel2);
        frame.add(main);
        frame.setVisible(true);

        //////////////////////////////////////////////////////////////
    }

    private static void ProgramStart(int max , int bufferSize , String fileName){
        Buffer b = new Buffer(bufferSize);
        StopWatch stopWatch = new StopWatch();
        Thread t1 = new Thread(new Producer(b, max));
        Thread t2 = new Thread(new Consumer(b, fileName));
        Thread t3 = new Thread(stopWatch);
        t1.start();
        t2.start();
        t3.start();
        while(t1.isAlive()){
            userLabel5.setText(""+b.largest);
            userLabel7.setText(""+b.counter);
            userLabel9.setText(""+stopWatch.time);
        }
    }

    public static void main(String[] args) {
        new GUI();
        // Buffer b = new Buffer(5);
        // Thread t1 = new Thread(new Producer(b, 1000));
        // Thread t2 = new Thread(new Consumer(b, "fileName"));
        // t1.start();
        // t2.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Integer IMax = Integer.parseInt(userText1.getText());
        int max = IMax.intValue();
        Integer IBufferSize = Integer.parseInt(userText2.getText());
        int bufferSize = IBufferSize.intValue();
        String fileName = userText3.getText();
        ProgramStart(max , bufferSize , fileName);
    }
}