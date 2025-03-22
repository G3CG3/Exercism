import java.util.HashSet;
import java.util.Set;

class TwoBucket {
    int bucketOneCap, bucketTwoCap, desiredLiters;
    String startBucket;

    TwoBucket(int bucketOneCap, int bucketTwoCap, int desiredLiters, String startBucket) {
        this.bucketOneCap = bucketOneCap;
        this.bucketTwoCap = bucketTwoCap;
        this.desiredLiters = desiredLiters;
        this.startBucket = startBucket;
    }

    Result getResult() {
        if (desiredLiters % gcd(bucketOneCap, bucketTwoCap) != 0) {
            throw new UnreachableGoalException();
        }
        return solve();
    }

    private Result solve() {
        int startCap = startBucket.equals("one") ? bucketOneCap : bucketTwoCap;
        int otherCap = startBucket.equals("one") ? bucketTwoCap : bucketOneCap;
        int start = 0, other = 0, moves = 0;
        Set<String> visited = new HashSet<>();
        while (true) {
            String state = start + "," + other;
            if (visited.contains(state)) break;
            visited.add(state);
            if (start == desiredLiters) {
                return new Result(moves, startBucket, other);
            }
            if (other == desiredLiters) {
                String otherBucketName = startBucket.equals("one") ? "two" : "one";
                return new Result(moves, otherBucketName, start);
            }
            moves++;
            if (start == 0) {
                start = startCap;
            } else if (other == otherCap) {
                other = 0;
            } else if (otherCap == desiredLiters) {
                other = otherCap;
            } else {
                int transfer = Math.min(start, otherCap - other);
                start -= transfer;
                other += transfer;
            }
        }
        throw new UnreachableGoalException();
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}