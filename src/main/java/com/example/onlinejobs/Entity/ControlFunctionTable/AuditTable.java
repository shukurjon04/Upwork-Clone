package com.example.onlinejobs.Entity.ControlFunctionTable;

import com.example.onlinejobs.Entity.MainUserTable.UserJobs;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

@EntityListeners(AuditingEntityListener.class)
public class AuditTable {

    @Column(updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column()
    @LastModifiedDate
    private Timestamp updatedAt;

    @Column(updatable = false)
    @CreatedBy
    private UserJobs createdBy;

    @Column(insertable = false)
    @LastModifiedBy
    private UserJobs updatedBy;


    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public UserJobs getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserJobs createdBy) {
        this.createdBy = createdBy;
    }

    public UserJobs getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(UserJobs updatedBy) {
        this.updatedBy = updatedBy;
    }
}
