package threads;

import java.awt.Color;
import java.util.Random;

import javax.swing.JLabel;

public class EtiquetaThread implements Runnable{

	private JLabel etiqueta;
	private String name;
	
	public EtiquetaThread() {
		super();
	}
	public EtiquetaThread(final JLabel etiqueta, final String name) {
		super();
		this.etiqueta = etiqueta;
		this.name = name;
	}
	
	public JLabel getEtiqueta() {
		return etiqueta;
	}
	
	public void setEtiqueta(final JLabel etiqueta) {
		this.etiqueta = etiqueta;
	}
	
	@Override
	public void run() {
		
		while (true) {
			
			try {
				
				Random r = new Random();
				Color c=new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256),r.nextInt(256));
		        etiqueta.setForeground(c);
				
				Thread.sleep(2000);
				
			} catch (InterruptedException e) {
				System.out.println("Thread: " + name + " acaba de terminar");
				
			}
		}
		
	}
	

}
