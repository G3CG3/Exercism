import java.util.*;

class BuildTree {

    TreeNode buildTree(ArrayList<Record> records) throws InvalidRecordsException {
        if (records.isEmpty()) {
            return null;
        }

        // Sort records by recordId
        records.sort(Comparator.comparing(Record::getRecordId));

        // Validate records
        validateRecords(records);

        // Create a map to store nodes
        Map<Integer, TreeNode> nodeMap = new HashMap<>();

        // Initialize nodes
        for (Record record : records) {
            nodeMap.put(record.getRecordId(), new TreeNode(record.getRecordId()));
        }

        TreeNode root = null;

        // Build tree structure
        for (Record record : records) {
            TreeNode node = nodeMap.get(record.getRecordId());

            if (record.getRecordId() == 0) {
                root = node; // Root node
            } else {
                TreeNode parent = nodeMap.get(record.getParentId());
                if (parent != null) {
                    parent.getChildren().add(node);
                }
            }
        }

        return root;
    }

    private void validateRecords(ArrayList<Record> records) throws InvalidRecordsException {
        if (records.get(0).getRecordId() != 0) {
            throw new InvalidRecordsException("Invalid Records");
        }

        Set<Integer> recordIds = new HashSet<>();
        for (Record record : records) {
            int recordId = record.getRecordId();
            int parentId = record.getParentId();

            // Ensure there are no missing IDs
            // Root node (ID 0) must have itself as parent
            // No record can have a parent ID greater than its own ID
            // No record can be its own parent (except root)
            if (recordId != recordIds.size()
                    || recordId == 0 && parentId != 0
                    || recordId < parentId
                    || (recordId == parentId && recordId != 0)) {
                throw new InvalidRecordsException("Invalid Records");
            }
            recordIds.add(recordId);
        }
    }
}
