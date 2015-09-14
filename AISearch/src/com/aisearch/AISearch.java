package com.aisearch;

import java.util.Date;
import java.awt.event.*;
import java.awt.*;

public class AISearch extends Frame {
	private static final long serialVersionUID = 1L;

	Dimension pantalla;
	int ancho, alto, x, y;

	MenuBar MMenu;
	MenuItem MAbrir, MSalir, MAcerca, MLimpiar;
	Menu MTemporizador;
	CheckboxMenuItem CMITemporizador, CMIPaso, CMILeyenda;
	CheckboxMenuItem CMIMuyRapido, CMIRapido, CMIMedio, CMILento;
	Choice CBusqueda;
	Button BEjecutar, BReiniciar;
	TextArea TAInformacion;
	Panel PNorte, PSur;
	FileDialog FDAbrir;
	Dialog DAcerca;

	String ArchivoGrafo;
	private Grafo grafo;
	private Grafo.BFS bfs;
	private Grafo.DFS dfs;
	private Grafo.SHC shc;
	private Grafo.SAHC sahc;
	private Grafo.BF bf;
	private Grafo.AE ae;

	// clase main
	public static void main(String arg[]) {
		// instancia una ventanas
		new AISearch();
	}

	// constructor de la clase (ventana del programa)
	public AISearch() {
		// ventana principal
		super();
		this.setTitle("AI-Search Algorithms");
		this.setLayout(new BorderLayout());
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		// incorpora el controlador de eventos a la ventana
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		// menu de la ventana principal
		MMenu = new MenuBar();
		Menu marchivo = new Menu("File"), mver = new Menu("View"), mejecucion = new Menu("Run"), macerca = new Menu("Help");
		MTemporizador = new Menu("Speed timer");
		MAbrir = new MenuItem("Open");
		MSalir = new MenuItem("Exit");
		MLimpiar = new MenuItem("Clear message panel");
		MAcerca = new MenuItem("About AI-Search Algorithms");
		CMILeyenda = new CheckboxMenuItem("Legend for nodes");
		CMILeyenda.setState(true);
		CMIPaso = new CheckboxMenuItem("Step by step");
		CMITemporizador = new CheckboxMenuItem("Timer");
		CMIMuyRapido = new CheckboxMenuItem("Very fast (0s)");
		CMIRapido = new CheckboxMenuItem("Fast (0.1s)");
		CMIMedio = new CheckboxMenuItem("Medium (0.5s)");
		CMILento = new CheckboxMenuItem("Slow (1s)");
		CMIPaso.setState(true);
		CMITemporizador.setState(false);
		CMIMuyRapido.setState(false);
		CMIRapido.setState(false);
		CMIMedio.setState(false);
		CMILento.setState(true);
		MTemporizador.setEnabled(false);
		marchivo.add(MAbrir);
		marchivo.addSeparator();
		marchivo.add(MSalir);
		mver.add(CMILeyenda);
		mver.addSeparator();
		mver.add(MLimpiar);
		mejecucion.add(CMIPaso);
		mejecucion.add(CMITemporizador);
		mejecucion.addSeparator();
		mejecucion.add(MTemporizador);
		MTemporizador.add(CMIMuyRapido);
		MTemporizador.add(CMIRapido);
		MTemporizador.add(CMIMedio);
		MTemporizador.add(CMILento);
		macerca.add(MAcerca);
		MMenu.add(marchivo);
		MMenu.add(mver);
		MMenu.add(mejecucion);
		MMenu.add(macerca);
		MAbrir.addActionListener(new ControladorMenuItem(MAbrir));
		MSalir.addActionListener(new ControladorMenuItem(MSalir));
		MLimpiar.addActionListener(new ControladorMenuItem(MLimpiar));
		MAcerca.addActionListener(new ControladorMenuItem(MAcerca));
		CMILeyenda.addItemListener(new ControladorCheckBox(CMILeyenda));
		CMIPaso.addItemListener(new ControladorCheckBox(CMIPaso));
		CMITemporizador.addItemListener(new ControladorCheckBox(CMITemporizador));
		CMIMuyRapido.addItemListener(new ControladorCheckBox(CMIMuyRapido));
		CMIRapido.addItemListener(new ControladorCheckBox(CMIRapido));
		CMIMedio.addItemListener(new ControladorCheckBox(CMIMedio));
		CMILento.addItemListener(new ControladorCheckBox(CMILento));

		// ventana de abrir archivos
		FDAbrir = new FileDialog(this, "Open graph file", FileDialog.LOAD);
		ArchivoGrafo = "";

		// ventana de acerca
		DAcerca = new Dialog(this, "About AI-Search Algorithms");
		DAcerca.setLayout(new GridLayout(12, 1));
		pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		ancho = 400;
		alto = 250;
		x = (pantalla.width - ancho) / 2;
		y = (pantalla.height - alto) / 2;
		DAcerca.setBounds(x, y, ancho, alto);
		DAcerca.setResizable(false);
		DAcerca.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				DAcerca.setVisible(false);
			}
		});

		// incorpora los elementos al dialog
		DAcerca.add(new Label(""));
		DAcerca.add(new Label(" AI-Search Algorithm Animation Software"));
		DAcerca.add(new Label(" Jose Antonio Lopez Suarez <jalopezsuarez@gmail.com>"));
		DAcerca.add(new Label(" University of Almeria (Spain)"));
		DAcerca.add(new Label(" Artificial Intelligent and Experts Systems "));
		DAcerca.add(new Label(" 2005-2006 Academic Year"));
		DAcerca.add(new Label(""));
		DAcerca.add(new Label(" Programming assignments for the course:"));
		DAcerca.add(new Label(" This algorithms animation software allows students understand"));
		DAcerca.add(new Label(" how work the most popular Artificial Intelligence search methods."));
		DAcerca.add(new Label(""));
		DAcerca.add(new Label(""));

		// lista de seleccion
		CBusqueda = new Choice();
		CBusqueda.addItem(" Breadth-First Search ");
		CBusqueda.addItem(" Depth-First Search ");
		CBusqueda.addItem(" Simple Hill Climbing ");
		CBusqueda.addItem(" Steepest Ascent Hill Climbing ");
		CBusqueda.addItem(" Best First Search ");
		CBusqueda.addItem(" A* Search ");
		CBusqueda.addItemListener(new ControladorChoice(CBusqueda));
		CBusqueda.setEnabled(false);

		// botones
		BEjecutar = new Button(" Fine step ");
		BReiniciar = new Button(" Reset search");
		BEjecutar.setEnabled(false);
		BReiniciar.setEnabled(false);
		BEjecutar.addActionListener(new ControladorButton(BEjecutar));
		BReiniciar.addActionListener(new ControladorButton(BReiniciar));

		// area de texto
		TAInformacion = new TextArea("", 5, 70, TextArea.SCROLLBARS_VERTICAL_ONLY);
		TAInformacion.setEditable(false);
		TAInformacion.setBackground(Color.white);

		// paneles contenedores
		PNorte = new Panel();
		PNorte.setLayout(new FlowLayout(FlowLayout.LEFT));
		PNorte.setBackground(Color.lightGray);
		PNorte.add(CBusqueda);
		PNorte.add(BEjecutar);
		PNorte.add(BReiniciar);
		PSur = new Panel();
		PSur.setLayout(new BorderLayout());
		PSur.setBackground(Color.lightGray);
		PSur.add("North", new Label("Message panel"));
		PSur.add("Center", TAInformacion);

		// componente grafo extiende de panel
		grafo = new Grafo();
		grafo.setBackground(new Color(90, 138, 212));
		// incorpora componentes a la venta principal
		this.setMenuBar(MMenu);
		this.add("North", PNorte);
		this.add("Center", grafo);
		this.add("South", PSur);

		// muestra la ventana principal
		this.setVisible(true);
	}

	// clases para controlar eventos
	class ControladorMenuItem implements ActionListener {
		MenuItem mi;

		ControladorMenuItem(MenuItem mip) {
			mi = mip;
		};

		public void actionPerformed(ActionEvent evt) {
			if (mi.equals(MAbrir)) {
				FDAbrir.setVisible(true);
				if (FDAbrir.getFile() != null) {
					ArchivoGrafo = FDAbrir.getDirectory() + FDAbrir.getFile();
					setTitle("AI-Search Algorithms - " + FDAbrir.getFile());
					try {
						// carga el grafo del archivo
						grafo.loadGrafo(ArchivoGrafo);
						TAInformacion.append((new Date()).toString() + ": Graph file successfully loaded.\n");
						// por defecto primer algoritmo de busqueda
						CBusqueda.select(0);
						bfs = null;
						dfs = null;
						shc = null;
						sahc = null;
						bf = null;
						ae = null;
						bfs = grafo.new BFS();
						TAInformacion.append("\n" + (new Date()).toString() + ": Breadth-First Search algorithm selected.\n");
						// activa los controles
						CBusqueda.setEnabled(true);
						BEjecutar.setEnabled(true);
						BReiniciar.setEnabled(true);
						// redibuja el grafo
						grafo.repaint();
					} catch (Exception exception) {
						CBusqueda.setEnabled(false);
						BEjecutar.setEnabled(false);
						BReiniciar.setEnabled(false);
						TAInformacion.append((new Date()).toString() + ": Error reading graph from file.\n");
					}
					;
				}
			} else if (mi.equals(MLimpiar)) {
				TAInformacion.setText("");
			} else if (mi.equals(MAcerca)) {
				DAcerca.setVisible(true);
			} else if (mi.equals(MSalir)) {
				System.exit(0);
			}
		}
	}

	class ControladorChoice implements ItemListener {
		Choice c;

		ControladorChoice(Choice cp) {
			c = cp;
		};

		public void itemStateChanged(ItemEvent evt) {
			// resetea el grafo y los algoritmos
			grafo.reset();
			bfs = null;
			dfs = null;
			sahc = null;
			shc = null;
			bf = null;
			ae = null;
			// establece el algoritmo seleccionado
			switch (c.getSelectedIndex()) {
			case 0:
				bfs = grafo.new BFS();
				TAInformacion.append("\n" + (new Date()).toString() + ": Breadth-First Search algorithm selected.\n");
				break;
			case 1:
				dfs = grafo.new DFS();
				TAInformacion.append("\n" + (new Date()).toString() + ": Depth-First Search algorithm selected.\n");
				break;
			case 2:
				shc = grafo.new SHC();
				TAInformacion.append("\n" + (new Date()).toString() + ": Simple Hill Climbing algorithm selected.\n");
				break;
			case 3:
				sahc = grafo.new SAHC();
				TAInformacion.append("\n" + (new Date()).toString() + ": Steepest Ascent Hill Climbing algorithm selected.\n");
				break;
			case 4:
				bf = grafo.new BF();
				TAInformacion.append("\n" + (new Date()).toString() + ": Best First Search algorithm selected.\n");
				break;
			case 5:
				ae = grafo.new AE();
				TAInformacion.append("\n" + (new Date()).toString() + ": A* Search algorithm selected.\n");
				break;
			}
			// redibuja el grafo
			grafo.repaint();
			// activa el boton de ejecucion
			BEjecutar.setEnabled(true);
		}
	}

	class ControladorCheckBox implements ItemListener {
		CheckboxMenuItem cmi;

		ControladorCheckBox(CheckboxMenuItem cmip) {
			cmi = cmip;
		};

		public void itemStateChanged(ItemEvent evt) {
			if (cmi.equals(CMILeyenda)) {
				grafo.setVerLeyenda(cmi.getState());
				grafo.repaint();
			} else if (cmi.equals(CMITemporizador)) {
				CMITemporizador.setState(true);
				MTemporizador.setEnabled(true);
				CMIPaso.setState(false);
				BEjecutar.setLabel("Start");
			} else if (cmi.equals(CMIPaso)) {
				CMIPaso.setState(true);
				CMITemporizador.setState(false);
				MTemporizador.setEnabled(false);
				BEjecutar.setLabel("Next step");
			} else if (cmi.equals(CMIMuyRapido)) {
				CMIMuyRapido.setState(true);
				CMIRapido.setState(false);
				CMIMedio.setState(false);
				CMILento.setState(false);
				grafo.setTemporizador(0);
			} else if (cmi.equals(CMIRapido)) {
				CMIMuyRapido.setState(false);
				CMIRapido.setState(true);
				CMIMedio.setState(false);
				CMILento.setState(false);
				grafo.setTemporizador(100);
			} else if (cmi.equals(CMIMedio)) {
				CMIMuyRapido.setState(false);
				CMIRapido.setState(false);
				CMIMedio.setState(true);
				CMILento.setState(false);
				grafo.setTemporizador(500);
			} else if (cmi.equals(CMILento)) {
				CMIMuyRapido.setState(false);
				CMIRapido.setState(false);
				CMIMedio.setState(false);
				CMILento.setState(true);
				grafo.setTemporizador(1000);
			}
		}
	}

	class ControladorButton implements ActionListener {
		Button b;

		ControladorButton(Button bp) {
			b = bp;
		};

		public void actionPerformed(ActionEvent evt) {
			String cadena;
			String[] estadisticas;
			if (b.equals(BEjecutar)) {
				switch (CBusqueda.getSelectedIndex()) {
				case 0:
					if (CMIPaso.getState()) {
						// muestra ejecucion
						cadena = bfs.getCola();
						if (cadena != "")
							TAInformacion.append((new Date()).toString() + ": " + cadena + "\n");
						if (!bfs.step()) {
							// muestra estatidsticas
							estadisticas = grafo.getEstadisticas();
							TAInformacion.append((new Date()).toString() + ": Breadth-First Search stadicstics.\n");
							TAInformacion.append((new Date()).toString() + ": " + estadisticas[0] + "\n");
							TAInformacion.append((new Date()).toString() + ": " + estadisticas[1] + "\n");
							TAInformacion.append((new Date()).toString() + ": " + estadisticas[2] + "\n");
							TAInformacion.append((new Date()).toString() + ": " + estadisticas[3] + "\n");
							// desactiva el boton de ejecucion
							BEjecutar.setEnabled(false);
						}
					} else {
						bfs.run();
						// muestra estatidsticas
						estadisticas = grafo.getEstadisticas();
						TAInformacion.append((new Date()).toString() + ": Breadth-First Search stadistics.\n");
						TAInformacion.append((new Date()).toString() + ": " + estadisticas[0] + "\n");
						TAInformacion.append((new Date()).toString() + ": " + estadisticas[1] + "\n");
						TAInformacion.append((new Date()).toString() + ": " + estadisticas[2] + "\n");
						TAInformacion.append((new Date()).toString() + ": " + estadisticas[3] + "\n");
						// desactiva el boton de ejecucion
						BEjecutar.setEnabled(false);
					}
					break;
				case 1:
					if (CMIPaso.getState()) {
						cadena = dfs.getPila();
						if (cadena != "")
							TAInformacion.append((new Date()).toString() + ": " + cadena + "\n");
						if (!dfs.step()) {
							// muestra estatidsticas
							estadisticas = grafo.getEstadisticas();
							TAInformacion.append((new Date()).toString() + ": Depth-First Search algorithm stadistics.\n");
							TAInformacion.append((new Date()).toString() + ": " + estadisticas[0] + "\n");
							TAInformacion.append((new Date()).toString() + ": " + estadisticas[1] + "\n");
							TAInformacion.append((new Date()).toString() + ": " + estadisticas[2] + "\n");
							TAInformacion.append((new Date()).toString() + ": " + estadisticas[3] + "\n");
							// desactiva el boton de ejecucion
							BEjecutar.setEnabled(false);
						}
					} else {
						dfs.run();
						// muestra estatidsticas
						estadisticas = grafo.getEstadisticas();
						TAInformacion.append((new Date()).toString() + ": Depth-First Search algorithm stadistics.\n");
						TAInformacion.append((new Date()).toString() + ": " + estadisticas[0] + "\n");
						TAInformacion.append((new Date()).toString() + ": " + estadisticas[1] + "\n");
						TAInformacion.append((new Date()).toString() + ": " + estadisticas[2] + "\n");
						TAInformacion.append((new Date()).toString() + ": " + estadisticas[3] + "\n");
						// desactiva el boton de ejecucion
						BEjecutar.setEnabled(false);
					}
					break;
				case 2:
					if (CMIPaso.getState()) {
						if (!shc.step()) {
							// muestra estatidsticas
							estadisticas = grafo.getEstadisticas();
							TAInformacion.append((new Date()).toString() + ": Simple Hill Climbing algorithm stadistics.\n");
							TAInformacion.append((new Date()).toString() + ": " + estadisticas[0] + "\n");
							TAInformacion.append((new Date()).toString() + ": " + estadisticas[1] + "\n");
							TAInformacion.append((new Date()).toString() + ": " + estadisticas[2] + "\n");
							TAInformacion.append((new Date()).toString() + ": " + estadisticas[3] + "\n");
							// desactiva el boton de ejecucion
							BEjecutar.setEnabled(false);
						}
					} else {
						shc.run();
						// muestra estatidsticas
						estadisticas = grafo.getEstadisticas();
						TAInformacion.append((new Date()).toString() + ": Simple Hill Climbing algorithm stadistics.\n");
						TAInformacion.append((new Date()).toString() + ": " + estadisticas[0] + "\n");
						TAInformacion.append((new Date()).toString() + ": " + estadisticas[1] + "\n");
						TAInformacion.append((new Date()).toString() + ": " + estadisticas[2] + "\n");
						TAInformacion.append((new Date()).toString() + ": " + estadisticas[3] + "\n");
						// desactiva el boton de ejecucion
						BEjecutar.setEnabled(false);
					}
					break;
				case 3:
					if (CMIPaso.getState()) {
						if (!sahc.step()) {
							// muestra estatidsticas
							estadisticas = grafo.getEstadisticas();
							TAInformacion.append((new Date()).toString()
									+ ": Steepest Ascent Hill Climbing algorithm stadistics.\n");
							TAInformacion.append((new Date()).toString() + ": " + estadisticas[0] + "\n");
							TAInformacion.append((new Date()).toString() + ": " + estadisticas[1] + "\n");
							TAInformacion.append((new Date()).toString() + ": " + estadisticas[2] + "\n");
							TAInformacion.append((new Date()).toString() + ": " + estadisticas[3] + "\n");
							// desactiva el boton de ejecucion
							BEjecutar.setEnabled(false);
						}
					} else {
						sahc.run();
						// muestra estatidsticas
						estadisticas = grafo.getEstadisticas();
						TAInformacion.append((new Date()).toString()
								+ ": Steepest Ascent Hill Climbing algorithm stadistics.\n");
						TAInformacion.append((new Date()).toString() + ": " + estadisticas[0] + "\n");
						TAInformacion.append((new Date()).toString() + ": " + estadisticas[1] + "\n");
						TAInformacion.append((new Date()).toString() + ": " + estadisticas[2] + "\n");
						TAInformacion.append((new Date()).toString() + ": " + estadisticas[3] + "\n");
						// desactiva el boton de ejecucion
						BEjecutar.setEnabled(false);
					}
					break;
				case 4:
					if (CMIPaso.getState()) {
						cadena = bf.getAbierta();
						if (cadena != "")
							TAInformacion.append((new Date()).toString() + ": " + cadena + "\n");
						cadena = bf.getCerrada();
						if (cadena != "")
							TAInformacion.append((new Date()).toString() + ": " + cadena + "\n");
						if (!bf.step()) {
							// muestra estatidsticas
							estadisticas = grafo.getEstadisticas();
							TAInformacion.append((new Date()).toString() + ": Best First Search algorithm stadistics.\n");
							TAInformacion.append((new Date()).toString() + ": " + estadisticas[0] + "\n");
							TAInformacion.append((new Date()).toString() + ": " + estadisticas[1] + "\n");
							TAInformacion.append((new Date()).toString() + ": " + estadisticas[2] + "\n");
							TAInformacion.append((new Date()).toString() + ": " + estadisticas[3] + "\n");
							// desactiva el boton de ejecucion
							BEjecutar.setEnabled(false);
						}
					} else {
						bf.run();
						// muestra estatidsticas
						estadisticas = grafo.getEstadisticas();
						TAInformacion.append((new Date()).toString() + ": Best First Search algorithm stadistics.\n");
						TAInformacion.append((new Date()).toString() + ": " + estadisticas[0] + "\n");
						TAInformacion.append((new Date()).toString() + ": " + estadisticas[1] + "\n");
						TAInformacion.append((new Date()).toString() + ": " + estadisticas[2] + "\n");
						TAInformacion.append((new Date()).toString() + ": " + estadisticas[3] + "\n");
						// desactiva el boton de ejecucion
						BEjecutar.setEnabled(false);
					}
					break;
				case 5:
					if (CMIPaso.getState()) {
						cadena = ae.getAbierta();
						if (cadena != "")
							TAInformacion.append((new Date()).toString() + ": " + cadena + "\n");
						cadena = ae.getCerrada();
						if (cadena != "")
							TAInformacion.append((new Date()).toString() + ": " + cadena + "\n");
						if (!ae.step()) {
							// muestra estatidsticas
							estadisticas = grafo.getEstadisticas();
							TAInformacion.append((new Date()).toString() + ": A* Search algorithm stadistics.\n");
							TAInformacion.append((new Date()).toString() + ": " + estadisticas[0] + "\n");
							TAInformacion.append((new Date()).toString() + ": " + estadisticas[1] + "\n");
							TAInformacion.append((new Date()).toString() + ": " + estadisticas[2] + "\n");
							TAInformacion.append((new Date()).toString() + ": " + estadisticas[3] + "\n");
							// desactiva el boton de ejecucion
							BEjecutar.setEnabled(false);
						}
					} else {
						ae.run();
						// muestra estatidsticas
						estadisticas = grafo.getEstadisticas();
						TAInformacion.append((new Date()).toString() + ": A* Search algorithm stadistics.\n");
						TAInformacion.append((new Date()).toString() + ": " + estadisticas[0] + "\n");
						TAInformacion.append((new Date()).toString() + ": " + estadisticas[1] + "\n");
						TAInformacion.append((new Date()).toString() + ": " + estadisticas[2] + "\n");
						TAInformacion.append((new Date()).toString() + ": " + estadisticas[3] + "\n");
						// desactiva el boton de ejecucion
						BEjecutar.setEnabled(false);
					}
					break;
				}
			}
			if (b.equals(BReiniciar)) {
				grafo.reset();
				switch (CBusqueda.getSelectedIndex()) {
				case 0:
					bfs.reset();
					TAInformacion.append("\n" + (new Date()).toString() + ": Breadth-First Search algorithm restarted.\n");
					break;
				case 1:
					dfs.reset();
					TAInformacion.append("\n" + (new Date()).toString() + ": Depth-First Search algorithm restarted.\n");
					break;
				case 2:
					shc.reset();
					TAInformacion.append("\n" + (new Date()).toString() + ": Simple Hill Climbing algorithm restarted.\n");
					break;
				case 3:
					sahc.reset();
					TAInformacion.append("\n" + (new Date()).toString()
							+ ": Steepest Ascent Hill Climbing algorithm restarted.\n");
					break;
				case 4:
					bf.reset();
					TAInformacion.append("\n" + (new Date()).toString() + ": Best First Search algorithm restarted.\n");
					break;
				case 5:
					ae.reset();
					TAInformacion.append("\n" + (new Date()).toString() + ": A* Search algorithm restarted.\n");
					break;
				}
				grafo.repaint();
				// activa el boton de ejecucion
				BEjecutar.setEnabled(true);
			}
		}
	}
}
