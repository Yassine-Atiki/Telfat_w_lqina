module com.firstproject.telfat_w_lqina {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;


    opens com.firstproject.telfat_w_lqina to javafx.fxml;
    exports com.firstproject.telfat_w_lqina;
}