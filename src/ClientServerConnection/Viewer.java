package ClientServerConnection;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

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
    private JButton serverPortButton;
    private JTextField serverPort;
    private JFrame popUp;
    private BufferedImage bg;
    private Color[] colors = new Color[]{Color.BLUE, Color.CYAN, Color.LIGHT_GRAY, Color.MAGENTA, Color.RED, Color.YELLOW,
        Color.PINK, Color.ORANGE, Color.GRAY, Color.WHITE};

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
        tabs.add("Server", serverPanel);

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

    public JButton getServerPortButton() {
        return serverPortButton;
    }

    public void setServerPortButton(JButton serverPortButton) {
        this.serverPortButton = serverPortButton;
    }

    public JTextField getServerPort() {
        return serverPort;
    }

    public void setServerPort(JTextField serverPort) {
        this.serverPort = serverPort;
    }

    public JFrame getPopUp() {
        return popUp;
    }

    public void setPopUp(JFrame popUp) {
        this.popUp = popUp;
    }

    //PUBLIC METHODS
    /**
     * Adds a new tab for the client connection.
     *
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
        Random random = new Random();
        panel.setBackground(colors[random.nextInt(9)]);

        //set up the text area
        textArea = new JTextArea(200, 300);
        textArea.setBounds(10, 50, 450, 300);
        textArea.setEditable(false);
        scroller = new JScrollPane(textArea);
        scroller.setBounds(15, 20, 450, 300);
        panel.add(scroller);
        textAreas.add(textArea);

        //set up the message text
        JTextPane textMessage = new JTextPane();
        textMessage.setBounds(10, 330, 100, 30);
        textMessage.setBackground(null);
        textMessage.setEditable(false);
        textMessage.setText("Message: ");
        panel.add(textMessage);

        //set up the message box
        message = new JTextField();
        message.setBounds(10, 360, 450, 30);
        panel.add(message);
        messages.add(message);

        //set up the send button
        button = new JButton("Send");
        button.setBounds(390, 410, 70, 30);
        button.setBackground(Color.WHITE);
        panel.add(button);

        //Fill lists
        buttons.add(button);
        tabs.addTab(ip, panel);
        panels.add(panel);

    }

    /**
     * Sets a window asking for the server port
     */
    public void serverPortPopUp() {
        //Jframe set up
        popUp = new JFrame("SERVER PORT");
        popUp.setBounds(0, 0, 220, 120);
        popUp.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        popUp.setUndecorated(true);
        popUp.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        popUp.setResizable(false);
        popUp.setIconImage(new ImageIcon("ICO/icoServerPort.png").getImage());

        //Jpanel set up
        JPanel jPanel = new JPanel();
        Random random = new Random();
        jPanel.setBackground(colors[random.nextInt(9)]);
        jPanel.setBounds(0, 0, 220, 120);
        jPanel.setLayout(null);

        //textPort set up
        JTextPane textPort = new JTextPane();
        textPort.setBounds(5, 5, 150, 30);
        textPort.setBackground(null);
        textPort.setEditable(false);
        textPort.setText("Insert the server port: ");
        jPanel.add(textPort);

        //serverPort set up
        serverPort = new JTextField();
        serverPort.setBounds(5, 40, 100, 30);
        jPanel.add(serverPort);

        //server send button set up
        serverPortButton = new JButton("SEND");
        serverPortButton.setBackground(Color.WHITE);
        serverPortButton.setBounds(135, 40, 70, 30);
        jPanel.add(serverPortButton);

        //Jframe set visible
        popUp.add(jPanel);
        popUp.setVisible(true);

    }

    //PRIVATE METHODS
    /**
     * Set up the class JFrame
     */
    private void setUpViewer() {
        this.setBounds(0, 0, 500, 525);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //window mode
        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        this.setResizable(false);
        this.setBackground(Color.yellow);
        this.setIconImage(new ImageIcon("ICO/icoWierdBackground.png").getImage());
        this.setBackground(Color.BLACK);

    }

    /**
     * Set up the panel for the server tab
     */
    private void setUpServerPanel() {
        Random random = new Random();
        serverPanel.setBackground(colors[random.nextInt(9)]);
        serverPanel.setLayout(null);//jpanel layout
        //Set up comms log
        commsLog = new JTextArea(200, 300);
        commsLog.setBounds(10, 50, 450, 300);
        commsLog.setEditable(false);
        //set up logScroll
        JScrollPane logScroll = new JScrollPane(commsLog);
        logScroll.setBounds(15, 20, 450, 300);
        serverPanel.add(logScroll);

        //Set up textIP
        JTextPane textIP = new JTextPane();
        textIP.setBounds(35, 350, 30, 30);
        textIP.setBackground(null);
        textIP.setEditable(false);
        textIP.setText("IP: ");
        serverPanel.add(textIP);
        //Set up ip
        ip = new JTextField();
        ip.setBounds(65, 350, 100, 30);
        serverPanel.add(ip);

        //set up textPort
        JTextPane textPort = new JTextPane();
        textPort.setBounds(300, 350, 50, 30);
        textPort.setBackground(null);
        textPort.setEditable(false);
        textPort.setText("PORT: ");
        //set up serverPanel
        serverPanel.add(textPort);
        port = new JTextField();
        port.setBounds(350, 350, 50, 30);
        serverPanel.add(port);
        //Set up connectButton
        connectButton = new JButton("Connect");
        connectButton.setBackground(Color.WHITE);
        connectButton.setBounds(200, 400, 100, 30);
        serverPanel.add(connectButton);

    }
    

}
