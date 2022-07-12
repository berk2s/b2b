package category.port;

import category.model.Category;
import category.usecase.CreateCategory;

public interface CategoryPort {

    Category create(CreateCategory category);
}
