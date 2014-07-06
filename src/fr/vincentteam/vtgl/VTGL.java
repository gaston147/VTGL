package fr.vincentteam.vtgl;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import fr.vincentteam.vtgl.bitmaps.Screen;
import fr.vincentteam.vtgl.events.VTGLListener;
import fr.vincentteam.vtgl.events.VTGLListenerManager;
import fr.vincentteam.vtgl.fonts.BaseFont;
import fr.vincentteam.vtgl.fonts.VTGLFont;
import fr.vincentteam.vtgl.geometry.Size2i;

public class VTGL {
	public static final int DO_NOTHING_ON_CLOSE = 0;
	public static final int EXIT_ON_CLOSE = 1;
	public static final int DESTROY_ON_CLOSE = 2;
	
	private static VTGLFont defaultFont = new BaseFont();
	
	public static VTGLFont getDefaultFont() {
		return defaultFont;
	}
	
	private String title;
	private Size2i size;
	private int closeAction;
	private boolean closeRequested;
	private Screen screen; 
	private JFrame window;
	private VTGLComponent panel;
	private boolean running;
	private List<VTGLListener> listeners;
	private VTGLListenerManager lm;
	private long lastTime, ellapsedTime;
	
	public VTGL() {
		title = "No title";
		size = new Size2i(640, 480);
		closeAction = EXIT_ON_CLOSE;
		running = false;
		window = new JFrame();
		listeners = new ArrayList<VTGLListener>();
		lastTime = 0;
		ellapsedTime = 0;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setSize(Size2i size) {
		this.size = size;
	}
	
	public Size2i getSize() {
		return size;
	}
	
	public void setCloseAction(int closeAction) {
		this.closeAction = closeAction;
	}
	
	public int getCloseAction() {
		return closeAction;
	}
	
	public void addListener(VTGLListener l) {
		listeners.add(l);
	}
	
	public void removeListener(VTGLListener l) {
		listeners.remove(l);
	}
	
	public boolean isCloseRequested() {
		return closeRequested;
	}
	
	public Screen getScreen() {
		return screen;
	}
	
	public void create() throws VTGLException {
		if (size == null)
			new VTGLException("Size can not be null");
		if (size.width < 1 || size.height < 1)
			throw new VTGLException("Size can not be negative");
		window.setTitle(title);
		window.setResizable(false);
		window.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
            	onClose();
            }
        });
		panel = new VTGLComponent(this);
		panel.setPreferredSize(new Dimension(size.width, size.height));
		window.setContentPane(panel);
		window.pack();
		window.setLocationRelativeTo(null);
		if (closeAction == EXIT_ON_CLOSE) {
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		} else {
			window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}
		screen = new Screen(new Size2i(size.width, size.height));
		screen.fillRect(0, 0, screen.getSize().width, screen.getSize().height, 0xffffff);
		lm = new VTGLListenerManager(this);
		panel.addFocusListener(lm);
		window.addKeyListener(lm);
		panel.addMouseListener(lm);
		window.addWindowListener(lm);
		System.out.println(window.getInsets().top);
		window.setVisible(true);
		System.out.println(window.getInsets().top);
		running = true;
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}
	
	public void destroy() {
		running = false;
		closeRequested = true;
		window.setVisible(false);
		window.dispose();
	}
	
	public void update() {
		if (running)
			panel.paint(panel.getGraphics());
	}
	
	private void onClose() {
		closeRequested = true;
		if (closeAction == DO_NOTHING_ON_CLOSE) {
		} else if (closeAction == EXIT_ON_CLOSE) {
			System.exit(0);
		} else if (closeAction == DESTROY_ON_CLOSE) {
			destroy();
		}
	}

	public void sync(int fps) {
		ellapsedTime = System.currentTimeMillis() - lastTime;
		long t = (int) (1000d / (double) fps) - ellapsedTime;
		if (t > 0) {
			try {
				Thread.sleep(t);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println(Thread.currentThread().getName());
		}
		lastTime = System.currentTimeMillis();
	}
	
	public long getEllapsedTime() {
		return ellapsedTime;
	}

	public List<VTGLListener> getEventListeners() {
		return listeners;
	}
}
