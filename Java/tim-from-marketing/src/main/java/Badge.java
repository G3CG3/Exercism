class Badge {
    public String print(Integer id, String name, String department) {
        String departmentLabel = (department == null) ? "OWNER" : department.toUpperCase();

        if (id == null) {
            return name + " - " + departmentLabel;
        } else {
            return "[" + id + "] - " + name + " - " + departmentLabel;
        }
    }
}
