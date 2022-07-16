package factory;

import category.model.Category;
import category.usecase.CreateCategory;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.mockito.stubbing.Answer;

public final class MockFactory {

    public static Answer<Object> answerCreateCategory() {
        return i -> {
            var createCategory = (CreateCategory) i.getArguments()[0];

            var parent = Category.builder()
                    .id(1L)
                    .title(RandomStringUtils.randomAlphabetic(5))
                    .slug(RandomStringUtils.randomAlphabetic(5))
                    .build();

            return Category.builder()
                    .id(1L)
                    .title(createCategory.getTitle())
                    .slug(createCategory.getSlug())
                    .parent(parent)
                    .build();
        };
    }

    public static Answer<Object> answerRetrieveCategory() {
        return i -> {
            Long categoryId = (Long) i.getArguments()[0];

            return Category.builder()
                    .id(categoryId)
                    .title(RandomStringUtils.randomAlphabetic(5))
                    .slug(RandomStringUtils.randomAlphabetic(5))
                    .seoTitle(RandomStringUtils.randomAlphabetic(5))
                    .metaDesc(RandomStringUtils.randomAlphabetic(5))
                    .imgUrl(RandomStringUtils.randomAlphabetic(5))
                    .parent(createCategory())
                    .build();
        };
    }

    public static Answer<Object> answerUpdateCategory() {
        return i -> i.getArguments()[0];
    }

    public static Category createCategory() {
        return Category.builder()
                .id(RandomUtils.nextLong())
                .title(RandomStringUtils.randomAlphabetic(5))
                .slug(RandomStringUtils.randomAlphabetic(5))
                .seoTitle(RandomStringUtils.randomAlphabetic(5))
                .metaDesc(RandomStringUtils.randomAlphabetic(5))
                .imgUrl(RandomStringUtils.randomAlphabetic(5))
                .build();
    }
}
