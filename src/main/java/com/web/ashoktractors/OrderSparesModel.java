package com.web.ashoktractors;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "spare_part_orders")
public class OrderSparesModel {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    
    @Column(name = "part_id", nullable = false)
    private int partId;

    
    @Column(name = "supplier_id", nullable = false)
    private Integer supplierId;

    
    @Column(name = "quantity_ordered", nullable = false)
    private Integer quantityOrdered;

    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "order_date", columnDefinition = "DATETIME DEFAULT GETDATE()")
    private Date orderDate = new Date();

    // Getters and setters
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getPartId() {
        return partId;
    }

    public void setPartId(Integer partId) {
        this.partId = partId;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getQuantityOrdered() {
        return quantityOrdered;
    }

    public void setQuantityOrdered(Integer quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
