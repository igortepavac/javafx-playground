package hr.vub.ui;

import hr.vub.util.JavaFxScheduler;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.concurrent.TimeUnit;

public class LoginController {

    @FXML
    Label label;

    int counter = 0;

    @FXML
    void onClickMe(ActionEvent event) {
        Observable.just(counter++)
            .observeOn(Schedulers.io())
            .delay(2, TimeUnit.SECONDS)
            .observeOn(JavaFxScheduler.platform())
            .subscribe(new Observer<Integer>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {

                }

                @Override
                public void onNext(@NonNull Integer integer) {

                    label.setText("Clicked " + integer);
                }

                @Override
                public void onError(@NonNull Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            });

    }
}
