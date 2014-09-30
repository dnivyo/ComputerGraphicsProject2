package nz.aut.cg.group5.project2.engine;

import javax.media.opengl.*;
import java.awt.*;
import java.awt.event.*;

/**
 * 3DCube.java <BR>
 * @author 
 */
public class MainClass {

 public static void main(String[] args) {
    try {

      // create set the capabilities to the default
      //GLCapabilities glCaps = new GLCapabilities();

      // create the canvas according to the default capabilities
      GLCanvas canvas = new GLCanvas(new GLCapabilities());

      // create the OpenGL event listener
       World canvasListener = new World();

      // create a new event listener for the GL canvas
      canvas.addGLEventListener(canvasListener);

      // create a new window called OGL Test
      Frame theApplication = new Frame("3D Cube");

      // set the application window size
      theApplication.setSize(700, 700);
      // set the application window position
      theApplication.setLocation(100, 100);

      theApplication.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });

      // add the drawing canvas to the application
      theApplication.add(canvas);

      // show the application
      theApplication.setVisible(true);

    }
    catch (GLException e) {
      System.out.println(e);
    }

  }

}