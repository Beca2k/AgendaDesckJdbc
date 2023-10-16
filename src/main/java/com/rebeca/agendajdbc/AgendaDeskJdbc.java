package com.rebeca.agendajdbc;

import java.awt.EventQueue;

import com.rebeca.agendajdbc.ui.UiPrincipal;

public class AgendaDeskJdbc {

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UiPrincipal window = new UiPrincipal(); // 12
					window.mostrar();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


}
