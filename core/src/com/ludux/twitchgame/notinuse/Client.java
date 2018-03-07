package com.ludux.twitchgame.notinuse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputSerial;
import java.net.InetAddress;
import java.net.Socket;
import java.text.ParseException;
import java.util.HashMap;

public class Client extends JFrame implements Runnable {
    static  public Socket connection;
    static private OutputSerial output;
    static private ObjectInputStream input;

    public static void main(String[] args) throws ParseException {
        new Thread(new Client("DD")).start();
    }

    public Client(String name) throws ParseException {
        super(name);
        setLayout(new FlowLayout());
        setSize(450,270);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        ButtonGroup group = new ButtonGroup();
        final JTextField t1 = new JTextField(10);
        final JLabel label = new JLabel("name");
        final JButton b1 = new JButton("Произвести выбранную операцию");
        final JRadioButton c1 = new JRadioButton("Встать в очередь на бой");
        final JRadioButton c2 = new JRadioButton("Атаковать");
        group.add(c1);
        group.add(c2);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==b1){
                    if(c1.isSelected()){
                        HashMap<String,String> m1 = new HashMap<String,String>();
                        System.out.println("WannaBattle");
                        m1.put("WannaBattle",t1.getText());
                        sendData(m1);
                    }
                }

                if(c2.isSelected()){
                    HashMap<String,String> m1 = new HashMap<String,String>();
                    System.out.println("Attack");
                    m1.put("Attack",t1.getText());
                    sendData(m1);
                }

            }
        });
        add(label);
        add(t1);
        add(c1);
        add(c2);
        add(b1);
    }
    @Override
    public void run() {
        try {
            while(true){
                connection = new Socket(InetAddress.getByName("127.0.0.1"),8000);
                output = new OutputSerial(connection.getOutputStream());
                input = new ObjectInputStream(connection.getInputStream());
                JOptionPane.showMessageDialog(null,(String)input.readObject());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private static void sendData(Object obj){
        try {
            output.flush();
            output.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}