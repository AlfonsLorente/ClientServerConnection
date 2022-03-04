/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientServerConnection;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.*;

/**
 * Class used to display the graphics
 *
 * @author alfon
 */
public class Viewer extends JFrame {

    //VARIABLES
    private JTabbedPane tabs;
    private JTextArea commsLog;
    private JButton connectButton;
    private JTextField ip, port;
    private JPanel serverPanel;
    private ArrayList<JTextArea> textAreas = new ArrayList<JTextArea>();
    private ArrayList<JTextField> messages = new ArrayList<JTextField>();
    private ArrayList<JButton> buttons = new ArrayList<JButton>();
    private ArrayList<JPanel> panels = new ArrayList<JPanel>();

    //CONSTRUCTOR
    /**
     * sets up the jframe and the server tab
     */
    public Viewer() {
        //instantiate class atributes 
        tabs = new JTabbedPane();
        //set up frame
        setUpViewer();

        //set up server frame
        serverPanel = new JPanel();
        setUpServerPanel();
        tabs.add("Servidor", serverPanel);

        //add the tabs to the frame pane
        getContentPane().add(tabs);
        setVisible(true);
    }

    
    //GETTER AND SETTER
    public JTabbedPane getTabs() {
        return tabs;
    }

    public void setTabs(JTabbedPane tabs) {
        this.tabs = tabs;
    }

    public JTextArea getCommsLog() {
        return commsLog;
    }

    public void setCommsLog(JTextArea commsLog) {
        this.commsLog = commsLog;
    }

    public JButton getConnectButton() {
        return connectButton;
    }

    public void setConnectButton(JButton connectButton) {
        this.connectButton = connectButton;
    }

    public JTextField getIp() {
        return ip;
    }

    public void setIp(JTextField ip) {
        this.ip = ip;
    }

    public JTextField getPort() {
        return port;
    }

    public void setPort(JTextField port) {
        this.port = port;
    }

    public ArrayList<JTextArea> getTextAreas() {
        return textAreas;
    }

    public void setTextAreas(ArrayList<JTextArea> textAreas) {
        this.textAreas = textAreas;
    }

    public ArrayList<JTextField> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<JTextField> messages) {
        this.messages = messages;
    }

    public ArrayList<JButton> getButtons() {
        return buttons;
    }

    public void setButtons(ArrayList<JButton> buttons) {
        this.buttons = buttons;
    }

    public ArrayList<JPanel> getPanels() {
        return panels;
    }

    public void setPanels(ArrayList<JPanel> panels) {
        this.panels = panels;
    }

    /**
     * Adds a new tab for the client side.
     * @param ip String
     */
    public void newTab(String ip) {
        //Declare variables
        JPanel panel;
        JScrollPane scroller;
        JTextArea textArea;
        JTextField message;
        JButton button;

        //set up panel
        panel = new JPanel();
        panel.setLayout(null);

        
        
        textArea = new JTextArea(200, 300);
        textArea.setBounds(10, 50, 450, 300);
        textArea.setEditable(false);
        scroller = new JScrollPane(textArea);
        scroller.setBounds(15, 20, 450, 300);
        panel.add(scroller);
        textAreas.add(textArea);

        JTextPane textMessage = new JTextPane();
        textMessage.setBounds(10,330,100,30);
        textMessage.setBackground(null);
        textMessage.setEditable(false);
        textMessage.setText("Message: ");
        panel.add(textMessage);
        
        message = new JTextField();
        message.setBounds(10, 360, 450, 30);
        panel.add(message);
        messages.add(message);

        button = new JButton("Send");
        button.setBounds(390, 410, 70, 30);
        panel.add(button);
        buttons.add(button);

        tabs.addTab(ip, panel);
        panels.add(panel);

    }

    private void setUpViewer() {
        this.setBounds(0, 0, 500, 525);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //window mode
        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        this.setResizable(false);
        this.setBackground(Color.yellow);
    }

    private void setUpServerPanel() {
        serverPanel.setLayout(null);

        commsLog = new JTextArea(200, 300);
        commsLog.setBounds(10, 50, 450, 300);
        commsLog.setEditable(false);

        JScrollPane logScroll = new JScrollPane(commsLog);
        logScroll.setBounds(15, 20, 450, 300);
        serverPanel.add(logScroll);

        
        JTextPane textIP = new JTextPane();
        textIP.setBounds(35,350,30,30);
        textIP.setBackground(null);
        textIP.setEditable(false);
        textIP.setText("IP: ");
        serverPanel.add(textIP);
        ip = new JTextField();
        ip.setBounds(65, 350, 100, 30);
        serverPanel.add(ip);

        JTextPane textPort = new JTextPane();
        textPort.setBounds(300,350,50,30);
        textPort.setBackground(null);
        textPort.setEditable(false);
        textPort.setText("PORT: ");
        serverPanel.add(textPort);
        port = new JTextField();
        port.setBounds(350, 350, 50, 30);
        serverPanel.add(port);
        connectButton = new JButton("Connectar");
        connectButton.setBounds(200, 400, 100, 30);
        serverPanel.add(connectButton);
    }
}
