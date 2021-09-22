package hr.vub.data.persistence;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import hr.vub.data.FileDatabase;
import hr.vub.data.models.UserPreferences;
import hr.vub.util.LogUtil;
import hr.vub.util.StringUtil;

import java.util.logging.Logger;

public class UserPreferencesPersistence implements Persistence<UserPreferences> {

    private static final Logger logger = LogUtil.getLogger();

    private final FileDatabase fileDatabase;
    private final JsonAdapter<UserPreferences> adapter;

    public UserPreferencesPersistence(Moshi moshi, FileDatabase fileDatabase) {
        this.fileDatabase = fileDatabase;
        this.adapter = moshi.adapter(UserPreferences.class);
    }

    @Override
    public void save(UserPreferences item) {
        fileDatabase.write(adapter.toJson(item));
    }

    @Override
    public UserPreferences read() {
        if (!fileDatabase.exists()) {
            return UserPreferences.empty();
        }
        String raw = fileDatabase.read();
        if (StringUtil.isEmpty(raw)) {
            return UserPreferences.empty();
        } else {
            try {
                return adapter.fromJson(raw);
            } catch (Exception e) {
                logger.warning("Failed to read from persistence" + e.getMessage());
                return UserPreferences.empty();
            }
        }
    }
}
