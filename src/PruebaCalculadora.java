import javax.swing.SwingUtilities;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;


class Calculadora extends JFrame implements ActionListener {

	GridBagLayout gbl = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();
	
	String resultado = "";

	Font fuente = new Font("Calibri", 1, 20);
	Font fuente2 = new Font("Calibri", 1, 30);
	JTextField txtCaja = new JTextField("0");

	JButton btnSuma = new JButton("+");
	JButton btnResta = new JButton("-");
	JButton btnMult = new JButton("x");
	JButton btnDiv = new JButton("/");
	JButton btnUnoSobreX = new JButton("1/x");
	JButton btnIgual = new JButton("=");

	JButton btnBorrar = new JButton("<=");
	JButton btnCuadrado = new JButton("x^2");
	JButton btnNueve = new JButton("9");
	JButton btnSeis = new JButton("6");
	JButton btnTres = new JButton("3");
	JButton btnPunto = new JButton(".");

	JButton btnUno = new JButton("1");
	JButton btnCuatro = new JButton("4");
	JButton btnSiete = new JButton("7");
	JButton btnCE = new JButton("CE");
	JButton btnPorcentaje = new JButton("%");
	JButton btnMasMenos = new JButton("+-");

	JButton btnOcho = new JButton("8");
	JButton btnC = new JButton("C");
	JButton btnRaiz = new JButton("Raiz");
	JButton btnCinco = new JButton("5");
	JButton btnDos = new JButton("2");
	JButton btnCero = new JButton("0");
	
	Pattern patron = Pattern.compile("\\.");
	Pattern patron2 = Pattern.compile("[x/+-]");
	Matcher buscador;
	ScriptEngine escaner = new ScriptEngineManager().getEngineByName("js");

	public Calculadora() {

		getContentPane().setLayout(gbl);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Calculadora");
		setVisible(true);
		gbc.insets = new Insets(5,5,5,5);
		gbc.fill = GridBagConstraints.BOTH;
		setResizable(false);

		txtCaja.setFont(fuente2);
		txtCaja.setHorizontalAlignment(JTextField.RIGHT);
		txtCaja.setEditable(false);

		alinearElemento(0, 0, 1, 4, txtCaja);

		alinearElemento(0, 1, 1, 1, btnPorcentaje);
		alinearElemento(1, 1, 1, 1, btnRaiz);
		alinearElemento(2, 1, 1, 1, btnCuadrado);
		alinearElemento(3, 1, 1, 1, btnUnoSobreX);

		alinearElemento(0, 2, 1, 1, btnCE);
		alinearElemento(1, 2, 1, 1, btnC);
		alinearElemento(2, 2, 1, 1, btnBorrar);
		alinearElemento(3, 2, 1, 1, btnDiv);

		alinearElemento(0, 3, 1, 1, btnSiete);
		alinearElemento(1, 3, 1, 1, btnOcho);
		alinearElemento(2, 3, 1, 1, btnNueve);
		alinearElemento(3, 3, 1, 1, btnMult);

		alinearElemento(0, 4, 1, 1, btnCuatro);
		alinearElemento(1, 4, 1, 1, btnCinco);
		alinearElemento(2, 4, 1, 1, btnSeis);
		alinearElemento(3, 4, 1, 1, btnResta);

		alinearElemento(0, 5, 1, 1, btnUno);
		alinearElemento(1, 5, 1, 1, btnDos);
		alinearElemento(2, 5, 1, 1, btnTres);
		alinearElemento(3, 5, 1, 1, btnSuma);

		alinearElemento(0, 6, 1, 1, btnMasMenos);
		alinearElemento(1, 6, 1, 1, btnCero);
		alinearElemento(2, 6, 1, 1, btnPunto);
		alinearElemento(3, 6, 1, 1, btnIgual);

		btnPorcentaje.addActionListener(this);
		btnRaiz.addActionListener(this);
		btnCuadrado.addActionListener(this);
		btnUnoSobreX.addActionListener(this);
		btnCE.addActionListener(this);
		btnC.addActionListener(this);
		btnBorrar.addActionListener(this);
		btnDiv.addActionListener(this);
		btnSiete.addActionListener(this);
		btnOcho.addActionListener(this);
		btnNueve.addActionListener(this);
		btnMult.addActionListener(this);
		btnCuatro.addActionListener(this);
		btnCinco.addActionListener(this);
		btnSeis.addActionListener(this);
		btnResta.addActionListener(this);
		btnUno.addActionListener(this);
		btnDos.addActionListener(this);
		btnTres.addActionListener(this);
		btnSuma.addActionListener(this);
		btnMasMenos.addActionListener(this);
		btnCero.addActionListener(this);
		btnIgual.addActionListener(this);
		btnPunto.addActionListener(this);

		pack();
		setLocationRelativeTo(null);

	}

