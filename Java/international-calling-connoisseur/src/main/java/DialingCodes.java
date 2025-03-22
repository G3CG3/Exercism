import java.util.HashMap;
import java.util.Map;

public class DialingCodes {
    private final Map<Integer, String> dialingCodes;

    public DialingCodes() {
        this.dialingCodes = new HashMap<>();
    }

    public Map<Integer, String> getCodes() {
        return dialingCodes;
    }

    public void setDialingCode(Integer code, String country) {
        dialingCodes.put(code, country);
    }

    public String getCountry(Integer code) {
        return dialingCodes.get(code);
    }

    public void addNewDialingCode(Integer code, String country) {
        if (!dialingCodes.containsKey(code) && !dialingCodes.containsValue(country)) {
            dialingCodes.put(code, country);
        }
    }

    public Integer findDialingCode(String country) {
        for (Map.Entry<Integer,String> entry : dialingCodes.entrySet()) {
            if (entry.getValue().equals(country)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public void updateCountryDialingCode(Integer code, String country) {
        Integer existingCode = findDialingCode(country);
        if (existingCode != null) {
            dialingCodes.remove(existingCode);
            dialingCodes.put(code,country);
        }
    }
}
