package hr.vub.data.persistence;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import hr.vub.data.FileDatabase;
import hr.vub.data.entities.ConcertEntity;
import hr.vub.util.LogUtil;
import hr.vub.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ConcertPersistence implements Persistence<List<ConcertEntity>> {

    private static final Logger logger = LogUtil.getLogger();

    private final FileDatabase fileDatabase;
    private final JsonAdapter<List<ConcertEntity>> adapter;

    public ConcertPersistence(Moshi moshi,  FileDatabase fileDatabase) {
        this.fileDatabase = fileDatabase;
        this.adapter = moshi.adapter(Types.newParameterizedType(List.class, ConcertEntity.class));
    }

    @Override
    public void save(List<ConcertEntity> items) {
        fileDatabase.write(adapter.toJson(items));
    }

    @Override
    public List<ConcertEntity> read() {
        if (!fileDatabase.exists()) {
            return new ArrayList<>();
        }
        String raw = fileDatabase.read();
        if (StringUtil.isEmpty(raw)) {
            return new ArrayList<>();
        } else {
            try {
                return adapter.fromJson(raw);
            } catch (Exception e) {
                logger.warning("Failed to read from persistence" + e.getMessage());
                return new ArrayList<>();
            }
        }
    }
}
