public class MemberLimitExceededException extends Exception {
    private final int limit;
    public MemberLimitExceededException(String message, int limit) {
        super(message);
        this.limit = limit;
    }
    public int getLimit() { return limit; }
}
