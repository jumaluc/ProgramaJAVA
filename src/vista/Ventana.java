package vista;
import controlador.*;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import controlador.AreasControlador;
import controlador.SalariosControlador;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;  

public class Ventana  implements ActionListener, ItemListener{

	private PersonalControlador personalControlador = new PersonalControlador();
    private JFrame ventana = new JFrame();
    private JMenuBar barraMenu = new JMenuBar();
    private JMenu menu = new JMenu("Operaciones");  
    private JMenu menu2 = new JMenu("Estadisticas");
    private JMenu menu3 = new JMenu("Sistema");
    private JMenu menu4 = new JMenu("Salir");

    //Operaciones
    private JMenu menu5 = new JMenu("Ingreso");
    private JMenuItem itemOperacionesG= new JMenuItem("Gerente");  
    private JMenuItem itemOperacionesE = new JMenuItem("Empleado");  
    private JMenuItem itemOperaciones2 = new JMenuItem("Consulta y Actualizacion");
    private JMenuItem itemOperaciones3 = new JMenuItem("Consulta masiva");
    private JMenuItem itemSistema = new JMenuItem("Acerca de...");
    
	private String personal;
	private SalariosControlador salariosControlador = new SalariosControlador();
    private AreasControlador areasControlador = new AreasControlador();

    // INGRESO 
    private JPanel panelIngreso = new JPanel();
    private JTextArea texto = new JTextArea("Ventana para el ingreso del Personal, es importante que \n"
            + "tenga en cuenta solo se pueden ingresar gerentes o empleados \n"
            + "y que hay que especificarlo. Ingresar nombre, apellido, dni, areas involucradas, contrato");

    private JLabel labelNombre = new JLabel("Nombre: ");
    private JTextField textNombre = new JTextField(20);
    private JLabel labelApellido = new JLabel("Apellido: ");
    private JTextField textApellido = new JTextField(20);
    private JLabel lbDni = new JLabel("DNI: ");
    private JTextField textDni = new JTextField(20);

    
    //LISTAS DESPLEGABLES
    private JPanel panelListasDesplegables = new JPanel();
    private JLabel lbTipoContrato = new JLabel("Tipo de Contrato : ");
    private JComboBox<String> listaDesplegableEstatica;
    private JComboBox<String> listaDesplegableDependiente;

    //LISTAS
    
    private JPanel panelListas = new JPanel();
    private JLabel lbArea = new JLabel("Elije la/las areas: ");
    private DefaultListModel<String> modelo;
    private JList<String> listaDinamica = new JList<String>(areasControlador.devolverAreasNombre());
    JScrollPane scroll = new JScrollPane(listaDinamica);
    private JButton buttonCopiar = new JButton("Copiar-->");
    JScrollPane scroll2;

    private JList<String> listaEstatica;

    //RADIO BUTTON
    private JPanel panelRadioPersonal = new JPanel();
    private JLabel lbPersonal = new JLabel("Genero:  ");
    private JRadioButton rbHombre = new JRadioButton("Hombre");
    private JRadioButton rbMujer = new JRadioButton("Mujer");
    private ButtonGroup rbGrupoPersonal = new ButtonGroup(); 
    
    //CHECK BOX GERENTE 
    
    private JPanel panelCheckBoxGerente = new JPanel();
    private JLabel lbActividades = new JLabel("Actividades: ");
    private JCheckBox chActividad1 = new JCheckBox("Planificacion");
    private JCheckBox chActividad2 = new JCheckBox("Toma de desiciones");
    private JCheckBox chActividad3 = new JCheckBox("Control de calidad");
    private JCheckBox chActividad4 = new JCheckBox("Negociacion");
    private JCheckBox chActividad5 = new JCheckBox("Analisis de datos");

    
    //RADIO BUTTON CON 1 X DEFECTO GERENTE
    
    private JPanel  panelRadioEdificioGerente = new JPanel();
    private JLabel lbEdificio = new JLabel("Edificio: ");
    private JRadioButton rbPrincipal = new JRadioButton("Principal");
    private JRadioButton rbSector1 = new JRadioButton("Sector 1");
    private JRadioButton rbSector2 = new JRadioButton("Sector 2");
    private ButtonGroup rbGrupoEdificio = new ButtonGroup();
    
    private JLabel lbBonificacion = new JLabel("Bonificacion $ (opcional) : ");
    private JTextField textBonificacion = new JTextField(20);
    
    //CHECK BOX EMPLEADO
    
