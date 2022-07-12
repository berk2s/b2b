package category;

import category.exception.EmptyCategoryTitle;
import category.model.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CategoryValidationTest {

    @DisplayName("Should throw exception when title is empty")
    @Test
    void should_throw_exception_when_title_empty() {
        var ex = assertThrows(EmptyCategoryTitle.class,
                () -> Category.newCategory(null,null, null));

        assertEquals("category.title.empty", ex.getMessage());
    }
}
