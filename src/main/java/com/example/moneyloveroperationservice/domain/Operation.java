package com.example.moneyloveroperationservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "operations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Operation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String date;
    Long walletId;
    @OneToOne
    Category category;
    Long operationSum;
    String note;
}