    private JPanel panelRadioDiasLaboralesEmpleados = new JPanel();
    private JLabel lbDiasLaborales = new JLabel("Dias Laborales: ");
    private JCheckBox chLunes = new JCheckBox("Lunes");
    private JCheckBox chMartes = new JCheckBox("Martes");
    private JCheckBox chMiercoles = new JCheckBox("Miercoles");
    private JCheckBox chJueves = new JCheckBox("Jueves");
    private JCheckBox chViernes = new JCheckBox("Viernes");
    private JCheckBox chSabado = new JCheckBox("Sabado");
    private JCheckBox chDomingo = new JCheckBox("Domingo");


    //RADIO BUTTON CON 1 X DEFECTO EMPLEADO 
    
    private JPanel panelRadioTrayectoria = new JPanel();
    private JLabel lbTrayectoria = new JLabel("Trayectoria: ");
    private JRadioButton rbTrainee = new JRadioButton("Trainee");
    private JRadioButton rbJunior = new JRadioButton("Junior");
    private JRadioButton rbSenior = new JRadioButton("Senior");
    private ButtonGroup rbGrupoTrayectoria = new ButtonGroup();
    
    //BOTONES MANDAR
    private JPanel panelBotones = new JPanel();
    private JButton btAceptar = new JButton("Aceptar");
    private JButton btCancelar = new JButton("Cancelar");
    private JPanel panel1 = new JPanel();
    
    //CONSULTA Y ACTUALIZACION 
    
    private JPanel panelConsultaYActualizacion = new JPanel();
    private JLabel lbBuscador = new JLabel("Ingrese el dni para buscar : ");
    private JTextField textBuscador = new JTextField(20);
    private JButton btBuscar = new JButton("Buscar");
    private JButton btEditar = new JButton("Editar");
    private JButton btAnular = new JButton("Anular");
    private JButton btAceptarEditar = new JButton("Aceptar");
    private JButton btCancelarEditar = new JButton("Cancelar");
    private JPanel panelBotonesConsulta = new JPanel();

    //CONSULTA MASIVA 
    
    private JPanel panelConsultaMasiva = new JPanel();
    private JLabel lbPersonalConsultaM = new JLabel("Tipo de personal: ");
    private JTextField textoPersonalConsultaM = new JTextField(20);
    private JLabel lbGeneroConsultaM = new JLabel("Genero: ");
    private JTextField textoGeneroConsultaM = new JTextField(20);
    private JTable tblPersonal;
    
    
    
    public Ventana() {
    	
    	crearVentana();
    	crearMenuBar();
               
    }

