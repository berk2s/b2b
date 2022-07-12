package category.exception;

public class EmptySlug extends RuntimeException{
    public EmptySlug(String message) {
        super(message);
    }
}
