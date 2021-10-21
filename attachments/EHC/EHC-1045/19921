package com.renxo.cms.domain.network;

import java.io.Serializable;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import com.renxo.cms.domain.LocalizedEntityImpl;
import com.renxo.cms.domain.category.TopChart;
import com.renxo.cms.domain.org.Organization;
import com.renxo.cms.remoting.RemotingPoolable;

/**
 * A short number assigned by mobile network operators to premium SMS service
 * providers. Usually a 3, 4 or 5 digit number. These numbers are mobile network
 * operator-specific, they are not valid across different network operators in
 * the same country.
 * 
 */
@RemotingPoolable
public class ShortNumber extends
		LocalizedEntityImpl<ShortNumber.LocalizedFields> {

	private static final long serialVersionUID = -3957569830663305358L;

	// ----------------------------------------------------------------------
	// Fields
	// ----------------------------------------------------------------------

	private String number;

	private String comment;

	private NetworkOperator networkOperator;

	private ShortNumber replyPath;

	private Organization defaultPartner;

	private TopChart defaultTopChart;

	private Boolean shared;

	private Boolean active;

	private Boolean contentBilling;

	private Boolean mobileOriginatedBilling;

	private Boolean mobileTerminatedBilling;

	private Boolean simBilling;

	private Boolean deliveryStatusNotification;

	private Boolean rejectOnTimeout;

	private Boolean outputWapPushAsText;

	private Boolean normalizeWapPushDiacritics;

	private Boolean enableWapPushHelpMessages;

	private Boolean enableContentRequestCombinedDelivery;

	private Boolean enableTextRequestCombinedDelivery;

	private Boolean enableTextSubscriptionCombinedDelivery;

	private Integer maxRequestsPerTimeUnit;

	private TimeUnit timeUnit;

	private Integer maxAttempts;

	private Long reattemptDelay;

	private Double reattemptDelayMultiplier;

	private Long deliveryStatusNotificationTimeout;

	private Long sessionTimeout;

	private Long sessionWarningTimeout;

	private String defaultReplyMessageText;

	private ApplicationType defaultApplicationType;

	private Integer maxSubscriptionsPerDay;

	private Integer maxSubscriptionsPerWeek;

	private Integer maxSubscriptionsPerMonth;

	private Integer maxCurrentSubscriptions;

	private Long unsubscriptionRepellentPeriod;

	private Boolean unsubscriptionRepelOnce;

	private Boolean unsubscriptionRepelOnMultipleSubscriptions;

	private Boolean enablePreferredSubscriptionPlanDefault;

	private Boolean avoidDefaultSubscriptionPlanForCurrentSubscribers;

	private Boolean enableUnsubscriptionPatternDetection;

	private Boolean assumeCancelAll;

	private Boolean skipCancelAllConfirmation;

	private Boolean externallyManaged;

	private Boolean downloadRequiresExternalWapConfirmation;

	// ----------------------------------------------------------------------
	// Business identity text
	// ----------------------------------------------------------------------

	@Override
	protected String businessIdentityText(Locale inLocale) {
		if (getNumber() != null && getNetworkOperator() != null) {
			return getNumber() + " "
					+ getNetworkOperator().identityText(inLocale);
		} else {
			return getNumber();
		}
	}

	// ----------------------------------------------------------------------
	// Business methods
	// ----------------------------------------------------------------------

	/**
	 * Gets the normalized id for this short number. The normalized id is the
	 * number, followed by a period, the network operator's text id, another
	 * period, and the lowercase country ISO code.
	 * <p>
	 * This string uniquely identifies a short number, and is friendlier for
	 * configuration and other purposes than the regular id.
	 * 
	 * @return the normalized id.
	 */
	public String normalizedId() {
		if (getNumber() != null && getNetworkOperator() != null) {
			return getNumber() + "." + getNetworkOperator().normalizedId();
		} else {
			return null;
		}
	}

	// ----------------------------------------------------------------------
	// Localization methods
	// ----------------------------------------------------------------------

	/**
	 * Gets the localized default reply message text for this short number and
	 * the given locale. A best effort is made to return the closest match
	 * according to locale, falling back to the default reply message text.
	 * 
	 * @param locale
	 *            the locale for which to return the localized default reply
	 *            message text.
	 * @return the localized default reply message tex.
	 */
	public String localizedDefaultReplyMessageText(Locale locale) {
		return localizedProperty("defaultReplyMessageText", locale);
	}

	// ----------------------------------------------------------------------
	// Getters and setters
	// ----------------------------------------------------------------------

	/**
	 * Gets the number.
	 * 
	 * @return the number.
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * Sets the number.
	 * 
	 * @param number
	 *            the number.
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * Gets the comment for this short number.
	 * 
	 * @return the comment.
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * Sets the comment for this short number.
	 * 
	 * @param comment
	 *            a comment.
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * Gets the network operator.
	 * 
	 * @return the network operator.
	 */
	public NetworkOperator getNetworkOperator() {
		return networkOperator;
	}

	/**
	 * Sets the network operator.
	 * 
	 * @param networkOperator
	 *            the network operator.
	 */
	public void setNetworkOperator(NetworkOperator networkOperator) {
		this.networkOperator = networkOperator;
	}

	/**
	 * Gets the reply path. If <code>null</code>, then it is assumed that
	 * replies should be routed through this same short number.
	 * 
	 * @return the reply path.
	 */
	public ShortNumber getReplyPath() {
		return replyPath;
	}

	/**
	 * Sets the reply path.
	 * 
	 * @param replyPath
	 *            the reply path.
	 */
	public void setReplyPath(ShortNumber replyPath) {
		this.replyPath = replyPath;
	}

	/**
	 * Gets the default partner.
	 * 
	 * @return the default partner.
	 */
	public Organization getDefaultPartner() {
		return defaultPartner;
	}

	/**
	 * Sets the default partner.
	 * 
	 * @param defaultPartner
	 *            the default partner.
	 */
	public void setDefaultPartner(Organization defaultPartner) {
		this.defaultPartner = defaultPartner;
	}

	/**
	 * Gets the default top chart.
	 * 
	 * @return the default top chart.
	 */
	public TopChart getDefaultTopChart() {
		return defaultTopChart;
	}

	/**
	 * Sets the default top chart.
	 * 
	 * @param defaultTopChart
	 *            the default top chart.
	 */
	public void setDefaultTopChart(TopChart defaultTopChart) {
		this.defaultTopChart = defaultTopChart;
	}

	/**
	 * Gets whether this number is shared with other service providers. If
	 * <code>true</code>, multiplexing will usually be handled by an
	 * intermediary between the mobile network operator and the system.
	 * 
	 * @return the shared state.
	 */
	public Boolean getShared() {
		return shared;
	}

	/**
	 * Sets the shared state.
	 * 
	 * @param shared
	 *            the shared state.
	 */
	public void setShared(Boolean shared) {
		this.shared = shared;
	}

	/**
	 * Gets whether this short number is active.
	 * 
	 * @return <code>true</code> if active, <code>false</code> otherwise.
	 */
	public Boolean getActive() {
		return active;
	}

	/**
	 * Sets whether this short number is active.
	 * 
	 * @param active
	 *            <code>true</code> if active, <code>false</code> otherwise.
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}

	/**
	 * Gets whether this short number is used for content billing.
	 * 
	 * @return whether this short number is used for content billing.
	 */
	public Boolean getContentBilling() {
		return contentBilling;
	}

	/**
	 * Sets whether this short number is used for content billing.
	 * 
	 * @param contentBilling
	 *            whether this short number is used for content billing.
	 */
	public void setContentBilling(Boolean contentBilling) {
		this.contentBilling = contentBilling;
	}

	/**
	 * Gets whether this short number is used for MO billing.
	 * 
	 * @return whether this short number is used for MO billing.
	 */
	public Boolean getMobileOriginatedBilling() {
		return mobileOriginatedBilling;
	}

	/**
	 * Sets whether this short number is used for MO billing.
	 * 
	 * @param mobileOriginatedBilling
	 *            whether this short number is used for MO billing.
	 */
	public void setMobileOriginatedBilling(Boolean mobileOriginatedBilling) {
		this.mobileOriginatedBilling = mobileOriginatedBilling;
	}

	/**
	 * Gets whether this short number is used for MT billing.
	 * 
	 * @return whether this short number is used for MT billing.
	 */
	public Boolean getMobileTerminatedBilling() {
		return mobileTerminatedBilling;
	}

	/**
	 * Sets whether this short number is used for MT billing.
	 * 
	 * @param mobileTerminatedBilling
	 *            whether this short number is used for MT billing.
	 */
	public void setMobileTerminatedBilling(Boolean mobileTerminatedBilling) {
		this.mobileTerminatedBilling = mobileTerminatedBilling;
	}

	/**
	 * Gets whether this short number is used for SIM billing.
	 * 
	 * @return whether this short number is used for SIM billing.
	 */
	public Boolean getSimBilling() {
		return simBilling;
	}

	/**
	 * Sets whether this short number is used for SIM billing.
	 * 
	 * @param simBilling
	 *            whether this short number is used for SIM billing.
	 */
	public void setSimBilling(Boolean simBilling) {
		this.simBilling = simBilling;
	}

	/**
	 * Gets whether this short number supports delivery status notification.
	 * 
	 * @return whether this short number supports delivery status notification.
	 */
	public Boolean getDeliveryStatusNotification() {
		return deliveryStatusNotification;
	}

	/**
	 * Sets whether this short number supports delivery status notification.
	 * 
	 * @param deliveryStatusNotification
	 *            whether this short number supports delivery status
	 *            notification.
	 */
	public void setDeliveryStatusNotification(Boolean deliveryStatusNotification) {
		this.deliveryStatusNotification = deliveryStatusNotification;
	}

	/**
	 * Gets whether to mark messages as rejected on delivery status notification
	 * timeout.
	 * 
	 * @return whether to mark messages as rejected on delivery status
	 *         notification timeout.
	 */
	public Boolean getRejectOnTimeout() {
		return rejectOnTimeout;
	}

	/**
	 * Sets whether to mark messages as rejected on delivery status notification
	 * timeout.
	 * 
	 * @param rejectOnTimeout
	 *            whether to mark messages as rejected on delivery status
	 *            notification timeout.
	 */
	public void setRejectOnTimeout(Boolean rejectOnTimeout) {
		this.rejectOnTimeout = rejectOnTimeout;
	}

	/**
	 * Gets whether to output WAP Push messages as text.
	 * 
	 * @return <code>true</code>to enable the option, <code>false</code>
	 *         otherwise.
	 */
	public Boolean getOutputWapPushAsText() {
		return outputWapPushAsText;
	}

	/**
	 * Sets whether to output WAP Push messages as text.
	 * 
	 * @param outputWapPushAsText
	 *            <code>true</code> to enable the option, <code>false</code>
	 *            otherwise.
	 */
	public void setOutputWapPushAsText(Boolean outputWapPushAsText) {
		this.outputWapPushAsText = outputWapPushAsText;
	}

	/**
	 * Gets whether to enable sending help plain text messages along with WAP
	 * Push messages. Many times customers are unfamiliar with WAP Push messages
	 * and they do not know where to look for these, so a plain text message
	 * explaining where to find them is usually beneficial. Sometimes network
	 * operators impose penalties when the amount of generated output messages
	 * exceeds the number of input messages. Since this behavior generates 2
	 * output messages for every input message, it might generate considerable
	 * penalties, and therefore can be disabled if needed.
	 * 
	 * @return <code>true</code>to enable the option, <code>false</code>
	 *         otherwise.
	 */
	public Boolean getEnableWapPushHelpMessages() {
		return enableWapPushHelpMessages;
	}

	/**
	 * Sets whether to enable sending help plain text messages along with WAP
	 * Push messages.
	 * 
	 * @param enableWapPushHelpMessages
	 *            <code>true</code> to enable the option, <code>false</code>
	 *            otherwise.
	 */
	public void setEnableWapPushHelpMessages(Boolean enableWapPushHelpMessages) {
		this.enableWapPushHelpMessages = enableWapPushHelpMessages;
	}

	/**
	 * Gets whether whether to enable combined delivery for content requests.
	 * 
	 * @return <code>true</code>to enable the option, <code>false</code>
	 *         otherwise.
	 */
	public Boolean getEnableContentRequestCombinedDelivery() {
		return enableContentRequestCombinedDelivery;
	}

	/**
	 * Sets whether to enable combined delivery for content requests.
	 * 
	 * @param enableContentRequestCombinedDelivery
	 *            <code>true</code> to enable the option, <code>false</code>
	 *            otherwise.
	 */
	public void setEnableContentRequestCombinedDelivery(
			Boolean enableContentRequestCombinedDelivery) {
		this.enableContentRequestCombinedDelivery = enableContentRequestCombinedDelivery;
	}

	/**
	 * Gets whether to enable combined delivery for text requests.
	 * 
	 * @return <code>true</code>to enable the option, <code>false</code>
	 *         otherwise.
	 */
	public Boolean getEnableTextRequestCombinedDelivery() {
		return enableTextRequestCombinedDelivery;
	}

	/**
	 * Sets whether to enable combined delivery for text requests.
	 * 
	 * @param enableTextRequestCombinedDelivery
	 *            <code>true</code> to enable the option, <code>false</code>
	 *            otherwise.
	 */
	public void setEnableTextRequestCombinedDelivery(
			Boolean enableTextRequestCombinedDelivery) {
		this.enableTextRequestCombinedDelivery = enableTextRequestCombinedDelivery;
	}

	/**
	 * Gets whether to enable combined delivery for text subscriptions.
	 * 
	 * @return <code>true</code>to enable the option, <code>false</code>
	 *         otherwise.
	 */
	public Boolean getEnableTextSubscriptionCombinedDelivery() {
		return enableTextSubscriptionCombinedDelivery;
	}

	/**
	 * Sets whether to enable combined delivery for text subscriptions.
	 * 
	 * @param enableTextSubscriptionCombinedDelivery
	 *            <code>true</code> to enable the option, <code>false</code>
	 *            otherwise.
	 */
	public void setEnableTextSubscriptionCombinedDelivery(
			Boolean enableTextSubscriptionCombinedDelivery) {
		this.enableTextSubscriptionCombinedDelivery = enableTextSubscriptionCombinedDelivery;
	}

	/**
	 * Gets the delivery status notification timeout.
	 * 
	 * @return the delivery status notification timeout.
	 */
	public Long getDeliveryStatusNotificationTimeout() {
		return deliveryStatusNotificationTimeout;
	}

	/**
	 * Sets the delivery status notification timeout.
	 * 
	 * @param deliveryStatusNotificationTimeout
	 *            the delivery status notification timeout to set (in
	 *            milliseconds).
	 */
	public void setDeliveryStatusNotificationTimeout(
			Long deliveryStatusNotificationTimeout) {
		this.deliveryStatusNotificationTimeout = deliveryStatusNotificationTimeout;
	}

	/**
	 * Gets the session timeout.
	 * 
	 * @return the session timeout in milliseconds.
	 */
	public Long getSessionTimeout() {
		return sessionTimeout;
	}

	/**
	 * Sets the session timeout.
	 * 
	 * @param sessionTimeout
	 *            the session timeout in milliseconds.
	 */
	public void setSessionTimeout(Long sessionTimeout) {
		this.sessionTimeout = sessionTimeout;
	}

	/**
	 * Gets the session expiration warning timeout.
	 * 
	 * @return the session expiration warning timeout (in milliseconds).
	 */
	public Long getSessionWarningTimeout() {
		return sessionWarningTimeout;
	}

	/**
	 * Sets the session expiration warning timeout.
	 * 
	 * @param sessionWarningTimeout
	 *            the session expiration warning timeout to set (in
	 *            milliseconds).
	 */
	public void setSessionWarningTimeout(Long sessionWarningTimeout) {
		this.sessionWarningTimeout = sessionWarningTimeout;
	}

	/**
	 * Gets the default reply message text to use.
	 * 
	 * @return the default reply message text to use.
	 */
	public String getDefaultReplyMessageText() {
		return defaultReplyMessageText;
	}

	/**
	 * Sets the default reply message text to use.
	 * 
	 * @param defaultReplyMessageText
	 *            the default reply message text to set.
	 */
	public void setDefaultReplyMessageText(String defaultReplyMessageText) {
		this.defaultReplyMessageText = defaultReplyMessageText;
	}

	/**
	 * Gets whether to normalize WAP Push diacritics. This may be necessary
	 * since many handsets incorrectly display diacritics when receiving this
	 * kind of messages.
	 * 
	 * @return <code>true</code>to enable the option, <code>false</code>
	 *         otherwise.
	 */
	public Boolean getNormalizeWapPushDiacritics() {
		return normalizeWapPushDiacritics;
	}

	/**
	 * Sets whether to normalize WAP Push diacritics.
	 * 
	 * @param normalizeWapPushDiacritics
	 *            <code>true</code> to enable the option, <code>false</code>
	 *            otherwise.
	 */
	public void setNormalizeWapPushDiacritics(Boolean normalizeWapPushDiacritics) {
		this.normalizeWapPushDiacritics = normalizeWapPushDiacritics;
	}

	/**
	 * Gets the maximum number of MT requests that can be processed per time
	 * unit.
	 * 
	 * @return the maximum number of MT requests that can be processed per time
	 *         unit.
	 */
	public Integer getMaxRequestsPerTimeUnit() {
		return maxRequestsPerTimeUnit;
	}

	/**
	 * Sets the maximum number of MT requests that can be processed per time
	 * unit.
	 * 
	 * @param maxRequestsPerTimeUnit
	 *            the maximum number of MT requests that can be processed per
	 *            time unit.
	 */
	public void setMaxRequestsPerTimeUnit(Integer maxRequestsPerTimeUnit) {
		this.maxRequestsPerTimeUnit = maxRequestsPerTimeUnit;
	}

	/**
	 * Gets the time unit.
	 * 
	 * @return the time unit.
	 */
	public TimeUnit getTimeUnit() {
		return timeUnit;
	}

	/**
	 * Sets the time unit.
	 * 
	 * @param timeUnit
	 *            the time unit to set.
	 */
	public void setTimeUnit(TimeUnit timeUnit) {
		this.timeUnit = timeUnit;
	}

	/**
	 * Gets the maximum number of times to attempt sending a message.
	 * 
	 * @return the maximum number of times to attempt sending a message.
	 */
	public Integer getMaxAttempts() {
		return maxAttempts;
	}

	/**
	 * Sets the maximum number of times to attempt sending a message.
	 * 
	 * @param maxAttempts
	 *            the number of attempts to set.
	 */
	public void setMaxAttempts(Integer maxAttempts) {
		this.maxAttempts = maxAttempts;
	}

	/**
	 * Gets the reattempt delay in milliseconds.
	 * 
	 * @return the reattempt delay in milliseconds.
	 */
	public Long getReattemptDelay() {
		return reattemptDelay;
	}

	/**
	 * Sets the reattempt delay in milliseconds.
	 * 
	 * @param reattemptDelay
	 *            the reattempt delay in milliseconds.
	 */
	public void setReattemptDelay(Long reattemptDelay) {
		this.reattemptDelay = reattemptDelay;
	}

	/**
	 * Gets the reattempt delay multiplier. The delay between successive
	 * reattempts will be multiplied by this factor every time an attempt fails.
	 * 
	 * @return the reattempt delay multiplier.
	 */
	public Double getReattemptDelayMultiplier() {
		return reattemptDelayMultiplier;
	}

	/**
	 * Sets the reattempt delay multiplier.
	 * 
	 * @param reattemptDelayMultiplier
	 *            the reattempt delay multiplier.
	 */
	public void setReattemptDelayMultiplier(Double reattemptDelayMultiplier) {
		this.reattemptDelayMultiplier = reattemptDelayMultiplier;
	}

	/**
	 * Gets the default application type.
	 * 
	 * @return the default application type.
	 */
	public ApplicationType getDefaultApplicationType() {
		return defaultApplicationType;
	}

	/**
	 * Sets the default application type.
	 * 
	 * @param defaultApplicationType
	 *            the default application type to set.
	 */
	public void setDefaultApplicationType(ApplicationType defaultApplicationType) {
		this.defaultApplicationType = defaultApplicationType;
	}

	/**
	 * Gets the maximum number of new subscriptions per customer per day.
	 * 
	 * @return the maximum number of new subscriptions per customer per day.
	 */
	public Integer getMaxSubscriptionsPerDay() {
		return maxSubscriptionsPerDay;
	}

	/**
	 * Sets the maximum number of new subscriptions per customer per day.
	 * 
	 * @param maxSubscriptionsPerDay
	 *            the maximum number of new subscriptions per customer per day.
	 */
	public void setMaxSubscriptionsPerDay(Integer maxSubscriptionsPerDay) {
		this.maxSubscriptionsPerDay = maxSubscriptionsPerDay;
	}

	/**
	 * Gets the maximum number of new subscriptions per customer per week.
	 * 
	 * @return the maximum number of new subscriptions per customer per week.
	 */
	public Integer getMaxSubscriptionsPerWeek() {
		return maxSubscriptionsPerWeek;
	}

	/**
	 * Sets the maximum number of new subscriptions per customer per week.
	 * 
	 * @param maxSubscriptionsPerWeek
	 *            the maximum number of new subscriptions per customer per week.
	 */
	public void setMaxSubscriptionsPerWeek(Integer maxSubscriptionsPerWeek) {
		this.maxSubscriptionsPerWeek = maxSubscriptionsPerWeek;
	}

	/**
	 * Gets the maximum number of new subscriptions per customer per month.
	 * 
	 * @return the maximum number of new subscriptions per customer per month.
	 */
	public Integer getMaxSubscriptionsPerMonth() {
		return maxSubscriptionsPerMonth;
	}

	/**
	 * Sets the maximum number of new subscriptions per customer per month.
	 * 
	 * @param maxSubscriptionsPerMonth
	 *            the maximum number of new subscriptions per customer per
	 *            month.
	 */
	public void setMaxSubscriptionsPerMonth(Integer maxSubscriptionsPerMonth) {
		this.maxSubscriptionsPerMonth = maxSubscriptionsPerMonth;
	}

	/**
	 * Gets the maximum number of current subscriptions.
	 * 
	 * @return the maximum number of current subscriptions.
	 */
	public Integer getMaxCurrentSubscriptions() {
		return maxCurrentSubscriptions;
	}

	/**
	 * Sets the maximum number of current subscriptions.
	 * 
	 * @param maxCurrentSubscriptions
	 *            the maximum number of current subscriptions to set.
	 */
	public void setMaxCurrentSubscriptions(Integer maxCurrentSubscriptions) {
		this.maxCurrentSubscriptions = maxCurrentSubscriptions;
	}

	/**
	 * Gets the unsubscription repellent period.
	 * 
	 * @return the unsubscription repellent period.
	 */
	public Long getUnsubscriptionRepellentPeriod() {
		return unsubscriptionRepellentPeriod;
	}

	/**
	 * Sets the unsubscription repellent period.
	 * 
	 * @param unsubscriptionRepellentPeriod
	 *            the unsubscription repellent period to set.
	 */
	public void setUnsubscriptionRepellentPeriod(
			Long unsubscriptionRepellentPeriod) {
		this.unsubscriptionRepellentPeriod = unsubscriptionRepellentPeriod;
	}

	/**
	 * Gets whether the unsubscription repellent should only act once.
	 * 
	 * @return whether the unsubscription repellent should only act once.
	 */
	public Boolean getUnsubscriptionRepelOnce() {
		return unsubscriptionRepelOnce;
	}

	/**
	 * Sets whether the unsubscription repellent should only act once.
	 * 
	 * @param unsubscriptionRepelOnce
	 *            whether the unsubscription repellent should only act once.
	 */
	public void setUnsubscriptionRepelOnce(Boolean unsubscriptionRepelOnce) {
		this.unsubscriptionRepelOnce = unsubscriptionRepelOnce;
	}

	/**
	 * Gets whether the unsubscription repellent should act upon customers with
	 * multiple subscriptions.
	 * 
	 * @return whether the unsubscription repellent should act upon customers
	 *         with multiple subscriptions.
	 */
	public Boolean getUnsubscriptionRepelOnMultipleSubscriptions() {
		return unsubscriptionRepelOnMultipleSubscriptions;
	}

	/**
	 * Sets whether the unsubscription repellent should act upon customers with
	 * multiple subscriptions.
	 * 
	 * @param unsubscriptionRepelOnMultipleSubscriptions
	 *            whether the unsubscription repellent should act upon customers
	 *            with multiple subscriptions.
	 */
	public void setUnsubscriptionRepelOnMultipleSubscriptions(
			Boolean unsubscriptionRepelOnMultipleSubscriptions) {
		this.unsubscriptionRepelOnMultipleSubscriptions = unsubscriptionRepelOnMultipleSubscriptions;
	}

	/**
	 * Gets whether to enable selection of a preferred subscription plan by
	 * default when no valid plan is specified.
	 * 
	 * @return <code>true</code>to enable the option, <code>false</code>
	 *         otherwise.
	 */
	public Boolean getEnablePreferredSubscriptionPlanDefault() {
		return enablePreferredSubscriptionPlanDefault;
	}

	/**
	 * Sets whether to enable selection of a preferred subscription plan by
	 * default when no valid plan is specified.
	 * 
	 * @param enablePreferredSubscriptionPlanDefault
	 *            <code>true</code> to enable the option, <code>false</code>
	 *            otherwise.
	 */
	public void setEnablePreferredSubscriptionPlanDefault(
			Boolean enablePreferredSubscriptionPlanDefault) {
		this.enablePreferredSubscriptionPlanDefault = enablePreferredSubscriptionPlanDefault;
	}

	/**
	 * Gets whether to avoid selection of a preferred subscription plan by
	 * default for current subscribers.
	 * 
	 * @return <code>true</code>to enable the option, <code>false</code>
	 *         otherwise.
	 */
	public Boolean getAvoidDefaultSubscriptionPlanForCurrentSubscribers() {
		return avoidDefaultSubscriptionPlanForCurrentSubscribers;
	}

	/**
	 * Sets whether to avoid selection of a preferred subscription plan by
	 * default for current subscribers.
	 * 
	 * @param avoidDefaultSubscriptionPlanForCurrentSubscribers
	 *            <code>true</code> to enable the option, <code>false</code>
	 *            otherwise.
	 */
	public void setAvoidDefaultSubscriptionPlanForCurrentSubscribers(
			Boolean avoidDefaultSubscriptionPlanForCurrentSubscribers) {
		this.avoidDefaultSubscriptionPlanForCurrentSubscribers = avoidDefaultSubscriptionPlanForCurrentSubscribers;
	}

	/**
	 * Gets whether to enable unsubscription pattern detection for this short
	 * number.
	 * 
	 * @return whether to enable unsubscription pattern detection for this short
	 *         number.
	 */
	public Boolean getEnableUnsubscriptionPatternDetection() {
		return enableUnsubscriptionPatternDetection;
	}

	/**
	 * Sets whether to enable unsubscription pattern detection for this short
	 * number.
	 * 
	 * @param enableUnsubscriptionPatternDetection
	 *            whether to enable unsubscription pattern detection for this
	 *            short number.
	 */
	public void setEnableUnsubscriptionPatternDetection(
			Boolean enableUnsubscriptionPatternDetection) {
		this.enableUnsubscriptionPatternDetection = enableUnsubscriptionPatternDetection;
	}

	/**
	 * Gets whether to assume that unsubscription requests should cancel all
	 * subscriptions.
	 * 
	 * @return whether to assume that unsubscription requests should cancel all
	 *         subscriptions.
	 */
	public Boolean getAssumeCancelAll() {
		return assumeCancelAll;
	}

	/**
	 * Sets whether to assume that unsubscription requests should cancel all
	 * subscriptions.
	 * 
	 * @param assumeCancelAll
	 *            whether to assume that unsubscription requests should cancel
	 *            all subscriptions.
	 */
	public void setAssumeCancelAll(Boolean assumeCancelAll) {
		this.assumeCancelAll = assumeCancelAll;
	}

	/**
	 * Gets whether to skip confirmation for unsubscription requests to cancel
	 * all susbcriptions.
	 * 
	 * @return <code>true</code> to skip confirmation, <code>false</code>
	 *         otherwise.
	 */
	public Boolean getSkipCancelAllConfirmation() {
		return skipCancelAllConfirmation;
	}

	/**
	 * Sets whether to skip confirmation for unsubscription requests to cancel
	 * all susbcriptions.
	 * 
	 * @param skipCancelAllConfirmation
	 *            <code>true</code> to skip confirmation, <code>false</code>
	 *            otherwise.
	 */
	public void setSkipCancelAllConfirmation(Boolean skipCancelAllConfirmation) {
		this.skipCancelAllConfirmation = skipCancelAllConfirmation;
	}

	/**
	 * Gets whether to enable external management.
	 * 
	 * @return <code>true</code>to enable the option, <code>false</code>
	 *         otherwise.
	 */
	public Boolean getExternallyManaged() {
		return externallyManaged;
	}

	/**
	 * Sets whether to enable external management.
	 * 
	 * @param externallyManaged
	 *            <code>true</code> to enable the option, <code>false</code>
	 *            otherwise.
	 */
	public void setExternallyManaged(Boolean externallyManaged) {
		this.externallyManaged = externallyManaged;
	}

	/**
	 * Gets whether downloads require external WAP confirmation.
	 * 
	 * @return whether downloads require external WAP confirmation.
	 */
	public Boolean getDownloadRequiresExternalWapConfirmation() {
		return downloadRequiresExternalWapConfirmation;
	}

	/**
	 * Sets whether downloads require external WAP confirmation.
	 * 
	 * @param downloadRequiresExternalWapConfirmation
	 *            whether downloads require external WAP confirmation.
	 */
	public void setDownloadRequiresExternalWapConfirmation(
			Boolean downloadRequiresExternalWapConfirmation) {
		this.downloadRequiresExternalWapConfirmation = downloadRequiresExternalWapConfirmation;
	}

	// ----------------------------------------------------------------------
	// LocalizedFields inner class
	// ----------------------------------------------------------------------

	/**
	 * Simple data holder for locale-sensitive fields.
	 */
	public static class LocalizedFields implements Serializable {

		private static final long serialVersionUID = -2713979108053531575L;

		// ----------------------------------------------------------------------
		// Fields
		// ----------------------------------------------------------------------

		private String defaultReplyMessageText;

		// ----------------------------------------------------------------------
		// Getters and setters
		// ----------------------------------------------------------------------

		/**
		 * Gets the localized default reply message text.
		 * 
		 * @return the localized default reply message text.
		 */
		public String getDefaultReplyMessageText() {
			return defaultReplyMessageText;
		}

		/**
		 * Sets the localized default reply message text.
		 * 
		 * @param defaultReplyMessageText
		 *            the localized default reply message text.
		 */
		public void setDefaultReplyMessageText(String defaultReplyMessageText) {
			this.defaultReplyMessageText = defaultReplyMessageText;
		}
	}
}