    public void crearVentana() {
        ventana.setSize(800, 800);
        ventana.setTitle("Sistema de Empleados");
        ventana.setLayout(new FlowLayout());
        ventana.setVisible(true);
    }
    public void crearMenuBar() {
        
				 //itemOperaciones.addActionListener(this);
		 		 menu.add(menu5);

				 menu.add(itemOperaciones2);
				 itemOperaciones2.addActionListener(this);
				 menu.add(itemOperaciones3);
				 itemOperaciones3.addActionListener(this);
				 barraMenu.add(menu);
				 barraMenu.add(menu2);
				 barraMenu.add(menu3);
				 barraMenu.add(menu4);
				
				 menu5.add(itemOperacionesG);
				 menu5.add(itemOperacionesE);
				 
				 itemOperacionesG.addActionListener(this);;
				 itemOperacionesE.addActionListener(this);
				 
				 
				 //PANEL 1
				 	panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
			     	panel1.setSize(500, 300);
			     	texto.setEditable(false);
			     	panel1.add(texto);
			        panel1.add(labelNombre);
			        panel1.add(textNombre);
			        panel1.add(labelApellido);
			        panel1.add(textApellido);
			        panel1.add(lbDni);
			        panel1.add(textDni);
			        
			        panel1.setVisible(true);
				 
				 //LISTAS DESPLEGABLES 
			    	panelListasDesplegables.setSize(200, 200);
			    	listaDesplegableEstatica = new JComboBox(salariosControlador.devolveTiposSalarios());
			    	listaDesplegableEstatica.addItemListener(this);
			    	panelListasDesplegables.add(lbTipoContrato);
			        panelListasDesplegables.add(listaDesplegableEstatica);
			        listaDesplegableDependiente = new JComboBox<String>();
			        panelListasDesplegables.add(listaDesplegableDependiente);
			        listaDesplegableDependiente.setVisible(false);
			        listaDesplegableEstatica.addItemListener(this);
			        panelListasDesplegables.setLayout(new FlowLayout());
			        
			        panelListasDesplegables.setVisible(true);
			        
			        
			      
			      //LISTAS
			        
			        panelListas.setLayout(new GridLayout(1, 3));
			        panelListas.setSize(200,200);
			        listaDinamica.setVisibleRowCount(4);
			        panelListas.add(lbArea);
			        listaDinamica.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			        listaDinamica.setFixedCellWidth(85);
			        listaDinamica.setFixedCellHeight(15);
			        panelListas.add(scroll);
			        panelListas.add(buttonCopiar);

			        buttonCopiar.addActionListener(this);

			        modelo = new DefaultListModel<String>();
			        listaEstatica = new JList<String>(modelo);
			        listaEstatica.setFixedCellWidth(85);
			        listaEstatica.setFixedCellHeight(15);
			        listaEstatica.setVisibleRowCount(4);
			        listaEstatica.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			        scroll2 = new JScrollPane(listaEstatica);
			        panelListas.add(scroll2);

			        panelListas.setVisible(true);
			      
			      //RADIO BUTTON
			        
			    	panelRadioPersonal.add(lbPersonal);
			    	rbGrupoPersonal.add(rbHombre);
			    	rbGrupoPersonal.add(rbMujer);
			    	panelRadioPersonal.add(rbHombre);
			    	panelRadioPersonal.add(rbMujer);
			    	
			    	panelRadioPersonal.setLayout(new FlowLayout());
			    	
			    	panelRadioPersonal.setVisible(true);
			    	
				 //CHECK BOX GERENTES
			    	panelCheckBoxGerente.add(lbActividades);
			    	panelCheckBoxGerente.add(chActividad1);
			    	panelCheckBoxGerente.add(chActividad2);
			    	panelCheckBoxGerente.add(chActividad3);
			    	panelCheckBoxGerente.add(chActividad4);
			    	panelCheckBoxGerente.add(chActividad5);
			    	
			    	panelCheckBoxGerente.setVisible(false);
				 
			    //RADIO BUTTON CON 1 X DEFECTO GERENTES
			    	
			    	rbGrupoEdificio.add(rbPrincipal);
			    	rbPrincipal.setSelected(true);
			    	rbGrupoEdificio.add(rbSector1);
			    	rbGrupoEdificio.add(rbSector2);
			    	panelRadioEdificioGerente.add(lbEdificio);
			    	panelRadioEdificioGerente.add(rbPrincipal);
			    	panelRadioEdificioGerente.add(rbSector1);
			    	panelRadioEdificioGerente.add(rbSector2);
			    	panelRadioEdificioGerente.add(lbBonificacion);
			    	panelRadioEdificioGerente.add(textBonificacion);
			    	panelRadioEdificioGerente.setVisible(false);
			    	
			    	
			    //CHECK BOX EMPLEADOS EMPLEADOS
			    	
			    	panelRadioDiasLaboralesEmpleados.add(lbDiasLaborales);
			    	panelRadioDiasLaboralesEmpleados.add(chLunes);
			    	panelRadioDiasLaboralesEmpleados.add(chMartes);
			    	panelRadioDiasLaboralesEmpleados.add(chMiercoles);
			    	panelRadioDiasLaboralesEmpleados.add(chJueves);
			    	panelRadioDiasLaboralesEmpleados.add(chViernes);
			    	panelRadioDiasLaboralesEmpleados.add(chSabado);
			    	panelRadioDiasLaboralesEmpleados.add(chDomingo);
			    	panelRadioDiasLaboralesEmpleados.setVisible(false);
			   
			    //RADIO BUTTON CON 1 X DEFECTO EMPLEADOS
			    	
			    	rbGrupoTrayectoria.add(rbTrainee);
			    	rbGrupoTrayectoria.add(rbJunior);
			    	rbGrupoTrayectoria.add(rbSenior);
			    	panelRadioTrayectoria.add(lbTrayectoria);
			    	panelRadioTrayectoria.add(rbTrainee);
			    	panelRadioTrayectoria.add(rbJunior);
			    	panelRadioTrayectoria.add(rbSenior);
			    	rbTrainee.setSelected(true);
			    	panelRadioTrayectoria.setVisible(false);
			    				    	
			   
			   //BOTONES MANDAR
			    	
			    	panelBotones.add(btAceptar);
			    	panelBotones.add(btCancelar);
			    	btAceptar.addActionListener(this);
			    	btCancelar.addActionListener(this);
			    	panelBotones.setVisible(true);
			    
			    //CONSULTA Y ACTUALIZACION
			    	
			    	panelConsultaYActualizacion.add(lbBuscador);
			    	panelConsultaYActualizacion.add(textBuscador);
			    	btBuscar.addActionListener(this);
			    	panelConsultaYActualizacion.setVisible(false);
			    	panelConsultaYActualizacion.setLayout(new BoxLayout(panelConsultaYActualizacion, BoxLayout.Y_AXIS));
			    	btEditar.addActionListener(this);
			    	btAnular.addActionListener(this);
			    	panelBotonesConsulta.add(btBuscar);
			    	panelBotonesConsulta.add(btEditar);
			    	panelBotonesConsulta.add(btAnular);
			    	panelBotonesConsulta.setLayout(new FlowLayout());
			    	panelConsultaYActualizacion.add(panelBotonesConsulta);
			    	btEditar.setVisible(false);
			    	btAnular.setVisible(false);
			    	panelBotonesConsulta.add(btAceptarEditar);
			    	panelBotonesConsulta.add(btCancelarEditar);
			    	btAceptarEditar.addActionListener(this);
			    	btAceptarEditar.setVisible(false);
			    	btCancelarEditar.addActionListener(this);
			    	btCancelarEditar.setVisible(false);
			    	ventana.add(panelConsultaYActualizacion);

			    //CONSULTA MASIVA 
			    	
			    	panelConsultaMasiva.add(lbPersonalConsultaM);
			    	panelConsultaMasiva.add(textoPersonalConsultaM);
			    	panelConsultaMasiva.add(lbGeneroConsultaM);
			    	panelConsultaMasiva.add(textoGeneroConsultaM);
			    	String[] titulosColumnas = {"Personal","Nombre","Apellido","DNI","Tipo Contrato","Sueldo","Genero"};
			    	String[][] valores = personalControlador.devolverMatrizPersonal();
			    	tblPersonal = new JTable(valores, titulosColumnas);
			    	JScrollPane scrollPane = new JScrollPane(tblPersonal);
			    	panelConsultaMasiva.add(lbPersonalConsultaM);
			    	panelConsultaMasiva.add(textoPersonalConsultaM);
			    	panelConsultaMasiva.add(lbGeneroConsultaM);
			    	panelConsultaMasiva.add(textoGeneroConsultaM);
			    	panelConsultaMasiva.add(scrollPane);  

			    	panelConsultaMasiva.setVisible(false);
			    	ventana.add(panelConsultaMasiva);
			    	
				 menu3.add(itemSistema);
				 panelIngreso.add(panel1);
				 panelIngreso.add(panelListasDesplegables);
				 panelIngreso.add(panelListas);
				 panelIngreso.add(panelRadioPersonal);
				 panelIngreso.add(panelCheckBoxGerente);
				 panelIngreso.add(panelRadioEdificioGerente);
				 panelIngreso.add(panelRadioDiasLaboralesEmpleados);
				 panelIngreso.add(panelRadioTrayectoria);
				 panelIngreso.setVisible(false);
				 panelIngreso.setLayout(new BoxLayout(panelIngreso, BoxLayout.Y_AXIS));

				 
				 
				 panelIngreso.add(panelBotones);
				 ventana.add(panelIngreso);
			     ventana.setJMenuBar(barraMenu);
			     ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    

    
    public void OperacionesIngresoGerentes() {
    	panelCheckBoxGerente.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	if(e.getSource() == itemOperaciones3) {
    		panelConsultaMasiva.setVisible(true);
    	}
    	if(e.getSource() == btAnular) {
    		String dniBuscar = textBuscador.getText();
    		int dni = Integer.parseInt(dniBuscar);
    		personalControlador.borrarPersonal(dni);
    	}
    	if(e.getSource() == btAceptarEditar) {
    		aceptarIngreso(true);
    	}
    	if(e.getSource() == btCancelarEditar) {
    		verEditar(false);
    	}
    	if(e.getSource() == btEditar) {
    		
    		verEditar(true);

    	}
    	if(e.getSource() ==itemOperaciones2) {
    		panelConsultaYActualizacion.setVisible(true);
    		panelIngreso.setVisible(false);
    	}
    	if(e.getSource() == btBuscar) {
    		String dniBuscar = textBuscador.getText();
    		if(dniBuscar.isEmpty()) {
    			return;
    		}
    		int dni = Integer.parseInt(dniBuscar);
    		int [] encontro =  personalControlador.encontrarPersonal(dni);
    		if(encontro[0] == 1 || encontro[0] == 2) {
    			ArrayList<String> datos = personalControlador.obtenerDatosPersonal(encontro[1]);
    			ArrayList<String> areas = personalControlador.obtenerAreasPersonal(encontro[1]);
    			texto.setVisible(false);
    			lbDni.setVisible(false);
    			textNombre.setText(datos.get(0));
    			textNombre.setEditable(false);
       			textApellido.setText(datos.get(1));
    			textApellido.setEditable(false);
    			textDni.setVisible(false);
    			textDni.setText(dniBuscar);
    			listaDesplegableEstatica.setSelectedItem(datos.get(4));
    			listaDesplegableEstatica.setEnabled(false);
    			listaDesplegableDependiente.setSelectedItem(datos.get(5));
    			listaDesplegableDependiente.setEnabled(false);
    			scroll.setVisible(false);
    			listaEstatica.setVisible(true);
    			buttonCopiar.setVisible(false);	
    			btAceptar.setVisible(false);
    			btCancelar.setVisible(false);
    			modelo.clear();
    			btEditar.setVisible(true);
    			btAnular.setVisible(true);
    			for(String s : areas) {
    				System.out.println(s);
    				modelo.addElement(s);
    			}
    			if(datos.get(3).equals("Hombre")) {
    				rbHombre.setSelected(true);
    				rbMujer.setVisible(false);
    			}
    			else {
    				rbMujer.setSelected(true);
    				rbHombre.setVisible(false);
    			}
    			if(encontro[0] == 1 ) {
    				ArrayList<String> actividades = personalControlador.obtenerActividadesGerente(encontro[1]);
    				String edificio = personalControlador.obtenerEdificioGerente(encontro[1]);
    				String bonificacion = personalControlador.obtenerBonificacionGerente();
    	            panelRadioDiasLaboralesEmpleados.setVisible(false);
    	            panelRadioTrayectoria.setVisible(false);
    	            panelIngreso.setVisible(true);   
    	            panelCheckBoxGerente.setVisible(true);
    	            panelRadioEdificioGerente.setVisible(true);
    	            chActividad1.setEnabled(false);
    	            chActividad2.setEnabled(false);
    	            chActividad3.setEnabled(false);
    	            chActividad4.setEnabled(false);
    	            chActividad5.setEnabled(false);
    	            chActividad1.setVisible(false);
    	            chActividad2.setVisible(false);
    	            chActividad3.setVisible(false);
    	            chActividad4.setVisible(false);
    	            chActividad5.setVisible(false);

    	            for(String s : actividades) {
    	            	
    	            	switch(s) {
    	            	case "Planificacion":
    	            		chActividad1.setSelected(true);
    	            		chActividad1.setVisible(true);
    	            		break;
     	            	case "Toma de desiciones":
    	            		chActividad2.setSelected(true);
    	            		chActividad2.setVisible(true);
    	            		break;
     	            	case "Control de calidad":
    	            		chActividad3.setSelected(true);
    	            		chActividad3.setVisible(true);
     	            		break;
     	            	case "Negociacion":
    	            		chActividad4.setSelected(true);
    	            		chActividad4.setVisible(true);
    	            		break;
     	            	case "Analisis de datos":
     	            		chActividad5.setSelected(true);
     	            		chActividad5.setVisible(true);
     	            		break;
    	            	}
    	            }
    	            rbPrincipal.setVisible(false);
    	            rbSector1.setVisible(false);
    	            rbSector2.setVisible(false);
    	            if(edificio.equals("Principal")) {
    	            	rbPrincipal.setVisible(true);
    	            	rbPrincipal.setSelected(true);
    	            }
    	            else if(edificio.equals("Sector 1")) {
    	            	rbSector1.setVisible(true);
    	            	rbSector1.setSelected(true);
    	            }
    	            else if(edificio.equals("Sector 2")) {
    	            	rbSector2.setVisible(true);
    	            	rbSector2.setSelected(true);
    	            }
    	            textBonificacion.setText(bonificacion);
    	            textBonificacion.setEditable(false);
    	            
    			}
    			else if(encontro[0] == 2 ) {
    				ArrayList<String> diasLaborales = personalControlador.obtenerDiasLaboralesEmpleado(encontro[1]);
    				String trayectoria = personalControlador.obtenerTrayectoriaEmpleado(encontro[1]);
    	            panelIngreso.setVisible(true);   
    	            panelRadioDiasLaboralesEmpleados.setVisible(true);
    	            panelRadioTrayectoria.setVisible(true);
    				chLunes.setVisible(false); chLunes.setEnabled(false);
    				chMartes.setVisible(false); chMartes.setEnabled(false);
    				chMiercoles.setVisible(false); chMiercoles.setEnabled(false);
    				chJueves.setVisible(false); chJueves.setEnabled(false);
    				chViernes.setVisible(false); chViernes.setEnabled(false);
    				chSabado.setVisible(false); chSabado.setEnabled(false);
    				chDomingo.setVisible(false); chDomingo.setEnabled(false);

    	            for(String s : diasLaborales) {
    	            	
    	            	switch(s) {
    	            	case "Lunes":
    	            		chLunes.setSelected(true);
    	            		chLunes.setVisible(true);
    	            		break;
     	            	case "Martes":
     	            		chMartes.setSelected(true);
     	            		chMartes.setVisible(true);
    	            		break;
     	            	case "Miercoles":
     	            		chMiercoles.setSelected(true);
     	            		chMiercoles.setVisible(true);
     	            		break;
     	            	case "Jueves":
     	            		chJueves.setSelected(true);
     	            		chJueves.setVisible(true);
    	            		break;
     	            	case "Viernes":
     	            		chViernes.setSelected(true);
     	            		chViernes.setVisible(true);
     	            		break;
     	            	case "Sabado":
     	            		chSabado.setSelected(true);
     	            		chSabado.setVisible(true);
     	            		break;
     	            	case "Domingo":
     	            		chDomingo.setSelected(true);
     	            		chDomingo.setVisible(true);
     	            		break;
    	            	}
    	            }
    	            rbTrainee.setVisible(false);
    	            rbJunior.setVisible(false);
    	            rbSenior.setVisible(false);
    	            if(trayectoria.equals("Trainee")) {
    	            	rbTrainee.setVisible(true);
    	            	rbTrainee.setSelected(true);
    	            }
    	            else if(trayectoria.equals("Junior")) {
    	            	rbJunior.setVisible(true);
    	            	rbJunior.setSelected(true);
    	            }
    	            else if(trayectoria.equals("Senior")) {
    	            	rbSenior.setVisible(true);
    	            	rbSenior.setSelected(true);
    	            }
    			}
    			
    		}

    		else {
    			JOptionPane.showMessageDialog(null, "No se encontro una persona con ese dni");
    			textBuscador.setText("");
    		}
    	}
    	
    	if(e.getSource() == itemOperacionesG) {
    		limpiarCamposIngreso();
    		 habilitarCamposIngreso();
    		panelConsultaYActualizacion.setVisible(false);

    		personal="gerente";
            panelRadioDiasLaboralesEmpleados.setVisible(false);
            panelRadioTrayectoria.setVisible(false);
            panelIngreso.setVisible(true);   
            panelCheckBoxGerente.setVisible(true);
            panelRadioEdificioGerente.setVisible(true);

    	}
    	else if(e.getSource() == itemOperacionesE) {
    		limpiarCamposIngreso();
    		 habilitarCamposIngreso() ;
    		personal="empleado";
    		panelConsultaYActualizacion.setVisible(false);

            panelCheckBoxGerente.setVisible(false);
            panelRadioEdificioGerente.setVisible(false);
            panelIngreso.setVisible(true);  
            panelRadioDiasLaboralesEmpleados.setVisible(true);
            panelRadioTrayectoria.setVisible(true);
    	}
    	
        if (e.getSource() == buttonCopiar) {
            Object[] elementosSeleccionados = listaDinamica.getSelectedValuesList().toArray();
            for (Object el : elementosSeleccionados) {
                int a = 0;
                for (Object m : modelo.toArray()) {
                    if (m.equals((String) el)) {
                        a = 1;
                        break;
                    }
                }
                if (a == 1) {
                    continue;
                } else {
                    modelo.addElement((String) el);

                }

            }
        }
        if(e.getSource() == btAceptar) {
        	aceptarIngreso(false);
        }
        if(e.getSource() == btCancelar) {
        	limpiarCamposIngreso();
        }
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) {
    	

    	
    	if(e.getSource() == listaDesplegableEstatica) {
    		String seleccionado = (String)listaDesplegableEstatica.getSelectedItem();
    		String [] contenidoDependiente = salariosControlador.devolverSalarios(seleccionado);
    		listaDesplegableDependiente.removeAllItems();
    		for(String s : contenidoDependiente) {
    			listaDesplegableDependiente.addItem(s);
    		}
    		listaDesplegableDependiente.setVisible(true);   		    		
    		
    	}
    }
    
    public void aceptarIngreso(boolean editado) {
  
    	String nombre = textNombre.getText();
    	String apellido = textApellido.getText();
    	String tipoContrato = (String)listaDesplegableEstatica.getSelectedItem();
    	String sueldo = (String)listaDesplegableDependiente.getSelectedItem();
    	String dni = textDni.getText();
    	String bonificacion = textBonificacion.getText();
    	ArrayList<String> areas = new ArrayList<String>();
    	for(Object o : modelo.toArray()) {
    		areas.add((String) o);
    	}

    	String [] areasArray = areas.toArray(new String[0]);
    	
    	String genero = "";
    	if(rbHombre.isSelected()) {
    		genero="hombre";
    	}
    	else if(rbMujer.isSelected()) {
    		genero="mujer";
    	}
		if(nombre.isEmpty()) {JOptionPane.showMessageDialog(null, "El nombre debe estar completo");return;};
		if(apellido.isEmpty()) {JOptionPane.showMessageDialog(null, "El apellido debe estar completo");return;};
		if(genero.isEmpty()) {JOptionPane.showMessageDialog(null, "Debes ingresar un genero");return;};
    	if(personal.equals("gerente")) {
    		ArrayList<String> actividades = new ArrayList<String>();
    		if(chActividad1.isSelected()) actividades.add(chActividad1.getText());
    		if(chActividad2.isSelected()) actividades.add(chActividad2.getText());
    		if(chActividad3.isSelected()) actividades.add(chActividad3.getText());
    		if(chActividad4.isSelected()) actividades.add(chActividad4.getText());
    		if(chActividad5.isSelected()) actividades.add(chActividad5.getText());
    		
    		
    		
    		String edificio = "";
    		if(rbPrincipal.isSelected()) edificio=rbPrincipal.getText();
    		
    		else if(rbSector1.isSelected()) edificio=rbSector1.getText();    		
    		
    		else if(rbSector2.isSelected())edificio=rbSector2.getText();
    		
    
    		if(nombre.isEmpty() || apellido.isEmpty() || genero.isEmpty() || areasArray.length == 0 || genero.isEmpty() || actividades.size() == 0 || edificio.isEmpty()) {
    			
    			JOptionPane.showMessageDialog(null, "Datos Incompletos");
    			return;
    		}
    		try {
        		personalControlador.cargarPersonal(nombre, apellido, tipoContrato, sueldo, areas, actividades, edificio, genero, dni, bonificacion,1, editado);

    		}
    		catch(ExcepcionPropia e) {
    			JOptionPane.showMessageDialog(null, e.getMessage());
    		}
    		JOptionPane.showMessageDialog(null, "Finalizado correctamente!");
    		limpiarCamposIngreso();
    		
    	}
    	else if(personal.equals("empleado")) {
    		ArrayList<String> diasLaborales = new ArrayList<String>();
    		if(chLunes.isSelected()) diasLaborales.add(chLunes.getText());
    		if(chMartes.isSelected()) diasLaborales.add(chMartes.getText());
    		if(chMiercoles.isSelected()) diasLaborales.add(chMiercoles.getText());
    		if(chJueves.isSelected()) diasLaborales.add(chJueves.getText());
    		if(chViernes.isSelected()) diasLaborales.add(chViernes.getText());
    		if(chSabado.isSelected()) diasLaborales.add(chSabado.getText());
    		if(chDomingo.isSelected()) diasLaborales.add(chDomingo.getText());
    	
    		String trayectoria = "";
    		if(rbTrainee.isSelected())trayectoria=rbTrainee.getText();
    		else if(rbJunior.isSelected())trayectoria=rbJunior.getText();
    		else if(rbSenior.isSelected())trayectoria=rbSenior.getText();
    		
    		try {
    			
        		personalControlador.cargarPersonal(nombre, apellido, tipoContrato, sueldo, areas, diasLaborales, trayectoria, genero, dni,bonificacion, 0, editado);

    		}
    		catch(ExcepcionPropia e) {
    			JOptionPane.showMessageDialog(null, e.getMessage());
    		}
    		JOptionPane.showMessageDialog(null, "Empleado agregado correctamente!");
    		limpiarCamposIngreso();
    	}
    	
    }
    public void limpiarCamposIngreso() {
    	textNombre.setText("");
    	textApellido.setText("");
    	listaDesplegableEstatica.setSelectedIndex(0);
    	textDni.setText("");
    	textBonificacion.setText("");
    	modelo.clear();
    	listaEstatica.clearSelection();
    	rbHombre.setSelected(false);
    	rbMujer.setSelected(false);
    	chActividad1.setSelected(false);
    	chActividad2.setSelected(false);
    	chActividad3.setSelected(false);
    	chActividad4.setSelected(false);
    	chActividad5.setSelected(false);
    	rbPrincipal.setSelected(true);
    	rbSector1.setSelected(false);
    	rbSector2.setSelected(false);
    	chLunes.setSelected(false);
    	chMartes.setSelected(false);
    	chMiercoles.setSelected(false);
    	chJueves.setSelected(false);
    	chViernes.setSelected(false);
    	chSabado.setSelected(false);
    	chDomingo.setSelected(false);
    	rbTrainee.setSelected(true);
    	rbJunior.setSelected(false);
    	rbSenior.setSelected(false);
    }
    public void habilitarCamposIngreso() {
        panelIngreso.setVisible(false);   
        
			texto.setVisible(true);
			lbDni.setVisible(true);
			textNombre.setEditable(true);
			textNombre.setText("");
   			textApellido.setText("");
			textApellido.setEditable(true);
			textDni.setVisible(true);
			listaDesplegableEstatica.setEnabled(true);
			listaDesplegableEstatica.setSelectedIndex(0);
			listaDesplegableDependiente.setEnabled(true);
			textBuscador.setText("");
            chActividad1.setEnabled(true);
            chActividad2.setEnabled(true);
            chActividad3.setEnabled(true);
            chActividad4.setEnabled(true);
            chActividad5.setEnabled(true);
            chActividad1.setVisible(true);
            chActividad2.setVisible(true);
            chActividad3.setVisible(true);
            chActividad4.setVisible(true);
            chActividad5.setVisible(true);
            chActividad1.setSelected(false);
            chActividad2.setSelected(false);
            chActividad3.setSelected(false);
            chActividad4.setSelected(false);
            chActividad5.setSelected(false);
            rbPrincipal.setVisible(true); rbPrincipal.setSelected(true);
            rbSector1.setVisible(true); rbSector2.setVisible(true);
			scroll.setVisible(true);
			buttonCopiar.setVisible(true);	
			btAceptar.setVisible(true);
			btCancelar.setVisible(true);
			rbHombre.setVisible(true);
			rbHombre.setSelected(false);
			rbMujer.setVisible(true);
			rbMujer.setSelected(false);
			chLunes.setVisible(true); chLunes.setEnabled(true);
			chMartes.setVisible(true); chMartes.setEnabled(true);
			chMiercoles.setVisible(true); chMiercoles.setEnabled(true);
			chJueves.setVisible(true); chJueves.setEnabled(true);
			chViernes.setVisible(true); chViernes.setEnabled(true);
			chSabado.setVisible(true); chSabado.setEnabled(true);
			chDomingo.setVisible(true); chDomingo.setEnabled(true);
			rbTrainee.setVisible(true);rbTrainee.setSelected(true);
			rbJunior.setVisible(true);rbJunior.setSelected(false);
			rbSenior.setVisible(true);rbSenior.setSelected(false);
			textBonificacion.setEditable(true);;
			btBuscar.setVisible(true);
    }
    public void verEditar(boolean b) {
		textNombre.setEditable(b);
		textApellido.setEditable(b);
		lbDni.setVisible(b);
		textDni.setEditable(false); //POR EL BIEN DE MI PROGRAMA NO SE PUEDE EDITAR
		textDni.setVisible(b);
		listaDesplegableEstatica.setEnabled(b);
		listaDesplegableDependiente.setEnabled(b);
		scroll.setVisible(b);
		buttonCopiar.setVisible(b);	
		rbMujer.setVisible(b);
		rbHombre.setVisible(b);
		chLunes.setVisible(b); chLunes.setEnabled(b);
		chMartes.setVisible(b); chMartes.setEnabled(b);
		chMiercoles.setVisible(b); chMiercoles.setEnabled(b);
		chJueves.setVisible(b); chJueves.setEnabled(b);
		chViernes.setVisible(b); chViernes.setEnabled(b);
		chSabado.setVisible(b); chSabado.setEnabled(b);
		chDomingo.setVisible(b); chDomingo.setEnabled(b);
		rbTrainee.setVisible(b);
		rbJunior.setVisible(b);
		rbSenior.setVisible(b);
		textBonificacion.setEditable(b);
        chActividad1.setEnabled(b);
        chActividad2.setEnabled(b);
        chActividad3.setEnabled(b);
        chActividad4.setEnabled(b);
        chActividad5.setEnabled(b);
        chActividad1.setVisible(b);
        chActividad2.setVisible(b);
        chActividad3.setVisible(b);
        chActividad4.setVisible(b);
        chActividad5.setVisible(b);
        btAceptarEditar.setVisible(b);
        btCancelarEditar.setVisible(b);
    }


}
