export class Category {
  name: string;

  static cloneBase(category: Category): Category {
    const clonedCategory: Category = new Category();
    clonedCategory.name = category.name;
    return clonedCategory;
  }
}

export class CategoryStr {
}
