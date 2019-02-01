package sample.controlls;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.interfaces.impl.CollectionProductTable;
import sample.objects.Product;

import java.io.IOException;

public class TableViewControl {
    private CollectionProductTable collectionProductTable = new CollectionProductTable();
    @FXML
    private TableView tblView;
    @FXML
    private TableColumn<Product, String> tblType, tblManufacturer, tblSpecification;
    @FXML
    private TableColumn<Product, Double> tblRetail, tblWholesale, tblDealer;
    @FXML
    private TableColumn<Product, Integer> tblId;
    @FXML
    private Label lblQuantity;
    @FXML
    private Button btnAdd, btnUpdate, btnDelete;

    private Parent fxmlUtil;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private UtilTableControl utilTable;
    private Stage utilTableStage;
    private Stage tableStage;

    public void setTableStage(Stage tableStage) {
        this.tableStage = tableStage;
    }

    @FXML
    private void initialize() {
        tblId.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
        tblType.setCellValueFactory(new PropertyValueFactory<Product, String>("productType"));
        tblManufacturer.setCellValueFactory(new PropertyValueFactory<Product, String>("manufacturer"));
        tblSpecification.setCellValueFactory(new PropertyValueFactory<Product, String>("specification"));
        tblRetail.setCellValueFactory(new PropertyValueFactory<Product, Double>("retailPrice"));
        tblWholesale.setCellValueFactory(new PropertyValueFactory<Product, Double>("smallWholesale"));
        tblDealer.setCellValueFactory(new PropertyValueFactory<Product, Double>("largeWholesale"));
        initListeners();
        excelToTableData();
        initLoader();
    }

    private void excelToTableData() {
        collectionProductTable.excelRead();
        tblView.setItems(collectionProductTable.getProductList());
    }

    private void initLoader() {
        try {

            fxmlLoader.setLocation(getClass().getResource("../fxml/utilTable.fxml"));
            fxmlUtil = fxmlLoader.load();
            utilTable = fxmlLoader.getController();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    private void initListeners() {
        collectionProductTable.getProductList().addListener(new ListChangeListener<Product>() {
            @Override
            public void onChanged(Change<? extends Product> c) {
                updateLabelQuantity();
                tblView.refresh();
            }
        });
        tblView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    utilTable.setProduct((Product) tblView.getSelectionModel().getSelectedItem());
                    showUpdate();
                }
            }
        });


    }

    private void updateLabelQuantity() {
        lblQuantity.setText("Quantity records: " + collectionProductTable.getProductList().size());
    }

    public void actionBtnPressed(ActionEvent actionEvent) throws Exception {
        Object object = actionEvent.getSource();
        if (!(object instanceof Button)) {
            return;
        }
        Button clickedButton = (Button) object;
        Product selectedProduct = (Product) tblView.getSelectionModel().getSelectedItem();
        utilTable.setProduct(selectedProduct);
        switch (clickedButton.getId()) {
            case "btnAdd":
                utilTable.setProduct(new Product());
                showUpdate();
                collectionProductTable.add(utilTable.getProduct());
                break;

            case "btnUpdate":
                showUpdate();
                utilTable.setProduct((Product) tblView.getSelectionModel().getSelectedItem());
                break;

            case "btnDelete":
                collectionProductTable.delete((Product) tblView.getSelectionModel().getSelectedItem());
                break;
        }

    }

    private void showUpdate() {

        if (utilTableStage == null) {
            utilTableStage = new Stage();
            utilTableStage.setTitle("Update product");
            utilTableStage.setMinHeight(utilTableStage.getHeight());
            utilTableStage.setMinWidth(utilTableStage.getWidth());
            utilTableStage.setResizable(false);
            utilTableStage.setScene(new Scene(fxmlUtil));
            utilTableStage.initModality(Modality.WINDOW_MODAL);
            utilTableStage.initOwner(tableStage);
            utilTableStage.getScene().getStylesheets().add(getClass().getResource("../css/style.css").toExternalForm());

        }
        utilTableStage.showAndWait();
    }
}
