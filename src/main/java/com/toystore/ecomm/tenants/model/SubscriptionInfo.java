package com.toystore.ecomm.tenants.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;
import org.springframework.validation.annotation.Validated;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Subscription
 */
@Validated

@Entity
@Table(name = "SUBSCRIPTION")
public class SubscriptionInfo {

	@Id
	@Column(name = "SUBSCRIPTION_ID", nullable = false)
	private Integer subscriptionId;

	@Column(name = "TENANT_ID", nullable = false)
	private Integer tenantId;

	@Column(name = "PLAN_TYPE_ID", nullable = false)
	private Integer planTypeId = null;

	@ManyToOne
	@JoinColumn(name = "PLAN_TYPE_ID", insertable = false, updatable = false)
	private SubscriptionTypeInfo planType;

	@ManyToOne
	@JoinColumn(name = "TENANT_ID", insertable = false, updatable = false)
	private TenantInfo tenant;

	@Column(name = "RENEWAL_TYPE_ID", nullable = false)
	private Integer renewalTypeId = null;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "RENEWAL_TYPE_ID", referencedColumnName = "RENEWAL_TYPE_ID", insertable = false, updatable = false)
	private RenewalTypeInfo renewalType;

	@Column(name = "START_DATE", nullable = false)
	private Date startDate = null;

	@Column(name = "END_DATE", nullable = false)
	private Date endDate = null;

	@Column(name = "VALID", nullable = false)
	private String isValid = null;

	@Column(name = "CREATED_TS", nullable = false)
	private Timestamp createdTS = null;

	@Column(name = "LAST_UPDATED_TS", nullable = false)
	private Timestamp lastUpdatedTS = null;

	@Column(name = "CREATED_BY", nullable = false)
	private String createdBy = null;

	public Integer getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(Integer subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

	public Integer getRenewalTypeId() {
		return renewalTypeId;
	}

	public void setRenewalTypeId(Integer renewalTypeId) {
		this.renewalTypeId = renewalTypeId;
	}

	public Integer getPlanTypeId() {
		return planTypeId;
	}

	public void setPlanTypeId(Integer planTypeId) {
		this.planTypeId = planTypeId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public Timestamp getCreatedTS() {
		return createdTS;
	}

	public void setCreatedTS(Timestamp createdTS) {
		this.createdTS = createdTS;
	}

	public Timestamp getLastUpdatedTS() {
		return lastUpdatedTS;
	}

	public void setLastUpdatedTS(Timestamp lastUpdatedTS) {
		this.lastUpdatedTS = lastUpdatedTS;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public RenewalTypeInfo getRenewalType() {
		return renewalType;
	}

	public void setRenewalType(RenewalTypeInfo renewalType) {
		this.renewalType = renewalType;
	}

	public SubscriptionTypeInfo getPlanType() {
		return planType;
	}

	public void setPlanType(SubscriptionTypeInfo planType) {
		this.planType = planType;
	}

	public TenantInfo getTenant() {
		return tenant;
	}

	public void setTenant(TenantInfo tenant) {
		this.tenant = tenant;
	}

	public SubscriptionInfo withId(Integer id) {
		this.setSubscriptionId(id);
		;
		return this;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		SubscriptionInfo subscription = (SubscriptionInfo) o;
		return Objects.equals(this.subscriptionId, subscription.subscriptionId)
				&& Objects.equals(this.planType.getPlanTypeId(), subscription.planType.getPlanTypeId())
				&& Objects.equals(this.tenant.getTenantId(), subscription.tenant.getTenantId())
				&& Objects.equals(this.getRenewalType().getRenewalTypeId(),
						subscription.getRenewalType().getRenewalTypeId())
				&& Objects.equals(this.startDate, subscription.startDate)
				&& Objects.equals(this.endDate, subscription.endDate)
				&& Objects.equals(this.isValid, subscription.isValid);
	}

	@Override
	public int hashCode() {
		return Objects.hash(subscriptionId, tenant.getTenantId(), planType.getPlanTypeId(),
				renewalType.getRenewalTypeId(), startDate, endDate, isValid);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Subscription {\n");

		sb.append("    subscriptionId: ").append(toIndentedString(subscriptionId)).append("\n");
		sb.append("    tenantId: ").append(toIndentedString(tenant.getTenantId())).append("\n");
		sb.append("    planTypeId: ").append(toIndentedString(planType.getPlanTypeId())).append("\n");
		sb.append("    renewalTypeId: ").append(toIndentedString(renewalType.getRenewalTypeId())).append("\n");
		sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
		sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
		sb.append("    isValid: ").append(toIndentedString(isValid)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
