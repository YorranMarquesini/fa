package com.projeto.entidades;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class entidades {
	protected double x;
	protected double y;
	protected int altura;
	protected int largura;
	
	private BufferedImage sprite;
	
	public entidades(int x, int y, int altura, int largura, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.altura = altura;
		this.largura = largura;
		this.sprite = sprite;
	}
	public void setX(int newX) {
		this.x = newX;
	}
	
	public void setY(int newY) {
		this.y = newY;
	}
	
	public int GetX() {
		return (int)this.x;
	}
	public int GetY() {
		return (int)this.y;
	}
	public int GetAltura() {
		return this.altura;
	}
	public int GetLargura() {
		return this.largura;
	}
	public void tick() {
		
	}
	public void render(Graphics g) {
		g.drawImage(sprite, this.GetX(), this.GetY(), null);
	}
}
