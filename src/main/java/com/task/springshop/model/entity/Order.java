package com.task.springshop.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Order {
    private long orderId;
    private LocalDate date;
    private BigDecimal price;
    private String address;
    private OrderStatus status;
    private String clientName;
    private String clientSurname;

    public Order(){}

    public Order(long orderId, LocalDate date, BigDecimal price, String address, OrderStatus status, String clientName, String clientSurname) {
        this.orderId = orderId;
        this.date = date;
        this.price = price;
        this.address = address;
        this.status = status;
        this.clientName = clientName;
        this.clientSurname = clientSurname;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public void setClientSurname(String clientSurname) {
        this.clientSurname = clientSurname;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Order newOrder = (Order) obj;
        if (orderId != newOrder.orderId) return false;
        if (date != null ? !date.equals(newOrder.date) : newOrder.date != null) return false;
        if (price.compareTo(newOrder.price) != 0) return false;
        if (address != null ? !address.equals(newOrder.address) : newOrder.address != null) return false;
        if (clientName != null ? !clientName.equals(newOrder.clientName) : newOrder.clientName != null) return false;
        return clientSurname != null ? !clientSurname.equals(newOrder.clientSurname) : newOrder.clientSurname != null;
    }

    @Override
    public int hashCode() {
        int result = (int) (orderId ^ (orderId >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + price.intValue();
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (clientName != null ? clientName.hashCode() : 0);
        result = 31 * result + (clientSurname != null ? clientSurname.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("orderId=").append(orderId);
        sb.append(", date='").append(date).append('\'');
        sb.append(", price=").append(price);
        sb.append(", address='").append(address).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", clientName=").append(clientName).append('\'');
        sb.append(", clientSurname=").append(clientSurname).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
