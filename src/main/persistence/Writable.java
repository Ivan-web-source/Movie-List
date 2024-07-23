// This code uses the formatting of persistence package of
// the sample term project phase 2 in CPSC 210 2024 S

package persistence;

import org.json.JSONObject;

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
