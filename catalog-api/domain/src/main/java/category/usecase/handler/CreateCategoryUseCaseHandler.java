package category.usecase.handler;

import category.model.Category;
import category.port.CategoryPort;
import lombok.RequiredArgsConstructor;
import category.usecase.CreateCategory;
import usecase.UseCaseHandler;

@RequiredArgsConstructor
public class CreateCategoryUseCaseHandler implements UseCaseHandler<Category, CreateCategory> {

    private final CategoryPort categoryPort;

    @Override
    public Category handle(CreateCategory createCategory) {
        return categoryPort.create(createCategory);
    }
}
