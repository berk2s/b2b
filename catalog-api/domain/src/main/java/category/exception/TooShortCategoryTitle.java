package category.exception;

public class TooShortCategoryTitle extends RuntimeException {
    public TooShortCategoryTitle(String message) {
        super(message);
    }
}
