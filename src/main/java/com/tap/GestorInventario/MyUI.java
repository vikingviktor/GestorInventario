package com.tap.GestorInventario;

import java.util.Set;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
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
	
	
	Inventario inventario = new Inventario();
	Inventario inventario2 = new Inventario();
	
	HorizontalSplitPanel splitPanel = new HorizontalSplitPanel();
	Grid<Producto> grid = new Grid<Producto>();
	Grid<Producto> grid2 = new Grid<Producto>();
	
	
	private Producto selectedProduct; 

    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	
    	HorizontalLayout horizontalLayout = new HorizontalLayout();
    	
    	Window subWindow = new Window("Detalles del Producto");
        VerticalLayout subContent = new VerticalLayout();
        
        Label labelValueEur = new Label();
        Label labelName = new Label();
        Label labelValueDol = new Label();
        Label labelUnidades = new Label();
        Button buttonDelete = new Button("Eliminar Producto");
        Button buttonAdd = new Button("Añadir un Producto");
        
        buttonDelete.addClickListener(e -> {
        	
        	if (selectedProduct.getNumber() == 0) {
        		//Inventario.getInstance().deleteProduct(selectedProduct);
        		inventario.deleteProduct(selectedProduct);
        	//grid.setItems(Inventario.getInstance().getProducts());
        	grid.setItems(inventario.getProducts());
        	removeWindow(subWindow);
        	}
        	else
        	{
        		if ((grid.getSelectedItems() == selectedProduct) //A cambiar
        		{
        			selectedProduct.setNumber(selectedProduct.getNumber()-1);
        			grid.setItems(inventario.getProducts());
        		}
        		else
        			Notification.show("No se pueden añadir o eliminar productos en tu inventario, compra o vende...");
        		
        	}
        	
        });
        
        buttonAdd.addClickListener(e -> {
        	
        	
        	if (grid.getSelectedItems() == selectedProduct)
    		{
    			selectedProduct.setNumber(selectedProduct.getNumber()+1);
    			grid.setItems(inventario.getProducts());
    		}
    		else
    			Notification.show("No se pueden añadir o eliminar productos en tu inventario, compra o vende...");
    		
        });
        
      
        subContent.addComponents(labelName, labelValueEur, labelValueDol, labelUnidades, buttonDelete, buttonAdd);
        
        
        subWindow.center();
        subWindow.setContent(subContent);
        
        
    	
    	/* TABLE */
    	
    	
    	grid.addColumn(Producto::getName).setCaption("Nombre");
    	grid.addColumn(Producto::getValueEur).setCaption("Valor Euros");
    	grid.addColumn(Producto::getValueDol).setCaption("Valor Dolares");
    	grid.addColumn(Producto::getNumber).setCaption("Unidades");
    	grid.setSelectionMode(SelectionMode.SINGLE);
    	
    	grid.addItemClickListener(event -> {
    		
    		selectedProduct = event.getItem();
    		
        	labelName.setValue(selectedProduct.getName());
        	labelValueEur.setValue(selectedProduct.getValueEur());
        	labelValueDol.setValue(String.valueOf(selectedProduct.getValueDol()));
        	labelUnidades.setValue(String.valueOf(selectedProduct.getNumber()));
        	
        	
        	removeWindow(subWindow);
        	addWindow(subWindow);
        	
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
        	addWindow(subWindow);
        	
    	});
    	
    	
    	/* FORM */
    	
    	
    	FormLayout formLayout = new FormLayout();
    	
    	TextField textFieldName = new TextField("Nombre del Producto");
    	TextField textFieldEur = new TextField("Valor en Euros");
    	Button buttonAddProduct = new Button("Crear nuevo producto");
    	Button buttonBuyProduct = new Button("Comprar producto");
        Button buttonSellProduct = new Button("Vender producto");
        	
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
    		
    		if (selectedProduct.getNumber() != 0) {
    			selectedProduct.setNumber(selectedProduct.getNumber()-1);
        		grid.setItems(inventario.getProducts());
        		removeWindow(subWindow);
        		Notification.show("Producto comprado...");
        	}
    		else {
    			Notification.show("Producto fuera de stock... Crea más unidades");
    		}
    			
    		
    	});
    		
    		
    		
    	
    	
    	formLayout.addComponents(
    			textFieldName,
    	    	textFieldEur,
    	    	buttonAddProduct,
    			buttonBuyProduct, 
    			buttonSellProduct
    	);
    	
    	
    	FormLayout formLayout2 = new FormLayout();
    	
    	
    	Button buttonCombine = new Button("Combinar Productos");
    	
    	formLayout.addComponents(
    			buttonCombine
    	);
    	
    	grid.setItems(inventario.getProducts());
    	grid2.setItems(inventario2.getProducts());
    	
    
    	horizontalLayout.addComponents(grid, formLayout, grid2, formLayout2);
    	
    	
    	
    	setContent(horizontalLayout);
    	
    
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
