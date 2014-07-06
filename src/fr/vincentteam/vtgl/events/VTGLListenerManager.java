package fr.vincentteam.vtgl.events;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import fr.vincentteam.vtgl.VTGL;

public class VTGLListenerManager implements FocusListener, KeyListener, MouseListener, WindowListener {
	private VTGL vtgl;
	
	public VTGLListenerManager(VTGL vtgl) {
		this.vtgl = vtgl;
	}
	
	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		sendEvent(new MouseClickEvent(event.getX(), event.getY()));
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	@Override
	public void keyPressed(KeyEvent event) {
		sendEvent(new KeyTypedEvent(event.getKeyCode()));
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent event) {
	}

	@Override
	public void focusGained(FocusEvent arg0) {
	}

	@Override
	public void focusLost(FocusEvent arg0) {
	}
	
	public void sendEvent(VTGLEvent event) {
		for (VTGLListener listener : vtgl.getEventListeners()) {
			Class<? extends VTGLListener> clazz = listener.getClass();
			for (Method m : clazz.getDeclaredMethods()) {
				if (m.isAnnotationPresent(VTGLEventHandler.class) && m.getParameterTypes().length == 1 && m.getParameterTypes()[0] == event.getClass()) {
					try {
						m.invoke(listener, event);
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}