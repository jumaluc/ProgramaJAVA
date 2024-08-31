package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import controlador.*;

public class VentanaOperacionesIngreso implements ActionListener, ItemListener {

    // CONTROLADOR
	private String personal;
	private SalariosControlador salariosControlador = new SalariosControlador();
    private AreasControlador areasControlador = new AreasControlador();

    // VENTANA
    private JFrame ventana = new JFrame("Ingreso");
    private JTextArea texto = new JTextArea("Ventana para el ingreso del Personal, es importante que \n"
            + "tenga en cuenta solo se pueden ingresar gerentes o empleados \n"
            + "y que hay que especificarlo. Ingresar nombre, apellido, dni, areas involucradas, contrato");

    private JLabel labelNombre = new JLabel("Nombre: ");
    private JTextField textNombre = new JTextField(20);
    private JLabel labelApellido = new JLabel("Apellido: ");
    private JTextField textApellido = new JTextField(20);

    
    //LISTAS DESPLEGABLES
    private JPanel panelListasDesplegables = new JPanel();
    private JLabel lbTipoContrato = new JLabel("Tipo de Contrato : ");
    private JComboBox<String> listaDesplegableEstatica;
    private JComboBox<String> listaDesplegableDependiente;

    //LISTAS
    private JPanel panelListas = new JPanel();
    private DefaultListModel<String> modelo;
    private JList<String> listaDinamica = new JList<String>(areasControlador.devolverAreasNombre());
    private JButton buttonCopiar = new JButton("Copiar-->");
    private JList<String> listaEstatica;

    //RADIO BUTTON
    private JPanel panelRadioPersonal = new JPanel();
    private JLabel lbPersonal = new JLabel("Personal : ");
    private JRadioButton rbGerente = new JRadioButton("Gerente");
    private JRadioButton rbEmpleado = new JRadioButton("Empleado");
    private ButtonGroup rbGrupoPersonal = new ButtonGroup(); 
    
    //BOTONES DE OPCIONES
    
    
    private JPanel panel1 = new JPanel();

    private JButton buttonAceptar = new JButton("Aceptar");
    private JButton buttonCancelar = new JButton("Cancelar");

    public VentanaOperacionesIngreso(String personal) {

    	this.personal = personal;
    	
        creacionVentana();
        creacionPanel1();
        creacionPanelListas();
        creacionPanelListasDesplegables(personal);
        creacionPanelRadioPersonal();

        

    }

    public void creacionVentana() {
        ventana.setSize(800, 800);
        ventana.setTitle("Ingreso");
        
        ventana.setLayout(new BoxLayout(ventana.getContentPane(), BoxLayout.Y_AXIS)); 
        ventana.setVisible(true);
    }

    public void creacionPanel1() {

        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel1.setSize(500, 300);
        texto.setEditable(false);
        panel1.add(texto);

        panel1.add(labelNombre);
        panel1.add(textNombre);
        panel1.add(labelApellido);
        panel1.add(textApellido);

        ventana.add(panel1);

    }

    public void creacionPanelListasDesplegables(String personal) {
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
        ventana.add(panelListasDesplegables); 
    }

    public void creacionPanelListas() {
        panelListas.setLayout(new GridLayout(1, 3));
        panelListas.setSize(200,200);
        listaDinamica.setVisibleRowCount(4);
        listaDinamica.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scroll = new JScrollPane(listaDinamica);
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
        JScrollPane scroll2 = new JScrollPane(listaEstatica);
        panelListas.add(scroll2);

        ventana.add(panelListas); 
    }
    public void creacionPanelRadioPersonal() {
    	
    	panelRadioPersonal.add(lbPersonal);
    	rbGrupoPersonal.add(rbGerente);
    	rbGrupoPersonal.add(rbEmpleado);
    	panelRadioPersonal.add(rbGerente);
    	panelRadioPersonal.add(rbEmpleado);
    	
    	panelRadioPersonal.setLayout(new FlowLayout());
    	ventana.add(panelRadioPersonal);
    	
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == buttonCopiar) {
            Object[] elementosSeleccionados = listaDinamica.getSelectedValuesList().toArray();
            for (Object el : elementosSeleccionados) {
                int a = 0;
                for (Object m : modelo.toArray()) {
                    if (m == (String) el) {
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
}
