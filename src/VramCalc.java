import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.Objects;

public class VramCalc extends JFrame implements ActionListener {
    int bus;
    double freq;
    double band;
    JFrame frame = new JFrame("VRAM Memory Bandwidth Calculator");
    JPanel titlePanel;
    JPanel fieldsPanel;
    JPanel resultPanel;
    JPanel buttonsPanel;
    JTextField busField;
    JTextField freqField;
    JLabel title;
    JLabel busLabel;
    JLabel freqLabel;
    JLabel resultLabel;
    JLabel bandLabel;
    JButton calculateButton;
    JButton resetButton;
    ImageIcon icon;

    VramCalc(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(375,400);
        frame.setBackground(Color.lightGray);
        frame.setResizable(false);
        frame.setLayout(new GridLayout(4,1));
        icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("img/icon.png")));
        frame.setIconImage(icon.getImage());

        title = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("img/logo.png"))));
        titlePanel = new JPanel();
        titlePanel.add(title);
        titlePanel.setLayout(new GridLayout(1,1));

        busField = new JTextField();
        busField.setPreferredSize(new Dimension(100,40));
        busField.setFont(new Font(null, Font.BOLD, 20));
        busField.setForeground(Color.green);
        busField.setBackground(Color.black);
        busField.setCaretColor(Color.white);
        busField.setHorizontalAlignment(JTextField.RIGHT);

        freqField = new JTextField();
        freqField.setPreferredSize(new Dimension(100,40));
        freqField.setFont(new Font(null, Font.BOLD, 20));
        freqField.setForeground(Color.green);
        freqField.setBackground(Color.black);
        freqField.setCaretColor(Color.white);
        freqField.setHorizontalAlignment(JTextField.RIGHT);

        busLabel = new JLabel("BUS Width");
        busLabel.setFont(new Font("SansSerif",Font.BOLD,16));
        freqLabel = new JLabel("Frequency (in GHz)");
        freqLabel.setFont(new Font("SansSerif",Font.BOLD,16));

        fieldsPanel = new JPanel();
        fieldsPanel.add(busLabel);
        fieldsPanel.add(busField);
        fieldsPanel.add(freqLabel);
        fieldsPanel.add(freqField);
        fieldsPanel.setLayout(new GridLayout(2,1,10, 5));

        resultLabel = new JLabel("Memory Bandwidth");
        resultLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        resultLabel.setHorizontalAlignment(JLabel.CENTER);
        bandLabel = new JLabel();
        bandLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        bandLabel.setHorizontalAlignment(JLabel.CENTER);
        bandLabel.setVisible(false);

        resultPanel = new JPanel();
        resultPanel.add(resultLabel);
        resultPanel.add(bandLabel);
        resultPanel.setLayout(new GridLayout(2,1));

        calculateButton = new JButton("CALCULATE");
        calculateButton.setBounds(0,0,100,40);
        calculateButton.setFocusable(true);
        calculateButton.addActionListener(this);
        resetButton = new JButton("RESET");
        resetButton.setBounds(0,0,100,40);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1,2));
        buttonsPanel.add(calculateButton);
        buttonsPanel.add(resetButton);

        frame.add(titlePanel);
        frame.add(fieldsPanel);
        frame.add(resultPanel);
        frame.add(buttonsPanel);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==calculateButton) {
            try {
                bus = Integer.parseInt(this.busField.getText());
                freq = Double.parseDouble(this.freqField.getText());
                band = ((bus * freq) / 8);
                bandLabel.setText(String.format(Locale.US ,"%.2f", band) + " GBp/s");
                bandLabel.setVisible(true);
            } catch (NumberFormatException ex) {
                bandLabel.setText("Please, use valid numbers.");
                bandLabel.setVisible(true);
            }
        } else if(e.getSource()==resetButton){
            busField.setText("");
            freqField.setText("");
            bandLabel.setVisible(false);
        }
    }
}
