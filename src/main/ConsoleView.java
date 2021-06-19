package main;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;

public class ConsoleView extends JTextArea
{
	//Constructor - Sets wrap text, border, colour and disables editable text
	public ConsoleView(int rows, int columns)
	{
		setRows(rows);
		setColumns(columns);
		setLineWrap(true);
		setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		setEditable(false);
		setBackground(new Color(173,216,230));
		setFont(getFont().deriveFont(6f));
	}	
}