	@Override
	public void actionPerformed(ActionEvent evento) {

		String textoObtenido;
		Object eventoObtenido = evento.getSource();

		if (txtCaja.getText().equals("Error X.x")) {
			txtCaja.setText("0");
		}

		textoObtenido = txtCaja.getText();

		try {
			if (eventoObtenido == btnCero) {

				if (!textoObtenido.equals("0")) {
					concatenacionPersonalizada("0", textoObtenido);
				}

			} else if (eventoObtenido == btnUnoSobreX) {

				verificarOperador("UnoSobreX", textoObtenido);

			} else if (eventoObtenido == btnPunto) {

				colocarPunto(textoObtenido);

			} else if (eventoObtenido == btnBorrar) {

				borrar(textoObtenido);

			} else if (eventoObtenido == btnC) {

				limpiarResultado();
				txtCaja.setText("0");

			} else if (eventoObtenido == btnCE) {

				int longitud = textoObtenido.length();

				if (!resultado.equals("")) {
					resultado = resultado.substring(0, resultado.length() - longitud);
					txtCaja.setText("0");
				}

			} else if (eventoObtenido == btnSuma) {

				concatenacionOperacional("+", textoObtenido);

			} else if (eventoObtenido == btnRaiz) {

				verificarOperador("Raiz", textoObtenido);

			} else if (eventoObtenido == btnPorcentaje) {

				verificarOperador("Porcentaje", textoObtenido);

			} else if (eventoObtenido == btnResta) {

				concatenacionOperacional("-", textoObtenido);

			} else if (eventoObtenido == btnMult) {

				concatenacionOperacional("*", textoObtenido);

			} else if (eventoObtenido == btnDiv) {

				concatenacionOperacional("/", textoObtenido);

			} else if (eventoObtenido == btnMasMenos) {

				cambiarSigno(textoObtenido);

			} else if (eventoObtenido == btnIgual) {

				verificarOperador("Igual", textoObtenido);

			} else if (eventoObtenido == btnCuadrado) {

				verificarOperador("Cuadrado", textoObtenido);

			} else {

				if (eventoObtenido == btnUno) {
					concatenacionPersonalizada("1", textoObtenido);
				} else if (eventoObtenido == btnDos) {
					concatenacionPersonalizada("2", textoObtenido);
				} else if (eventoObtenido == btnTres) {
					concatenacionPersonalizada("3", textoObtenido);
				} else if (eventoObtenido == btnCuatro) {
					concatenacionPersonalizada("4", textoObtenido);
				} else if (eventoObtenido == btnCinco) {
					concatenacionPersonalizada("5", textoObtenido);
				} else if (eventoObtenido == btnSeis) {
					concatenacionPersonalizada("6", textoObtenido);
				} else if (eventoObtenido == btnSiete) {
					concatenacionPersonalizada("7", textoObtenido);
				} else if (eventoObtenido == btnOcho) {
					concatenacionPersonalizada("8", textoObtenido);
				} else if (eventoObtenido == btnNueve) {
					concatenacionPersonalizada("9", textoObtenido);
				}

			}
		} catch (ScriptException e) {
			limpiarResultado();
			txtCaja.setText("Error X.x");
		}
		System.out.println(resultado);

	}

