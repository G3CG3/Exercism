import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

class RestApi {
    private final Map<String, User> usersList;

    RestApi(User... users) {
        usersList = new HashMap<>();
        for (User user : users) {
            usersList.put(user.name(), user);
        }
    }

    String get(String url) {
        JSONArray jarray = new JSONArray();
        JSONObject output = new JSONObject().put("users", jarray);
        for (Map.Entry<String, User> user : usersList.entrySet()) {
            jarray.put(getJson(user.getKey()));
        }
        return output.toString();
    }
    public String get(String url, JSONObject payload) {
        JSONArray jarray = new JSONArray();
        JSONObject output = new JSONObject().put("users", jarray);
        for (Object name : payload.getJSONArray("users")) {
            jarray.put(getJson(name.toString()));
        }
        return output.toString();
    }

    String post(String url, JSONObject payload) {
        switch (url) {
            case "/add":
                add(payload);
                return getJson(payload.getString("user")).toString();
            case "/iou":
                String lender = payload.getString("lender");
                String borrower = payload.getString("borrower");
                double amount = payload.getDouble("amount");
                iou(lender, borrower, amount);
                JSONObject newPayload;
                if (lender.compareTo(borrower) < 0) {
                    newPayload = new JSONObject().put("users", new JSONArray().put(lender).put(borrower));
                } else {
                    newPayload = new JSONObject().put("users", new JSONArray().put(borrower).put(lender));
                }
                return get("/users", newPayload);
        }
        throw new UnsupportedOperationException("Something went wrong");
    }

    private void iou(String lenderName, String borrowerName, double amount) {
        User lender = usersList.get(lenderName);
        User borrower = usersList.get(borrowerName);
        List<Iou> lenderOwes = new LinkedList<>(lender.owes());
        List<Iou> borrowerOwes = new LinkedList<>(borrower.owes());
        borrowerOwes.add(new Iou(lenderName, amount));
        List<Iou> lenderOwedBy = new LinkedList<>(lender.owedBy());
        lenderOwedBy.add(new Iou(borrowerName, amount));
        List<Iou> borrowerOwedBy = new LinkedList<>(borrower.owedBy());
        newBuild(lenderName, lenderOwes, lenderOwedBy);
        newBuild(borrowerName, borrowerOwes, borrowerOwedBy);
    }
    private void newBuild(String name, List<Iou> owes, List<Iou> owedBy) {
        User.Builder newBorrowerBuilder = User.builder().setName(name);
        outerloop: for (Iou i : owes) {
            for (Iou j : owedBy) {
                if (i.name.equals(j.name)) {
                    if (i.amount > j.amount) {
                        double newAmount = i.amount - j.amount;
                        newBorrowerBuilder.owes(i.name, newAmount);
                        continue outerloop;
                    } else if (i.amount < j.amount) {
                        continue outerloop;
                    } else {
                        continue outerloop;
                    }
                }
            }
            newBorrowerBuilder.owes(i.name, i.amount);
        }
        outerloop: for (Iou i : owedBy) {
            for (Iou j : owes) {
                if (i.name.equals(j.name)) {
                    if (i.amount > j.amount) {
                        double namount = i.amount - j.amount;
                        newBorrowerBuilder.owedBy(i.name, namount);
                        continue outerloop;
                    } else if (i.amount < j.amount) {
                        continue outerloop;
                    } else {
                        continue outerloop;
                    }
                }
            }
            newBorrowerBuilder.owedBy(i.name,i.amount);
        }
        User newBorrower = newBorrowerBuilder.build();
        usersList.put(name, newBorrower);
    }
    private void add(JSONObject payload) {
        String userName = payload.getString("user");
        usersList.put(userName, User.builder().setName(userName).build());
    }
    private JSONObject getJson(String userName) {
        User user = usersList.get(userName);
        JSONObject out = new JSONObject();
        JSONObject owes = new JSONObject();
        double balance = 0;
        for (Iou owe : user.owes()) {
            owes.put(owe.name, owe.amount);
            balance -= owe.amount;
        }
        JSONObject owedBy = new JSONObject();
        for (Iou owed : user.owedBy()) {
            owedBy.put(owed.name, owed.amount);
            balance += owed.amount;
        }

        out.put("name", userName)
                .put("owes", owes)
                .put("owedBy", owedBy)
                .put("balance", balance);
        return out;
    }
}