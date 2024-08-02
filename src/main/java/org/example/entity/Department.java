package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dept_info")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int departmentId;
    private String  departmentName;
    private String departmentAddress;
    private String departmentCode;

    public void updateFrom(Department other) {
        if (other.departmentName != null && !other.departmentName.isEmpty()) {
            this.departmentName = other.departmentName;
        }
        if (other.departmentAddress != null && !other.departmentAddress.isEmpty()) {
            this.departmentAddress = other.departmentAddress;
        }
        if (other.departmentCode != null && !other.departmentCode.isEmpty()) {
            this.departmentCode = other.departmentCode;
        }
    }
}
