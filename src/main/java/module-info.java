module hr.vub {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.logging;

    requires io.reactivex.rxjava3;

    requires moshi;
    requires kotlin.stdlib;

    opens hr.vub.data;
    opens hr.vub.data.adapters;
    opens hr.vub.data.entities;

    exports hr.vub.data.models;
    opens hr.vub.data.models;

    exports hr.vub;
    opens hr.vub;

    exports hr.vub.ui;
    opens hr.vub.ui;

    exports hr.vub.ui.artists;
    opens hr.vub.ui.artists;

    exports hr.vub.ui.events;
    opens hr.vub.ui.events;

    exports hr.vub.ui.home;
    opens hr.vub.ui.home;

    exports hr.vub.ui.login;
    opens hr.vub.ui.login;
}