/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientServerConnection;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author alfon
 */
public class Viewer extends JFrame{
    public JTabbedPane tabs;
    public JPanel server;
    public JPanel client;
    public JTextArea textArea;
    public JScrollPane scroller;
    public JButton button;
    public JTextField ip; 
    public JTextField port; 
    
    
    public Viewer(){
        setUpViewer();
        
        
        tabs = new JTabbedPane();
        
        server = new JPanel();
        
        server.setLayout(null);
        
        textArea = new JTextArea();
        textArea.setBounds(10, 50, 400, 300);
        
        textArea.setEditable(false);
        server.add(textArea);
        scroller = new JScrollPane(textArea);
        scroller.setBounds(10,50,400,300);
        
        server.add(scroller);
        
        
        tabs.addTab("Server", server);
        
        client = new JPanel();
        client.setLayout(null);
        textArea = new JTextArea(400,300);
        textArea.setBounds(10, 50, 400, 300);
        scroller = new JScrollPane(textArea);
        scroller.setBounds(10, 50, 400, 300);
        client.add(scroller);
        
        button = new JButton("Connectar");
        button.setBounds(10,400,100,30);
        client.add(button);
        
        ip = new JTextField();
        ip.setBounds(130,400, 100,30);
        client.add(ip);
        
        port = new JTextField();
        port.setBounds(250,400, 50,30);
        client.add(port);
        
        
        tabs.add("Client", client);
        
        
        this.add(tabs);
        this.setVisible(true);
       
    }

    
    private void setUpViewer() {
        this.setBounds(0,0,800,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
    public void newTab(){
        JPanel panel;
        JScrollPane scroller;
        JTextArea textArea;
        
        panel = new JPanel();
        panel.setLayout(null);
        
        textArea = new JTextArea(400,300);
        textArea.setBounds(10, 50, 400, 300);
        textArea.setEditable(false);
        scroller = new JScrollPane(textArea);
        scroller.setBounds(10, 50, 400, 300);
        panel.add(scroller);
        tabs.addTab("New", panel);
        
        
        
        
    }
    
    
}
