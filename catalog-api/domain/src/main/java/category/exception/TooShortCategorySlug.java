package category.exception;

public class TooShortCategorySlug extends RuntimeException {
    public TooShortCategorySlug(String message) {
        super(message);
    }
}
