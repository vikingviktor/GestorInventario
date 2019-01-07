package com.tap.GestorInventario;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Set;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

	int i = 0;
	
	Inventario inventario = new Inventario();
	Inventario inventario2 = new Inventario();
	
	
	
	Date in = new Date();
	LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	HorizontalSplitPanel splitPanel = new HorizontalSplitPanel();
	Grid<Producto> grid = new Grid<Producto>();
	Grid<Producto> grid2 = new Grid<Producto>();
	Grid<Transaccion> grid3 = new Grid<Transaccion>();
	
	Label titulo1 = new Label("INVENTARIO (PROVEEDOR)");
	Label titulo2 = new Label("FÁBRICA (CLIENTE)");
	Label titulo3 = new Label("TRANSACCIONES");
	Label relleno1 = new Label("");
	Label relleno2 = new Label("");
	Label label = new Label("Configura tu presupuesto inicial en euros: ");
	Label combinaciones= new Label(
    		"Las combinaciones posibles son:\n"+
    		"Flecha: piedra+pluma+palo\n"+
    		"Arco: palo+palo+cuerda\n"+
    		"Espada: palo+hierro+hierro\n",
    		ContentMode.PREFORMATTED);
	
	
	private Producto selectedProduct; 

    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	
    	HorizontalLayout horizontalLayout = new HorizontalLayout();
    	
    	Window subWindow = new Window("Detalles del Producto");
    	Window subWindow2 = new Window("Opciones: ");
    	
    	subWindow.setHeight("340px");
    	subWindow.setWidth("250px");
    	
    	
        VerticalLayout subContent = new VerticalLayout();
        VerticalLayout subContent2 = new VerticalLayout();
        
        Label labelValueEur = new Label();
        Label labelName = new Label();
        Label labelValueDol = new Label();
        Label labelUnidades = new Label();
        Button buttonDelete = new Button("Eliminar Producto");
        Button buttonAdd = new Button("Añadir un Producto");
        
        buttonDelete.addClickListener(e -> {
        	
        	if (selectedProduct.getNumber() == 0) {
        		if (i == 0) {
        			Notification.show("¿Seguro que quieres eliminar el producto completamente? (Presiona otra vez eliminar para borrar)");
        			i = 1;
        		}
        		else {
        			inventario.deleteProduct(selectedProduct);
		        	grid.setItems(inventario.getProducts());
		        	removeWindow(subWindow);
		        	i = 0;
        		}
        		
        	}
        	else
        	{
        		
    			selectedProduct.setNumber(selectedProduct.getNumber()-1);
    			grid.setItems(inventario.getProducts());
    		
        	}
        	
        });
        
        buttonAdd.addClickListener(e -> {
        	
        	
			selectedProduct.setNumber(selectedProduct.getNumber()+1);
			grid.setItems(inventario.getProducts());
		
        });
        
        
    	
    	/* TABLE */
    	
    	
    	grid.addColumn(Producto::getName).setCaption("Nombre");
    	grid.addColumn(Producto::getValueEur).setCaption("Valor Euros");
    	grid.addColumn(Producto::getValueDol).setCaption("Valor Dolares");
    	grid.addColumn(Producto::getNumber).setCaption("Unidades");
    	grid.setSelectionMode(SelectionMode.SINGLE);
    	
    	grid.addItemClickListener(event -> {
    		
    		i = 0;
    		selectedProduct = event.getItem();
    		
        	labelName.setValue(selectedProduct.getName());
        	labelValueEur.setValue(selectedProduct.getValueEur());
        	labelValueDol.setValue(String.valueOf(selectedProduct.getValueDol()));
        	labelUnidades.setValue(String.valueOf(selectedProduct.getNumber()));
        	
        	removeWindow(subWindow2);
        	removeWindow(subWindow);
        	addWindow(subWindow);
        	subWindow.setPositionX(560);
        	subWindow.setPositionY(300);
        	
    	});
    	
    	
    	// BAG
    	
    	grid2.addColumn(Producto::getName).setCaption("Nombre");
    	grid2.addColumn(Producto::getValueEur).setCaption("Valor Euros");
    	grid2.addColumn(Producto::getValueDol).setCaption("Valor Dolares");
    	grid2.addColumn(Producto::getNumber).setCaption("Unidades");
    	grid2.setSelectionMode(SelectionMode.SINGLE);
    	
    	grid2.addItemClickListener(event -> {
    		
    		selectedProduct = event.getItem();
    		
        	labelName.setValue(selectedProduct.getName());
        	labelValueEur.setValue(selectedProduct.getValueEur());
        	labelValueDol.setValue(String.valueOf(selectedProduct.getValueDol()));
        	labelUnidades.setValue(String.valueOf(selectedProduct.getNumber()));
        	
        	
        	removeWindow(subWindow);
        	addWindow(subWindow2);
        	subWindow2.setPositionX(560);
        	subWindow2.setPositionY(300);
    	});
    	
    	// Grid Transacciones
    	
    	grid3.addColumn(Transaccion::getCodTrans).setCaption("Cod. Transaccion");
    	grid3.addColumn(Transaccion::getFecha).setCaption("Fecha");
    	grid3.addColumn(Transaccion::getCoste).setCaption("Coste");
    	grid3.setSelectionMode(SelectionMode.SINGLE);
    	
    	
    	
    	
    	/* FORM */
    	
    	
    	FormLayout formLayout = new FormLayout();
    	
    	TextField textFieldName = new TextField("Nombre del Producto");
    	TextField textFieldEur = new TextField("Valor en Euros");
    	Button buttonAddProduct = new Button("Crear nuevo producto");
    	Button buttonBuyProduct = new Button("Comprar producto");
        Button buttonSellProduct = new Button("Vender producto");
        Button buttonCombine = new Button("Crear combinando materiales");
        
        TextField textFieldMoney = new TextField("Presupuesto: ");
        	
    	buttonAddProduct.addClickListener(e -> {
    		
    		Producto p = new Producto(
    				textFieldName.getValue(),
    				textFieldEur.getValue()
    				);
    		
    		
    		inventario.addProduct(p);
    		inventario2.addProduct(p);
    		
    		textFieldName.clear();
    		textFieldEur.clear();
    		
    		//grid.setItems(Inventario.getInstance().getProducts());
    		
    		grid.setItems(inventario.getProducts());
        	grid2.setItems(inventario2.getProducts());
    		
    		Notification.show("Producto creado... ");
    		
    	});
    	
    	/* BUTTON2 */
       	
		
    	buttonBuyProduct.addClickListener(e2 -> {
    		
    		if (selectedProduct.getNumber() == 0) {
    			Notification.show("Producto fuera de stock... Crea más unidades");
        	}
    		else {
    			if (inventario2.getPresupuesto() > Double.parseDouble(selectedProduct.getValueEur())) {
    				
    				Transaccion transaccion = new Transaccion();
    				
    				selectedProduct.setNumber(selectedProduct.getNumber()-1);
    				inventario2.setPresupuesto(inventario2.getPresupuesto() - Double.parseDouble(selectedProduct.getValueEur()));
    				
    				inventario2.actualizarProductos(inventario2.addUnit(selectedProduct.getName(), inventario2.getProducts()));
    				transaccion.setCodTrans("Compra");
    				transaccion.setFecha(ldt.format(formatter));
    				transaccion.setCoste(Double.parseDouble(selectedProduct.getValueEur()));
    				inventario2.addTransaction(transaccion);
    				
	        		grid.setItems(inventario.getProducts());
	        		grid2.setItems(inventario2.getProducts());
	        		grid3.setItems(inventario2.getTransactions());
	        		
	        		textFieldMoney.setValue(Double.toString(inventario2.getPresupuesto()));
	        		removeWindow(subWindow);
	        		Notification.show("Producto comprado...");
    			}
    			else
    				Notification.show("No hay suficiente presupuesto...");
    			
    		}
    			
    		
    	});
    	
    	buttonSellProduct.addClickListener(e2 -> {
    		
    		if (selectedProduct.getNumber() == 0) {
    			Notification.show("No tienes unidades de este producto... Compra más");
        	}
    		else {
    			
    			Transaccion transaccion = new Transaccion();
				selectedProduct.setNumber(selectedProduct.getNumber()-1);
				inventario2.setPresupuesto(inventario2.getPresupuesto() + Double.parseDouble(selectedProduct.getValueEur()));
				
				transaccion.setCodTrans("Venta");
				transaccion.setFecha(ldt.format(formatter));
				transaccion.setCoste(Double.parseDouble(selectedProduct.getValueEur()));
				inventario2.addTransaction(transaccion);
				
        		grid2.setItems(inventario2.getProducts());
        		grid3.setItems(inventario2.getTransactions());
        		
        		textFieldMoney.setValue(Double.toString(inventario2.getPresupuesto()));
        		removeWindow(subWindow2);
        		Notification.show("Producto vendido...");
    		}
    			
    		
    	});
    	
    	
    	buttonCombine.addClickListener(e2 -> {
    		
    		if (selectedProduct.getName() == "Flecha") {
    			if (inventario2.getUnit("Palo", inventario2.getProducts()).getNumber() > 0 || inventario2.getUnit("Pluma", inventario2.getProducts()).getNumber() > 0 || inventario2.getUnit("Piedra", inventario2.getProducts()).getNumber() > 0){
    				selectedProduct.setNumber(selectedProduct.getNumber()+1);
	    			inventario2.actualizarProductos(inventario2.substractUnit("Piedra", inventario2.getProducts()));
	    			inventario2.actualizarProductos(inventario2.substractUnit("Palo", inventario2.getProducts()));
	    			inventario2.actualizarProductos(inventario2.substractUnit("Pluma", inventario2.getProducts()));
	    			Notification.show("Flecha creada...");
	    			grid2.setItems(inventario2.getProducts());
    			}
				
        	}
    		else if (selectedProduct.getName() == "Arco") {
    			if (inventario2.getUnit("Palo", inventario2.getProducts()).getNumber() > 1 || inventario2.getUnit("Cuerda", inventario2.getProducts()).getNumber() > 0){
    				selectedProduct.setNumber(selectedProduct.getNumber()+1);
	    			inventario2.actualizarProductos(inventario2.substractUnit("Palo", inventario2.getProducts()));
	    			inventario2.actualizarProductos(inventario2.substractUnit("Palo", inventario2.getProducts()));
	    			inventario2.actualizarProductos(inventario2.substractUnit("Cuerda", inventario2.getProducts()));
	    			Notification.show("Arco creado...");
	    			grid2.setItems(inventario2.getProducts());
    			}
				
        	}
    		else if (selectedProduct.getName() == "Espada") {
    			if (inventario2.getUnit("Palo", inventario2.getProducts()).getNumber() > 0 || inventario2.getUnit("Hierro", inventario2.getProducts()).getNumber() > 1){
    				selectedProduct.setNumber(selectedProduct.getNumber()+1);
	    			inventario2.actualizarProductos(inventario2.substractUnit("Hierro", inventario2.getProducts()));
	    			inventario2.actualizarProductos(inventario2.substractUnit("Palo", inventario2.getProducts()));
	    			inventario2.actualizarProductos(inventario2.substractUnit("Hierro", inventario2.getProducts()));
	    			Notification.show("Espada creada...");
	    			grid2.setItems(inventario2.getProducts());
    			}
				
        	}
    		else {
				
        		Notification.show("No existen combinaciones para este producto o no tienes los materiales necesarios...");
    		}
    			
    		
    	});
    		
    	
    	
    	formLayout.addComponents(
    			relleno1,
    			textFieldName,
    	    	textFieldEur,
    	    	buttonAddProduct
    	);
    	
    	// Presupuesto
    	
    	FormLayout formLayout2 = new FormLayout();
    	
    	
    	
    	Button buttonMoney = new Button("Asignar Presupuesto");
    	
    	buttonMoney.addClickListener(e -> {
    		
    		inventario2.setPresupuesto(Double.parseDouble(textFieldMoney.getValue()));		
    		
    		textFieldMoney.setReadOnly(true);
    		textFieldMoney.setValue(Double.toString(inventario2.getPresupuesto()));
    		
    		Notification.show("Presupuesto inicializado... ");
    		
    	});
    	
    	formLayout2.addComponents(
    			relleno2,
    			label,
    			textFieldMoney,
    			buttonMoney
    	);
    	
    	// Ventanas subwindow
    	subContent.addComponents(labelName, labelValueEur, labelValueDol, labelUnidades, buttonDelete, buttonAdd, buttonBuyProduct);
        
        subWindow.center();
        subWindow.setContent(subContent);
    	
    	subContent2.addComponents(buttonSellProduct, buttonCombine);
        
        subWindow2.setContent(subContent2);	
    	
    	grid.setItems(inventario.getProducts());
    	grid2.setItems(inventario2.getProducts());
    	
    	FormLayout inventarioLayout = new FormLayout();
    	
    	inventarioLayout.addComponents(
    			titulo1,
    			grid,
    			titulo3,
    			grid3
    			
    	);
    	
    	FormLayout bolsaLayout = new FormLayout();
    	
    	bolsaLayout.addComponents(
    			titulo2,
    			grid2,
    			combinaciones
    	);
    	
    	
    
    	horizontalLayout.addComponents(inventarioLayout, formLayout, bolsaLayout, formLayout2);
    	
    	
    	
    	setContent(horizontalLayout);
    	
    
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
