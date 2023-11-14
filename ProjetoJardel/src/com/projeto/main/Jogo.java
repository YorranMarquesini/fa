package com.projeto.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.projeto.entidades.Player;
import com.projeto.entidades.entidades;
import com.projeto.graficos.Sprite;

public class Jogo extends Canvas implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	private Thread thread;
	private boolean isRunning = true;
	private int altura = 639;
	private int largura = 339;
	private int scala = 1;
	private BufferedImage image;
	
	public List<entidades> entities;
	public static Sprite spritesheet;
	
	private Player player;
	
	
	public Jogo() {
		addKeyListener(this);
		frame = new JFrame("Teste");
		setPreferredSize(new Dimension(altura*scala,largura*scala));
		initFrame();
		//iniciando objetos
		image = new BufferedImage(altura, largura,BufferedImage.TYPE_INT_RGB);
		entities = new ArrayList<entidades>();
		spritesheet = new Sprite("/Spritesheet_64_bits.png");
		player = new Player(0,0,64,64,spritesheet.getSprite(0, 0, 64, 64));
		entities.add(player);
	}
	
	public void initFrame() {
		frame.add(this);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.pack();
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		isRunning = true;
	}
	
	public synchronized void stop() {
		
	}
	
	public static void main(String args[]) {
		Jogo jogo = new Jogo();
		jogo.start();
	}
	
	public void tick() {
		for(int i = 0; i < entities.size(); i++){
			entidades e = entities.get(i);
			e.tick();
		}
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = image.getGraphics();
		g.setColor(new Color(155,155,155));
		g.fillRect(0, 0, altura, largura);
		for(int i = 0; i < entities.size(); i++){
			entidades e = entities.get(i);
			e.render(g);
		}
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, altura*scala, largura*scala, null);
		bs.show();
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double ticks = 60.0;
		double ns = 1000000000/ticks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		while(isRunning) {
			long now = System.nanoTime();
			delta+= (now - lastTime)/ ns;
			lastTime = now;
			if(delta>= 1) {
				tick();
				render();
				frames++;
				delta--;
			}
			if(System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS"+ frames);
				frames = 0;
				timer +=1000;
			}
		}
		stop();

}

	public void keyTyped(KeyEvent e) {
		
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_D) {
			player.right = true;
		}else if(e.getKeyCode()== KeyEvent.VK_A) {
			player.left = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_W) {
			player.up = true;
			
		}else if(e.getKeyCode() == KeyEvent.VK_S) {
			player.down = true;
			
		}
	}


	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_D) {
			player.right = false;
		}else if(e.getKeyCode()== KeyEvent.VK_A) {
			player.left = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_W) {
			player.up = false;
			
		}else if(e.getKeyCode() == KeyEvent.VK_S) {
			player.down = false;
			
		}
		
	}
}
