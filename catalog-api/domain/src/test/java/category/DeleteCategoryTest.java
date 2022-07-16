package category;

import category.port.CategoryPort;
import category.usecase.DeleteCategory;
import category.usecase.handler.DeleteCategoryUseCaseHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteCategoryTest {

    @Mock
    CategoryPort categoryPort;

    @InjectMocks
    DeleteCategoryUseCaseHandler deleteCategoryUseCaseHandler;

    @DisplayName("Should delete category successfully")
    @Test
    void should_delete_category_successfully() {
        // Given
        var deleteCategory = DeleteCategory.builder()
                .categoryId(1L)
                .build();

        doNothing().when(categoryPort).delete(any());

        // When
        deleteCategoryUseCaseHandler.handle(deleteCategory);

        // Verify
        verify(categoryPort, times(1)).delete(any());
    }

}
