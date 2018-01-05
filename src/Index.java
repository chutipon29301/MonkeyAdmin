/*
Copyright [2017] [Chutipon]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Index extends JFrame {

    private JTextField textField;
    static final String VDO_LOCATION = "\\\\192.168.1.150\\vdo\\";
    static final String DB_LOCATION = "\\\\192.168.1.150\\database\\";

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Index frame = new Index();
                frame.setTitle("Monkey Admin Version 1.4.1");
                frame.setExtendedState(MAXIMIZED_BOTH);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    private Index() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 1920, 1080);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Scan Student ID");
        lblNewLabel.setFont(new Font("Cordia New", Font.PLAIN, 90));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(654, 321, 558, 104);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setFont(new Font("Cordia New", Font.PLAIN, 80));
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent arg0) {
                if (arg0.getKeyChar() == '\n') {
//					String idInput[] = {textField.getText()};
//					textField.setText("");
                    run(textField.getText());
                }
            }
        });
        textField.setBounds(514, 495, 839, 95);
        contentPane.add(textField);
        textField.setColumns(10);


        JButton button = new JButton("159991");
        button.setFont(new Font("Cordia New", Font.PLAIN, 70));
        button.setBounds(1600, 850, 250, 150);
        button.setBackground(new Color(255,255,255));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                run("159991");
            }
        });
        contentPane.add(button);
    }

    private void run(String id) {
        System.out.println("Index.run() is called");
        if (!isValid(id)) {
            JOptionPane.showMessageDialog(null, "Wrong student ID", "Error: ID input not found", JOptionPane.INFORMATION_MESSAGE);
            textField.setText("");
            return;
        }
        textField.setText("");
        this.setVisible(false);
        String[] input = {id};
        Menu.main(input);
        this.setVisible(true);
    }

    private boolean isValid(String id) {
        System.out.println("Index.isValid() is called");
        return id.length() == 6 /*&& (id.charAt(id.length() - 1) == '1' || id.charAt(id.length() - 1) == '2')  && isInDB(id)*/;
    }
}
