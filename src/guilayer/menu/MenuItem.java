package guilayer.menu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class MenuItem extends JPanel {
	
	private List<MouseListener> listeners;
	private JLabel label;
	private JPanel current;
	
	public MenuItem(String name) {
		super();
		
		listeners = new ArrayList<>();
		label = new JLabel(name);
		current = new JPanel();
		
		initialize();
	}
	private void initialize() {
	
		int height = 32;
		int textIndent = 24;
		int currentWidth = 8;
		Color fontColour = Color.WHITE;
		Color currentColour = Color.RED;
		Color backgroundColour = Color.DARK_GRAY;
		Color hoveredBackgroundColour = Color.GRAY;

		this.setSize(this.getWidth(), height);
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.setForeground(fontColour);
		this.setCurrentColour(currentColour);
		this.setBackground(backgroundColour);
		this.setLayout(null);
		
		current.setSize(currentWidth, height);
		current.setBackground(currentColour);
		current.setLayout(null);
		current.setVisible(false);
		this.add(current);
		
		label.setSize(this.getSize());
		label.setForeground(this.getForeground());
		label.setFont(this.getFont());
		label.setBorder(new EmptyBorder(0, textIndent, 0, 0));
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(label);
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				/*
				Color backgroundColour = Menu.this.getBackground();
				Menu.this.setBackground(backgroundColour.darker());
				*/
				setBackground(hoveredBackgroundColour);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				/*
				Color backgroundColour = Menu.this.getBackground();
				Menu.this.setBackground(backgroundColour.brighter());
				*/
				setBackground(backgroundColour);
			}
		});
	}
	public void setCurrentColour(Color colour) {
		current.setBackground(colour);
	}
	public Color getCurrentColour() {
		return current.getBackground();
	}
	public void setTextIndent(int textIndent) {
		EmptyBorder border = new EmptyBorder(0, textIndent, 0, 0);
		label.setBorder(border);
	}
	public int getTextIndent() {
		EmptyBorder border = (EmptyBorder)label.getBorder();
		return border.getBorderInsets().left;
	}
	public void setActive(boolean active) {
		current.setVisible(active);
		calculateLayout();
	}
	public boolean getActive() {
		return current.isVisible();
	}
	@Override
	public void setForeground(Color colour) {
		if (label == null)
			return;
		label.setForeground(colour);
	}
	@Override
	public Color getForeground() {
		if (label == null)
			return null;
		
		return label.getForeground();
	}
	@Override
	public void setFont(Font font) {
		if (label == null)
			return;
		
		label.setFont(font);
	}
	@Override
	public Font getFont() {
		if (label == null)
			return null;
		
		return label.getFont();
	}
	@Override
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		calculateLayout();
	}
	
	private void calculateLayout() {
		int height = this.getHeight();
		int currentWidth = current.getWidth();
		int offset = getActive() ? currentWidth : 0;
		current.setBounds(0, 0, currentWidth, height);
		label.setBounds(offset, 0, this.getWidth() - offset, height);
		
	}
}
