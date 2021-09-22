package hr.vub.ui.events;

import hr.vub.DependencyGraph;
import hr.vub.data.models.Artist;
import hr.vub.data.models.Concert;
import hr.vub.data.models.Event;
import hr.vub.data.models.Festival;
import hr.vub.data.repository.ConcertRepository;
import hr.vub.data.repository.FestivalRepository;
import hr.vub.ui.Screen;
import hr.vub.ui.ScreenController;
import hr.vub.util.JavaFxScheduler;
import io.reactivex.rxjava3.core.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EventsController {

    private ScreenController screenController;
    private ConcertRepository concertRepository;
    private FestivalRepository festivalRepository;

    @FXML
    TableView<Event> tableView;

    @FXML
    TableColumn<Event, UUID> uuidColumn;

    @FXML
    TableColumn<Event, String> descriptionColumn;

    @FXML
    TableColumn<Event, Integer> capacityColumn;

    @FXML
    TableColumn<Event, String> estimatedTickerPriceColumn;

    @FXML
    TableColumn<Event, String> genreColumn;

    private Event selectedItem = null;

    public void initialize() {
        screenController = DependencyGraph.getInstance().getScreenController();
        concertRepository = DependencyGraph.getInstance().getConcertRepository();
        festivalRepository = DependencyGraph.getInstance().getFestivalRepository();

        initColumns();
        initData();
    }

    private void initColumns() {
        uuidColumn.setCellValueFactory(new PropertyValueFactory<>("uuid"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        estimatedTickerPriceColumn.setCellValueFactory(param -> {
            if (param.getValue() != null) {
                return new SimpleStringProperty("" + param.getValue().calculateEstimatedPrice());
            }
            return new SimpleStringProperty("");
        });
        genreColumn.setCellValueFactory(param -> {
            if (param.getValue() != null) {
                if (param.getValue() instanceof Concert) {
                    Artist artist = ((Concert) param.getValue()).getArtist();
                    String genre = artist != null ? artist.getGenre() : "null";
                    return new SimpleStringProperty(genre);
                } else if (param.getValue() instanceof Festival) {
                    return new SimpleStringProperty(((Festival) param.getValue()).getGenre());
                }
            }
            return new SimpleStringProperty("");
        });

        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedItem = newValue;
        });
    }

    private void initData() {
        Observable.combineLatest(
            concertRepository.getAllObservable(),
            festivalRepository.getAllObservable(),
            (concerts, festivals) -> {
                List<Event> events = new ArrayList<>();
                events.addAll(concerts);
                events.addAll(festivals);
                return events;
            }
        )
            .observeOn(JavaFxScheduler.platform())
            .subscribe(events -> {
                tableView.getItems().setAll(events);
            });
    }

    @FXML
    public void onAddNewRow(ActionEvent event) {
        screenController.navigateToStage(Screen.ADD_EVENT);
    }

    @FXML
    public void onDeleteSelectedItem(ActionEvent event) {
        final Event item = selectedItem;
        if (item != null) {
            if (item instanceof Concert) {
                concertRepository.delete((Concert) item);
            } else if (item instanceof Festival) {
                festivalRepository.delete((Festival) item);
            }
        }
    }

    @FXML
    public void onEditSelectedItem(ActionEvent event) {
        final Event item = selectedItem;
        if (item != null) {
            screenController.navigateToStage(Screen.EDIT_EVENT, fxmlLoader -> {
                EditEventController controller = fxmlLoader.getController();
                controller.setEvent(item);
                return null;
            });
        }
    }

    @FXML
    public void onLogout(ActionEvent event) {
        screenController.navigateToScreen((Node) event.getSource(), Screen.LOGIN);
    }
}
