package com.task.springshop.model.entity;

import java.math.BigDecimal;

public class Product {
    private long productId;
    private String name;
    private String description;
    private String imageUrl;
    private BigDecimal price;
    private int quantity;
    private ProductStatus status;
    private long innerCategoryId;

    public Product(){}

    public Product(long productId, String name, String description, String imageUrl, BigDecimal price, int quantity, ProductStatus status, long innerCategoryId) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
        this.innerCategoryId = innerCategoryId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public long getCategoryId() {
        return innerCategoryId;
    }

    public void setCategoryId(long categoryId) {
        this.innerCategoryId = categoryId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product newProduct = (Product) obj;
        if (productId != newProduct.productId) return false;
        if (name != null ? !name.equals(newProduct.name) : newProduct.name != null) return false;
        if (description != null ? !description.equals(newProduct.description) : newProduct.description != null) return false;
        if (imageUrl != null ? !imageUrl.equals(newProduct.imageUrl) : newProduct.imageUrl != null) return false;
        if (price.compareTo(newProduct.price) != 0) return false;
        if (quantity != newProduct.quantity) return false;
        if (status != newProduct.status) return false;
        return innerCategoryId == newProduct.innerCategoryId;
    }

    @Override
    public int hashCode() {
        int result = (int) (productId ^ (productId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        result = 31 * result + price.intValue();
        result = 31 * result + quantity;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (int) (innerCategoryId ^ (innerCategoryId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("productId=").append(productId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", imageUrl='").append(imageUrl).append('\'');
        sb.append(", price=").append(price);
        sb.append(", quantity=").append(quantity);
        sb.append(", status='").append(status).append('\'');
        sb.append(", categoryId=").append(innerCategoryId);
        sb.append('}');
        return sb.toString();
    }
}
