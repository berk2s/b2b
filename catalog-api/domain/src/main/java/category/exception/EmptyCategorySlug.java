package category.exception;

public class EmptyCategorySlug extends RuntimeException{
    public EmptyCategorySlug(String message) {
        super(message);
    }
}
