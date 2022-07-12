package category;

import category.model.Category;
import category.port.CategoryPort;
import category.usecase.CreateCategory;
import category.usecase.handler.CreateCategoryUseCaseHandler;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CategoryCreateTest {

    @Mock
    CategoryPort categoryPort;

    @InjectMocks
    CreateCategoryUseCaseHandler createCategoryUseCaseHandler;

    @DisplayName("Should create category successfully")
    @Test
    void should_create_category_successfully() {
        // Given
        when(categoryPort.create(any())).thenAnswer(i -> {
            var createCategory = (CreateCategory) i.getArguments()[0];
            var category = Category.newCategory(1L, createCategory.getTitle(), Category.newCategory(createCategory.getParentId(), "", null));

            return category;
        });

        var createCategory = CreateCategory.builder()
                .title(RandomStringUtils.randomAlphabetic(5))
                .parentId(1L)
                .build();

        // When
        var category = createCategoryUseCaseHandler.handle(createCategory);

        // Then
        verify(categoryPort, times(1)).create(any());
    }
}
