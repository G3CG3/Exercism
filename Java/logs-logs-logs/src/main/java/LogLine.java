public class LogLine {
    private final String log;

    public LogLine(String logLine) {
        this.log = logLine;
    }

    public LogLevel getLogLevel() {
        String levelString = log.substring(1, log.indexOf("]"));

        return switch (levelString) {
            case "TRC" -> LogLevel.TRACE;
            case "DBG" -> LogLevel.DEBUG;
            case "INF" -> LogLevel.INFO;
            case "WRN" -> LogLevel.WARNING;
            case "ERR" -> LogLevel.ERROR;
            case "FTL" -> LogLevel.FATAL;
            default -> LogLevel.UNKNOWN;
        };
    }

    public String getOutputForShortLog() {
        LogLevel level = getLogLevel();
        String message = log.substring(log.indexOf("]: ") + 3);
        return level.getCode() + ":" + message;
    }
}