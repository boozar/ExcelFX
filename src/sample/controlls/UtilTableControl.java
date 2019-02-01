package sample.controlls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.objects.Product;

public class UtilTableControl {
    @FXML
    private TextField txtID, txtType, txtManufacturer, txtSpecification, txtRetail, txtWhole, txtDealer;
    @FXML
    private Button btnClose, btnOk;

    private Product product;


    public void setProduct(Product product) {
        if(product==null) return;
        this.product = product;
        txtID.setText("" + product.getId());
        txtType.setText(product.getProductType());
        txtManufacturer.setText(product.getManufacturer());
        txtSpecification.setText(product.getSpecification());
        txtRetail.setText("" + product.getRetailPrice());
        txtWhole.setText("" + product.getSmallWholesale());
        txtDealer.setText("" + product.getLargeWholesale());
    }

    public Product getProduct() {
        return product;
    }

    public void actionClose(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }

    public void actionSave(ActionEvent actionEvent) {

        product.setId(Integer.parseInt((txtID.getText().trim())));
        product.setProductType(txtType.getText());
        product.setManufacturer(txtManufacturer.getText());
        product.setSpecification(txtSpecification.getText());
        product.setRetailPrice(Double.parseDouble(txtRetail.getText().trim()));
        product.setSmallWholesale(Double.parseDouble(txtWhole.getText().trim()));
        product.setLargeWholesale(Double.parseDouble(txtDealer.getText().trim()));
        actionClose(actionEvent);

    }

}
