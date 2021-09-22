package hr.vub.ui.events;

import hr.vub.DependencyGraph;
import hr.vub.data.models.Artist;
import hr.vub.data.models.Concert;
import hr.vub.data.models.Event;
import hr.vub.data.models.Festival;
import hr.vub.data.repository.ArtistRepository;
import hr.vub.data.repository.ConcertRepository;
import hr.vub.data.repository.FestivalRepository;
import hr.vub.ui.util.AlertUtil;
import hr.vub.ui.util.WindowUtil;
import hr.vub.util.LogUtil;
import hr.vub.util.StringUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

public class EditEventController {

    private static final Logger logger = LogUtil.getLogger();

    private ArtistRepository artistRepository;
    private ConcertRepository concertRepository;
    private FestivalRepository festivalRepository;

    @FXML
    TextField uuidField;

    @FXML
    TextField descriptionField;

    @FXML
    TextField capacityField;

    @FXML
    TextField genreField;

    @FXML
    ComboBox<Artist> artistComboBox;

    private Artist selectedArtist = null;
    private Event event = null;

    public void initialize() {
        artistRepository = DependencyGraph.getInstance().getArtistRepository();
        concertRepository = DependencyGraph.getInstance().getConcertRepository();
        festivalRepository = DependencyGraph.getInstance().getFestivalRepository();

        initArtistComboBox();
    }

    private void initArtistComboBox() {
        List<Artist> artists = artistRepository.getAll();
        artistComboBox.getItems().setAll(artists);
        Callback<ListView<Artist>, ListCell<Artist>> cellFactory = param -> new ListCell<>() {
            final Label label = new Label();

            @Override
            protected void updateItem(Artist item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setGraphic(null);
                } else {
                    label.setText(item.getName());
                    setGraphic(label);
                }
            }
        };
        artistComboBox.setCellFactory(cellFactory);
        artistComboBox.setButtonCell(cellFactory.call(null));
        artistComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedArtist = newValue;
        });
    }

    public void setEvent(Event event) {
        this.event = event;
        uuidField.setText(event.getUuid().toString());
        fillData(event);
    }

    private void fillData(Event event) {
        if (event instanceof Concert) {
            Concert concert = (Concert) event;
            selectedArtist = concert.getArtist();
            artistComboBox.getSelectionModel().select(selectedArtist);
            genreField.setDisable(true);
        } else if (event instanceof Festival) {
            Festival festival = (Festival) event;
            genreField.setText(festival.getGenre());
            artistComboBox.setDisable(true);
        }
        descriptionField.setText(event.getDescription());
        capacityField.setText(String.valueOf(event.getCapacity()));
    }

    @FXML
    public void onSave(ActionEvent event) {
        if (StringUtil.isEmpty(descriptionField.getText()) || StringUtil.isEmpty(capacityField.getText())) {
            AlertUtil.showError("Please fill the description and capacity!");
            return;
        }
        if (StringUtil.isEmpty(genreField.getText()) && selectedArtist == null) {
            AlertUtil.showError("Please choose a genre or an artist!");
            return;
        }
        if (StringUtil.isNotEmpty(genreField.getText()) && selectedArtist != null) {
            AlertUtil.showError("Please choose a genre or an artist, not both!");
            return;
        }
        if (this.event != null) {
            updateEvent();
        } else {
            addEvent();
        }
        WindowUtil.closeStage(event);
    }

    private void updateEvent() {
        if (selectedArtist != null) {
            concertRepository.update(
                new Concert(
                    event.getUuid(),
                    descriptionField.getText(),
                    Integer.parseInt(capacityField.getText()),
                    selectedArtist
                )
            );
        } else {
            festivalRepository.update(
                new Festival(
                    event.getUuid(),
                    descriptionField.getText(),
                    Integer.parseInt(capacityField.getText()),
                    genreField.getText()
                )
            );
        }
    }

    private void addEvent() {
        if (selectedArtist != null) {
            concertRepository.add(
                new Concert(
                    UUID.randomUUID(),
                    descriptionField.getText(),
                    Integer.parseInt(capacityField.getText()),
                    selectedArtist
                )
            );
        } else {
            festivalRepository.add(
                new Festival(
                    UUID.randomUUID(),
                    descriptionField.getText(),
                    Integer.parseInt(capacityField.getText()),
                    genreField.getText()
                )
            );
        }
    }
}
