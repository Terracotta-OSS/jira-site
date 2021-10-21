package com.renxo.cms.domain;

import java.util.Date;
import java.util.Locale;

import com.renxo.cms.domain.user.User;
import com.renxo.cms.remoting.RemotingExclude;
import com.renxo.cms.remoting.RemotingExcludeBehavior;

/**
 * An <code>Entity</code> represents a persistent domain entity.
 * 
 */
public interface Entity {

	// ----------------------------------------------------------------------
	// Getters and setters
	// ----------------------------------------------------------------------

	/**
	 * Gets the entity id.
	 * 
	 * @return the entity id.
	 */
	public Long getId();

	/**
	 * Sets the entity id.
	 * 
	 * @param id
	 *            the entity id to set.
	 */
	public void setId(Long id);

	/**
	 * Gets the version of this entity, used for optimistic locking.
	 * 
	 * @return the version.
	 */
	public Long getVersion();

	/**
	 * Sets the version of this entity, used for optimistic locking.
	 * 
	 * @param version
	 *            the version to set.
	 */
	public void setVersion(Long version);

	/**
	 * Gets the time this entity was created. Used for auditing purposes.
	 * 
	 * @return the time this entity was created.
	 */
	public Date getCreatedOn();

	/**
	 * Sets the time this entity was created. Used for auditing purposes.
	 * 
	 * @param createdOn
	 *            the time this entity was created.
	 */
	public void setCreatedOn(Date createdOn);

	/**
	 * Gets the user that created this entity. Used for auditing purposes.
	 * 
	 * @return the user that created this entity.
	 */
	@RemotingExclude(behavior = RemotingExcludeBehavior.PROXY)
	public User getCreatedBy();

	/**
	 * Sets the user that last created this entity. Used for auditing purposes.
	 * 
	 * @param createdBy
	 *            the user that created this entity.
	 */
	public void setCreatedBy(User createdBy);

	/**
	 * Gets the last time this entity was modified. Used for auditing purposes.
	 * 
	 * @return the last time this entity was modified.
	 */
	public Date getLastModifiedOn();

	/**
	 * Sets the last time this entity was modified. Used for auditing purposes.
	 * 
	 * @param lastModifiedOn
	 *            the last time this entity was modified.
	 */
	public void setLastModifiedOn(Date lastModifiedOn);

	/**
	 * Gets the user that last modified this entity. Used for auditing purposes.
	 * 
	 * @return the user that last modified this entity.
	 */
	@RemotingExclude(behavior = RemotingExcludeBehavior.PROXY)
	public User getLastModifiedBy();

	/**
	 * Sets the user that last modified this entity. Used for auditing purposes.
	 * 
	 * @param lastModifiedBy
	 *            the user that last modified this entity.
	 */
	public void setLastModifiedBy(User lastModifiedBy);

	// ----------------------------------------------------------------------
	// Business methods
	// ----------------------------------------------------------------------

	/**
	 * Gets the identity text for the entity in the default locale. Falls back
	 * to the entity id if no business identity text is available.
	 * 
	 * @return the identity text.
	 */
	public String identityText();

	/**
	 * Gets the identity text for the entity in the given locale. Falls back to
	 * the entity id if no business identity text is available.
	 * 
	 * @param inLocale
	 *            the locale for which to get the identity text.
	 * @return the identity text.
	 */
	public String identityText(Locale inLocale);

	// ----------------------------------------------------------------------
	// Miscellaneous methods
	// ----------------------------------------------------------------------

	/**
	 * A safe way to obtain the class name, discarding any suffixes that may
	 * have been added by dynamic proxy frameworks, such as CGLIB or Javassist.
	 * 
	 * @return the class name.
	 */
	public String className();

	/**
	 * A safe way to obtain the simple class name, discarding any suffixes that
	 * may have been added by dynamic proxy frameworks, such as CGLIB or
	 * Javassist.
	 * 
	 * @return the simple class name.
	 */
	public String simpleClassName();

	/**
	 * Obtains the manager class name for this entity class.
	 * 
	 * @return the manager class name for this entity class.
	 */
	public String managerClassName();

	/**
	 * Returns the instantiation timestamp.
	 * 
	 * @return the instantiation timestamp.
	 */
	public long instantiationTimestamp();

	/**
	 * Returns whether this instance is mature for entity pool purposes. An
	 * instance is considered to be mature for entity pool purposes after 10
	 * seconds from being instantiated.
	 * 
	 * @return whether this instance is mature for entity pool purposes
	 */
	public boolean mature();
}
