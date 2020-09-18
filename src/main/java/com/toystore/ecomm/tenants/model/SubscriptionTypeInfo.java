package com.toystore.ecomm.tenants.model;

import org.springframework.validation.annotation.Validated;

import com.toystore.ecomm.tenants.factory.POJOFactory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Subscription
 */
@Validated

@Entity
@Table(name = "SUBSCRIPTION_TYPE")
public class SubscriptionTypeInfo {
	
	static {
        POJOFactory.register("SUBSCRIPTIONTYPEINFO", SubscriptionTypeInfo.class);
    }

	@Id
	@Column(name = "PLAN_TYPE_ID", nullable = false)
	private Integer planTypeId;

	@Column(name = "PLAN_NAME", nullable = false)
	private String planName;

	@Column(name = "PRICING", nullable = false)
	private String pricing;

	public Integer getPlanTypeId() {
		return planTypeId;
	}

	public void setPlanTypeId(Integer planTypeId) {
		this.planTypeId = planTypeId;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getPricing() {
		return pricing;
	}

	public void setPricing(String pricing) {
		this.pricing = pricing;
	}
}
