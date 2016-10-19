package threads;

import javax.swing.JLabel;

public class RelojThread extends Thread {

	private JLabel etiqueta;
	private int min;
	private int seg;
	private boolean pause = false;
	private boolean restart = false;
	

	public boolean isRestarted() {
		return restart;
	}

	public void setRestarted(final boolean restart) {
		this.restart = restart;
	}

	public RelojThread(final String name, final JLabel etiqueta) {
		super(name);
		this.etiqueta = etiqueta;
	}
	
	public JLabel getEtiqueta() {
		return etiqueta;
	}
	
	public void setEtiqueta(final JLabel etiqueta) {
		this.etiqueta = etiqueta;
	}
	
	public int getMin() {
		return min;
	}
	
	public void setMin(final int min) {
		this.min = min;
	}
	
	public int getSeg() {
		return seg;
	}
	
	public void setSeg(final int seg) {
		this.seg = seg;
	}
	public boolean isPaused() {
		return pause;
	}
	
	public void setPaused(final boolean pause) {
		this.pause = pause;
	}

	@Override
	public void run() {
		
		super.run();
		
		resetThread();
		
		try {
			
			while (true) {
				
					if(seg == 59){
						min += 1;
						seg = 0;
						if(min == 60)
							min = 0;
					}else{
						seg += 1;
					}
					
					while( isPaused() ){
						
						Thread.sleep(1);
						
					}
					
					if( isRestarted() ){
						resetThread();
						restart = false;
					}
					if(seg < 10)
						this.etiqueta.setText(min + ":0" + seg);
					else
						this.etiqueta.setText(min + ":" + seg);
					Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			resetThread();
			System.out.println("El hilo " + getName() + " ha terminado");
		}
		
	}
	
	
	public void resetThread(){
		min = 0;
		seg = 0;
		this.etiqueta.setText(min + "0:0" + seg);
	}

	
	
}
