/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientServerConnection;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

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

        tabs.add("Client", client);
        
        
        this.add(tabs);
        this.setVisible(true);
        
        
        
    }

    
    private void setUpViewer() {
        this.setBounds(0,0,540,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
    
}
