package factory;

import category.model.Category;
import category.usecase.CreateCategory;
import org.mockito.stubbing.Answer;

public final class MockFactory {

    public static Answer<Object> answerCreateCategory() {
        return i -> {
            var createCategory = (CreateCategory) i.getArguments()[0];
            var category = Category.newCategory(1L, createCategory.getTitle(), Category.newCategory(createCategory.getParentId(), "", null));

            return category;
        };
    }
}
