package category.usecase.handler;

import category.model.Category;
import category.port.CategoryPort;
import category.usecase.UpdateCategory;
import lombok.RequiredArgsConstructor;
import usecase.UseCaseHandler;

import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UpdateCategoryUseCaseHandler implements UseCaseHandler<Category, UpdateCategory> {

    private final CategoryPort categoryPort;

    @Override
    public Category handle(UpdateCategory updateCategory) {
        var category = categoryPort.retrieve(updateCategory.getCategoryId());

        Category parent = category.getParent();

        if (updateCategory.getParentId() != null) {
            parent = categoryPort.retrieve(updateCategory.getParentId());
        }

        var childCategories = updateCategory.getChildCategoryIds().stream()
                .map(categoryPort::retrieve)
                .collect(Collectors.toSet());

        var updatedCategory = Category.builder()
                .id(updateCategory.getCategoryId())
                .title(updateCategory.getTitle())
                .slug(updateCategory.getSlug())
                .seoTitle(updateCategory.getSeoTitle())
                .metaDesc(updateCategory.getMetaDesc())
                .imgUrl(updateCategory.getImgUrl())
                .parent(parent)
                .child(childCategories)
                .build();

        return categoryPort.update(updatedCategory);
    }
}
