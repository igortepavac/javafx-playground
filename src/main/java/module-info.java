module hr.vub {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.logging;

    requires io.reactivex.rxjava3;

    exports hr.vub;

    opens hr.vub;
    exports hr.vub.ui;
    opens hr.vub.ui;
}