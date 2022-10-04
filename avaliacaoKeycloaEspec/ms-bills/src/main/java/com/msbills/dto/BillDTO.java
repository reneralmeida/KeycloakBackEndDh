package com.msbills.dto;

import com.msbills.models.Bill;
import lombok.Data;

import java.io.Serializable;

@Data
public class BillDTO implements Serializable {

    private String idBill;
    private String customerBill;
    private String productBill;
    private Double totalPrice;

    public static BillDTO billToDTO(Bill bill) {
        BillDTO dto = new BillDTO();
        dto.setIdBill(bill.getIdBill());
        dto.setCustomerBill(bill.getCustomerBill());
        dto.setProductBill(bill.getProductBill());
        dto.setTotalPrice(bill.getTotalPrice());
        return dto;
    }

    public Bill toBill() {
        Bill bil = new Bill();
        bil.setIdBill(this.getIdBill());
        bil.setCustomerBill(this.getCustomerBill());
        bil.setProductBill(this.getProductBill());
        bil.setTotalPrice(this.getTotalPrice());
        return bil;
    }

}
