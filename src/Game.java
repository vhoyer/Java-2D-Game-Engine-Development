package io.github.vhoyer.game;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.swing.JFrame;
import io.github.vhoyer.game.gfx.SpriteSheet;
import io.github.vhoyer.game.gfx.Screen;

public class Game extends Canvas implements Runnable {
	public static final long serialVersionUID = 1L;

	public static final int WIDTH = 160;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 3;
	public static final String NAME = "Game";

	private JFrame frame;

	public boolean running = false;
	public int tickCount = 0;

	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

	private Screen screen;

	public Game() {
		setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		frame = new JFrame(NAME);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		frame.add(this, BorderLayout.CENTER);
		frame.pack();

		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void init(){
		screen = new Screen(WIDTH, HEIGHT, new SpriteSheet("/sprite_sheet.png"));
	}

	public synchronized void start(){
		running = true;
		new Thread(this).run();
	}

	public synchronized void stop(){
		
	}

	@Override
	public void run(){
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D / 60D;

		int ticks = 0;
		int frames = 0;

		long lastTimer = System.currentTimeMillis();
		double delta = 0;

		init();
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;

			boolean shouldRender = true;
			while (delta >= 1){
				ticks++;
				tick();
				delta--;
				shouldRender = true;
			}

			try{
				Thread.sleep(2);
			} catch(InterruptedException ex) { ex.printStackTrace(); }

			if (shouldRender){
				frames++;
				render();
			}

			if(System.currentTimeMillis() - lastTimer >= 1000){
				lastTimer += 1000;
				System.out.println("fps:" + frames + ", ticks:" + ticks);
				frames = 0;
				ticks = 0;
			}
		}
	}

	public void tick(){
		tickCount++;
	}

	public void render(){
		BufferStrategy bs = getBufferStrategy();
		if (bs == null){
			createBufferStrategy(3);
			return;
		}

		screen.render(pixels, 0, WIDTH);

		Graphics g = bs.getDrawGraphics();

		g.dispose();
		bs.show();
	}

	public static void main(String[] args){
		new Game().start();
	}
}
