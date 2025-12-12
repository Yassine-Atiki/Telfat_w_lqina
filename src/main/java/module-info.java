module com.firstproject.telfat_w_lqina {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.firstproject.telfat_w_lqina to javafx.fxml;
    exports com.firstproject.telfat_w_lqina;
}