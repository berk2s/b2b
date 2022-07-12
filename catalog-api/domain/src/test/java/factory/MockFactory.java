package factory;

import category.model.Category;
import category.usecase.CreateCategory;
import org.apache.commons.lang3.RandomStringUtils;
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
}
