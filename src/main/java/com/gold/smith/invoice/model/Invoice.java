package com.gold.smith.invoice.model;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "order_invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ref_id")
    private Long refId;
    @Column(name = "order_number")
    private Long orderNumber;
    @NonNull
    @Column(name = "showroom")
    private String showroom;
    @NonNull
    @Column(name = "item_name")
    private String itemName;
    @NonNull
    @Column(name = "gross_weight")
    private Float grossWeight;
    @Column(name = "total_stones")
    @NonNull
    private Integer totalStones;

    @Column(name = "stone_weight_grams")
    private Float stoneWeightInGrams;

    @Column(name = "stone_weight_carat")
    private Float stoneWeightInCarat;

    @Column(name = "net_weight")
    private Float netWeight;
    @Column(name = "gold_92_per")
    private Float gold92Per;
    @Column(name = "gold_12_per")
    private Float gold12Per;
    @Column(name = "delivery_date")
    private Date deliveryDate;
    @Column(name = "making_charges")
    private Double makingCharges;

    public Long getRefId() {
        return refId;
    }

    public void setRefId(Long refId) {
        this.refId = refId;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    @NonNull
    public String getShowroom() {
        return showroom;
    }

    public void setShowroom(@NonNull String showroom) {
        this.showroom = showroom;
    }

    @NonNull
    public String getItemName() {
        return itemName;
    }

    public void setItemName(@NonNull String itemName) {
        this.itemName = itemName;
    }

    @NonNull
    public Float getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(@NonNull Float grossWeight) {
        this.grossWeight = grossWeight;
    }

    @NonNull
    public Integer getTotalStones() {
        return totalStones;
    }

    public void setTotalStones(@NonNull Integer totalStones) {
        this.totalStones = totalStones;
    }

    public Float getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(Float netWeight) {
        this.netWeight = netWeight;
    }

    public Float getGold92Per() {
        return gold92Per;
    }

    public void setGold92Per(Float gold92Per) {
        this.gold92Per = gold92Per;
    }

    public Float getGold12Per() {
        return gold12Per;
    }

    public void setGold12Per(Float gold12Per) {
        this.gold12Per = gold12Per;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Double getMakingCharges() {
        return makingCharges;
    }

    public void setMakingCharges(Double makingCharges) {
        this.makingCharges = makingCharges;
    }

    public Float getStoneWeightInGrams() {
        return stoneWeightInGrams;
    }

    public void setStoneWeightInGrams(Float stoneWeightInGrams) {
        this.stoneWeightInGrams = stoneWeightInGrams;
    }

    public Float getStoneWeightInCarat() {
        return stoneWeightInCarat;
    }

    public void setStoneWeightInCarat(Float stoneWeightInCarat) {
        this.stoneWeightInCarat = stoneWeightInCarat;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "refId=" + refId +
                ", orderNumber=" + orderNumber +
                ", showroom='" + showroom + '\'' +
                ", itemName='" + itemName + '\'' +
                ", grossWeight=" + grossWeight +
                ", totalStones=" + totalStones +
                ", stoneWeightGrams=" + stoneWeightInGrams +
                ", stoneWeightCarat=" + stoneWeightInCarat +
                ", netWeight=" + netWeight +
                ", gold92Per=" + gold92Per +
                ", gold12Per=" + gold12Per +
                ", deliveryDate=" + deliveryDate +
                ", makingCharges=" + makingCharges +
                '}';
    }
}
