package com.task.springshop.model.entity;

public class InnerCategory {
    private long innerCategoryId;
    private String name;
    private long categoryId;

    public InnerCategory(){}

    public InnerCategory(long innerCategoryId, String name, long categoryId) {
        this.innerCategoryId = innerCategoryId;
        this.name = name;
        this.categoryId = categoryId;
    }

    public long getInnerCategoryId() {
        return innerCategoryId;
    }

    public void setInnerCategoryId(long innerCategoryId) {
        this.innerCategoryId = innerCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        InnerCategory newInnerCategory = (InnerCategory) obj;
        return innerCategoryId == newInnerCategory.innerCategoryId && (name != null ? name.equals(newInnerCategory.name) : newInnerCategory.name == null)
                && categoryId == newInnerCategory.categoryId;
    }

    @Override
    public int hashCode() {
        int result = (int) (innerCategoryId ^ (innerCategoryId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) (categoryId ^ (categoryId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("InnerCategory{");
        sb.append("innerCategoryId=").append(innerCategoryId);
        sb.append(", name='").append(name);
        sb.append("categoryId=").append(categoryId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
