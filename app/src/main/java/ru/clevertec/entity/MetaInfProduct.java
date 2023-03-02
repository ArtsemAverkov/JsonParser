package ru.clevertec.entity;


public class MetaInfProduct {
    private Long id;
    private boolean isDiscount;
    private Info info;

    public MetaInfProduct() {
    }

    public MetaInfProduct(Long id, boolean isDiscount, Info info) {
        this.id = id;
        this.isDiscount = isDiscount;
        this.info = info;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isDiscount() {
        return isDiscount;
    }

    public void setDiscount(boolean discount) {
        isDiscount = discount;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "MetaInfProduct{" +
                "id=" + id +
                ", isDiscount=" + isDiscount +
                ", info=" + info +
                '}';
    }
}
