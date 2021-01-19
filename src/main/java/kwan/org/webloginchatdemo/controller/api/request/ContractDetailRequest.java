package kwan.org.webloginchatdemo.controller.api.request;

import lombok.Data;

/**
 * Created by https://github.com/kwanpham
 */
@Data
public class ContractDetailRequest {

    private Long id;



    private String type;



    private String companyName;


    private Long contractId;



    private String contractDt;



    private String contractAmount;


    private String contractTerm;


    private String paymentRatio;



    private String paymentDt;


    private String paymentValue;


    private String paymentRemain;



    private String paymentInvoiceDt;


    private String changedContent;



}
