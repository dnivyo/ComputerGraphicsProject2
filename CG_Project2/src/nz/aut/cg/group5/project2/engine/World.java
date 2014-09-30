package nz.aut.cg.group5.project2.engine;

/**
 *
 * @author ºúê¿Óî
 */

import nz.aut.cg.group5.project2.objects.*;
import javax.media.opengl.*;
import com.sun.opengl.util.Animator;
import javax.media.opengl.glu.GLU;


public class World  implements GLEventListener{
	private  Animator theAnimator;

	// for look at
	private float eyeX = 15;
	private float eyeY = 0;
	private float eyeZ = 15;
	private float atX = 0;
	private float atY = 0;
	private float atZ = 0;
	private float upX = 0;
	private float upY = 1;
	private float upZ = 0;
	private float[] angleRotate = {0.0001f,0.0001f,0};
	private float changesY = 0.01f;
	private ObjectLoader cube = new ObjectLoader("obj\\conecircle.obj");
	private float[] cubeCoords = {3,3,3};



	public World() {
	}

	public void init(GLAutoDrawable gld) {
		// get the GL drawing functionality for the canvas
		GL gl = gld.getGL();

		// enable depth testing
		gl.glEnable(GL.GL_DEPTH_TEST);

		// create the animator and attach it to the GL drawing canvas
		theAnimator = new Animator(gld);
		theAnimator.start();


		gl.glShadeModel(GL.GL_SMOOTH);

		// normalize the normals
		gl.glEnable(GL.GL_NORMALIZE);

		gl.glEnable(GL.GL_DEPTH_TEST);
		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glLoadIdentity();




	}

	public void display(GLAutoDrawable gLAutoDrawable) {
		GL gl = gLAutoDrawable.getGL();


		// change to model-view mode so that we can change camera position
		gl.glMatrixMode(GL.GL_MODELVIEW);

		// clear the model-view matrix ¨C start fresh
		gl.glLoadIdentity();


		gl.glClear(GL.GL_COLOR_BUFFER_BIT |GL.GL_DEPTH_BUFFER_BIT);


		GLU glu = new GLU();


		glu.gluPerspective(0, 1, 1, 60);

		//    glu.gluLookAt(eyeX,eyeY,eyeZ,atX,atY,atZ,upX,upY,upZ);
		glu.gluLookAt(30,30,30,
				atX,atY,atZ,
				upX,upY,upZ);


		//*****************************start drawing************************************
		gl.glColor3f(1, 0, 0);
		gl.glBegin(GL.GL_LINES);
		gl.glVertex3f(0, 0, 0);
		gl.glVertex3f(10, 0, 0);
		gl.glEnd();
		gl.glColor3f(0, 1, 0);
		gl.glBegin(GL.GL_LINES);
		gl.glVertex3f(0, 0, 0);
		gl.glVertex3f(0, 10, 0);
		gl.glEnd();
		gl.glColor3f(0, 0, 1);
		gl.glBegin(GL.GL_LINES);
		gl.glVertex3f(0, 0, 0);
		gl.glVertex3f(0, 0, 10);
		gl.glEnd();
		cube.DrawModel(gl);
		cubeCoords = rotate(cubeCoords, angleRotate);
		gl.glFlush();
		//*************************finish drawing***************************************
		//****************************for actions***************************************
		//eyeX = (float)Math.cos(Math.toRadians(angle))*30;
		//eyeZ = (float)Math.sin(Math.toRadians(angle))*30;
		//angle++;
		if(Math.abs(eyeY) > 30)
			changesY = -changesY;
		eyeY+=changesY;
	}
	//	X=cosøy*cosøz*x - sinøz*y + sinøy*z
	//	Y=sinøz*x + cosøx*cosøz*y - sinøx*z
	//	Z=-sinøy*x + sinæx*y + cosøx*cosøy*z
	//	Return x,y,z;


	public float[] rotate(float[] coords, float[] angleRotate){
		float[] newCoords = {0, 0, 0};
		newCoords[0] = (float) (Math.cos(angleRotate[1])*Math.cos(angleRotate[2])*coords[0] - Math.sin(angleRotate[2])*coords[1] + Math.sin(angleRotate[1])*coords[2]);
		newCoords[1] = (float) (Math.sin(angleRotate[2])*coords[0] + Math.cos(angleRotate[0])*Math.cos(angleRotate[2])*coords[1] - Math.sin(angleRotate[0])*coords[2]);
		newCoords[2] = (float) (-Math.sin(angleRotate[1])*coords[0] + Math.sin(angleRotate[0])*coords[1] + Math.cos(angleRotate[0])*Math.cos(angleRotate[1])*coords[2]);
		return newCoords;
	}


	public void reshape(GLAutoDrawable gld, int _int, int _int2,
			int width, int height) {
		// get the GL drawing functionality for the canvas
		GLU glu = new GLU();

		// get the GL drawing functionality for the canvas
		GL gl = gld.getGL();

		// change into projection mode to change the camera properties
		gl.glMatrixMode(GL.GL_PROJECTION);

		// load the identity matrix into the projection matrix
		gl.glLoadIdentity();


		glu.gluPerspective(45, (float) width / (float) height, 0.1, 100);

	}

	public void displayChanged(GLAutoDrawable gLAutoDrawable, boolean _boolean,
			boolean _boolean2) {
	}
}
