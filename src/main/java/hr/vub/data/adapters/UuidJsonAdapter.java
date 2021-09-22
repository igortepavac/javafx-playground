package hr.vub.data.adapters;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

import java.util.UUID;

public class UuidJsonAdapter {

    @FromJson
    public UUID fromJson(String uuid) {
        return UUID.fromString(uuid);
    }

    @ToJson
    public String toJson(UUID uuid) {
        return uuid.toString();
    }
}
