package com.aisearch;

import java.util.Vector;
import java.awt.geom.Ellipse2D;
import java.awt.*;

public class Nodo {
	// define un nuevo tipo anidado
	public enum TEstado {
		NOGENERADO, ACTUAL, ABIERTO, CERRADO
	};

	// elementos del profesor
	private String Identificacion;
	private int PosicionX;
	private int PosicionY;
	private double Valor;
	private boolean EsObjetivo;
	private Vector<Sucesor> Sucesores;
	private TEstado Estado;

	// mis movidas
	private Sucesor Puntero; // puntero a nodo de nivel superior
	private double CosteCamino; // coste del camino del nodo actual a la raiz

	// constructor
	public Nodo(String Identificacion) {
		this.Identificacion = Identificacion;
		PosicionX = 0;
		PosicionY = 0;
		Valor = 0.0f;
		EsObjetivo = false;
		Sucesores = new Vector<Sucesor>();

		Estado = TEstado.NOGENERADO;
		Puntero = new Sucesor("", 0);
		CosteCamino = 0;
	}

	// metodos de inicializacion
	public void setPosicionX(int PosicionX) {
		this.PosicionX = PosicionX;
	}

	public void setPosicionY(int PosicionY) {
		this.PosicionY = PosicionY;
	}

	public void setEstado(TEstado Estado) {
		this.Estado = Estado;
	}

	public void setValor(double Valor) {
		this.Valor = Valor;
	}

	public void setEsObjetivo(boolean EsObjetivo) {
		this.EsObjetivo = EsObjetivo;
	}

	// metodos de obtencion de datos
	public String toString() {
		return Identificacion;
	}

	public int getX() {
		return PosicionX;
	}

	public int getY() {
		return PosicionY;
	}

	public Color getColorEstado() {
		Color color;
		switch (Estado.ordinal()) {
		case 0:
			color = Color.white;
			break;
		case 1:
			color = Color.red;
			break;
		case 2:
			color = Color.green;
			break;
		case 3:
			color = Color.orange;
			break;
		default:
			color = Color.white;
		}
		return color;
	}

	public TEstado getEstado() {
		return Estado;
	}

	public boolean getEsObjetivo() {
		return EsObjetivo;
	}

	public double getValor() {
		return Valor;
	}

	// metodos para los sucesores
	public void addSucesor(String IdSucesor, int CosteSucesor) {
		Sucesor miSucesor = new Sucesor(IdSucesor, CosteSucesor);
		Sucesores.add(miSucesor);
	}

	public int maxSucesores() {
		return Sucesores.size();
	}

	public String getIdSucesor(int indice) {
		return ((Sucesor) Sucesores.get(indice)).IdSucesor;
	}

	public double getCosteSucesor(int indice) {
		return ((Sucesor) Sucesores.get(indice)).CosteSucesor;
	}

	// metodos para la gestion del puntero al nodo de nivel superior
	public String getIdPuntero() {
		return Puntero.getIdSucesor();
	}

	public double getCostePuntero() {
		return Puntero.getCosteSucesor();
	}

	public double getCosteCamino() {
		return CosteCamino;
	}

	public void setIdPuntero(String IdPuntero) {
		this.Puntero.setIdSucesor(IdPuntero);
	}

	public void setCostePuntero(double CostePuntero) {
		this.Puntero.setCosteSucesor(CostePuntero);
	}

	public void setCosteCamino(double CosteCamino) {
		this.CosteCamino = CosteCamino;
	}

	// metodos para pintar por pantalla
	public void pintarNodo(Graphics g) {
		Color color;
		Graphics2D g2 = (Graphics2D) g;
		GradientPaint colorGradiente;

		color = this.getColorEstado();
		if (color == Color.black)
			colorGradiente = new GradientPaint(PosicionX - 10, PosicionY - 10, color, PosicionX + 50, PosicionY + 50,
					Color.white);
		else
			colorGradiente = new GradientPaint(PosicionX - 10, PosicionY - 10, color, PosicionX + 50, PosicionY + 50,
					Color.black);

		g2.setPaint(colorGradiente);
		g2.fill(new Ellipse2D.Double(PosicionX - 10, PosicionY - 10, 2.0 * 10, 2.0 * 10));

		Font fuente = g.getFont();
		g.setFont(new Font(fuente.getFontName(), Font.BOLD, fuente.getSize()));
		g2.setColor(color.brighter());
		g2.drawString(Double.toString(Valor), (int) PosicionX + 6, (int) PosicionY - 8);

		if (color == Color.black)
			g2.setColor(Color.white);
		else
			g2.setColor(Color.black);
		g2.drawString(Identificacion, (int) PosicionX - 4, (int) PosicionY + 4);
		g.setFont(fuente);

		if (EsObjetivo) {
			g2.setColor(Color.red);
			g2.drawOval((int) PosicionX - 13, (int) PosicionY - 13, 2 * 13, 2 * 13);
		}
	}

	// gestion de la lista de pares de sucesores
	public class Sucesor {
		private String IdSucesor;
		private double CosteSucesor;

		public Sucesor(String IdSucesor, int CosteSucesor) {
			this.IdSucesor = IdSucesor;
			this.CosteSucesor = CosteSucesor;
		}

		public String getIdSucesor() {
			return IdSucesor;
		}

		public double getCosteSucesor() {
			return CosteSucesor;
		}

		public void setIdSucesor(String IdSucesor) {
			this.IdSucesor = IdSucesor;
		}

		public void setCosteSucesor(double CosteSucesor) {
			this.CosteSucesor = CosteSucesor;
		}
	}
}
