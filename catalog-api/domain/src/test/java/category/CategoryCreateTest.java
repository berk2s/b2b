package category;

import category.model.Category;
import category.port.CategoryPort;
import category.usecase.CreateCategory;
import category.usecase.handler.CreateCategoryUseCaseHandler;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static factory.MockFactory.answerCreateCategory;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryCreateTest {

    @Mock
    CategoryPort categoryPort;

    @InjectMocks
    CreateCategoryUseCaseHandler createCategoryUseCaseHandler;

    @DisplayName("Should create category successfully")
    @Test
    void should_create_category_successfully() {
        // Given
        when(categoryPort.create(any())).thenAnswer(answerCreateCategory());

        var createCategory = CreateCategory.builder()
                .title(RandomStringUtils.randomAlphabetic(5))
                .parentId(1L)
                .build();

        // When
        var category = createCategoryUseCaseHandler.handle(createCategory);

        // Then
        assertThat(category).isNotNull()
                        .returns(createCategory.getTitle(), Category::getTitle)
                        .returns(createCategory.getParentId(), i -> i.getParent().getId());

        verify(categoryPort, times(1)).create(any());
    }
}
