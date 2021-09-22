package hr.vub.ui.artists;

import hr.vub.DependencyGraph;
import hr.vub.data.models.Artist;
import hr.vub.data.repository.ArtistRepository;
import hr.vub.ui.Screen;
import hr.vub.ui.ScreenController;
import hr.vub.util.JavaFxScheduler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.UUID;

public class ArtistsController {

    private ScreenController screenController;
    private ArtistRepository artistRepository;

    @FXML
    TableView<Artist> tableView;

    @FXML
    TableColumn<Artist, UUID> uuidColumn;

    @FXML
    TableColumn<Artist, String> nameColumn;

    @FXML
    TableColumn<Artist, String> genreColumn;

    private Artist selectedItem = null;

    public void initialize() {
        screenController = DependencyGraph.getInstance().getScreenController();
        artistRepository = DependencyGraph.getInstance().getArtistRepository();

        initColumns();
        initData();
    }

    private void initColumns() {
        uuidColumn.setCellValueFactory(new PropertyValueFactory<>("uuid"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));

        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedItem = newValue;
        });
    }

    private void initData() {
        artistRepository.getAllObservable()
            .observeOn(JavaFxScheduler.platform())
            .subscribe(artists -> {
                tableView.getItems().setAll(artists);
            });
    }

    @FXML
    public void onAddNewRow(ActionEvent event) {
        screenController.navigateToStage(Screen.ADD_ARTIST);
    }

    @FXML
    public void onDeleteSelectedItem(ActionEvent event) {
        final Artist item = selectedItem;
        if (item != null) {
            artistRepository.delete(item);
        }
    }

    @FXML
    public void onEditSelectedItem(ActionEvent event) {
        final Artist item = selectedItem;
        if (item != null) {
            screenController.navigateToStage(Screen.EDIT_ARTIST, fxmlLoader -> {
                EditArtistController controller = fxmlLoader.getController();
                controller.setUuid(item.getUuid());
                return null;
            });
        }
    }

    @FXML
    public void onLogout(ActionEvent event) {
        screenController.navigateToScreen((Node) event.getSource(), Screen.LOGIN);
    }
}
