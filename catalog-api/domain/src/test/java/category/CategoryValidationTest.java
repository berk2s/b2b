package category;

import category.exception.*;
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
        var ex = assertThrows(EmptyTitle.class,
                () -> Category.builder().title(null).build());

        assertEquals("category.title.empty", ex.getMessage());
    }

    @DisplayName("Should throw exception when slug is empty")
    @Test
    void should_throw_exception_when_slug_empty() {
        var ex = assertThrows(EmptySlug.class,
                () -> Category.builder()
                        .title(RandomStringUtils.randomAlphabetic(10))
                        .slug(null)
                        .build());

        assertEquals("category.slug.empty", ex.getMessage());
    }

    @DisplayName("Should throw exception when title is too short")
    @Test
    void should_throw_exception_when_title_too_short() {
        var ex = assertThrows(OutOfRangeTitle.class,
                () -> Category.builder()
                        .title(RandomStringUtils.randomAlphabetic(2))
                        .slug(RandomStringUtils.randomAlphabetic(10))
                        .build());

        assertEquals("category.title.outOfRange", ex.getMessage());
    }

    @DisplayName("Should throw exception when title is too long")
    @Test
    void should_throw_exception_when_title_too_long() {
        var ex = assertThrows(OutOfRangeTitle.class,
                () -> Category.builder()
                        .title(RandomStringUtils.randomAlphabetic(200))
                        .slug(RandomStringUtils.randomAlphabetic(10))
                        .build());

        assertEquals("category.title.outOfRange", ex.getMessage());
    }

    @DisplayName("Should throw exception when slug is too short")
    @Test
    void should_throw_exception_when_slug_too_short() {
        var ex = assertThrows(OutOfRangeSlug.class,
                () -> Category.builder()
                        .title(RandomStringUtils.randomAlphabetic(10))
                        .slug(RandomStringUtils.randomAlphabetic(2))
                        .build());

        assertEquals("category.slug.outOfRange", ex.getMessage());
    }

    @DisplayName("Should throw exception when slug is too long")
    @Test
    void should_throw_exception_when_slug_too_long() {
        var ex = assertThrows(OutOfRangeSlug.class,
                () -> Category.builder()
                        .title(RandomStringUtils.randomAlphabetic(10))
                        .slug(RandomStringUtils.randomAlphabetic(200))
                        .build());

        assertEquals("category.slug.outOfRange", ex.getMessage());
    }

    @DisplayName("Should throw exception when seo title is too long")
    @Test
    void should_throw_exception_when_seo_title_too_long() {
        var ex = assertThrows(OutOfRangeSeoTitle.class,
                () -> Category.builder()
                        .title(RandomStringUtils.randomAlphabetic(10))
                        .slug(RandomStringUtils.randomAlphabetic(10))
                        .seoTitle(RandomStringUtils.randomAlphabetic(200))
                        .build());

        assertEquals("category.seoTitle.tooLong", ex.getMessage());
    }

    @DisplayName("Should throw exception when meta desc is too long")
    @Test
    void should_throw_exception_when_meta_desc_too_long() {
        var ex = assertThrows(OutOfRangeMetaDesc.class,
                () -> Category.builder()
                        .title(RandomStringUtils.randomAlphabetic(10))
                        .slug(RandomStringUtils.randomAlphabetic(10))
                        .metaDesc(RandomStringUtils.randomAlphabetic(200))
                        .build());

        assertEquals("category.metaDesc.tooLong", ex.getMessage());
    }
}
