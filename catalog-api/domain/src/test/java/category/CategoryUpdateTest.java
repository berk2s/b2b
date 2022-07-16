package category;

import category.model.Category;
import category.port.CategoryPort;
import category.usecase.UpdateCategory;
import category.usecase.handler.UpdateCategoryUseCaseHandler;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;

import static factory.MockFactory.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryUpdateTest {

    @Mock
    private CategoryPort categoryPort;

    @InjectMocks
    private UpdateCategoryUseCaseHandler updateCategoryUseCaseHandler;

    @DisplayName("Should update category successfully")
    @Test
    void should_update_category_successfully() {
        // Given
        var childCategoryIds = List.of(5L, 6L, 7L, 8L);

        var updateCategory = UpdateCategory.builder()
                .categoryId(1L)
                .title(RandomStringUtils.randomAlphabetic(5))
                .slug(RandomStringUtils.randomAlphabetic(5))
                .seoTitle(RandomStringUtils.randomAlphabetic(5))
                .metaDesc(RandomStringUtils.randomAlphabetic(5))
                .imgUrl(RandomStringUtils.randomAlphabetic(5))
                .parentId(2L)
                .childCategoryIds(childCategoryIds)
                .build();

        when(categoryPort.retrieve(any())).thenAnswer(answerRetrieveCategory());
        when(categoryPort.update(any())).thenAnswer(answerUpdateCategory());

        // When
        var updatedCategory = updateCategoryUseCaseHandler.handle(updateCategory);

        // Then
        assertThat(updatedCategory).isNotNull()
                .returns(updateCategory.getTitle(), Category::getTitle)
                .returns(updateCategory.getSlug(), Category::getSlug)
                .returns(updateCategory.getSeoTitle(), Category::getSeoTitle)
                .returns(updateCategory.getMetaDesc(), Category::getMetaDesc)
                .returns(updateCategory.getImgUrl(), Category::getImgUrl)
                .returns(updateCategory.getParentId(), i -> i.getParent().getId());

        assertThat(updatedCategory.getChild().stream().map(Category::getId).collect(Collectors.toList()))
                .contains(5L, 6L, 7L, 8L);

        verify(categoryPort, times(6)).retrieve(any());
        verify(categoryPort, times(1)).update(any());
    }


}
