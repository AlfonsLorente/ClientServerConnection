/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientServerConnection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author alfon
 */
public class Controller implements ActionListener{
    private Viewer viewer;
    private Model model;
    public Controller(Viewer viewer, Model model){
        this.viewer = viewer;
        this.model = model;
        
        this.viewer.button.addActionListener(this);
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        Client client = new Client(model.bufferConnection);
        client.connect(this.viewer.ip.getText(), Integer.parseInt(this.viewer.port.getText()));
        this.viewer.newTab();
    }
    
    
    
    
}
