import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RandomNumberGeneratorWithIntervalUI extends JFrame {

    private JTextField intervalField, minField, maxField;
    private JButton startButton, stopButton;
    private JLabel randomNumberLabel;
    private boolean generating;

    public RandomNumberGeneratorWithIntervalUI() {
        setTitle("Random Number Generator");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 5, 5));

        panel.add(new JLabel("Time Interval (seconds):"));
        intervalField = new JTextField();
        panel.add(intervalField);

        panel.add(new JLabel("Minimum Number:"));
        minField = new JTextField();
        panel.add(minField);

        panel.add(new JLabel("Maximum Number:"));
        maxField = new JTextField();
        panel.add(maxField);

        startButton = new JButton("Start");
        panel.add(startButton);

        stopButton = new JButton("Stop");
        panel.add(stopButton);

        panel.add(new JLabel("Random Number:"));
        randomNumberLabel = new JLabel("");
        panel.add(randomNumberLabel);

        add(panel);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGenerating();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopGenerating();
            }
        });
    }

    private void startGenerating() {
        int timeInterval = Integer.parseInt(intervalField.getText());
        int min = Integer.parseInt(minField.getText());
        int max = Integer.parseInt(maxField.getText());

        generating = true;

        Random random = new Random();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (generating) {
                    int randomNumber = random.nextInt(max - min + 1) + min;
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            randomNumberLabel.setText(Integer.toString(randomNumber));
                        }
                    });
                    try {
                        Thread.sleep(timeInterval * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void stopGenerating() {
        generating = false;
        randomNumberLabel.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                RandomNumberGeneratorWithIntervalUI generator = new RandomNumberGeneratorWithIntervalUI();
                generator.setVisible(true);
            }
        });
    }
}
