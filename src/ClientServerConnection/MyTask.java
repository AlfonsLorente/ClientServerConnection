/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientServerConnection;

/**
 *
 * @author alfon
 */
public class MyTask {
    public static void main(String[] args) {
        Viewer viewer = new Viewer();
        Model model = new Model();
        Controller controller = new Controller(viewer, model);
    }
}