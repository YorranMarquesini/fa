package com.projeto.entidades;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.projeto.main.Jogo;

public class Player extends entidades {

	public boolean right,left,up,down;
	public int right_dir = 0, left_dir = 1;
	public int dir = right_dir;
	public double speed = 1.5;
	
	private int frames = 0,maxFrames = 8,index = 0,maxIndex = 3;
	private boolean moved = false;
	private BufferedImage[] rightPlayer;
	private BufferedImage[] leftPlayer;
	
	public Player(int x, int y, int altura, int largura, BufferedImage sprite) {
		super(x, y, altura, largura, sprite);
		
		rightPlayer = new BufferedImage[4];
		leftPlayer = new BufferedImage[4];
		for(int i = 0; i<4; i++) {
		rightPlayer[i] = Jogo.spritesheet.getSprite(0 + (i*64), 0, 64, 64);
		}
		for(int i = 0; i<4; i++) {
			leftPlayer[i] = Jogo.spritesheet.getSprite(0 + (i*64), 64, 64, 64);
			}
	}
	
	public void tick() {
		moved = false;
		if(right) {
			moved = true;
			dir = right_dir;
			x+=speed;
		}else if(left) {
			moved = true;
			dir = left_dir;
			x-=speed;
		}
		if (up) {
			moved = true;
			y-=speed;
		}else if(down) {
			moved = true;
			y+=speed;
		}
		
		if(moved) {
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				index++;
				if(index > maxIndex) {
					index = 0;
				}
			}
		}
	}
	
	public void render(Graphics g) {
		if(dir == right_dir) {
			g.drawImage(rightPlayer[index], this.GetX(), this.GetY(),null);
		}else if(dir == left_dir) {
			g.drawImage(leftPlayer[index], this.GetX(), this.GetY(),null);
		}
	}
	
}