	public void alinearElemento(int x, int y, int height, int width, Component componente) {

		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridheight = height;
		gbc.gridwidth = width;

		gbl.setConstraints(componente, gbc);
		add(componente);

	}

	public void concatenacionPersonalizada(String cadena, String textoObtenido) {

		resultado += cadena;
		txtCaja.setText((textoObtenido.equals("0") ? "" : textoObtenido) + cadena);

	}

	public void verificarOperador(String cadena, String textoObtenido) throws ScriptException {

		switch (cadena) {

		case "Suma":

			concatenacionOperacional("+", textoObtenido);

			break;

		case "Resta":

			concatenacionOperacional("-", textoObtenido);

			break;

		case "Multiplicacion":

			concatenacionOperacional("*", textoObtenido);

			break;

		case "Division":

			concatenacionOperacional("/", textoObtenido);

			break;

		case "Raiz":

			try {

				resultado = String.valueOf(escaner.eval(resultado));
				resultado = String.valueOf(Math.sqrt(Double.parseDouble(resultado)));
				txtCaja.setText(resultado);

			} catch (NumberFormatException e) {
				txtCaja.setText("Error X.x");
				resultado = "";
			}

			break;

		case "Cuadrado":

			try {

				resultado = String.valueOf(escaner.eval(resultado));
				resultado = String.valueOf(Math.pow(Double.parseDouble(resultado), 2));
				txtCaja.setText(resultado);

			} catch (NumberFormatException e) {
				txtCaja.setText("0");
			}

			break;

		case "Porcentaje":

			concatenacionOperacional("%", textoObtenido);

			break;

		case "UnoSobreX":

			resultado = String.valueOf(escaner.eval("1/" + resultado));
			txtCaja.setText(resultado);

			break;

		case "Igual":

			resultado = String.valueOf(escaner.eval(resultado));

			if (resultado.equals("0")) {

				limpiarResultado();
				txtCaja.setText("0");

			} else {
				txtCaja.setText(resultado);
			}

			break;

		}

	}

	public void concatenacionOperacional(String cadena, String textoObtenido) {

		resultado += cadena;
		txtCaja.setText("0");

	}

	public void limpiarResultado() {
		resultado = "";
	}

	public void borrar(String textoObtenido) {

		if (!textoObtenido.equals("0")) {

			if (textoObtenido.length() == 2 && textoObtenido.substring(0, 1).equals("-")
					|| textoObtenido.length() == 2 && textoObtenido.substring(0, 1).equals("0")
					|| textoObtenido.length() == 1) {
				txtCaja.setText("0");
			} else {
				txtCaja.setText(textoObtenido.substring(0, textoObtenido.length() - 1));
			}
			resultado = resultado.substring(0, resultado.length() - 1);

		}

	}

	public void colocarPunto(String textoObtenido) {

		if (!textoObtenido.substring(textoObtenido.length() - 1).equals(".")) {

			buscador = patron.matcher(textoObtenido);

			if (!buscador.find()) {
				if (textoObtenido.equals("0")) {
					concatenacionPersonalizada(textoObtenido + ".", textoObtenido);
				} else {
					concatenacionPersonalizada(".", textoObtenido);
				}
			}
		}

	}

	public void cambiarSigno(String textoObtenido) {

		if (!textoObtenido.equals("0")) {
			if (textoObtenido.substring(0, 1).equals("-")) {
				resultado = textoObtenido.replace("-", "");
				txtCaja.setText(resultado);
			} else {
				resultado = "-" + textoObtenido;
				txtCaja.setText(resultado);
			}
		}

	}

}

public class PruebaCalculadora {

	public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				new Calculadora();
			}
		});
	    
	}

}
