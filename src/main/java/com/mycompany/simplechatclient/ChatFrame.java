/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.simplechatclient;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author NicholasBocchini
 */
public class ChatFrame extends JFrame{
    JTextArea output = new JTextArea();
    JTextField input = new JTextField();
    
    public ChatFrame(final ChatClient client)
    {
        super(client.name);
        Container pane = getContentPane();
        pane.setLayout(new BorderLayout());
        pane.add(new JScrollPane(output), BorderLayout.CENTER);
        output.setEditable(false);
        pane.add(input, BorderLayout.SOUTH);
        
        input.addKeyListener(
            new KeyAdapter() 
            {
                public void keyPressed(KeyEvent e) 
                {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER)
                    {
                        client.sendTextToChat(input.getText());
                        input.setText("");
                    }
                }
            }
        ); 
        addWindowListener(
            new WindowAdapter()
            {
                public void windowClosing(WindowEvent e)
                {
                    client.disconnect();
                    System.exit(0);
                }
            }
        );
        
        setSize(400, 200);
        setVisible(true);
        input.requestFocus();
    }
}
