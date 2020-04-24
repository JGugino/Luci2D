/*Created By: Gugino
 *Date Created: Apr 23, 2020
 */
package com.gugino.engine.graphics.renderer;

import java.awt.Graphics2D;

public class GraphicsRenderer {
	
	public Graphics2D graphics;
	
	public FontRenderer fontRenderer;
	public ShapeRenderer shapeRenderer;
	
	public GraphicsRenderer() {
		fontRenderer = new FontRenderer();
		shapeRenderer = new ShapeRenderer();
	}
	
	public void setGraphics(Graphics2D _g) {
		graphics = _g;
		fontRenderer.graphics = _g;
		shapeRenderer.graphics = _g;
	}
}
