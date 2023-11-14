package com.projeto.graficos;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {
	private BufferedImage Sprites;
	public Sprite(String path) {
		File file = new File("C:\\Users\\Cliente\\eclipse-workspace\\ProjetoJardel\\res\\Spritesheet_64_bits.png");
		try {
			Sprites = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (Sprites == null) {
			System.out.print("ta null");
			return;
		}
	}
	public BufferedImage getSprite(int x, int y, int altura, int largura) {
		return Sprites.getSubimage(x, y , altura, largura);
	}
}
