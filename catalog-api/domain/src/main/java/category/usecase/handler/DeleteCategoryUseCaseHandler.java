package category.usecase.handler;

import category.port.CategoryPort;
import category.usecase.DeleteCategory;
import lombok.RequiredArgsConstructor;
import usecase.VoidUseCaseHandler;

@RequiredArgsConstructor
public class DeleteCategoryUseCaseHandler implements VoidUseCaseHandler<DeleteCategory> {

    private final CategoryPort categoryPort;

    @Override
    public void handle(DeleteCategory deleteCategory) {
        categoryPort.delete(deleteCategory.getCategoryId());
    }
}
