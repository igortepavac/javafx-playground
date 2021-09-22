package hr.vub.ui.artists;

import hr.vub.DependencyGraph;
import hr.vub.data.models.Artist;
import hr.vub.data.repository.ArtistRepository;
import hr.vub.ui.util.AlertUtil;
import hr.vub.ui.util.WindowUtil;
import hr.vub.util.LogUtil;
import hr.vub.util.StringUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.UUID;
import java.util.logging.Logger;

public class EditArtistController {

    private static final Logger logger = LogUtil.getLogger();

    private ArtistRepository artistRepository;

    @FXML
    TextField uuidField;

    @FXML
    TextField nameField;

    @FXML
    TextField genreField;

    private UUID uuid = null;

    public void initialize() {
        artistRepository = DependencyGraph.getInstance().getArtistRepository();
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
        uuidField.setText(uuid.toString());
        fillData(uuid);
    }

    private void fillData(UUID uuid) {
        Artist artist = artistRepository.get(uuid);
        if (artist != null) {
            nameField.setText(artist.getName());
            genreField.setText(artist.getGenre());
        } else {
            logger.warning("Failed to find an artist with UUID " + uuid);
        }
    }

    @FXML
    public void onSave(ActionEvent event) {
        if (StringUtil.isEmpty(nameField.getText()) || StringUtil.isEmpty(genreField.getText())) {
            AlertUtil.showError("Please fill all fields!");
            return;
        }
        if (uuid != null) {
            updateArtist();
        } else {
            addArtist();
        }
        WindowUtil.closeStage(event);
    }

    private void updateArtist() {
        artistRepository.update(new Artist(uuid, nameField.getText(), genreField.getText()));
    }

    private void addArtist() {
        artistRepository.add(new Artist(UUID.randomUUID(), nameField.getText(), genreField.getText()));
    }
}
