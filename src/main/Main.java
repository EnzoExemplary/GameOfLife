package main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main{
	JFrame frame;
	JPanel panel;
	boolean stop = false;
	Grid grid = new Grid(50);
	ConsoleView console;
	JButton startButton;
	
	
	public Main() {
		frame = new JFrame();
		panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(300, 300, 100, 300));
		panel.setLayout(new BorderLayout());
		frame.add(panel, BorderLayout.CENTER);
		
		console = new ConsoleView(20, 20);
		panel.add(console);
		console.append(grid.toString());
		
		
		JPanel buttonPanel = new JPanel();
		frame.add(buttonPanel, BorderLayout.SOUTH);
		startButton = new JButton("Start");
		addActionListenerStart(startButton);
		buttonPanel.add(startButton);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Game of Life");
		frame.pack();
		frame.setVisible(true);
		
		frame.revalidate();
		frame.repaint();
	}
	
	private void addActionListenerStart(JButton button) {
		button.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e) {
					startButton.setEnabled(true);
					new Thread() {
						
						@Override
						public void run() {
							int i = 0;
							
							while(i < 1000) {
								grid.newGeneration();
								clearConsole();
								console.append(grid.toString());

								
								try {
									Thread.sleep(50);
								} catch (InterruptedException e1) {
									e1.printStackTrace();
								}
								++i;
							}
						}
					}.start();
					startButton.setEnabled(false);
				}
			});
	}
	
	private void clearConsole() {
		console.setText("");
		frame.revalidate();
		frame.repaint();
		panel.revalidate();
		panel.repaint();
		console.revalidate();
		console.repaint();
	}
	
	
	public static void main(String[] args){
		new Main();
		
		
//		Grid grid = new Grid(10);
//		
//		for(int i = 0; i < 10; i++) {
//			System.out.println(grid);
////			TimeUnit.MILLISECONDS.sleep(500);
//			grid.newGeneration();
//			
//		}

	}
}

