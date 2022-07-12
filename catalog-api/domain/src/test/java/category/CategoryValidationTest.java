package category;

import category.exception.EmptyCategorySlug;
import category.exception.EmptyCategoryTitle;
import category.exception.TooShortCategorySlug;
import category.exception.TooShortCategoryTitle;
import category.model.Category;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CategoryValidationTest {

    @DisplayName("Should throw exception when title is empty")
    @Test
    void should_throw_exception_when_title_empty() {
        var ex = assertThrows(EmptyCategoryTitle.class,
                () -> Category.builder().title(null).build());

        assertEquals("category.title.empty", ex.getMessage());
    }

    @DisplayName("Should throw exception when slug is empty")
    @Test
    void should_throw_exception_when_slug_empty() {
        var ex = assertThrows(EmptyCategorySlug.class,
                () -> Category.builder()
                        .title(RandomStringUtils.randomAlphabetic(10))
                        .slug(null)
                        .build());

        assertEquals("category.slug.empty", ex.getMessage());
    }

    @DisplayName("Should throw exception when title is too short")
    @Test
    void should_throw_exception_when_title_too_short() {
        var ex = assertThrows(TooShortCategoryTitle.class,
                () -> Category.builder()
                        .title(RandomStringUtils.randomAlphabetic(2))
                        .slug(RandomStringUtils.randomAlphabetic(10))
                        .build());

        assertEquals("category.title.tooShort", ex.getMessage());
    }

    @DisplayName("Should throw exception when slug is too short")
    @Test
    void should_throw_exception_when_slug_too_short() {
        var ex = assertThrows(TooShortCategorySlug.class,
                () -> Category.builder()
                        .title(RandomStringUtils.randomAlphabetic(10))
                        .slug(RandomStringUtils.randomAlphabetic(2))
                        .build());

        assertEquals("category.slug.tooShort", ex.getMessage());
    }
}
