package butinfo.model;

public class ParseResult<T> {
    private T result;
    private String rest;

    public ParseResult(String rest, T result) {
        this.result = result;
        this.rest = rest;
    }

    public T getResult() {
        return result;
    }

    public String getRest() {
        return rest;
    }
}
