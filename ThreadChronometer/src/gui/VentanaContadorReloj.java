package gui;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import threads.EtiquetaThread;
import threads.RelojThread;

public class VentanaContadorReloj extends JFrame implements  ActionListener{

	// JPanel
	private JPanel contentPane;
	
	// labels
	private JLabel lblReloj;
	private JLabel lbl1;
	private JLabel lbl2;
	
	// buttons
	private JButton btnIniciar;
	private JButton btnPausar;
	private JButton btnReiniciar;
	private JButton btnSalir;
	private JButton btnParar;
	
	// threads
	private RelojThread threadReloj;
	private Thread threadDos;
	private Thread threadTres;
	
	// others
	private static final long serialVersionUID = 1L;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaContadorReloj frame = new VentanaContadorReloj();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public VentanaContadorReloj() {
		setBackground(SystemColor.info);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 542, 177);
		
		// JPanel
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// labels
		lblReloj = new JLabel("00:00");
		lblReloj.setHorizontalAlignment(SwingConstants.CENTER);
		lblReloj.setFont(new Font("Roboto Mono Thin for Powerline", Font.PLAIN, 54));
		lblReloj.setBounds(164, 15, 180, 67);
		contentPane.add(lblReloj);
		
		lbl1 = new JLabel("<<");
		lbl1.setFont(new Font("Roboto Mono Thin for Powerline", Font.PLAIN, 54));
		lbl1.setBounds(65, 6, 89, 85);
		contentPane.add(lbl1);
		
		lbl2 = new JLabel(">>");
		lbl2.setFont(new Font("Roboto Mono Thin for Powerline", Font.PLAIN, 54));
		lbl2.setBounds(378, 15, 129, 67);
		contentPane.add(lbl2);
		// END labels
		
		// buttons
		btnIniciar = new JButton("Iniciar");
		btnIniciar.setBackground(new Color(220, 220, 220));
		btnIniciar.setFont(new Font("Roboto Mono for Powerline", Font.PLAIN, 13));
		btnIniciar.setBounds(22, 99, 89, 23);
		contentPane.add(btnIniciar);
		
		btnPausar = new JButton("Pausar");
		btnPausar.setFont(new Font("Roboto Mono for Powerline", Font.PLAIN, 13));
		btnPausar.setBounds(123, 99, 89, 23);
		contentPane.add(btnPausar);
		
		btnReiniciar = new JButton("Reiniciar");
		btnReiniciar.setFont(new Font("Roboto Mono for Powerline", Font.PLAIN, 13));
		btnReiniciar.setBounds(326, 99, 100, 23);
		contentPane.add(btnReiniciar);
		
		btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Roboto Mono for Powerline", Font.PLAIN, 13));
		btnSalir.setBounds(438, 99, 89, 23);
		contentPane.add(btnSalir);
		
		btnIniciar.addActionListener(this);
		btnPausar.addActionListener(this);
		btnReiniciar.addActionListener(this);
		btnSalir.addActionListener(this);
		
		btnPausar.setEnabled(false);
		btnReiniciar.setEnabled(false);
		threadReloj = new RelojThread("Reloj",lblReloj);
		
		btnParar = new JButton("Parar");
		btnParar.setFont(new Font("Roboto Mono for Powerline", Font.PLAIN, 13));
		btnParar.setBounds(227, 99, 89, 23);
		contentPane.add(btnParar);
		btnParar.setEnabled(false);
		
		btnParar.addActionListener(this);
		// END buttons
		
		// threads
		threadDos = new Thread(new EtiquetaThread(lbl1,"LadoIzquierdo"),"LadoIzquierda");
		threadTres = new Thread(new EtiquetaThread(lbl2,"LadoDerecha"),"LadoDerecha");
		
		threadDos.setPriority(1);
		threadTres.setPriority(1);
		
		threadDos.start();
		threadTres.start();
		// END threads
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource().equals(btnSalir)) {
			
			salir();
		
		}else if(e.getSource().equals(btnIniciar)){
			
			iniciar();
			
		}else if(e.getSource().equals(btnPausar)){
			
			pauseThread();
			
		}else if(e.getSource().equals(btnReiniciar)){
			
			threadReloj.setRestarted(true);
			threadReloj.resetThread();
			
		}else if(e.getSource().equals(btnParar)){
			
			parar();
			
		}
	}
	
	private void parar() {
		
		if(threadReloj.isAlive()){
			threadReloj.interrupt();
		}
		
		btnIniciar.setEnabled(true);
		btnReiniciar.setEnabled(false);
		btnPausar.setEnabled(false);
		btnParar.setEnabled(false);
		
	}

	private void iniciar() {
		
		if(!threadReloj.isAlive()){
			threadReloj = new RelojThread("Reloj",lblReloj);
			threadReloj.start();
			btnPausar.setText("Pausar");
		}
		
		btnIniciar.setEnabled(false);
		btnReiniciar.setEnabled(true);
		btnPausar.setEnabled(true);
		btnParar.setEnabled(true);
		
	}

	private void pauseThread() {
		
		threadReloj.setPaused( !threadReloj.isPaused() );
		btnPausar.setText(threadReloj.isPaused() ? "Continuar" : "Pausar");
		
	}

	private void salir() {
		
		if(threadReloj.isAlive()){
			threadReloj.interrupt();
		}
		
		if(threadDos.isAlive()){
			threadDos.interrupt();
		}
		
		if(threadTres.isAlive()){
			threadTres.interrupt();
		}
		
		
		System.exit(0);
	}
}
